ALTER TABLE core.t_product_commission ADD COLUMN tariff_group_id VARCHAR(20);
ALTER TABLE core.t_product_commission ADD COLUMN from_amount NUMERIC;
ALTER TABLE core.t_product_commission ADD COLUMN to_amount NUMERIC;
ALTER TABLE core.t_product_commission ADD COLUMN name VARCHAR(50) NOT NULL UNIQUE;
ALTER TABLE core.t_product_commission ADD COLUMN note TEXT;

ALTER TABLE core.t_product_commission_template ADD COLUMN tariff_group_id VARCHAR(20);
ALTER TABLE core.t_product_commission_template ADD COLUMN from_amount NUMERIC;
ALTER TABLE core.t_product_commission_template ADD COLUMN to_amount NUMERIC;
ALTER TABLE core.t_product_commission_template ADD COLUMN name VARCHAR(50) NOT NULL UNIQUE;
ALTER TABLE core.t_product_commission_template ADD COLUMN note TEXT;

ALTER TABLE core.t_product_charge ADD COLUMN currency CHAR(3);
ALTER TABLE core.t_product_charge ADD COLUMN note TEXT;


