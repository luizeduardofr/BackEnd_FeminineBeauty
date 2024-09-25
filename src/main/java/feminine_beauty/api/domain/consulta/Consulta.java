package feminine_beauty.api.domain.consulta;

import feminine_beauty.api.domain.cliente.Cliente;
import feminine_beauty.api.domain.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDateTime data;

    private String tipoPagamento;

    private String motivoCancelamento;

    public Consulta(Long id, Funcionario funcionario, Cliente cliente, LocalDateTime data, String tipoPagamento, String motivoCancelamento) {
        this.id = id;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.data = data;
        this.tipoPagamento = tipoPagamento;
        this.motivoCancelamento = motivoCancelamento;
    }

    public void cancelar(String motivoCancelamento){
        this.motivoCancelamento = motivoCancelamento;
    }
}


