package feminine_beauty.api.domain.consulta;

import feminine_beauty.api.domain.cliente.Cliente;
import feminine_beauty.api.domain.funcionario.Funcionario;
import feminine_beauty.api.domain.servico.Servico;
import feminine_beauty.api.dtos.consulta.StatusConsulta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    private String tipoPagamento;
    private String motivoCancelamento;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Consulta(Funcionario funcionario, Cliente cliente, Servico servico, LocalDateTime data,
                    String tipoPagamento) {
        this.status = StatusConsulta.PENDENTE;
        this.funcionario = funcionario;
        this.servico = servico;
        this.cliente = cliente;
        this.data = data;
        this.tipoPagamento = tipoPagamento;
    }

    public void cancelar(String motivoCancelamento){
        this.status = StatusConsulta.CANCELADO;
        this.motivoCancelamento = motivoCancelamento;
    }

    public void concluir() {
        this.status = StatusConsulta.CONCLUIDO;
    }
}


