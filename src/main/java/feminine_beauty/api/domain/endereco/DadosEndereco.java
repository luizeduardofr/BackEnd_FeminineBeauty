package feminine_beauty.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank(message = "Logradouro é obrigatório.")
        String logradouro,

        @NotBlank(message = "Bairro é obrigatório.")
        String bairro,

        @NotBlank(message = "CEP é obrigatório.")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido.")
        String cep,

        @NotBlank(message = "Cidade é obrigatório.")
        String cidade,

        @NotBlank(message = "UF é obrigatório.")
        @Pattern(regexp = "[A-Z]{2}", message = "UF inválida.")
        String uf,

        String complemento,

        String numero) {
}
