CREATE TABLE servicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    preco DOUBLE,
    duracao BIGINT,
    imagem_url VARCHAR(255),
    ativo BOOLEAN NOT NULL
);