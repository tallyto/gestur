CREATE TABLE produto
(
    id        SERIAL PRIMARY KEY,
    nome      VARCHAR(100),
    descricao TEXT,
    preco     DECIMAL(10, 2)
);
