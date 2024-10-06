package feminine_beauty.api.dtos.consulta;

import feminine_beauty.api.dtos.cliente.DadosListagemCliente;
import feminine_beauty.api.dtos.funcionario.DadosListagemFuncionario;
import feminine_beauty.api.dtos.servico.DadosListagemServico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        @NotNull(message = "Data é obrigatória")
        @Future(message = "Data deve ser futura")
        LocalDateTime data,

        @NotBlank(message = "Tipo de pagamento é obrigatório")
        String tipoPagamento,

        DadosListagemFuncionario funcionario,

        DadosListagemCliente cliente,

        DadosListagemServico servico

) {}
