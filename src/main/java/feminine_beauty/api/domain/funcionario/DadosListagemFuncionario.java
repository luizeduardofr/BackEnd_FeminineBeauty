package feminine_beauty.api.domain.funcionario;

public record DadosListagemFuncionario(Long id, String nome, String cpf, String email, String telefone) {

    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getEmail(), funcionario.getTelefone());
    }
}
