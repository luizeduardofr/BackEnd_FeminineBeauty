package feminine_beauty.api.dtos.consulta.validacoes.cancelamento;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.dtos.consulta.DadosCancelamentoConsulta;
import feminine_beauty.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            var role = requestAttributes.getRequest().getAttribute("role");
            if (role != null && !role.equals("usuario")) return;
        }

        var consulta = repository.getReferenceById(dados.idConsulta());
        var dataConsulta = consulta.getData().atZone(ZoneOffset.UTC);
        var dataTratada = dataConsulta.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, dataTratada).toHours();

        if (diferencaEmHoras < 2) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 2h!");
        }
    }
}
