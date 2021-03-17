# Sistema de Fidelización de Clientes
#### Descripción

Sistema que implementa la fidelización de clientes para hacer seguimientos de los puntos otorgados por operaciones de pago. 

Proyecto correspondiente al primer parcial de la asignatura de Programación Web Back-end. 

#### Herramientas utilizadas

**Integrated Development Environment :** IntelliJ IDEA 

**Lenguaje de Programación:** Java Enterprise Edition ( JEE ) 

**Gestor de Proyecto:** Maven 3.x

**Servidor de Aplicaciones:** Wildfly version-22.0.0. Final

**SGBD:** PostgreSQL version-13 y PgAdmin 4 

**Stack tecnológico JEE del proyecto:**

- Modelo y capa de persistencia: JPA Hibernate
- Capa de negocios : EJB 3 
- Capa de exposición o presentación de servicios RESTful: JAX-RS 

**Para testing de requests:** Postman y la herramienta integrada por IntelliJ IDEA

#### **Development team**

- Alex Gómez
- Victor Gonzalez 

#### **Módulos del proyecto**

- 

#### **How to : **

**Crear base de datos, tablas y relaciones**

Se tiene un script `scriptSFC.sql`que se debe ejecutar para la creación de la base de datos, tablas y relaciones en la base de datos. 

**Construir el proyecto**

Cuando el proyecto se ejecuta por primera vez es conveniente asegurarse de que las dependencias se instalan correctamente  utilizando, `mvn clean install`

Luego, para construir el proyecto solo se necesita ejecutar `mvn clean package`

**Servidor de Aplicaciones Wildfly**

1. Para el despliegue de la aplicación en un contenedor bajar el Servidor Wildfly https://www.wildfly.org/downloads/

2. Ir hasta el directorio `~/wildfly-22.0.0.Final/bin` y desde la línea de comandos  crearse un usuario administrador `add-user` y luego iniciar el servidor `standalone`.

3. Asegurarse de que en el archivo `standalone.xml` se encuentran los datos correctos para la conexión del `datasource`. 

4. Limpiar los directorios `standalone/deployments`, `standalone/data` y `standalone/tmp` 

5. Copiar el war generado por el package realizado en el paso anterior al directorio `standalone/deployments` .

6. Ejecutar el script de inicio del wildfly `sh standalone.sh`

   La aplicación se levantará en http://localhost:8080/SFC