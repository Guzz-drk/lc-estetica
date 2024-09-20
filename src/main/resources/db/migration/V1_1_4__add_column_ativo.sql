alter table if exists estetica.clients add column active boolean default true;
alter table if exists estetica.services add column active boolean default true;
alter table if exists estetica.categories add column active boolean default true;
alter table if exists estetica.customer_orders add column active boolean default true;