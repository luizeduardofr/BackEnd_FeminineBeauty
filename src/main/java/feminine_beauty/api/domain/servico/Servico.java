package feminine_beauty.api.domain.servico;

import feminine_beauty.api.dtos.servico.DadosAtualizacaoServico;
import feminine_beauty.api.dtos.servico.DadosCadastroServico;
import jakarta.persistence.*;
import lombok.*;

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
    private Integer preco;
    private String imagem;

    private Boolean ativo;

    public Servico (DadosCadastroServico dados) {
        this.ativo = true;
        this.descricao = dados.descricao();
        this.preco = dados.preco();
        this.imagem = dados.imagem();
    }

    public void atualizarInformacoes (DadosAtualizacaoServico dados) {
        if (dados.preco() != null ) {
            this.preco = dados.preco();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
