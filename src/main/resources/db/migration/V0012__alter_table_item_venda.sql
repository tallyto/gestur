ALTER TABLE item_venda
    DROP COLUMN valor_pago;

ALTER TABLE item_venda
    DROP COLUMN status_pagamento;

ALTER TABLE item_venda
    DROP COLUMN quantidade;

ALTER TABLE item_venda
    DROP COLUMN produto_id;

ALTER TABLE item_venda
    ADD COLUMN fornecedor_id BIGINT;

ALTER TABLE item_venda
    ADD CONSTRAINT fk_item_venda_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id);

ALTER TABLE item_venda
    ADD COLUMN descricao VARCHAR(150);

ALTER TABLE item_venda
    ADD COLUMN anotacao VARCHAR(255);

ALTER TABLE item_venda
    ADD COLUMN comissao_recebida DECIMAL(10, 2);

ALTER TABLE item_venda
    ADD COLUMN comissao_a_receber DECIMAL(10, 2);