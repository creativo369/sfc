DROP DATABASE  sfc;
CREATE DATABASE sfc owner postgres;

-- Tabla para el modulo de Administración de datos del cliente
CREATE TABLE cliente
(
    id_cliente       INTEGER                      NOT NULL,
    nombre           CHARACTER VARYING(50)        NOT NULL,
    apellido         CHARACTER VARYING(50)        NOT NULL,
    numero_documento CHARACTER VARYING(15) UNIQUE NOT NULL,
    tipo_documento   CHARACTER VARYING(80)        NOT NULL,
    nacionalidad     CHARACTER VARYING(15)        NOT NULL,
    email            CHARACTER VARYING(60)        NOT NULL,
    telefono         CHARACTER VARYING(20)        NOT NULL,
    fecha_nacimiento DATE                         NOT NULL,
    CONSTRAINT pk_idcliente PRIMARY KEY (id_cliente)
);
CREATE SEQUENCE cliente_sec;

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(1,'Victor','Gonzalez','5528793','Cedula de Identidad Civil','Paraguaya','geekpy@hotmail.com','0976175870','1994-06-03');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(2,'Juan','Gonzalez','4321258','Cédula de Identidad Civil','Paraguaya','juancito8@hotmail.com','0983253941','1970-05-24');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(3,'Maria','Fleitas','1354789','Cedula de Identidad Civil','Paraguaya','marifle@hotmail.com','0976456955','1990-08-12');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(4,'Jose','Martinez','36821455','Cedula de Identidad Civil','Paraguaya','jM@gmail.com','0985653214','1992-03-22');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(5,'Marcelo','Britez','6357846','Cedula de Identidad Civil','Paraguaya','marcelitob@hotmail.com','0961425368','1990-09-23');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(6,'Jorge','Jara','3658987','Cedula de Identidad Civil','Paraguaya','jorge1234@hotmail.com','0986582324','1992-07-24');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(7,'Fatima','Lopez','4326538','Cedula de Identidad Civil','Paraguaya','falope@hotmail.com','0986741852','1997-04-25');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(8,'Pablo','Villamayor','4567892','Cedula de Identidad Civil','Paraguaya','pabloV@hotmail.com','0961532478','1996-07-07');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(9,'Karina','Britos','3265412','Cedula de Identidad Civil','Paraguaya','karinaBr@hotmail.com','0986785412','1998-10-24');

INSERT INTO cliente(id_cliente, nombre, apellido, numero_documento, tipo_documento, nacionalidad, email, telefono, fecha_nacimiento)
    VALUES(10,'Daniel','Garcia','4235687','Cedula de Identidad Civil','Paraguaya','DaniGa@hotmail.com','0974321951','1999-12-03');


----------------------------------------------------------------------------------------------------------------------
-- Tabla para el modulo de Administración de conceptos de uso de puntos

CREATE TABLE conceptoPunto
(
    id_conceptoPunto     INTEGER                NOT NULL,
    descripcion_concepto CHARACTER VARYING(200) NOT NULL,
    puntos_requeridos    INTEGER                NOT NULL,
    CONSTRAINT pk_conceptoPunto PRIMARY KEY (id_conceptoPunto)
);
CREATE SEQUENCE conceptoPunto_sec;

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(1,'Saldo para Hablar',200);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(2,'SMS gratis por 12 horas',80);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(3,'Alquila 1 pelicula por 48 hrs.',100);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(4,'Pack de internet',15);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(5,'Descuentos en Abonos de TV cable',300);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(6,'Descuento en Servicio Técnico',900);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(7,'Pack de internet',15);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(8,'Descuento en Equipos',1500);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(9,'Vale de premio',30);

INSERT INTO conceptoPunto(id_conceptoPunto, descripcion_concepto, puntos_requeridos)
    VALUES(10,'Pack de redes sociales gratis por 1 semana',500);


----------------------------------------------------------------------------------------------------------------------
-- Tabla para el modulo de Administración de reglas de asignación de puntos
/*
    Cantidad de consumisión al mes 
    0 a 50000                       1 punto cada 5000 
    50001 a 100000                  1 punto cada 3000
    100001 a 200000                 1 punto cada 1000
*/
CREATE TABLE reglaPunto
(
    id_reglaPunto      INTEGER NOT NULL,
    limite_inferior    INTEGER NOT NULL,
    limite_superior    INTEGER NOT NULL,
    monto_equivalencia INTEGER NOT NULL,
    id_vencimiento     INTEGER NOT NULL,
    CONSTRAINT pk_reglaPunto PRIMARY KEY (id_reglaPunto),
    CONSTRAINT fk_id_vencimiento
    FOREIGN KEY (id_vencimiento) REFERENCES vencimientoPunto(id_vencimiento) ON DELETE CASCADE;
    
);
CREATE SEQUENCE reglaPunto_sec;

INSERT INTO reglaPunto(id_reglaPunto, limite_inferior, limite_superior, monto_equivalencia,)
    VALUES(1,0,50000,1,);

INSERT INTO reglaPunto(id_reglaPunto, limite_inferior, limite_superior, monto_equivalencia,)
    VALUES(2,50001,100000,1,);

INSERT INTO reglaPunto(id_reglaPunto, limite_inferior, limite_superior, monto_equivalencia,)
    VALUES(3,100001,200000,1,);

----------------------------------------------------------------------------------------------------------------------


CREATE TABLE vencimientoPunto
(
    id_vencimientoPunto INTEGER NOT NULL,
    fechaInicioValidez  DATE    NOT NULL,
    fechaFinValidez     DATE    NOT NULL,
    duracionDiasPuntaje INTEGER NOT NULL,
    CONSTRAINT pk_vencimientoPunto PRIMARY KEY (id_vencimientoPunto)
);
CREATE SEQUENCE vencimientoPunto_sec;


----------------------------------------------------------------------------------------------------------------------


CREATE TABLE bolsaPunto
(
    id_bolsaPunto            INTEGER NOT NULL,
    id_cliente               INTEGER NOT NULL,
    fecha_asignacion_puntaje DATE    NOT NULL,
    fecha_caducidad_puntaje  DATE    NOT NULL,
    puntaje_asignado         INTEGER NOT NULL,
    puntaje_utilizado        INTEGER NOT NULL,
    saldo_puntos             INTEGER NOT NULL,
    monto_operacion          INTEGER NOT NULL,
    CONSTRAINT pk_idbolsaPunto PRIMARY KEY (id_bolsaPunto),
    CONSTRAINT fk_idcliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente)
        ON DELETE CASCADE
);
CREATE SEQUENCE bolsaPunto_sec;

CREATE TABLE usoPunto
(
    id_usoPunto       INTEGER                NOT NULL,
    id_cliente        INTEGER                NOT NULL,
    puntaje_utilizado INTEGER                NOT NULL,
    fecha_usoPunto    DATE                   NOT NULL,
    concepto_uso      CHARACTER VARYING(200) NOT NULL,
    CONSTRAINT pk_idUsoPunto PRIMARY KEY (id_usoPunto),
    CONSTRAINT fk_idcliente2 FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente)
        ON DELETE CASCADE
);
CREATE SEQUENCE usoPunto_sec;


-- ANOTACIONES
-- CHARACTER VARYING(n) == VARCHAR(n) | CHARACTER (n) == CHAR(n)
