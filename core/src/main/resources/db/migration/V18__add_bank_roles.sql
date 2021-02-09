insert into core.t_roles(name,note)
 VALUES ('ROLE_BANK.WRITE','Can create a new bank account objects in the system'),
 ('ROLE_BANK.READ','Can read details of a bank in system including the account details'),
 ('ROLE_BANK_DEPOSIT.WRITE','Can create new deposit requests in the system for a given bank'),
 ('ROLE_BANK_DEPOSIT.CHECKER','Can verifies and approves bank deposit top ups to main account'),
 ('ROLE_BANK_ACCOUNT.LINK','Can link a bank account to a main account in payhub system'),
 ('ROLE_USER.WRITE','Can create a new user in the system'),
 ('ROLE_USER.READ','Can view all system users'),
 ('ROLE_USER.CHECKER','Can approve or reject a new user created in the system');


 ALTER TABLE core.t_user ADD COLUMN initial_password_reset BOOLEAN NOT NULL DEFAULT FALSE;


