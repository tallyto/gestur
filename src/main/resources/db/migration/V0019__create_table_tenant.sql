CREATE TABLE IF NOT EXISTS public.tenants
(
    id    SERIAL PRIMARY KEY,
    domain VARCHAR(255) UNIQUE NOT NULL,
    name         VARCHAR(255)        NOT NULL,
    email        VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    address      TEXT,
    created_at   TIMESTAMP NOT NULL ,
    updated_at   TIMESTAMP NOT NULL
);
