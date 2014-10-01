create table groups (
	id int not null auto_increment,
	name varchar(100) not null,
	PRIMARY KEY(id)
);

INSERT INTO groups ( id , name ) VALUES ( 1 , 'admin');
INSERT INTO groups ( id , name ) VALUES ( 2 , 'members');
INSERT INTO groups ( id , name ) VALUES ( 3 , 'users');
INSERT INTO groups ( id , name ) VALUES ( 4 , 'guests');


create table users (
	id int not null auto_increment,
	id_group int not null,
	name varchar(100) not null,
	email varchar(100) not null,
	PRIMARY KEY(id)
);

INSERT INTO users ( id , name ) VALUES ( 1 , 'adriano');
UPDATE users SET email = 'adriano@mail.com' WHERE id = 1;

create table clients (
	id int not null auto_increment,
	id_user int not null,
	name varchar(100) not null,
	email varchar(100) not null,
	PRIMARY KEY(id)
);



