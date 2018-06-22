DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 100;

CREATE TABLE users (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR(100) NOT NULL,
  age         INTEGER,
  createdDate TIMESTAMP    NOT NULL,
  isAdmin     BOOLEAN             DEFAULT FALSE
)