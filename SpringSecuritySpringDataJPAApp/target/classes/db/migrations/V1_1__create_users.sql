CREATE TABLE users
(
  userid bigserial NOT NULL,
  username text NOT NULL,
  email text NOT NULL,
  password text NOT NULL,
  enabled boolean DEFAULT true,
  CONSTRAINT users_pk PRIMARY KEY (userid),
  CONSTRAINT users_username_unique UNIQUE (username)
);
  
CREATE TABLE user_roles
(
  userid bigint NOT NULL,
  role text NOT NULL,
  user_role_id bigserial NOT NULL,
  CONSTRAINT user_roles_userid_fk FOREIGN KEY (userid)
      REFERENCES users (userid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_roles_unique_role_userid UNIQUE (userid, role)
);

INSERT INTO users(username,email,password,enabled)
VALUES ('priya','abc@abc.com','$2a$04$CO93CT2ObgMiSnMAWwoBkeFObJlMYi/wzzOnPlsTP44r7qVq0Jln2', true);
INSERT INTO users(username,email,password,enabled)
VALUES ('naveen','def@def.com','$2a$04$j3JpPUp6CTAe.kMWmdRNC.Wie58xDNPfcYz0DBJxWkucJ6ekJuiJm', true);

INSERT INTO user_roles (userid, role)
VALUES (1, 'ROLE_USER');
INSERT INTO user_roles (userid, role)
VALUES (2, 'ROLE_ADMIN');
INSERT INTO user_roles (userid, role)
VALUES (2, 'ROLE_USER');