CREATE TABLE consultas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME NOT NULL,
    tipo_pagamento VARCHAR(50) NOT NULL,
    motivo_cancelamento VARCHAR(50),
    status ENUM('PENDING', 'CONFIRMED', 'CANCELLED') NOT NULL,
    servico_id BIGINT,
    funcionario_id BIGINT,
    cliente_id BIGINT,
    CONSTRAINT fk_consulta_servico FOREIGN KEY (servico_id) REFERENCES servicos(id),
    CONSTRAINT fk_consulta_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
    CONSTRAINT fk_consulta_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);