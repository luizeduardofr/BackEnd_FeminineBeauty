package feminine_beauty.api.dtos.servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoServico(
        @NotNull
        Long id,
        Double preco,
        String imagemUrl) {
}
