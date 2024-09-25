package feminine_beauty.api.dtos.funcionario;

import feminine_beauty.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroFuncionario(

        @NotBlank
        String login,

        @NotNull
        String senha,

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @CPF
        String cpf,

        @NotNull
        @Valid DadosEndereco endereco) { }
