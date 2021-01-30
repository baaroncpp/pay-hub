ALTER TABLE core.t_terms_and_conditions ADD COLUMN target VARCHAR(10) NOT NULL;
ALTER TABLE core.t_terms_and_conditions DROP CONSTRAINT t_terms_and_conditions_version_no_key;
ALTER TABLE core.t_terms_and_conditions ADD CONSTRAINT t_terms_and_conditions_version_no_key UNIQUE (version_no,target);

INSERT INTO core.t_terms_and_conditions (version_no,content,is_active,target,created_by,created_on)
values
('V1.0','This is temporary content, replace with html',true,'USER',1,now()),
('V1.0','This is temporary content, replace with html',true,'AGENT',1,now());

ALTER TABLE core.t_user_meta ADD COLUMN terms_and_condition_id INTEGER REFERENCES core.t_terms_and_conditions(id);
ALTER TABLE core.t_user ADD COLUMN approved BOOLEAN NOT NULL DEFAULT true;

CREATE TABLE core.t_user_approval
    (id BIGSERIAL PRIMARY KEY,
     user_id INTEGER,
     status VARCHAR NOT NULL DEFAULT 'PENDING',
     created_on TIMESTAMP NOT NULL DEFAULT now(),
     modified_on TIMESTAMP,
     created_by INTEGER REFERENCES core.t_user(id),
     modified_by INTEGER references core.t_user(id));


ALTER TABLE core.t_user ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE core.t_user ADD COLUMN approved_by INTEGER REFERENCES core.t_user(id);
