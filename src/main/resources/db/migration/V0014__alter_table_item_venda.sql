ALTER TABLE item_venda ADD COLUMN localizador VARCHAR(50);
ALTER TABLE item_venda ADD COLUMN atendente VARCHAR(100);

ALTER TABLE item_venda ADD COLUMN quantidade INT;
ALTER TABLE item_venda ADD COLUMN desconto DECIMAL(10, 2);

ALTER TABLE item_venda ADD COLUMN quantidade_fornecedor INT;
ALTER TABLE item_venda ADD COLUMN valor_fornecedor DECIMAL(10,2);
ALTER TABLE item_venda ADD COLUMN desconto_fornecedor DECIMAL(10, 2);

