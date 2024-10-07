package feminine_beauty.api.services;

import feminine_beauty.api.domain.ValidacaoException;
import feminine_beauty.api.domain.consulta.Consulta;
import feminine_beauty.api.dtos.consulta.ConsultaSpecification;
import feminine_beauty.api.dtos.consulta.DadosAgendamentoConsulta;
import feminine_beauty.api.dtos.consulta.DadosCancelamentoConsulta;
import feminine_beauty.api.dtos.consulta.DadosListagemConsulta;
import feminine_beauty.api.dtos.consulta.StatusConsulta;
import feminine_beauty.api.dtos.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import feminine_beauty.api.dtos.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import feminine_beauty.api.repositories.ClienteRepository;
import feminine_beauty.api.repositories.ConsultaRepository;
import feminine_beauty.api.repositories.FuncionarioRepository;
import feminine_beauty.api.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadorCancelamento;

    public DadosListagemConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!clienteRepository.existsById(dados.cliente().id())) {
            throw new ValidacaoException("Id do cliente informado não existe!");
        }

        if (!servicoRepository.existsById(dados.servico().id())) {
            throw new ValidacaoException("Id do servico informado não existe!");
        }

        if (!funcionarioRepository.existsById(dados.funcionario().id())) {
            throw new ValidacaoException("Id do funcionario informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var cliente = clienteRepository.getReferenceById(dados.cliente().id());
        var funcionario = funcionarioRepository.getReferenceById(dados.funcionario().id());
        var servico = servicoRepository.getReferenceById(dados.servico().id());

        var consulta = new Consulta(funcionario, cliente, servico, dados.data(), dados.tipoPagamento());
        var consultaCriada = consultaRepository.save(consulta);

        return new DadosListagemConsulta(consultaCriada);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadorCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
        consultaRepository.save(consulta);
    }

    public Page<DadosListagemConsulta> listarConsultasCliente(Long idCliente, Long idServico, Long idFuncionario, Pageable paginacao) {
        var specs = ConsultaSpecification.clienteId(idCliente).and(ConsultaSpecification.status(StatusConsulta.PENDENTE));
        if (idServico != null) {
            specs = specs.and(ConsultaSpecification.servicoId(idServico));
        }
        if (idFuncionario != null) {
            specs = specs.and(ConsultaSpecification.funcionarioId(idFuncionario));
        }
        return consultaRepository.findAll(specs, paginacao).map(DadosListagemConsulta::new);
    }

    public Page<DadosListagemConsulta> listarOldConsultasCliente(Long idCliente, Long idServico, Long idFuncionario, StatusConsulta status, Pageable paginacao) {
        var specs = ConsultaSpecification.clienteId(idCliente);
        if (idServico != null) {
            specs = specs.and(ConsultaSpecification.servicoId(idServico));
        }
        if (idFuncionario != null) {
            specs = specs.and(ConsultaSpecification.funcionarioId(idFuncionario));
        }
        if (status != null) {
            specs = specs.and(ConsultaSpecification.status(status));
        } else {
            specs = specs.and(ConsultaSpecification.statusNot(StatusConsulta.PENDENTE));
        }
        return consultaRepository.findAll(specs, paginacao).map(DadosListagemConsulta::new);
    }

    public DadosListagemConsulta concluir(Long idConsulta) {
        if (!consultaRepository.existsById(idConsulta)) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(idConsulta);
        consulta.concluir();
        var consultaConcluida = consultaRepository.save(consulta);

        return new DadosListagemConsulta(consultaConcluida);
    }

    public Page<DadosListagemConsulta> listarConsultasFuncionario(Long idFuncionario,
                                                                  Long idServico, Pageable paginacao) {
        var specs = ConsultaSpecification.funcionarioId(idFuncionario).and(ConsultaSpecification.status(StatusConsulta.PENDENTE));
        if (idServico != null) {
            specs = specs.and(ConsultaSpecification.servicoId(idServico));
        }
        return consultaRepository.findAll(specs, paginacao).map(DadosListagemConsulta::new);
    }

    public Page<DadosListagemConsulta> listarOldConsultasFuncionario(Long idFuncionario, StatusConsulta status, Long idServico, Pageable paginacao) {
        var specs = ConsultaSpecification.funcionarioId(idFuncionario);
        if (idServico != null) {
            specs = specs.and(ConsultaSpecification.servicoId(idServico));
        }
        if (status != null) {
            specs = specs.and(ConsultaSpecification.status(status));
        } else {
            specs = specs.and(ConsultaSpecification.statusNot(StatusConsulta.PENDENTE));
        }
        return consultaRepository.findAll(specs, paginacao).map(DadosListagemConsulta::new);
    }
}
