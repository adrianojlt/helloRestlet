create table groups (
	id int not null auto_increment,
	name varchar(100) not null,
	PRIMARY KEY(id)
);

create table users (
	id int not null auto_increment,
	id_group int not null,
	name varchar(100) not null,
	email varchar(100) not null,
	PRIMARY KEY(id)
);

create table clients (
	id int not null auto_increment,
	id_user int not null,
	name varchar(100) not null,
	email varchar(100) not null,
	PRIMARY KEY(id)
);

