CREATE TABLE users (
  username VARCHAR2(50),
  password CHAR(68),
  enabled NUMBER(1),

  CONSTRAINT users_pk PRIMARY KEY (username)
);


INSERT INTO users (username, password, enabled) VALUES ('marko', '{bcrypt}$2a$04$kR.gld7CmZbxKfGg8eZmSOzeSEMmSP5SBz5Je2htWXZ1sLb4MF0RW', 1); -- p123word
INSERT INTO users (username, password, enabled) VALUES ('diego', '{bcrypt}$2a$04$oi9nGs5Ooz/uGjMHr98HCOI0BD56VPjxhxWsBCHVojBULTdkAk3LW', 1); -- p456word
INSERT INTO users (username, password, enabled) VALUES ('bilbo', '{bcrypt}$2a$04$H1W3/tdjDRoNZRikuDuhEOGmygxWm25mm77U2Yrn6U6o6vAWzsTB.', 1); -- p789word
INSERT INTO users (username, password, enabled) VALUES ('olaf', '{bcrypt}$2a$04$EDniyB8vYk1sLyKe7CrSJuGb3b3S.MHpVHM00gsgH76LTvptv2R.y', 1); -- p012word


