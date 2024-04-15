CREATE TABLE venda (
                       id SERIAL PRIMARY KEY,
                       cliente_id INT,
                       valor_pago DECIMAL(10, 2),
                       valor_repassado DECIMAL(10, 2),
                       forma_pagamento VARCHAR(50),
                       comissao_rav DECIMAL(10, 2),
                       comissao_markup DECIMAL(10, 2),
                       desconto_aplicado DECIMAL(5, 2),
                       FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
