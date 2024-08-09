package feminine_beauty.api.domain.cliente;

import feminine_beauty.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
