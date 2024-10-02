package feminine_beauty.api.dtos.funcionario;

import feminine_beauty.api.domain.funcionario.Funcionario;
import feminine_beauty.api.domain.servico.Servico;

import java.util.List;

public record DadosListagemFuncionario(Long id, String nome, String cpf, String email, String telefone, List<Servico> servicos) {

    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getEmail(), funcionario.getTelefone(), funcionario.getServicos());
    }
}
