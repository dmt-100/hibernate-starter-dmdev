DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS users_id_seq;
DROP TABLE IF EXISTS all_sequence;

CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(128) UNIQUE ,
    firstname VARCHAR(128),
    lastname VARCHAR(128),
    birth_date DATE,
    role VARCHAR(32),
    info JSONB
);

-- CREATE SEQUENCE IF NOT EXISTS users_id_seq
-- OWNED BY users.id;

CREATE TABLE all_sequence
(
    table_name VARCHAR(32) PRIMARY KEY ,
    pk_value BIGINT NOT NULL
);