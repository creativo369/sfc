DROP DATABASE  sfc;
CREATE DATABASE sfc owner  postgres;

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
    VALUES(1,"Victor","Gonzalez","5528793","Cedula de Identidad Civil","Paraguaya","geekpy@hotmail.com","0976175870","1994-06-03");


----------------------------------------------------------------------------------------------------------------------



CREATE TABLE conceptoPunto
(
    id_conceptoPunto     INTEGER                NOT NULL,
    descripcion_concepto CHARACTER VARYING(200) NOT NULL,
    puntos_requeridos    INTEGER                NOT NULL,
    CONSTRAINT pk_conceptoPunto PRIMARY KEY (id_conceptoPunto)
);
CREATE SEQUENCE conceptoPunto_sec;

CREATE TABLE reglaPunto
(
    id_reglaPunto      INTEGER NOT NULL,
    limite_inferior    INTEGER NOT NULL,
    limite_superior    INTEGER NOT NULL,
    monto_equivalencia INTEGER NOT NULL,
    CONSTRAINT pk_reglaPunto PRIMARY KEY (id_reglaPunto)
);
CREATE SEQUENCE reglaPunto_sec;

CREATE TABLE vencimientoPunto
(
    id_vencimientoPunto INTEGER NOT NULL,
    fechaInicioValidez  DATE    NOT NULL,
    fechaFinValidez     DATE    NOT NULL,
    duracionDiasPuntaje INTEGER NOT NULL,
    CONSTRAINT pk_vencimientoPunto PRIMARY KEY (id_vencimientoPunto)
);
CREATE SEQUENCE vencimientoPunto_sec;

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