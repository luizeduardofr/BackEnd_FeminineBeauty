package feminine_beauty.api.services;

import feminine_beauty.api.domain.servico.Servico;
import feminine_beauty.api.dtos.servico.DadosAtualizacaoServico;
import feminine_beauty.api.dtos.servico.DadosCadastroServico;
import feminine_beauty.api.dtos.servico.ServicoResponse;
import feminine_beauty.api.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Transactional
    public ServicoResponse cadastrar(DadosCadastroServico dados) {
        var servico = new Servico(dados);
        repository.save(servico);
        return new ServicoResponse(servico);
    }

    public Page<ServicoResponse> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(ServicoResponse::new);
    }

    @Transactional
    public ServicoResponse atualizar(DadosAtualizacaoServico dados) {
        var servico = repository.getReferenceById(dados.id());
        servico.atualizarInformacoes(dados);
        return new ServicoResponse(servico);
    }

    @Transactional
    public void excluir(Long id) {
        var servico = repository.getReferenceById(id);
        servico.excluir();
    }

    public ServicoResponse detalhar(Long id) {
        var servico = repository.getReferenceById(id);
        return new ServicoResponse(servico);
    }
}




