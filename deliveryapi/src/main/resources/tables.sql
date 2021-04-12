create table cozinha (
  id bigint not null auto_increment,
  nome varchar(60) not null,

  primary key (id)
) engine=InnoDB default charset=utf8;


create table estado (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;


create table cidade (
	id bigint not null auto_increment primary key,
	nome_cidade varchar(80) not null,
	nome_estado varchar(80) not null,
	id_estado bigint(20) not null,
	FOREIGN KEY (id_estado) REFERENCES estado(id)
) engine=InnoDB default charset=utf8;


create table restaurante (
	id bigint not null auto_increment primary key,
	nome varchar(80) not null,
	taxa_frete decimal(10,2) not null,
	data_atualizacao datetime not null,
	data_cadastro datetime not null,
	cozinha_id bigint not null,
	FOREIGN KEY (cozinha_id) REFERENCES cozinha(id)
) engine=InnoDB default charset=utf8;


create table forma_pagamento (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;


create table permissao (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	nome varchar(100) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;




