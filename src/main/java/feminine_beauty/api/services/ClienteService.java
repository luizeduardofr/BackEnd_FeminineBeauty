package feminine_beauty.api.services;

import feminine_beauty.api.domain.cliente.Cliente;
import feminine_beauty.api.dtos.cliente.DadosAtualizacaoCliente;
import feminine_beauty.api.dtos.cliente.DadosCadastroCliente;
import feminine_beauty.api.dtos.cliente.DadosDetalhamentoCliente;
import feminine_beauty.api.dtos.cliente.DadosListagemCliente;
import feminine_beauty.api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public DadosDetalhamentoCliente cadastrar(DadosCadastroCliente dados) {
        var cliente = new Cliente(dados);
        repository.save(cliente);
        return new DadosDetalhamentoCliente(cliente);
    }

    public Page<DadosListagemCliente> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
    }

    @Transactional
    public DadosDetalhamentoCliente atualizar(DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return new DadosDetalhamentoCliente(cliente);
    }

    @Transactional
    public void excluir(Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.excluir();
    }

    public DadosDetalhamentoCliente detalhar(Long id) {
        var cliente = repository.findByUsuarioId(id);
        return new DadosDetalhamentoCliente(cliente);
    }
}

