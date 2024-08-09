package feminine_beauty.api.domain.servico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroServico(

        @NotBlank
        String descricao,

        @NotBlank
        String preco) {
}
