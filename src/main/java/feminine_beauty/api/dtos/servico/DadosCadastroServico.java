package feminine_beauty.api.dtos.servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroServico(

        @NotBlank(message = "Descrição é obrigatório.")
        String descricao,

        @NotNull(message = "Preço é obrigatório.")
        Double preco,

        @NotNull(message = "Duração é obrigatório.")
        Long duracao,

        Boolean ativo,

        String imagemUrl) {
}
