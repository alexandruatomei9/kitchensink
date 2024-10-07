insert into MEMBER (id, name, email, phone_number) values (1, 'John Smith', 'john.smith@mailinator.com', '2125551212');
insert into MEMBER (id, name, email, phone_number) values (2, 'Jane Doe', 'jane.doe@mailinator.com', '0123456789');

ALTER TABLE member ALTER COLUMN id RESTART WITH 3;