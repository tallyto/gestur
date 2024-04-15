CREATE TABLE cliente
(
    id              SERIAL PRIMARY KEY,
    nome            VARCHAR(255),
    cpf             VARCHAR(14),
    rg              VARCHAR(20),
    email           VARCHAR(255),
    telefone        VARCHAR(20),
    cep             VARCHAR(9),
    logradouro      VARCHAR(255),
    complemento     VARCHAR(255),
    bairro          VARCHAR(100),
    cidade          VARCHAR(100),
    estado          VARCHAR(2),
    renda           DECIMAL(10, 2),
    profissao       VARCHAR(255),
    data_nascimento date
);
