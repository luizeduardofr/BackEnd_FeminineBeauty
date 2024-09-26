CREATE TABLE consultas (

    id bigint not null auto_increment,
    funcionario_id bigint not null,
    cliente_id bigint not null,
    data datetime not null,
    tipoPagamento varchar(50) not null,
    motivoCancelamento varchar(50),

    primary key(id),
    constraint fk_consultas_funcionario_id foreign key(funcionario_id) references funcionarios(id),
    constraint fk_consultas_cliente_id foreign key(cliente_id) references clientes(id),
);