CREATE TABLE item_venda
(
    id         SERIAL PRIMARY KEY,
    venda_id   BIGINT,
    produto_id BIGINT,
    quantidade INT,
    FOREIGN KEY (venda_id) REFERENCES venda (id),
    FOREIGN KEY (produto_id) REFERENCES produto (id)
);