CREATE TABLE item_pedido
(
    id         SERIAL PRIMARY KEY,
    pedido_id  BIGINT,
    produto_id BIGINT,
    quantidade INT,
    FOREIGN KEY (pedido_id) REFERENCES pedido (id),
    FOREIGN KEY (produto_id) REFERENCES produto (id)
);