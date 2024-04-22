ALTER TABLE item_venda ADD forma_pagamento VARCHAR(50);
ALTER TABLE item_venda ADD status_pagamento VARCHAR(50);
ALTER TABLE item_venda ADD valor_total DECIMAL(10,2);
ALTER TABLE item_venda ADD valor_pago DECIMAL(10, 2);