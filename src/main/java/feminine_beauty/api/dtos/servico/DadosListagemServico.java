package feminine_beauty.api.dtos.servico;

import feminine_beauty.api.domain.servico.Servico;

public record DadosListagemServico(Long id, String descricao, Double preco, Long duracao, Boolean ativo, String img) {

    public DadosListagemServico(Servico servico){
        this(servico.getId(), servico.getDescricao(), servico.getPreco(), servico.getDuracao(), servico.getAtivo(), servico.getImagemUrl());
    }
}
