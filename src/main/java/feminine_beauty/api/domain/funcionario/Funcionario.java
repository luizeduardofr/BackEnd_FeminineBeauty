package feminine_beauty.api.domain.funcionario;

import feminine_beauty.api.domain.endereco.Endereco;
import feminine_beauty.api.domain.servico.Servico;
import feminine_beauty.api.dtos.funcionario.DadosAtualizacaoFuncionario;
import feminine_beauty.api.dtos.funcionario.DadosCadastroFuncionario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "funcionarios")
@Entity(name = "Funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @ManyToMany()
    @JoinTable(joinColumns = @JoinColumn(name = "funcionario_id"), inverseJoinColumns = @JoinColumn(name = "servico_id"))
    private List<Servico> listaServicos;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Funcionario (DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
    }

    public void atualizarInformacoes (DadosAtualizacaoFuncionario dados) {

    }

    public void excluir() {
        this.ativo = false;
    }
}
