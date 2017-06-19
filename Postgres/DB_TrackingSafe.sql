-- DB: TRACKINGSAFE
-- TLB: TABLA

create table tlbVehiculo(
marca varchar(20),
modelo varchar(20),
año integer check (año>99999 and año<=9999999),
color varchar(20),
comentarios varchar(100)
);

