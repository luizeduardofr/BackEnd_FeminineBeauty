package feminine_beauty.api.dtos.cliente;

import feminine_beauty.api.domain.endereco.DadosEndereco;
import feminine_beauty.api.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;


public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        Usuario login,
        Usuario senha,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
