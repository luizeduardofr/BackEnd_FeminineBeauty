package feminine_beauty.api.dtos.cliente;

import feminine_beauty.api.domain.cliente.Cliente;

public record DadosListagemCliente(Long id, String nome, String telefone, String email, String cpf) {

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf());
    }
}
