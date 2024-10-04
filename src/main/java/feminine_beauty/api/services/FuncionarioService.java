package feminine_beauty.api.services;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.domain.endereco.Endereco;
import feminine_beauty.api.domain.funcionario.Funcionario;
import feminine_beauty.api.domain.usuario.UserRole;
import feminine_beauty.api.domain.usuario.Usuario;
import feminine_beauty.api.dtos.funcionario.DadosAtualizacaoFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosCadastroFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosDetalhamentoFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosListagemFuncionario;
import feminine_beauty.api.repositories.FuncionarioRepository;
import feminine_beauty.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public DadosDetalhamentoFuncionario cadastrar(DadosCadastroFuncionario dados) {
        if (usuarioRepository.findByLogin(dados.login()) != null) {
            throw new ValidacaoException("Login já existe!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());

        Usuario novoUsuario = new Usuario(dados.login(), encryptedPassword);
        novoUsuario.setRole(UserRole.FUNC);

        Funcionario funcionario = new Funcionario(dados);
        funcionario.setUsuario(novoUsuario);
        funcionarioRepository.save(funcionario);

        return new DadosDetalhamentoFuncionario(funcionario);
    }

    public Page<DadosListagemFuncionario> listar(Pageable paginacao) {
        return funcionarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemFuncionario::new);
    }

    @Transactional
    public DadosDetalhamentoFuncionario atualizar(DadosAtualizacaoFuncionario dados) {
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.setNome(dados.nome());
        funcionario.setEmail(dados.email());
        funcionario.setTelefone(dados.telefone());
        funcionario.setCpf(dados.cpf());
        funcionario.setAtivo(dados.ativo());
        funcionario.setServicos(dados.servicos());
        funcionario.setEndereco(new Endereco(dados.endereco()));
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    @Transactional
    public void excluir(Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.excluir();
    }

    public DadosDetalhamentoFuncionario detalhar(Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        return new DadosDetalhamentoFuncionario(funcionario);
    }
}

