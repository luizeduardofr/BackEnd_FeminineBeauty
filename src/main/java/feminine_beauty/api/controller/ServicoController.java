package feminine_beauty.api.controller;

import feminine_beauty.api.domain.servico.Servico;
import feminine_beauty.api.dtos.servico.DadosAtualizacaoServico;
import feminine_beauty.api.dtos.servico.DadosCadastroServico;
import feminine_beauty.api.dtos.servico.ServicoResponse;
import feminine_beauty.api.repositories.ServicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroServico dados, UriComponentsBuilder uriBuilder) {
        var servico = new Servico(dados);
        repository.save(servico);

        var uri = uriBuilder.path("/servico/{id}").buildAndExpand(servico.getId()).toUri();

        return ResponseEntity.created(uri).body(new ServicoResponse(servico));
    }

    @GetMapping
    public ResponseEntity<Page<ServicoResponse>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ServicoResponse::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoServico dados) {
        var servico = repository.getReferenceById(dados.id());
        servico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new ServicoResponse(servico));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var servico = repository.getReferenceById(id);
        servico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var servico = repository.getReferenceById(id);
        return ResponseEntity.ok(new ServicoResponse(servico));
    }
}
