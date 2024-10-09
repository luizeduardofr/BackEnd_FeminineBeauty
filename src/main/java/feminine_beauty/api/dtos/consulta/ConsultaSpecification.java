package feminine_beauty.api.dtos.consulta;

import feminine_beauty.api.domain.consulta.Consulta;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public class ConsultaSpecification {

    public static Specification<Consulta> clienteId(Long idCliente) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cliente").get("id"), idCliente);
    }

    public static Specification<Consulta> status(StatusConsulta status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Consulta> statusNot(StatusConsulta status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("status"), status);
    }

    public static Specification<Consulta> funcionarioId(Long idFuncionario) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("funcionario").get("id"), idFuncionario);
    }

    public static Specification<Consulta> servicoId(Long idServico) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("servico").get("id"), idServico);
    }

    public static Specification<Consulta> dataBetween(ZonedDateTime dataInicio, ZonedDateTime dataFim) {
        return (root, query, criteriaBuilder) -> {
            var inicio = criteriaBuilder.greaterThanOrEqualTo(root.get("data"), dataInicio);
            var fim = criteriaBuilder.lessThanOrEqualTo(root.get("data"), dataFim);
            return criteriaBuilder.and(inicio, fim);
        };
    }
}
