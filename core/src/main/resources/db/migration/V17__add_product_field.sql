ALTER TABLE core.t_product ADD COLUMN root_provider VARCHAR(50) NOT NULL;

comment on column t_product.root_provider is 'The final service provider of this service';

ALTER TABLE core.t_product_commission ADD COLUMN system_flat_amount numeric;
ALTER TABLE core.t_product_commission ADD COLUMN system_percent_amount numeric;
ALTER TABLE core.t_product_commission ADD COLUMN system_tariff numeric;
ALTER TABLE core.t_product_commission ADD COLUMN tariff numeric;

ALTER TABLE core.t_product_commission_template ADD COLUMN system_flat_amount numeric;
ALTER TABLE core.t_product_commission_template ADD COLUMN system_percent_amount numeric;
ALTER TABLE core.t_product_commission_template ADD COLUMN system_tariff numeric;
ALTER TABLE core.t_product_commission_template ADD COLUMN tariff numeric;

ALTER TABLE core.t_product_charge ADD COLUMN system_flat_amount numeric;
ALTER TABLE core.t_product_charge ADD COLUMN system_percent_amount numeric;
ALTER TABLE core.t_product_charge ADD COLUMN system_tariff numeric;
ALTER TABLE core.t_product_charge ADD COLUMN tariff numeric;


ALTER TABLE core.t_account_mapping ADD COLUMN system_account BOOLEAN NOT NULL DEFAULT false;
ALTER TABLE core.t_account_mapping ADD COLUMN agent_id_commission BIGINT;