CREATE TABLE produto
(
    id        SERIAL PRIMARY KEY,
    nome      VARCHAR(255),
    descricao TEXT,
    preco     DECIMAL(10, 2)
);
