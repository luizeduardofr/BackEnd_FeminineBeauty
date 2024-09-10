package feminine_beauty.api.domain.cupomDesconto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.util.Date;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CupomDesconto {

    private String codigo;
    private Integer desconto;
    private Date validade;

    public CupomDesconto (DadosCupomDesconto dados) {
        this.codigo = dados.codigo();
        this.desconto = dados.desconto();
        this.validade = dados.validade();
    }

    public void atualizarInformacoes (DadosCupomDesconto dados) {
        if (dados.codigo() != null) {
            this.codigo = dados.codigo();
        }
        if (dados.desconto() != null) {
            this.desconto = dados.desconto();
        }
        if (dados.validade() != null) {
            this.validade = dados.validade();
        }
    }
}
