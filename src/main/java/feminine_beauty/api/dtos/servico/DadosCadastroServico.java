package feminine_beauty.api.dtos.servico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroServico(

        @NotBlank
        String descricao,

        @NotBlank
        String preco) {
}
