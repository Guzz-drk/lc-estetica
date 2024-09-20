drop table if exists estetica.services_orders;

create table if not exists estetica.services_orders(
    order_id uuid not null,
    service_id uuid not null,
    PRIMARY KEY(order_id, service_id),
    FOREIGN KEY (order_id) REFERENCES estetica.customer_orders(id),
    FOREIGN KEY (service_id) REFERENCES estetica.services(id),
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);