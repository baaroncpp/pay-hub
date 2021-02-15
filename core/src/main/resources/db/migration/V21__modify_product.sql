CREATE TABLE core.t_product_category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    note text,
    created_on timestamp not null default now(),
    modified_on timestamp
);

CREATE TABLE core.t_provider (
    id VARCHAR(20) PRIMARY KEY,
    name TEXT,
    note TEXT
);

INSERT INTO core.t_provider
(id,name,note)
VALUES
('MTN_UG','MTN Uganda','Telecom services'),
('AIRTEL_UG','Airtel Uganda', 'Telecom services'),
('ORANGE_UG','Orange Uganda',' Telecom services'),
('UMEME_UG','UMEME Uganda','Electricity services'),
('NWSC','National Water and Sewerage cooperation','Water supply and sewer treatment'),
('CELLULANT','Cellulant','Integration services'),
('DSTV','DSTV','Pay TV services'),
('ZUKU','ZUKU','Pay TV and Internet services'),
('INTERSWITCH','Interswitch Uganda','Integration services');

ALTER TABLE core.t_product alter column category TYPE INTEGER USING category::integer;
ALTER TABLE core.t_product alter column category SET NOT NULL;
ALTER TABLE core.t_product ADD FOREIGN KEY (category) REFERENCES core.t_product_category(id);
ALTER TABLE core.t_product alter column root_provider TYPE VARCHAR(20);
ALTER TABLE core.t_product ADD FOREIGN KEY (root_provider) REFERENCES core.t_provider(id);
ALTER TABLE core.t_product alter column provider TYPE VARCHAR(20);
ALTER TABLE core.t_product ADD FOREIGN KEY (provider) REFERENCES core.t_provider(id);
ALTER TABLE core.t_product add column note text;
ALTER TABLE core.t_product alter column modified_by DROP NOT NULL;
ALTER TABLE core.t_product DROP CONSTRAINT t_product_name_key;

INSERT INTO t_product_category(name,note)
values
('Mobile Money','Mobile money services'),
('Airtime','Airtime vending'),
('Utilities','Water, TV, Electricity'),
('School Fees','School fees payments');


INSERT INTO core.t_product(
    name,
    provider,
    official_name,
    product_code,
    category,
    has_charge,
    has_tariff,
    root_provider,
    created_by
)
VALUES
('MTN_AIRTIME','MTN_UG','MTN Airtime','0000001',(SELECT id FROM core.t_product_category where name = 'Airtime'),true,false,'MTN_UG',1),
('MTN_AIRTIME','INTERSWITCH','MTN Airtime','0000002',(SELECT id FROM core.t_product_category where name = 'Airtime'),true,false,'MTN_UG',1),
('MTN_AIRTIME','CELLULANT','MTN Airtime','0000003',(SELECT id FROM core.t_product_category where name = 'Airtime'),true,false,'MTN_UG',1),
('AIRTEL_AIRTIME','AIRTEL_UG','Airtel Airtime','0000004',(SELECT id FROM core.t_product_category where name = 'Airtime'),true,false,'AIRTEL_UG',1),
('AIRTEL_AIRTIME','INTERSWITCH','Airtel Airtime','0000005',(SELECT id FROM core.t_product_category where name = 'Airtime'),true,false,'AIRTEL_UG',1),
('AIRTEL_AIRTIME','INTERSWITCH','Airtel Airtime','0000006',(SELECT id FROM core.t_product_category where name = 'Airtime'),true,false,'AIRTEL_UG',1),
('MTN_MOMO_CASHOUT','MTN_UG','MTN Mobile Money Cash out','0000007',(SELECT id FROM core.t_product_category where name = 'Mobile Money'),true,false,'MTN_UG',1),
('MTN_MOMO_CASHIN','MTN_UG','MTN Mobile Money Cash in','0000008',(SELECT id FROM core.t_product_category where name = 'Mobile Money'),true,false,'MTN_UG',1),
('AIRTEL_MOMO_CASHOUT','AIRTEL_UG','MTN Mobile Money Cash out','0000009',(SELECT id FROM core.t_product_category where name = 'Mobile Money'),true,false,'MTN_UG',1),
('AIRTEL_MOMO_CASHIN','AIRTEL_UG','MTN Mobile Money Cash in','0000010',(SELECT id FROM core.t_product_category where name = 'Mobile Money'),true,false,'MTN_UG',1),
('DSTV','INTERSWITCH','DSTV Subscription','0000015',(SELECT id FROM core.t_product_category where name = 'Utilities'),true,false,'DSTV',1),
('GOTV','INTERSWITCH','GOTV Subscription','0000016',(SELECT id FROM core.t_product_category where name = 'Utilities'),true,false,'DSTV',1),
('ZUKU','INTERSWITCH','GOTV Subscription','0000011',(SELECT id FROM core.t_product_category where name = 'Utilities'),true,false,'ZUKU',1),
('DSTV','CELLULANT','DSTV Subscription','0000012',(SELECT id FROM core.t_product_category where name = 'Utilities'),true,false,'DSTV',1),
('GOTV','CELLULANT','GOTV Subscription','0000013',(SELECT id FROM core.t_product_category where name = 'Utilities'),true,false,'DSTV',1),
('ZUKU','CELLULANT','GOTV Subscription','0000014',(SELECT id FROM core.t_product_category where name = 'Utilities'),true,false,'ZUKU',1);
