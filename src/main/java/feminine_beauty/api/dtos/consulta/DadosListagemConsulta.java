package feminine_beauty.api.dtos.consulta;

import feminine_beauty.api.domain.consulta.Consulta;
import feminine_beauty.api.dtos.cliente.DadosListagemCliente;
import feminine_beauty.api.dtos.funcionario.DadosListagemFuncionario;
import feminine_beauty.api.dtos.servico.DadosListagemServico;

import java.time.LocalDateTime;

public record DadosListagemConsulta(
        Long id,
        LocalDateTime data,
        String tipoPagamento,
        String motivoCancelamento,
        StatusConsulta status,
        DadosListagemFuncionario funcionario,
        DadosListagemServico servico,
        DadosListagemCliente cliente
) {

    public DadosListagemConsulta(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getData(),
                consulta.getTipoPagamento(),
                consulta.getMotivoCancelamento(),
                consulta.getStatus(),
                new DadosListagemFuncionario(consulta.getFuncionario()),
                new DadosListagemServico(consulta.getServico()),
                new DadosListagemCliente(consulta.getCliente())
        );
    }
}
