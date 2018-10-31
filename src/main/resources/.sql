create database spr_hib_declarative_transaction_management_database;
create schema spr_hib_declarative_transaction_management_database;

CREATE TABLE spr_hib_declarative_transaction_management_database.trainer (
  id         bigserial PRIMARY KEY,
  name       VARCHAR(32),
  language   VARCHAR(16) NOT NULL,
  experience INTEGER     NOT NULL
);

CREATE TABLE spr_hib_declarative_transaction_management_database.trainee (
  id         bigserial PRIMARY KEY,
  name       VARCHAR(32) NOT NULL,
  trainer_id BIGINT REFERENCES spr_hib_declarative_transaction_management_database.trainer (id)
);
