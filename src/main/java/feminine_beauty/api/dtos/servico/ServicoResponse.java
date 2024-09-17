package feminine_beauty.api.dtos.servico;

import feminine_beauty.api.domain.servico.Servico;

public record ServicoResponse(Long id, String descricao, Double preco, String img) {

    public ServicoResponse(Servico servico){
        this(servico.getId(), servico.getDescricao(), servico.getPreco(), servico.getImagem());
    }
}
