package feminine_beauty.api.dtos.servico;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroServico(

        @NotBlank(message = "Descrição é obrigatório.")
        String descricao,

        @NotNull(message = "Preço é obrigatório.")
        @DecimalMin(value = "0.01", message = "Preço inválido.")
        Double preco,

        @NotNull(message = "Duração é obrigatório.")
        @Min(value = 1, message = "Duração inválida.")
        Long duracao,

        Boolean ativo,

        String imagemUrl) {
}
