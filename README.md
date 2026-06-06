# Sistema ServiMant

Prototipo académico del sistema de gestión de reclamos y solicitudes de mantenimiento interno para la materia **Seminario de Prácticas Informáticas**.

## Descripción

El sistema ServiMant permite centralizar la gestión de solicitudes de mantenimiento, incluyendo:

- inicio de sesión de usuarios;
- registro de solicitudes;
- consulta/listado de solicitudes;
- asignación de técnicos responsables;
- registro de intervenciones técnicas;
- actualización de estados;
- consulta de historial básico.

## Tecnologías utilizadas

- Java
- MySQL
- JDBC
- Maven
- SQL

## Estructura del proyecto

- `src/main/java/modelo`: clases principales del dominio.
- `src/main/java/servicio`: clases de lógica de negocio.
- `src/main/java/dao`: clases de acceso a datos y conexión JDBC.
- `src/main/java/vista`: pantallas simples por consola.
- `src/main/java/principal`: clase principal de ejecución.
- `database`: scripts SQL.
- `diagramas`: carpeta sugerida para imágenes de diagramas.

## Aplicación de Programación Orientada a Objetos

El prototipo incorpora conceptos fundamentales de la Programación Orientada a Objetos mediante la organización del código en clases, paquetes y capas de responsabilidad.

Para reforzar la abstracción y evitar la repetición de atributos comunes, se incorporó la clase abstracta `Persona`, ubicada en el paquete `modelo`. Esta clase concentra datos generales como nombre, apellido, email y estado activo.

A partir de esta clase se derivan las clases `Usuario` y `Tecnico`, aplicando herencia. De esta manera, ambas clases reutilizan la estructura común definida en `Persona`, pero conservan sus atributos específicos, como el nombre de usuario y rol en el caso de `Usuario`, y la especialidad en el caso de `Tecnico`.

También se aplica polimorfismo mediante el método abstracto `obtenerDescripcion()`, que es implementado de manera diferente por cada subclase. En `Usuario`, la descripción se orienta al rol dentro del sistema, mientras que en `Tecnico`, se orienta a la especialidad técnica.

Además, el sistema mantiene el encapsulamiento mediante atributos privados o protegidos y el uso de métodos getters y setters para acceder y modificar los datos de los objetos.

## Base de datos

Ejecutar el script:

```sql
database/servimant_db.sql
```

Este script crea la base de datos `servimant_db`, las tablas principales y datos iniciales.

## Configuración de conexión

La conexión se configura en:

```text
src/main/java/dao/ConexionBD.java
```

Valores por defecto:

```java
private static final String URL = "jdbc:mysql://localhost:3306/servimant_db";
private static final String USUARIO = "root";
private static final String PASSWORD = "";
```

Modificar usuario o contraseña si corresponde.

## Ejecución

Desde un IDE Java, ejecutar la clase:

```text
principal.Main
```

O desde consola, con Maven instalado:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="principal.Main"
```

## Usuarios de prueba

| Usuario | Contraseña | Rol |
|---|---|---|
| mgomez | 1234 | Usuario solicitante |
| cperez | 1234 | Coordinador |
| aruiz | 1234 | Administrador |

## Funcionalidades validadas

Durante la prueba del prototipo se verificó el flujo principal del sistema:

1. inicio de sesión de usuario;
2. listado de solicitudes existentes;
3. registro de una nueva solicitud;
4. asignación de técnico responsable;
5. registro de intervención técnica;
6. consulta del historial de intervenciones;
7. actualización del estado de la solicitud.

Estas funcionalidades permiten demostrar el ciclo principal de atención de una solicitud dentro del sistema ServiMant.

## Autor

Sebastian Santalla
