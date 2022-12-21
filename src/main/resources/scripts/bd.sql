create database Proyecto;


use Proyecto;

create table origen(
id_origen int AUTO_INCREMENT,
descripcion VARCHAR(50),
estado char(1),
primary key(id_origen)
);

create table destino(
id_destino int AUTO_INCREMENT,
descripcion VARCHAR(50),
estado char(1),
primary key(id_destino)
);

create table ruta(
id_ruta int AUTO_INCREMENT,
nombre_ruta VARCHAR(100),
estado char(1),
primary key(id_ruta)
);


ALTER TABLE ruta
ADD id_origen int REFERENCES  origen(id_origen);


ALTER TABLE ruta
ADD id_destino int REFERENCES  destino(id_destino);

create table detalleruta(
ID_Detalle_Ruta int NOT NULL PRIMARY KEY AUTO_INCREMENT,
ID_RUTA NUMERIC(6) NOT NULL  REFERENCES  RUTA(ID_RUTA),
longitud int,
latitud int,
ESTADO CHAR(1 ) DEFAULT '1' NOT NULL

);




create table Vehiculo(
id_vehiculo int AUTO_INCREMENT,
placa VARCHAR(6),
modelo VARCHAR(50),
marca VARCHAR(50),
estado char(1),
primary key(id_vehiculo)
);



create table ROL(
ID_ROL int AUTO_INCREMENT,
NOMBRE VARCHAR(100),
estado char(1),
primary key(id_rol)
);

create table MENU(
ID_MENU int AUTO_INCREMENT,
NOMBRE VARCHAR(100),
estado char(1),
primary key(id_menu)
);

create table DETALLEROL(
ID_DETALLE_ROL int AUTO_INCREMENT,
id_menu int NOT NULL  REFERENCES  MENU(ID_MENU),
id_rol int NOT NULL  REFERENCES  ROL(ID_ROL),
estado char(1),
primary key(ID_DETALLE_ROL)
);

create table USUARIO(
ID_USUARIO int AUTO_INCREMENT,
NOMBRE VARCHAR(50),
APELLIDO VARCHAR(50),
DNI VARCHAR(8),
EMAIL VARCHAR(50),
USUARIO VARCHAR(50),
CLAVE VARCHAR(1000),
id_rol int NOT NULL  REFERENCES  ROL(ID_ROL),
ESTADO char(1),
primary key(ID_USUARIO)
);

insert into MENU(path,nombre,estado) values('ruta','Ruta','1');
insert into MENU(path,nombre,estado) values('buscaruta','Buscar Ruta','1');
insert into MENU(path,nombre,estado) values('mant-base','Mantenimiento de Bases','1');
insert into MENU(path,nombre,estado) values('mant-destino','Mantenimiento de Destinos','1');
insert into MENU(path,nombre,estado) values('mant-infracciones','Mantenimiento de Infracciones','1');
insert into MENU(path,nombre,estado) values('mant-papeleta','Mantenimiento de Papeletas','1');
insert into MENU(path,nombre,estado) values('mant-grupo-inspectores','Mantenimiento de Grupo de Inspectores','1');
insert into MENU(path,nombre,estado) values('reporte-papeleta','Reporte de Papeletas','1');
insert into MENU(path,nombre,estado) values('mant-usuario','Mantenimiento de Usuarios','1');

insert into ROL(nombre,estado) values('admin','1');



insert into DETALLEROL(id_menu,id_rol,estado) values(1,1,'1');
insert into DETALLEROL(id_menu,id_rol,estado) values(2,1,'1');
insert into DETALLEROL(id_menu,id_rol,estado) values(3,1,'1');
insert into DETALLEROL(id_menu,id_rol,estado) values(4,1,'1');
insert into DETALLEROL(id_menu,id_rol,estado) values(5,1,'1');
insert into DETALLEROL(id_menu,id_rol,estado) values(6,1,'1');
insert into DETALLEROL(id_menu,id_rol,estado) values(7,1,'1');
insert into DETALLEROL(id_menu,id_rol,estado) values(8,1,'1');
insert into DETALLEROL(id_menu,id_rol,estado) values(9,1,'1');

insert into Usuario(nombre,apellido,dni,email,usuario,clave,id_rol,estado) values('Fernando','Guzman','54982365',
'fernando@gmail.com','admin','123',1,'1');
