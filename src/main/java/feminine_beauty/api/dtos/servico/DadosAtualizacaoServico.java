package feminine_beauty.api.dtos.servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoServico(
        @NotNull(message = "ID é obrigatório.")
        Long id,

        @NotBlank(message = "Descrição é obrigatório.")
        String descricao,

        @NotNull(message = "Preço é obrigatório.")
        Double preco,

        @NotNull(message = "Duração é obrigatório.")
        Long duracao,

        Boolean ativo,

        String imagemUrl) {
}
