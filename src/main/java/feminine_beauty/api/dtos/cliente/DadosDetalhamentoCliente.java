package feminine_beauty.api.dtos.cliente;

import feminine_beauty.api.domain.cliente.Cliente;
import feminine_beauty.api.domain.endereco.Endereco;

public record DadosDetalhamentoCliente(Long id, String nome, String telefone, String email, String cpf, Endereco endereco) {

    public DadosDetalhamentoCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf(), cliente.getEndereco());
    }
}
