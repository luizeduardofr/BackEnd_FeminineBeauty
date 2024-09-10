package feminine_beauty.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonCreator;
import feminine_beauty.api.domain.ValidacaoException;

public enum TipoPagamento {

    CREDITO,
    DEBITO,
    PIX,
    DINHEIRO;

    @JsonCreator
    public static TipoPagamento fromString(String value) {
        for (TipoPagamento tipoPagamento : TipoPagamento.values()) {
            if (tipoPagamento.name().equalsIgnoreCase(value)) {
                return tipoPagamento;
            }
        }
        throw new ValidacaoException("Tipo de pagamento invalido: " + value);
    }
}
