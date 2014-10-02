show databases;
use hellorestlet;
show tables;

select * from groups;
select * from users;
select * from clients;

desc users;

-- UPDATES
update groups set name='manager' where id = 2;
UPDATE users SET email = 'adriano@mail.com' WHERE id = 1;






