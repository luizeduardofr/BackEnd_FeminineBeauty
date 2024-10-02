package feminine_beauty.api.domain.servico;

import feminine_beauty.api.domain.consulta.Consulta;
import feminine_beauty.api.domain.funcionario.Funcionario;
import feminine_beauty.api.dtos.servico.DadosAtualizacaoServico;
import feminine_beauty.api.dtos.servico.DadosCadastroServico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "servicos")
@Entity(name = "Servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double preco;
    private Long duracao;
    private String imagemUrl;
    private Boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "funcionarios_servicos",
            joinColumns = @JoinColumn(name = "servico_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<Consulta> consultas;

    public Servico(DadosCadastroServico dados) {
        this.ativo = true;
        this.descricao = dados.descricao();
        this.preco = dados.preco();
        this.duracao = dados.duracao();
        this.imagemUrl = dados.imagemUrl();
    }

    public void atualizarInformacoes(DadosAtualizacaoServico dados) {
        if (dados.preco() != null ) {
            this.preco = dados.preco();
        }
        if (dados.imagemUrl() != null) {
            this.imagemUrl = dados.imagemUrl();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
