package feminine_beauty.api.controller;

import feminine_beauty.api.dtos.funcionario.DadosAtualizacaoFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosCadastroFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosListagemFuncionario;
import feminine_beauty.api.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        var detalhamentoFuncionario = service.cadastrar(dados);
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(detalhamentoFuncionario.id()).toUri();
        return ResponseEntity.created(uri).body(detalhamentoFuncionario);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados) {
        var detalhamentoFuncionario = service.atualizar(dados);
        return ResponseEntity.ok(detalhamentoFuncionario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var detalhamentoFuncionario = service.detalhar(id);
        return ResponseEntity.ok(detalhamentoFuncionario);
    }
}

