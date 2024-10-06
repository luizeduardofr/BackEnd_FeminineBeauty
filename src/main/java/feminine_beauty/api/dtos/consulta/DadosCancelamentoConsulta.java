package feminine_beauty.api.dtos.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull(message = "ID da consulta é obrigatório")
        Long idConsulta,

        @NotNull(message = "Motivo do cancelamento é obrigatório")
        String motivoCancelamento) {
}
