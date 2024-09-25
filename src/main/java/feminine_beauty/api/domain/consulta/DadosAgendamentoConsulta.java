package feminine_beauty.api.domain.consulta;

import feminine_beauty.api.domain.servico.Servico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idFuncionario,

        @NotNull
        Long idCliente,

        @NotNull
        @Future
        LocalDateTime data,

        @NotBlank
        Servico servico,

        @NotBlank
        String tipoPagamento,

        String motivoCancelamento) {
}
