DELETE FROM anexo_cliente;

DELETE FROM anexo_venda;

DELETE FROM anexo;

DELETE FROM item_venda;

DELETE FROM venda;

DELETE FROM cliente;

DELETE FROM produto;

-- RESTART IDS

ALTER SEQUENCE anexo_id_seq RESTART WITH 1;

ALTER SEQUENCE anexo_cliente_id_seq RESTART WITH 1;

ALTER SEQUENCE anexo_venda_id_seq RESTART WITH 1;

ALTER SEQUENCE item_venda_id_seq RESTART WITH 1;

ALTER SEQUENCE venda_id_seq RESTART WITH 1;

ALTER SEQUENCE cliente_id_seq RESTART WITH 1;

ALTER SEQUENCE produto_id_seq RESTART WITH 1;

-- END RESTART IDS

INSERT INTO cliente (nome, cpf, rg, email, telefone, cep, rua, numero, complemento, bairro, cidade, estado, renda, profissao, data_nascimento)
VALUES
    ('João Silva', '123.456.789-00', '1234567-8', 'joao@example.com', '(11) 1234-5678', '12345-678', 'Rua A', '123', 'Apto 101', 'Centro', 'Cidade A', 'AA', 5000.00, 'Engenheiro', '1990-05-15'),
    ('Maria Souza', '987.654.321-00', '9876543-2', 'maria@example.com', '(22) 9876-5432', '54321-876', 'Rua B', '456', 'Casa', 'Bairro X', 'Cidade B', 'BB', 4000.00, 'Advogada', '1985-10-20'),
    ('Pedro Oliveira', '111.222.333-44', '1112223-4', 'pedro@example.com', '(33) 1111-2222', '11111-222', 'Rua C', '789', 'Sala 203', 'Centro', 'Cidade C', 'CC', 6000.00, 'Médico', '1978-03-10'),
    ('Ana Santos', '555.666.777-88', '5556667-8', 'ana@example.com', '(44) 5555-6666', '99999-888', 'Rua D', '1011', 'Casa', 'Bairro Y', 'Cidade D', 'DD', 4500.00, 'Professor', '1992-12-05'),
    ('Lucas Pereira', '999.888.777-66', '9998887-6', 'lucas@example.com', '(55) 9999-8888', '77777-666', 'Rua E', '1213', 'Apto 301', 'Centro', 'Cidade E', 'EE', 5500.00, 'Empresário', '1980-08-25');


INSERT INTO produto (nome, descricao, preco)
VALUES
    ('Pacote de Viagem para Cancún', 'Inclui hospedagem em resort all-inclusive por uma semana', 1999.99),
    ('Passeio de Barco pelo Rio Sena', 'Passeio de barco de 2 horas com guia turístico', 49.99),
    ('Excursão ao Machu Picchu', 'Inclui transporte, guia turístico e entrada no parque', 599.99),
    ('Pacote de Cruzeiro pelo Mediterrâneo', 'Cruzeiro de 7 dias com pensão completa', 1499.99),
    ('Excursão de Safari na África do Sul', 'Safari de 3 dias em reserva natural com guia especializado', 999.99);

INSERT INTO venda (cliente_id, data_embarque, data_desembarque, status)
VALUES
    (1, '2024-06-15', '2024-06-22', 'Confirmada'),
    (2, '2024-07-10', '2024-07-15', 'Confirmada'),
    (3, '2024-08-05', '2024-08-12', 'Pendente'),
    (4, '2024-09-20', '2024-09-25', 'Confirmada'),
    (5, '2024-10-15', '2024-10-22', 'Pendente');


INSERT INTO item_venda (venda_id, produto_id, quantidade, forma_pagamento, status_pagamento, valor_total, valor_pago)
VALUES
    (1, 1, 1, 'PIX', 'PAGO', 1800, 2000),
    (2, 2, 2, 'DINHEIRO', 'PAGO', 2000, 2400),
    (1, 3, 2, 'CARTÃO', 'AGUARDANDO CONFIRMAÇÃO', 5000, 5300),
    (2, 2, 1, 'CARTÃO', 'PAGO', 10000, 11000),
    (3, 4, 1, 'PIX', 'PAGO', 3500, 3800),
    (4, 5, 1, 'DINHEIRO', 'PAGO', 8000, 8200),
    (5, 1, 3, 'CARTÃO', 'CANCELADO', 5000, 5300);
