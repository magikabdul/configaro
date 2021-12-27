CREATE TABLE IF NOT EXISTS roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles(name)
VALUES ('admin'),
       ('user')
ON CONFLICT(name) DO NOTHING;
