package feminine_beauty.api.controller;

import feminine_beauty.api.dtos.cliente.DadosAtualizacaoCliente;
import feminine_beauty.api.dtos.cliente.DadosCadastroCliente;
import feminine_beauty.api.dtos.cliente.DadosListagemCliente;
import feminine_beauty.api.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var detalhamentoCliente = service.cadastrar(dados);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(detalhamentoCliente.id()).toUri();
        return ResponseEntity.created(uri).body(detalhamentoCliente);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var detalhamentoCliente = service.atualizar(dados);
        return ResponseEntity.ok(detalhamentoCliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var detalhamentoCliente = service.detalhar(id);
        return ResponseEntity.ok(detalhamentoCliente);
    }
}

