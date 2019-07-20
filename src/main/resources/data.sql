DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id         RAW(16)     DEFAULT RANDOM_UUID(),
    first_name varchar(45) DEFAULT NULL,
    last_name  varchar(45) DEFAULT NULL,
    email      varchar(45) DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO customer (first_name, last_name, email)
VALUES ('David','Adams','david@testmail.org');
INSERT INTO customer (first_name, last_name, email)
VALUES 	('John','Doe','john@testmail.org');
INSERT INTO customer (first_name, last_name, email)
VALUES  ('Ajay','Rao','ajay@testmail.org');
INSERT INTO customer (first_name, last_name, email)
VALUES	('Mary','Public','mary@testmail.org');
INSERT INTO customer (first_name, last_name, email)
VALUES	('Maxwell','Dixon','max@testmail.org');

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         RAW(16)   DEFAULT RANDOM_UUID(),
    username   VARCHAR2(50),
    password   VARCHAR2(255),
    first_name VARCHAR2(50),
    last_name  VARCHAR2(50),
    email      VARCHAR2(50),
    enabled    NUMBER(1) default 1,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

INSERT INTO users (id, username, password, enabled) VALUES ('8A480C9D073DA8C9E05011AC02000365','marko', '$2a$04$kR.gld7CmZbxKfGg8eZmSOzeSEMmSP5SBz5Je2htWXZ1sLb4MF0RW', 1); -- p123word
INSERT INTO users (id, username, password, enabled) VALUES ('8A480C9D073EA8C9E05011AC02000365','diego', '$2a$04$oi9nGs5Ooz/uGjMHr98HCOI0BD56VPjxhxWsBCHVojBULTdkAk3LW', 1); -- p456word
INSERT INTO users (id, username, password, enabled) VALUES ('8A480C9D073FA8C9E05011AC02000365','bilbo', '$2a$04$H1W3/tdjDRoNZRikuDuhEOGmygxWm25mm77U2Yrn6U6o6vAWzsTB.', 1); -- p789word
INSERT INTO users (id, username, password, enabled) VALUES ('8A480C9D0740A8C9E05011AC02000365','olaf', '$2a$04$EDniyB8vYk1sLyKe7CrSJuGb3b3S.MHpVHM00gsgH76LTvptv2R.y', 1); -- p012word

DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    id      RAW(16),
    name    VARCHAR2(50),
    enabled NUMBER(1) default 1,
    CONSTRAINT roles_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles
(
    user_id RAW(16),
    role_id RAW(16),
    CONSTRAINT users_roles_users_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT users_roles_roles_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (id, name, enabled) VALUES ('8A35B808E057E422E05011AC0200012B', 'ROLE_ADMIN', 1);
INSERT INTO roles (id, name, enabled) VALUES ('8A35B808E058E422E05011AC0200012B', 'ROLE_EMPLOYEE', 1);
INSERT INTO roles (id, name, enabled) VALUES ('8A35B808E059E422E05011AC0200012B', 'ROLE_MANAGER', 1);

-- grant employee role
INSERT INTO users_roles (user_id, role_id) values ('8A480C9D073DA8C9E05011AC02000365', '8A35B808E058E422E05011AC0200012B');
INSERT INTO users_roles (user_id, role_id) values ('8A480C9D073EA8C9E05011AC02000365', '8A35B808E058E422E05011AC0200012B');
INSERT INTO users_roles (user_id, role_id) values ('8A480C9D073FA8C9E05011AC02000365', '8A35B808E058E422E05011AC0200012B');
INSERT INTO users_roles (user_id, role_id) values ('8A480C9D0740A8C9E05011AC02000365', '8A35B808E058E422E05011AC0200012B');

-- grant manager role
INSERT INTO users_roles (user_id, role_id) values ('8A480C9D073DA8C9E05011AC02000365', '8A35B808E059E422E05011AC0200012B');
