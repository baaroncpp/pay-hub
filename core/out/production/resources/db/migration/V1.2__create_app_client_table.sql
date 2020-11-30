CREATE TABLE t_app_client (

    id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE,
    secret TEXT,
    is_secret_required BOOLEAN NOT NULL DEFAULT FALSE,
    is_scoped BOOLEAN NOT NULL DEFAULT FALSE,
    scope TEXT,
    grant_types TEXT NOT NULL,
    redirect_uri TEXT,
    authorities TEXT,
    token_validity INTEGER NOT NULL,
    is_enabled BOOLEAN NOT NULL DEFAULT TRUE,
    disabled_on TIMESTAMP,
    note TEXT,
    created_on TIMESTAMP NOT NULL DEFAULT now(),
    modified_on TIMESTAMP

);


INSERT INTO t_app_client (name,secret,grant_types,token_validity,scope) VALUES
('client','$2y$12$2/MUgBJL46KHZiTOaxz5WeLfhLn6IQsaGH/393KxZpx/3VYXxZy4y','password,refresh_token',120,'read');