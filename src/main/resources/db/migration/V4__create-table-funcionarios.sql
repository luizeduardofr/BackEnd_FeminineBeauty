CREATE TABLE funcionarios (

        id bigint not null auto_increment,
        nome varchar(100) not null,
        email varchar(100) not null unique,
        telefone varchar(20) not null,
        cpf varchar(11) not null unique,
        usuario_id bigint not null,
        servico_id bigint not null,
        logradouro varchar(100) not null,
        bairro varchar(100) not null,
        cep varchar(9) not null,
        complemento varchar(100),
        numero varchar(20),
        cidade varchar(100) not null,
        uf char(2) not null,
        ativo tinyint,

        primary key (id),
        constraint fk_funcionarios_usuario_id foreign key(usuario_id) references usuarios(id),
        constraint fk_funcionarios_servico_id foreign key(servico_id) references servicos(id)
);