create table if not exists estetica.clients(
    id uuid primary key not null unique,
    name varchar not null,
    cpf varchar,
    foneNumberAreaCode varchar,
    foneNumber varchar,
    mail varchar,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);