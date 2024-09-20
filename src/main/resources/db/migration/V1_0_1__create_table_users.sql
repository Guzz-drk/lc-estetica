CREATE TABLE IF NOT EXISTS estetica.users(
    id uuid primary key not null unique,
    name varchar not null,
    login varchar not null unique,
    password varchar not null, 
    mail varchar,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);