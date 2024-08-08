package feminine_beauty.api.domain.servico;

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
    private String preco;
    private Boolean ativo;

    public void excluir() {
        this.ativo = false;
    }
}
