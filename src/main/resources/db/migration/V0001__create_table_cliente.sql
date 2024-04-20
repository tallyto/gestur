CREATE TABLE cliente
(
    id              SERIAL PRIMARY KEY,
    nome            VARCHAR(100),
    cpf             VARCHAR(14),
    rg              VARCHAR(20),
    email           VARCHAR(100),
    telefone        VARCHAR(20),
    cep             VARCHAR(9),
    rua             VARCHAR(100),
    numero          VARCHAR(100),
    complemento     VARCHAR(100),
    bairro          VARCHAR(100),
    cidade          VARCHAR(100),
    estado          VARCHAR(2),
    renda           DECIMAL(10, 2),
    profissao       VARCHAR(50),
    data_nascimento date
);
