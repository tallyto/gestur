CREATE TABLE anexo (
    id SERIAL PRIMARY KEY,
    nome_arquivo VARCHAR(100),
    descricao VARCHAR(255),
    tipo VARCHAR(100),
    tamanho INTEGER
);

CREATE TABLE cliente_anexo (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER REFERENCES cliente(id),
    anexo_id INTEGER REFERENCES anexo(id)
);

CREATE TABLE venda_anexo (
    id SERIAL PRIMARY KEY,
    venda_id INTEGER REFERENCES venda(id),
    anexo_id INTEGER REFERENCES anexo(id)
);