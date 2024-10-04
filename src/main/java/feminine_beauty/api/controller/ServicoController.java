package feminine_beauty.api.controller;

import feminine_beauty.api.dtos.servico.DadosAtualizacaoServico;
import feminine_beauty.api.dtos.servico.DadosCadastroServico;
import feminine_beauty.api.dtos.servico.DadosListagemServico;
import feminine_beauty.api.services.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroServico dados, UriComponentsBuilder uriBuilder) {
        var servicoResponse = service.cadastrar(dados);
        var uri = uriBuilder.path("/servicos/{id}").buildAndExpand(servicoResponse.id()).toUri();
        return ResponseEntity.created(uri).body(servicoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemServico>> listar(@PageableDefault(size = 10, sort = {"descricao"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoServico dados) {
        var servicoResponse = service.atualizar(dados);
        return ResponseEntity.ok(servicoResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var servicoResponse = service.detalhar(id);
        return ResponseEntity.ok(servicoResponse);
    }
}


