# Sistema ServiMant

Prototipo académico de un sistema de gestión de solicitudes de mantenimiento interno, desarrollado para la materia **Seminario de Prácticas Informáticas**.

## Descripción

ServiMant permite centralizar el registro, seguimiento y resolución de solicitudes de mantenimiento realizadas por los distintos sectores de una organización.

El prototipo fue desarrollado en Java y utiliza una base de datos MySQL para almacenar usuarios, solicitudes, técnicos, asignaciones e intervenciones.

## Funcionalidades

El sistema permite:

* iniciar sesión mediante usuario y contraseña;
* registrar solicitudes de mantenimiento;
* listar las solicitudes en orden ascendente;
* asignar un técnico responsable;
* registrar intervenciones técnicas;
* actualizar el estado de una solicitud;
* consultar el historial de intervenciones;
* cerrar solicitudes que posean intervenciones registradas;
* consultar un resumen de solicitudes agrupadas por estado.

## Tecnologías utilizadas

* Java 20
* MySQL
* JDBC
* Maven
* SQL
* Visual Studio Code
* phpMyAdmin
* Draw.io

## Arquitectura del proyecto

El sistema fue organizado mediante una arquitectura por capas:

* **principal:** contiene la clase de inicio del sistema.
* **vista:** administra la interacción con el usuario mediante consola.
* **servicio:** implementa las reglas y validaciones de negocio.
* **dao:** realiza el acceso y la persistencia de datos.
* **modelo:** contiene las entidades principales del dominio.
* **database:** contiene el script de creación de la base de datos.
* **diagramas:** contiene la documentación UML y el DER.

## Patrón de diseño DAO

El proyecto aplica el patrón **Data Access Object (DAO)** para separar la lógica de negocio de las operaciones realizadas sobre la base de datos.

Las clases DAO encapsulan las consultas SQL y utilizan `ConexionBD` para obtener conexiones JDBC con MySQL.

Entre las clases implementadas se encuentran:

* `UsuarioDAO`
* `SolicitudDAO`
* `TecnicoDAO`
* `IntervencionDAO`
* `ConexionBD`

Esta separación facilita el mantenimiento del código, reduce el acoplamiento y permite modificar la forma de persistencia sin afectar directamente a las vistas o a las reglas de negocio.

## Programación Orientada a Objetos

El prototipo incorpora los siguientes conceptos:

### Abstracción y herencia

La clase abstracta `Persona` reúne atributos comunes como nombre, apellido, correo electrónico y estado activo.

Las clases `Usuario` y `Tecnico` heredan de `Persona` y desarrollan sus características específicas.

### Polimorfismo

El método abstracto `obtenerDescripcion()` es implementado de manera diferente por `Usuario` y `Tecnico`.

### Encapsulamiento

Los atributos se mantienen privados o protegidos y se administran mediante métodos de acceso.

## Uso de arreglos y ArrayList

La funcionalidad de resumen por estado utiliza ambas estructuras de forma complementaria:

* un arreglo `String[]` almacena los estados posibles;
* un arreglo `int[]` almacena las cantidades correspondientes;
* un `ArrayList<Solicitud>` contiene dinámicamente las solicitudes recuperadas desde MySQL.

El reporte muestra la cantidad de solicitudes pendientes, asignadas, en proceso, resueltas y cerradas.

## Manejo de excepciones

El sistema controla diferentes situaciones mediante excepciones:

* `NumberFormatException` para entradas no numéricas;
* `IllegalArgumentException` para datos inválidos;
* `IllegalStateException` para reglas de negocio, como intentar cerrar una solicitud sin intervenciones;
* `SQLException` para errores de interacción con MySQL.

Esto evita cierres inesperados del programa y permite informar claramente cada inconveniente al usuario.

## Base de datos

El script principal se encuentra en:

```text
database/servimant_db.sql
```

El archivo:

* crea la base `servimant_db`;
* utiliza codificación `utf8mb4`;
* crea las tablas y relaciones;
* incorpora datos iniciales;
* configura claves primarias, foráneas y valores autoincrementales.

Para instalar la base:

1. Iniciar MySQL desde XAMPP.
2. Abrir phpMyAdmin.
3. Seleccionar la opción **Importar**.
4. Importar `database/servimant_db.sql`.

