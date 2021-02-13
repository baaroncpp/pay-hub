ALTER TABLE core.t_account_grouping ADD COLUMN group_type VARCHAR(20) NOT NULL;

insert into core.t_roles(name,note)
 VALUES ('ROLE_COMPANY.READ','Reads all details concerning a company(s)'),
    ('ROLE_COMPANY.WRITE','Can create or update company details'),
    ('ROLE_COMPANY.DELETE','Can delete a company that has no agents attached'),
    ('ROLE_AGENT.WRITE','Can create or update an agent in the system'),
    ('ROLE_AGENT.READ','Reads all details of an agent or agents'),
    ('ROLE_AGENT.DELETE','Can delete an agent from the system'),
    ('ROLE_AGENT.APPROVE','Can approve the creation of a new agent in the system'),
    ('ROLE_PRODUCT.WRITE','Can create or update company details'),
    ('ROLE_PRODUCT.READ','Reads all details of a product in the system'),
    ('ROLE_PRODUCT.DELETE','Can delete a product from the system'),
    ('ROLE_PRODUCT.DISABLE','Can disable a product in the system and get to a state of no transactions'),
    ('ROLE_PRODUCT.ENABLE','Can re-enable a product in the system');