INSERT INTO regiones(id,nombre) VALUES(1, 'Sudamérica');
INSERT INTO regiones(id,nombre) VALUES(2, 'Centroamérica');
INSERT INTO regiones(id,nombre) VALUES(3, 'Norteamérica');
INSERT INTO regiones(id,nombre) VALUES(4, 'Europa');
INSERT INTO regiones(id,nombre) VALUES(5, 'Asia');
INSERT INTO regiones(id,nombre) VALUES(6, 'Africa');
INSERT INTO regiones(id,nombre) VALUES(7, 'Oceanía');
INSERT INTO regiones(id,nombre) VALUES(8, 'Antartida');


INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Andres','Guzmnan', 'aguzman@gmail.com','2018-01-01',1);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Mr Jhhn','Doe', 'jhon@gmail.com','2018-01-02',2);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Juan','Reyes', 'jreyes@gmail.com','2018-01-03',4);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Natasha','claush', 'nclaush@gmail.com','2018-01-04',4);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Miram','perez', 'mperez@gmail.com','2018-01-05',5);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Clara','Restrepo', 'crestrep@gmail.com','2018-01-06',5);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Margarita','Tabares', 'mtabares@gmail.com','2018-01-07',6);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Luis','Escobar', 'ldescobar@gmail.com','2018-01-08',7);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Gloria','Ortiz', 'gortiz@gmail.com','2018-01-09',8);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Carmen','Guzmnan', 'cguzman@gmail.com','2018-01-10',6);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Cristhian','Tamayo', 'ctamayo@gmail.com','2018-01-11',7);
INSERT INTO clientes(nombre,apellido,email,create_at,region_id) VALUES('Melanie','Cuellar', 'mcuellar@gmail.com','2018-01-12',1);

/*Creamos algunos usuarios con sus roles*/

INSERT INTO usuarios (username, password, enabled) VALUES ('andres','$2a$10$kggo7GQVkFUpTRH5057ZFe2DVQIZ/U9e/oXtp/7Lj/ZxKxz0nDC9i',1)
INSERT INTO usuarios (username, password, enabled) VALUES ('admin','$2a$10$eIdmm/pXoFbq5gYOMYUPBey6GaI/o58m7iWu.Nc1ZFcJtnRoT1llq',1)

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,1);