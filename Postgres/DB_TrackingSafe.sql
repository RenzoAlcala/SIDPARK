-- DB: TRACKINGSAFE
-- TLB: TABLA

create table tlbVehiculo(
codigo serial primary key,
conductor varchar(8) REFERENCES tlbConductor (dni),
placa varchar(6),
marca varchar(20),
modelo varchar(20),
año integer check (año>999 and año<=9999),
color varchar(20),
comentarios varchar(100)
);

insert into tlbVehiculo(conductor,placa,marca,modelo,año,color,comentarios) values('12345678','A2O602','Toyota','Corolla',2000,'Blanco','');

drop table tlbVehiculo
drop table tlbVehiculoHistorial
drop table tlbConductor

create table tlbVehiculoHistorial(
codigoVehiculo integer REFERENCES tlbVehiculo (codigo),
fecha timestamp,
velocidad integer,
accx real,
accy real,
accz real,
longitud real,
latitud real
);

insert into tlbConductor values ('Juanito','Perez Perez','12345678',22,'ABCDEFGHI')

create table tlbConductor(
nombres varchar(20),
apellidos varchar(20),
dni varchar(8) primary key,
edad integer,
licencia varchar(9)
)




