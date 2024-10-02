CREATE TABLE funcionarios_servicos (
    funcionario_id BIGINT,
    servico_id BIGINT,
    PRIMARY KEY (funcionario_id, servico_id),
    CONSTRAINT fk_funcionario_servico_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
    CONSTRAINT fk_funcionario_servico_servico FOREIGN KEY (servico_id) REFERENCES servicos(id)
);