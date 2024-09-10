package feminine_beauty.api.domain.desconto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DadosCupomDesconto(
        @NotBlank
        String codigo,

        @NotBlank
        Integer desconto,

        @NotBlank
        Date validade) {
}
