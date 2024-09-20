create table if not exists estetica.categories(
    id uuid primary key not null unique,
    description varchar not null, 
    details text, 
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table if not exists estetica.services(
    id uuid primary key not null unique,
    description varchar not null,
    details text, 
    price numeric(15,2) not null,
    category_id uuid not null,
    FOREIGN KEY (category_id) REFERENCES estetica.categories(id),
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);