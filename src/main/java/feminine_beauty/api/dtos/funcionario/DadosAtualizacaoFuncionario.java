package feminine_beauty.api.dtos.funcionario;

import feminine_beauty.api.domain.endereco.DadosEndereco;
import feminine_beauty.api.domain.servico.Servico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record DadosAtualizacaoFuncionario(
        @NotNull(message = "ID é obrigatório.")
        Long id,

        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @NotBlank(message = "Email é obrigatório.")
        @Email(message = "Email inválido.")
        String email,

        @NotBlank(message = "Telefone é obrigatório.")
        String telefone,

        @NotBlank(message = "CPF é obrigatório.")
        @CPF(message = "CPF inválido.")
        String cpf,

        @NotNull
        @Valid DadosEndereco endereco,

        Boolean ativo,

        List<Servico> servicos
) {}
