CREATE TABLE consultas (

    id bigint not null auto_increment,
    data datetime not null,
    tipo_pagamento varchar(50) not null,
    motivo_cancelamento varchar(50),
    funcionario_id bigint not null,
    cliente_id bigint not null,

    primary key(id),
    constraint fk_consultas_funcionario_id foreign key(funcionario_id) references funcionarios(id),
    constraint fk_consultas_cliente_id foreign key(cliente_id) references clientes(id)
);