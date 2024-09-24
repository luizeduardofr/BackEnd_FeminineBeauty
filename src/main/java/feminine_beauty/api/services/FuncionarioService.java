package feminine_beauty.api.services;

import feminine_beauty.api.domain.funcionario.Funcionario;
import feminine_beauty.api.dtos.funcionario.DadosAtualizacaoFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosCadastroFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosDetalhamentoFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosListagemFuncionario;
import feminine_beauty.api.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Transactional
    public DadosDetalhamentoFuncionario cadastrar(DadosCadastroFuncionario dados) {
        var funcionario = new Funcionario(dados);
        repository.save(funcionario);
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    public Page<DadosListagemFuncionario> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemFuncionario::new);
    }

    @Transactional
    public DadosDetalhamentoFuncionario atualizar(DadosAtualizacaoFuncionario dados) {
        var funcionario = repository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    @Transactional
    public void excluir(Long id) {
        var funcionario = repository.getReferenceById(id);
        funcionario.excluir();
    }

    public DadosDetalhamentoFuncionario detalhar(Long id) {
        var funcionario = repository.getReferenceById(id);
        return new DadosDetalhamentoFuncionario(funcionario);
    }
}

