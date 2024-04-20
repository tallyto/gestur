CREATE TABLE venda
(
    id          SERIAL PRIMARY KEY,
    cliente_id  BIGINT,
    data_embarque DATE,
    data_desembarque DATE,
    status      VARCHAR(50),
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);