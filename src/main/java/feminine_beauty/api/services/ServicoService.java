package feminine_beauty.api.services;

import feminine_beauty.api.domain.servico.Servico;
import feminine_beauty.api.dtos.funcionario.DadosListagemFuncionario;
import feminine_beauty.api.dtos.servico.DadosAtualizacaoServico;
import feminine_beauty.api.dtos.servico.DadosCadastroServico;
import feminine_beauty.api.dtos.servico.DadosListagemServico;
import feminine_beauty.api.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Transactional
    public DadosListagemServico cadastrar(DadosCadastroServico dados) {
        var servico = new Servico(dados);
        repository.save(servico);
        return new DadosListagemServico(servico);
    }

    public Page<DadosListagemServico> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemServico::new);
    }

    @Transactional
    public DadosListagemServico atualizar(DadosAtualizacaoServico dados) {
        var servico = repository.getReferenceById(dados.id());
        servico.setAtivo(dados.ativo());
        servico.setDescricao(dados.descricao());
        servico.setPreco(dados.preco());
        servico.setDuracao(dados.duracao());
        servico.setImagemUrl(dados.imagemUrl());
        return new DadosListagemServico(servico);
    }

    @Transactional
    public void excluir(Long id) {
        var servico = repository.getReferenceById(id);
        servico.excluir();
    }

    public DadosListagemServico detalhar(Long id) {
        var servico = repository.getReferenceById(id);
        return new DadosListagemServico(servico);
    }

    public List<DadosListagemServico> listarServicosDoFuncionario(Long idFuncionario) {
        return repository.findAllByAtivoTrueAndFuncionariosId(idFuncionario).stream().map(DadosListagemServico::new).toList();
    }
}




