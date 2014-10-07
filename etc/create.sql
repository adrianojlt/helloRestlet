drop table if exists groups;
drop table if exists users;
drop table if exists clients;

create table groups (
	id int not null auto_increment,
	name varchar(100) not null,
	PRIMARY KEY(id)
);

INSERT INTO groups ( id , name ) VALUES ( 1 , 'admin');
INSERT INTO groups ( id , name ) VALUES ( 2 , 'manager');
INSERT INTO groups ( id , name ) VALUES ( 3 , 'users');
INSERT INTO groups ( id , name ) VALUES ( 4 , 'guests');


create table users (
	id int not null auto_increment,
	id_group int not null,
	name varchar(100) not null,
	email varchar(100) not null,
	PRIMARY KEY(id)
);

INSERT INTO users ( id , id_group , name , email ) VALUES ( 1 , 1 , 'adriano' , 'adriano@mail.com');
INSERT INTO users ( id , id_group , name , email ) VALUES ( 2 , 2 , 'manager1' , 'manager1@mail.com');
INSERT INTO users ( id , id_group , name , email ) VALUES ( 3 , 2 , 'manager2' , 'manager2@mail.com');
INSERT INTO users ( id , id_group , name , email ) VALUES ( 4 , 2 , 'manager3' , 'manager3@mail.com');
INSERT INTO users ( id , id_group , name , email ) VALUES ( 5 , 3 , 'user1' , 'user1@mail.com');
INSERT INTO users ( id , id_group , name , email ) VALUES ( 6 , 3 , 'user2' , 'user2@mail.com');
INSERT INTO users ( id , id_group , name , email ) VALUES ( 7 , 4 , 'guest1' , 'guest1@mail.com');

create table clients (
	id int not null auto_increment,
	id_user int not null,
	name varchar(100) not null,
	email varchar(100) not null,
	PRIMARY KEY(id)
);

INSERT INTO clients ( id , id_user , name , email ) VALUES ( 1 , 5 , 'client1' , 'client1@mail.com');
INSERT INTO clients ( id , id_user , name , email ) VALUES ( 2 , 5 , 'client2' , 'client2@mail.com');
INSERT INTO clients ( id , id_user , name , email ) VALUES ( 3 , 5 , 'client3' , 'client3@mail.com');
INSERT INTO clients ( id , id_user , name , email ) VALUES ( 4 , 6 , 'client4' , 'client4@mail.com');
INSERT INTO clients ( id , id_user , name , email ) VALUES ( 5 , 6 , 'client5' , 'client5@mail.com');

-- TODOS

create table todos (
	id int not null auto_increment,
	title varchar(30),
	description varchar(100),
	PRIMARY KEY(id)
);

-- TUTORIAL TABLES

DROP table IF EXISTS Contact;
DROP table IF EXISTS Company;

CREATE TABLE Company (
    id INT NOT NULL AUTO_INCREMENT, 
    duns VARCHAR(9) UNIQUE,
    name VARCHAR(255),
    address VARCHAR(255), 
    zip_code VARCHAR(5), 
    company_creation Date, 
    website VARCHAR(255), 
    phone_number VARCHAR(255), 
    city VARCHAR(255), 
    PRIMARY KEY(id)
);

CREATE TABLE Contact (
    id VARCHAR(255), 
    email VARCHAR (255) NOT NULL UNIQUE,
    age INT,
    name VARCHAR (255),
    firstname VARCHAR (255),
    company_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY (company_id) REFERENCES Company(id)
); 

