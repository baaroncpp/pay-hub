CREATE TABLE core.t_roles(id SERIAL PRIMARY KEY, name VARCHAR(30) UNIQUE NOT NULL,note TEXT,created_on TIMESTAMP NOT NULL DEFAULT now(), modified_on TIMESTAMP);

INSERT INTO core.t_roles(name,note)
VALUES
('ROLE_USER.WRITE','Create new and update user details in system'),
('ROLE_USER.CHECKER','Approve creation of new user in the system'),
('ROLE_USER.READ','Read details of user in the system'),
('ROLE_ACCOUNT.WRITE','Create new accounts in the system and update accounts'),
('ROLE_ACCOUNT.READ','Read details of an account in the system');