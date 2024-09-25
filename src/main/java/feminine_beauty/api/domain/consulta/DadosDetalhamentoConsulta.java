package feminine_beauty.api.domain.consulta;

import feminine_beauty.api.domain.servico.Servico;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idFuncionario, Long idCliente, LocalDateTime data, String tipoPagamento) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getFuncionario().getId(), consulta.getCliente().getId(), consulta.getData(), consulta.getTipoPagemento());
    }
}
