package feminine_beauty.api.controller;

import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.dtos.consulta.DadosCancelamentoConsulta;
import feminine_beauty.api.dtos.consulta.DadosListagemConsulta;
import feminine_beauty.api.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<Page<DadosListagemConsulta>> listar(@PathVariable Long idCliente, @PageableDefault(size = 10,
            sort = {"data"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        return ResponseEntity.ok(consultaService.listar(idCliente, paginacao));
    }

    @GetMapping("/old/cliente/{idCliente}")
    public ResponseEntity<Page<DadosListagemConsulta>> listarOld(@PathVariable Long idCliente, @PageableDefault(size =
            10, sort = {"data"}, direction = Sort.Direction.DESC) Pageable paginacao) {
        return ResponseEntity.ok(consultaService.listarOld(idCliente, paginacao));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = consultaService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        consultaService.cancelar(dados);
        return ResponseEntity.ok().build();
    }
}
