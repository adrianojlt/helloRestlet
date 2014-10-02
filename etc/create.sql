create table groups (
	id int not null auto_increment,
	name varchar(100) not null,
	PRIMARY KEY(id)
);

INSERT INTO groups ( id , name ) VALUES ( 1 , 'admin');
INSERT INTO groups ( id , name ) VALUES ( 2 , 'manager');
INSERT INTO groups ( id , name ) VALUES ( 3 , 'users');
INSERT INTO groups ( id , name ) VALUES ( 4 , 'guests');

-- update groups set name='manager' where id = 2;

create table users (
	id int not null auto_increment,
	id_group int not null,
	name varchar(100) not null,
	email varchar(100) not null,
	PRIMARY KEY(id)
);

INSERT INTO users ( id , id_group , name , email ) VALUES ( 1 , 1 , 'adriano' , 'adriano@mail.com');
INSERT INTO users ( id , id_group , name , email ) VALUES ( 1 , 1 , 'adriano' , 'adriano@mail.com');
UPDATE users SET email = 'adriano@mail.com' WHERE id = 1;

create table clients (
	id int not null auto_increment,
	id_user int not null,
	name varchar(100) not null,
	email varchar(100) not null,
	PRIMARY KEY(id)
);


