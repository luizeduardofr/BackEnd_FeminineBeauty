package feminine_beauty.api.dtos.servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroServico(

        @NotBlank(message = "Descrição é obrigatório.")
        String descricao,

        @NotBlank(message = "Preço é obrigatório.")
        String preco) {
}
