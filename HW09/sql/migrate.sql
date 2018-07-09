DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS addresses;

DROP SEQUENCE IF EXISTS phone_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;
DROP SEQUENCE IF EXISTS address_id_seq;

CREATE SEQUENCE phone_id_seq;
CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE address_id_seq;

CREATE TABLE addresses (
  id bigint NOT NULL DEFAULT nextval('address_id_seq') PRIMARY KEY,
  street varchar(255)
);

CREATE TABLE users (
  id bigint NOT NULL DEFAULT nextval('user_id_seq') PRIMARY KEY,
  name varchar(255),
  age integer,
  address_id bigint,
  FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE phones (
  id bigint NOT NULL DEFAULT nextval('phone_id_seq') PRIMARY KEY,
  number varchar(255),
  user_id bigint NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
