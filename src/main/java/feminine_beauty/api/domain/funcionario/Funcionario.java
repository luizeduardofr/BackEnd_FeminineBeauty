package feminine_beauty.api.domain.funcionario;

import feminine_beauty.api.domain.endereco.Endereco;
import jakarta.persistence.*;

public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Funcionario (DadosCadastroFuncionario dados) {

    }

    public void atualizarInformacoes (DadosAtualizacaoFuncionario dados) {

    }

    public void excluir() {
        this.ativo = false;
    }
}
