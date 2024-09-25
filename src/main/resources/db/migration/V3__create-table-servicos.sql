CREATE TABLE servicos(

    id bigint not null auto_increment,
    descricao varchar(50) not null,
    preco double not null,
    imagemUrl varchar(200),
    ativo tinyint,

    primary key(id)

);