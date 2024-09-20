CREATE SEQUENCE if not exists estetica.order_sequence START 1;

create table if not exists estetica.customer_orders(
    id uuid primary key not null unique,
    sequence_order varchar not null unique,
    client_id uuid not null,
    FOREIGN KEY (client_id) REFERENCES estetica.clients(id),
    total_price numeric(15,2) not null,
    details text, 
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table if not exists estetica.services_orders(
    order_id uuid primary key not null,
    FOREIGN KEY (order_id) REFERENCES estetica.customer_orders(id),
    service_id uuid not null,
    FOREIGN KEY (service_id) REFERENCES estetica.services(id),
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);