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

## Autor

Sebastian Santalla
