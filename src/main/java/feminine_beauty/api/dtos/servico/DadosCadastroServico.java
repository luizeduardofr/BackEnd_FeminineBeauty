package feminine_beauty.api.dtos.servico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroServico(

        @NotBlank(message = "Descrição é obrigatório.")
        String descricao,

        @NotBlank(message = "Preço é obrigatório.")
        Double preco,

        @NotBlank(message = "Duração é obrigatório.")
        Long duracao,

        String imagemUrl) {
}
