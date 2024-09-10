package feminine_beauty.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonCreator;
import feminine_beauty.api.domain.ValidacaoException;

public enum MotivoCancelamento {

    CLIENTE_DESISTIU,
    FUNCIONARIO_CANCELOU,
    OUTROS;

    @JsonCreator
    public static MotivoCancelamento fromString(String value) {
        for (MotivoCancelamento motivoCancelamento : MotivoCancelamento.values()) {
            if (motivoCancelamento.name().equalsIgnoreCase(value)) {
                return motivoCancelamento;
            }
        }
        throw new ValidacaoException("Motivo de cancelamento inválido: " + value);
    }
}
