DELETE FROM item_venda;

DELETE FROM fornecedor;

DELETE FROM anexo_cliente;

DELETE FROM anexo_venda;

DELETE FROM anexo;

DELETE FROM venda;

DELETE FROM cliente;

-- RESTART IDS

ALTER SEQUENCE fornecedor_id_seq RESTART WITH 1;

ALTER SEQUENCE anexo_id_seq RESTART WITH 1;

ALTER SEQUENCE anexo_cliente_id_seq RESTART WITH 1;

ALTER SEQUENCE anexo_venda_id_seq RESTART WITH 1;

ALTER SEQUENCE item_venda_id_seq RESTART WITH 1;

ALTER SEQUENCE venda_id_seq RESTART WITH 1;

ALTER SEQUENCE cliente_id_seq RESTART WITH 1;

-- END RESTART IDS

INSERT INTO cliente (nome, cpf, rg, email, telefone, cep, rua, numero, complemento, bairro, cidade, estado, renda,
                     profissao, data_nascimento)
VALUES ('João Silva', '123.456.789-00', '1234567-8', 'joao@example.com', '(11) 1234-5678', '12345-678', 'Rua A', '123',
        'Apto 101', 'Centro', 'Cidade A', 'AA', 5000.00, 'Engenheiro', '1990-05-15'),
       ('Maria Souza', '987.654.321-00', '9876543-2', 'maria@example.com', '(22) 9876-5432', '54321-876', 'Rua B',
        '456', 'Casa', 'Bairro X', 'Cidade B', 'BB', 4000.00, 'Advogada', '1985-10-20'),
       ('Pedro Oliveira', '111.222.333-44', '1112223-4', 'pedro@example.com', '(33) 1111-2222', '11111-222', 'Rua C',
        '789', 'Sala 203', 'Centro', 'Cidade C', 'CC', 6000.00, 'Médico', '1978-03-10'),
       ('Ana Santos', '555.666.777-88', '5556667-8', 'ana@example.com', '(44) 5555-6666', '99999-888', 'Rua D', '1011',
        'Casa', 'Bairro Y', 'Cidade D', 'DD', 4500.00, 'Professor', '1992-12-05'),
       ('Lucas Pereira', '999.888.777-66', '9998887-6', 'lucas@example.com', '(55) 9999-8888', '77777-666', 'Rua E',
        '1213', 'Apto 301', 'Centro', 'Cidade E', 'EE', 5500.00, 'Empresário', '1980-08-25');


-- Inserir vendas
INSERT INTO venda (cliente_id, data_embarque, data_desembarque, origem, destino, status)
VALUES
    (1, '2024-06-15', '2024-06-22', 'São Paulo', 'Paris', 'EM_ANDAMENTO'),
    (2, '2024-07-10', '2024-07-15', 'Nova York', 'Los Angeles', 'EM_ANDAMENTO'),
    (3, '2024-08-05', '2024-08-12', 'Cancún', 'Riviera Maya', 'PENDENTE'),
    (4, '2024-09-20', '2024-09-25', 'Roma', 'Veneza', 'EM_ANDAMENTO'),
    (5, '2024-10-15', '2024-10-22', 'Tóquio', 'Kyoto', 'PENDENTE');


-- Inserir fornecedores
INSERT INTO fornecedor (id, nome, cnpj)
VALUES (1, 'Hotel Paris', '12345678901234'),
       (2, 'Agência de Turismo Big Apple', '22334455667788'),
       (3, 'Resort Cancún All-Inclusive', '33333333333333'),
       (4, 'Hotel Roma Lux', '44444444444444'),
       (5, 'Ryokan Tokyo', '55555555555555');


-- Venda 1: Pacote Paris - 7 dias
INSERT INTO item_venda (venda_id, forma_pagamento, valor_total, descricao, fornecedor_id, anotacao, comissao_recebida,
                        comissao_a_receber)
VALUES (1, 'Cartão de crédito', 900.00, 'Hospedagem em hotel 4 estrelas - 7 noites', 1, 'Anotação 1', 50.00, 25.00),
       (1, 'Cartão de crédito', 600.00, 'Passeio pela Torre Eiffel com guia', 2, 'Anotação 2', 40.00, 20.00),
       (1, 'Cartão de crédito', 1000.00, 'Alimentação em restaurantes locais - 7 dias', 3, 'Anotação 3', 60.00, 30.00);

-- Venda 2: Pacote Nova York - 5 dias
INSERT INTO item_venda (venda_id, forma_pagamento, valor_total, descricao, fornecedor_id, anotacao, comissao_recebida,
                        comissao_a_receber)
VALUES (2, 'Dinheiro', 1200.00, 'Hospedagem em hotel no centro da cidade - 5 noites', 1, 'Anotação 4', 80.00, 40.00),
       (2, 'Dinheiro', 800.00, 'Ingressos para Empire State Building e Estátua da Liberdade', 2, 'Anotação 5', 60.00,
        30.00),
       (2, 'Dinheiro', 1200.00, 'Refeições em restaurantes temáticos - 5 dias', 3, 'Anotação 6', 60.00, 30.00);

-- Venda 3: Pacote Cancún - 6 dias
INSERT INTO item_venda (venda_id, forma_pagamento, valor_total, descricao, fornecedor_id, anotacao, comissao_recebida,
                        comissao_a_receber)
VALUES (3, 'Cartão de débito', 600.00, 'Hospedagem em resort all-inclusive - 6 noites', 1, 'Anotação 7', 40.00, 20.00),
       (3, 'Cartão de débito', 300.00, 'Passeio de barco com snorkel em Isla Mujeres', 2, 'Anotação 8', 20.00, 10.00),
       (3, 'Cartão de débito', 900.00, 'Transferências do aeroporto para o hotel - ida e volta', 3, 'Anotação 9', 60.00,
        30.00);

-- Venda 4: Pacote Roma - 8 dias
INSERT INTO item_venda (venda_id, forma_pagamento, valor_total, descricao, fornecedor_id, anotacao, comissao_recebida,
                        comissao_a_receber)
VALUES (4, 'Transferência bancária', 1500.00, 'Hospedagem em hotel 5 estrelas - 8 noites', 1, 'Anotação 10', 100.00,
        50.00),
       (4, 'Transferência bancária', 500.00, 'Tour guiado pelo Coliseu e Fórum Romano', 2, 'Anotação 11', 30.00, 15.00),
       (4, 'Transferência bancária', 900.00, 'Jantares em restaurantes tradicionais - 8 dias', 3, 'Anotação 12', 60.00,
        30.00);

-- Venda 5: Pacote Tóquio - 10 dias
INSERT INTO item_venda (venda_id, forma_pagamento, valor_total, descricao, fornecedor_id, anotacao, comissao_recebida,
                        comissao_a_receber)
VALUES (5, 'Boleto bancário', 2000.00, 'Hospedagem em ryokan tradicional - 10 noites', 1, 'Anotação 13', 120.00, 60.00),
       (5, 'Boleto bancário', 800.00, 'Passeio pelo Templo Senso-ji e Mercado de Nakamise', 2, 'Anotação 14', 50.00,
        25.00),
       (5, 'Boleto bancário', 700.00, 'Aula de culinária japonesa com chef local', 3, 'Anotação 15', 50.00, 25.00);

