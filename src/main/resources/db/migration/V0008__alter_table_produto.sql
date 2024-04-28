ALTER TABLE produto
ADD COLUMN preco_total DECIMAL(10,2),
ADD COLUMN fornecedor_id INTEGER REFERENCES fornecedor(id);