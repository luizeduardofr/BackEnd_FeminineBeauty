package feminine_beauty.api.domain.funcionario;

import feminine_beauty.api.domain.endereco.Endereco;
import feminine_beauty.api.domain.servico.Servico;
import feminine_beauty.api.domain.usuario.Usuario;
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
    private List<Servico> servicos;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name = "funcionario_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Usuario usuario;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Funcionario (DadosCadastroFuncionario dados) {
        this.ativo = true;
        this.usuario = new Usuario();
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes (DadosAtualizacaoFuncionario dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
