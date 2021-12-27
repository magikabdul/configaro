CREATE TABLE IF NOT EXISTS users
(
    id        BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(100)                 NOT NULL,
    lastname  VARCHAR(100)                 NOT NULL,
    email     VARCHAR(150)                 NOT NULL UNIQUE,
    password  VARCHAR(150)                 NOT NULL,
    role_id   BIGINT REFERENCES roles (id) NOT NULL
);
