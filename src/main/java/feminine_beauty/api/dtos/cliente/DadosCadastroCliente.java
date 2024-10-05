package feminine_beauty.api.dtos.cliente;

import feminine_beauty.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroCliente(

        @NotBlank(message = "Login é obrigatório")
        String login,

        @NotNull(message = "Senha é obrigatório")
        String senha,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "CPF é obrigatório")
        @CPF(message = "CPF inválido")
        String cpf,

        @NotNull(message = "Endereço é obrigatório")
        @Valid DadosEndereco endereco
) { }
