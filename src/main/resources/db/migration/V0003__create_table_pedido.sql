CREATE TABLE pedido
(
    id          SERIAL PRIMARY KEY,
    cliente_id  BIGINT,
    data_pedido DATE,
    status      VARCHAR(50),
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);