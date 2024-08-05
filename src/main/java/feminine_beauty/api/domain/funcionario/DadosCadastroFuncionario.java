package feminine_beauty.api.domain.funcionario;

import feminine_beauty.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroFuncionario(
        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @NotBlank
        @CPF(message = "Formato do CPF é inválido.")
        String cpf,

        @NotBlank(message = "Email é obrigatório.")
        @Email(message = "Formato do email é inválido.")
        String email,

        @NotBlank(message = "Telefone é obrigatório.")
        String telefone,

        @NotNull(message = "Dados do endereço são obrigatórios.")
        @Valid DadosEndereco endereco) {
}
