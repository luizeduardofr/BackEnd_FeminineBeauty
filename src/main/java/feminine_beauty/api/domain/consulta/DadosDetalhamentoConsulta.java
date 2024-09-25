package feminine_beauty.api.domain.consulta;

import feminine_beauty.api.domain.servico.Servico;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, String nomeFuncionario ,String nomeCliente, LocalDateTime data, String tipoPagamento) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getFuncionario().getNome() ,consulta.getCliente().getNome(), consulta.getData(), consulta.getTipoPagamento());
    }
}
