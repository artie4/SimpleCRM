-- CREATE TABLE authorities (
--   username VARCHAR2(50) NOT NULL,
--   authority VARCHAR2(50) NOT NULL ,
--   enabled NUMBER(1),
--
--   CONSTRAINT authorities_un UNIQUE (username, authority),
--   CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES users(username)
-- );

CREATE TABLE roles (
  id RAW(16),
  name VARCHAR2(50),
  enabled NUMBER(1) default 1,

  CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id RAW(16),
  role_id RAW(16),

    CONSTRAINT users_roles_users_fk FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT users_roles_roles_fk FOREIGN KEY (role_id) REFERENCES roles(id)
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
