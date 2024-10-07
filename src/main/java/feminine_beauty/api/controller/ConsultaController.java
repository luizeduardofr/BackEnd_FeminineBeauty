package feminine_beauty.api.controller;

import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.dtos.consulta.DadosCancelamentoConsulta;
import feminine_beauty.api.dtos.consulta.DadosListagemConsulta;
import feminine_beauty.api.dtos.consulta.StatusConsulta;
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

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<Page<DadosListagemConsulta>> listarConsultasCliente(
            @PathVariable Long idCliente,
            @RequestParam(required = false) Long idServico,
            @RequestParam(required = false) Long idFuncionario,
            @PageableDefault(size = 10, sort = {"data"}, direction = Sort.Direction.ASC) Pageable paginacao
    ) {
        return ResponseEntity.ok(consultaService.listarConsultasCliente(idCliente, idServico, idFuncionario, paginacao));
    }

    @GetMapping("/old/cliente/{idCliente}")
    public ResponseEntity<Page<DadosListagemConsulta>> listarOldConsultasCliente(
            @PathVariable Long idCliente,
            @RequestParam(required = false) Long idServico,
            @RequestParam(required = false) Long idFuncionario,
            @RequestParam(required = false) StatusConsulta status,
            @PageableDefault(size = 10, sort = {"data"}, direction = Sort.Direction.DESC) Pageable paginacao
    ) {
        return ResponseEntity.ok(consultaService.listarOldConsultasCliente(idCliente, idServico, idFuncionario, status, paginacao));
    }

    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<Page<DadosListagemConsulta>> listarConsultasFuncionario(
            @PathVariable Long idFuncionario,
            @RequestParam(required = false) Long idServico,
            @PageableDefault(size = 10, sort = {"data"}, direction = Sort.Direction.ASC) Pageable paginacao
    ) {
        return ResponseEntity.ok(consultaService.listarConsultasFuncionario(idFuncionario, idServico, paginacao));
    }

    @GetMapping("/old/funcionario/{idFuncionario}")
    public ResponseEntity<Page<DadosListagemConsulta>> listarOldConsultasFuncionario(
            @PathVariable Long idFuncionario,
            @RequestParam(required = false) StatusConsulta status,
            @RequestParam(required = false) Long idServico,
            @PageableDefault(size = 10, sort = {"data"}, direction = Sort.Direction.DESC) Pageable paginacao
    ) {
        return ResponseEntity.ok(consultaService.listarOldConsultasFuncionario(idFuncionario, status, idServico,
                paginacao));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = consultaService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/concluir")
    @Transactional
    public ResponseEntity<DadosListagemConsulta> concluir(@RequestBody Long idConsulta) {
        var dto = consultaService.concluir(idConsulta);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        consultaService.cancelar(dados);
        return ResponseEntity.ok().build();
    }
}