## Configuración JDBC

La conexión se configura en:

```text
src/main/java/dao/ConexionBD.java
```

Configuración predeterminada:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/servimant_db"
        + "?useUnicode=true"
        + "&characterEncoding=UTF-8"
        + "&connectionCollation=utf8mb4_unicode_ci";

private static final String USUARIO = "root";
private static final String PASSWORD = "";
```

Los valores deben adaptarse si la instalación local de MySQL utiliza otro usuario o contraseña.

## Ejecución

### Desde una IDE

Ejecutar la clase:

```text
principal.Main
```

### Mediante Maven

```powershell
mvn compile
mvn exec:java -Dexec.mainClass="principal.Main"
```

### Compilación manual en Windows PowerShell

Localizar el conector MySQL:

```powershell
$jar = Get-ChildItem "$env:USERPROFILE\.m2\repository\com\mysql\mysql-connector-j" -Recurse -Filter "mysql-connector-j-*.jar" |
Sort-Object LastWriteTime -Descending |
Select-Object -First 1 -ExpandProperty FullName
```

Compilar:

```powershell
New-Item -ItemType Directory -Force .\target\classes | Out-Null

Get-ChildItem .\src\main\java -Recurse -Filter *.java |
ForEach-Object { $_.FullName } |
Set-Content -Encoding ASCII .\sources.txt

javac -encoding UTF-8 -cp $jar -d .\target\classes "@sources.txt"
```

Ejecutar:

```powershell
chcp 65001
java "-Dfile.encoding=UTF-8" -cp ".\target\classes;$jar" principal.Main
```

## Usuario de prueba

```text
Usuario: cperez
Contraseña: 1234
Rol: Coordinador
```

También se incluyen usuarios de prueba con otros roles dentro del script SQL.

## Estructura principal

```text
servimant-sistema
├── database
│   └── servimant_db.sql
├── diagramas
├── src
│   └── main
│       └── java
│           ├── dao
│           ├── modelo
│           ├── principal
│           ├── servicio
│           └── vista
├── pom.xml
└── README.md
```

## Diagramas

La carpeta [`diagramas`](diagramas/) contiene:

* diagrama entidad-relación;
* arquitectura general;
* diagrama de clases de análisis;
* diagrama de clases de diseño;
* diagrama de paquetes;
* secuencia de registro de solicitud;
* secuencia de asignación de técnico;
* secuencia de registro de intervención.

Los diagramas se encuentran en formato PNG y en formato editable de Draw.io.

## Flujo principal validado

Durante las pruebas se verificó el siguiente recorrido:

1. Inicio de sesión.
2. Registro de una solicitud.
3. Consulta del listado.
4. Asignación de un técnico.
5. Registro de una intervención.
6. Consulta del historial.
7. Actualización del estado.
8. Cierre de la solicitud.
9. Generación del resumen por estado.
10. Persistencia de los cambios en MySQL.

## Reglas de negocio

* Las solicitudes nuevas se registran en estado `Pendiente`.
* Al asignar un técnico, la solicitud cambia a `Asignada`.
* Una intervención puede actualizar la solicitud a `En proceso`, `Resuelta` o `Cerrada`.
* Una solicitud no puede cerrarse si no posee intervenciones registradas.
* Las solicitudes se muestran ordenadas desde el identificador menor al mayor.

## Limitaciones del prototipo

* La interfaz funciona mediante consola.
* Las contraseñas de prueba se almacenan sin cifrado por tratarse de un prototipo académico.
* Los permisos según rol pueden ampliarse en futuras versiones.
* No se implementó recuperación de contraseñas.
* La configuración de conexión debe modificarse manualmente si cambian las credenciales de MySQL.

## Mejoras futuras

* incorporación de una interfaz gráfica o aplicación web;
* cifrado seguro de contraseñas;
* administración completa de usuarios y permisos;
* filtros de búsqueda y reportes adicionales;
* registro de archivos adjuntos;
* notificaciones de asignación y cierre;
* pruebas automatizadas.

## Autor

**Sebastian Santalla**

Proyecto final de la materia **Seminario de Prácticas Informáticas**.
