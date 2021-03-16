-- Tabla para el modulo de Administración de datos del cliente
CREATE TABLE cliente(
    id_cliente INTEGER NOT NULL,
    nombre CHARACTER VARYING (50) NOT NULL,
    apellido CHARACTER VARYING(50) NOT NULL,
    numero_documento CHARACTER VARYING(15) UNIQUE NOT NULL,
    tipo_documento CHARACTER VARYING (15) NOT NULL,
    nacionalidad CHARACTER VARYING(15) NOT NULL,
    email CHARACTER VARYING(15) NOT NULL,
    telefono CHARACTER VARYING(20) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY(id_cliente)

);
CREATE SEQUENCE cliente_sec;

CREATE TABLE conceptoPunto(
    id_conceptoPunto INTEGER NOT NULL,
    descripcion CHARACTER VARYING (250) NOT NULL,
    puntosRequeridos CHARACTER VARYING(15) NOT NULL,
    CONSTRAINT pk_conceptoPunto PRIMARY KEY(id_conceptoPunto)

);
CREATE SEQUENCE conceptoPunto_sec;

CREATE TABLE reglaPunto(
    id_reglaPunto INTEGER NOT NULL,
    limite_inferior INTEGER NOT NULL,
    limite_superior INTEGER NOT NULL,
    monto_equivalencia INTEGER NOT NULL,
    CONSTRAINT pk_reglaPunto PRIMARY KEY(id_reglaPunto)

);
CREATE SEQUENCE reglaPunto_sec;


CREATE TABLE bolsaPunto(


);
