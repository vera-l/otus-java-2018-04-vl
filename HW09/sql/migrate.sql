DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_id_seq;

CREATE SEQUENCE user_id_seq;

CREATE TABLE users (
  id bigint NOT NULL DEFAULT nextval('user_id_seq'),
  name varchar(255),
  age integer
);
