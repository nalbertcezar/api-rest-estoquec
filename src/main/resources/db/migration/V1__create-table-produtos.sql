create table produtos(

	id bigint not null auto_increment primary key,
	descricao varchar(100) not null,
	imei varchar (15) not null unique,
	valor decimal not null,
	categoria varchar(50) not null,
	modelo varchar(50) not null,
	memoria varchar(10)
	
);
