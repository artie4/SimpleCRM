CREATE TABLE authorities (
  username VARCHAR2(50) NOT NULL,
  authority VARCHAR2(50) NOT NULL ,
  enabled NUMBER(1),

  CONSTRAINT authorities_un UNIQUE (username, authority),
  CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES users(username)
);


INSERT INTO authorities (username, authority, enabled) VALUES ('marko', 'ROLE_EMPLOYEE', 1);
INSERT INTO authorities (username, authority, enabled) VALUES ('marko', 'ROLE_ADMIN', 1);
INSERT INTO authorities (username, authority, enabled) VALUES ('diego', 'ROLE_EMPLOYEE', 1);
INSERT INTO authorities (username, authority, enabled) VALUES ('diego', 'ROLE_MANAGER', 1);
INSERT INTO authorities (username, authority, enabled) VALUES ('bilbo', 'ROLE_EMPLOYEE', 1);
INSERT INTO authorities (username, authority, enabled) VALUES ('olaf', 'ROLE_EMPLOYEE', 1);
