ALTER TABLE cliente
    ADD COLUMN numero_passaporte VARCHAR(20),
    ADD COLUMN pais_emissao VARCHAR(20),
    ADD COLUMN data_emissao DATE,
    ADD COLUMN data_validade DATE;