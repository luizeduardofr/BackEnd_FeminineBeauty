package feminine_beauty.api.dtos.funcionario;

import feminine_beauty.api.domain.endereco.Endereco;
import feminine_beauty.api.domain.funcionario.Funcionario;
import feminine_beauty.api.domain.servico.Servico;
import feminine_beauty.api.dtos.servico.DadosListagemServico;

import java.util.List;

public record DadosListagemFuncionario(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        Boolean ativo,
        Endereco endereco,
        List<DadosListagemServico> servicos
) {

    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getEmail(),
                funcionario.getTelefone(), funcionario.getAtivo(), funcionario.getEndereco(),
                funcionario.getServicos().stream().map(DadosListagemServico::new).toList());
    }
}
