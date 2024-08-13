package feminine_beauty.api.dtos.funcionario;

import feminine_beauty.api.domain.endereco.Endereco;
import feminine_beauty.api.domain.funcionario.Funcionario;

public record DadosDetalhamentoFuncionario(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getEmail(), funcionario.getTelefone(), funcionario.getEndereco());
    }
}
