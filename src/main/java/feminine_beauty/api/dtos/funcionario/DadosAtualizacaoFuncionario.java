package feminine_beauty.api.dtos.funcionario;

import feminine_beauty.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFuncionario(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
