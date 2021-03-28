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
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente)
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
    VALUES(6,'Jorge','Ferrari','3658987','Cedula de Identidad Civil','Paraguaya','jorge1234@hotmail.com','0986582324','1992-07-24');

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

CREATE TABLE conceptoUsoPunto
(
    id_conceptoUsoPunto     INTEGER                NOT NULL,
    descripcion_concepto CHARACTER VARYING(200) NOT NULL,
    punto_requerido    INTEGER                NOT NULL,
    CONSTRAINT pk_conceptoUsoPunto PRIMARY KEY (id_conceptoUsoPunto)
);
CREATE SEQUENCE conceptoUsoPunto_sec;

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(1,'Saldo para Hablar',200);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(2,'SMS gratis por 12 horas',80);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(3,'Alquila 1 pelicula por 48 hrs.',100);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(4,'Pack de internet',15);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(5,'Descuentos en Abonos de TV cable',300);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(6,'Descuento en Servicio Técnico',900);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(7,'Pack de internet',15);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(8,'Descuento en Equipos',1500);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(9,'Vale de premio',30);

INSERT INTO conceptoUsoPunto(id_conceptoUsoPunto, descripcion_concepto, punto_requerido)
    VALUES(10,'Pack de redes sociales gratis por 1 semana',500);


----------------------------------------------------------------------------------------------------------------------
-- Tabla para el modulo de Administración de reglas de asignación de puntos
/*
    Cantidad de consumisión al mes 
    0 a 50000                       1 punto cada 5000 
    50001 a 100000                  1 punto cada 3000
    100001 a 200000                 1 punto cada 1000
*/
CREATE TABLE reglaAsignacionPunto
(
    id_reglaAsignacionPunto      INTEGER NOT NULL,
    limite_inferior    INTEGER NOT NULL,
    limite_superior    INTEGER NOT NULL,
    monto_equivalencia INTEGER NOT NULL,
    CONSTRAINT pk_reglaAsignacionPunto PRIMARY KEY (id_reglaAsignacionPunto)        
);
CREATE SEQUENCE reglaAsignacionPunto_sec;

INSERT INTO reglaAsignacionPunto(id_reglaAsignacionPunto, limite_inferior, limite_superior, monto_equivalencia)
    VALUES(1,0,50000,5000);

INSERT INTO reglaAsignacionPunto(id_reglaAsignacionPunto, limite_inferior, limite_superior, monto_equivalencia)
	VALUES(2,50001,100000,3000);

INSERT INTO reglaAsignacionPunto(id_reglaAsignacionPunto, limite_inferior, limite_superior, monto_equivalencia)
    VALUES(3,100001,200000,1000);


----------------------------------------------------------------------------------------------------------------------


CREATE TABLE vencimientoPunto
(
    id_vencimientoPunto INTEGER NOT NULL,
    fechaInicioValidez  DATE    NOT NULL,
    fechaFinValidez     DATE    NOT NULL,
    duracionDiasPuntaje INTEGER NOT NULL,
    --idReglaPunto,
    CONSTRAINT pk_vencimientoPunto PRIMARY KEY (id_vencimientoPunto)--,
    /*CONSTRAINT fk_idReglaPunto 
        FOREIGN KEY (idReglaPunto) REFERENCES reglaPunto(id_reglaPuntos) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION*/
);
CREATE SEQUENCE vencimientoPunto_sec;

INSERT INTO vencimientoPunto(id_vencimientoPunto, fechaInicioValidez, fechaFinValidez, duracionDiasPuntaje)--, idReglaPunto)
    VALUES(1,'2021-01-1','2021-06-30',60);--,1);

INSERT INTO vencimientoPunto(id_vencimientoPunto, fechaInicioValidez, fechaFinValidez, duracionDiasPuntaje)--, idReglaPunto)
    VALUES(2,'2021-07-1','2021-08-31',15);--,4);

INSERT INTO vencimientoPunto(id_vencimientoPunto, fechaInicioValidez, fechaFinValidez, duracionDiasPuntaje)--, idReglaPunto)
    VALUES(3,'2021-09-01','2021-10-31',25);--,2);

INSERT INTO vencimientoPunto(id_vencimientoPunto, fechaInicioValidez, fechaFinValidez, duracionDiasPuntaje)--, idReglaPunto)
    VALUES(4,'2021-11-01','2021-12-31',18);--,3);

----------------------------------------------------------------------------------------------------------------------

CREATE TABLE bolsaPunto
(
    id_bolsaPunto            INTEGER NOT NULL,
    id_cliente               INTEGER NOT NULL,
    puntos                   INTEGER NOT NULL,
    fecha_asignacion_puntaje DATE    NOT NULL,
    fecha_caducidad_puntaje  DATE    NOT NULL,
    puntaje_asignado         INTEGER NOT NULL,
    puntaje_utilizado        INTEGER NOT NULL,
    saldo_puntos             INTEGER NOT NULL,
    monto_operacion          INTEGER NOT NULL,
    CONSTRAINT pk_idbolsaPunto PRIMARY KEY (id_bolsaPunto),
    CONSTRAINT fk_puntos       FOREIGN KEY (puntos)
        REFERENCES reglaPunto (id_reglaPunto) 
        ON UPDATE CASCADE 
        ON DELETE CASCADE,
    CONSTRAINT fk_idcliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE SEQUENCE bolsaPunto_sec;

CREATE TABLE usoPunto
(
    id_usoPunto       INTEGER                NOT NULL,
    id_cliente        INTEGER                NOT NULL,
    puntaje_utilizado INTEGER                NOT NULL,
    fecha_usoPunto    DATE                   NOT NULL,
    concepto_uso      INTEGER				 NOT NULL,
    CONSTRAINT pk_idUsoPunto PRIMARY KEY (id_usoPunto),
    CONSTRAINT fk_idcli FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente)
        ON DELETE NO ACTION,
    CONSTRAINT fk_conceptoUso FOREIGN KEY (concepto_uso) 
    	REFERENCES conceptoPunto (id_conceptoPunto)
    	ON DELETE NO ACTION
);
CREATE SEQUENCE usoPunto_sec;

CREATE TABLE detUsoPunto
(
    id_detUsoPunto INTEGER NOT NULL,
    id_usoPunto INTEGER NOT NULL, -- relaci? OneToOne 
    id_bolsa_puntos INTEGER NOT NULL, -- relaci? OneToOne
    puntaje_utilizado INTEGER NOT NULL,
    CONSTRAINT pk_detUsoPuntos PRIMARY KEY (id_detUsoPunto),
    CONSTRAINT fk_idusoPunto FOREIGN KEY (id_usoPunto) REFERENCES usoPunto (id_usoPunto) ON DELETE CASCADE,
    CONSTRAINT fk_idbolsaPunto FOREIGN KEY (id_bolsa_puntos) REFERENCES bolsaPunto (id_bolsaPunto) ON DELETE CASCADE
);

CREATE SEQUENCE detUsopunto_sec;

-- ANOTACIONES
-- CHARACTER VARYING(n) == VARCHAR(n) | CHARACTER (n) == CHAR(n)
