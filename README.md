# jdbc-inicio

Primer proyecto de ejemplo del módulo de BBDD para la implementación y uso de JDBC (Java Database Connectivity) en Java.

## Descripción

El proyecto tiene como objetivo mostrar los principios para conectar, consultar y manipular bases de datos utilizando
JDBC en Java.

**No se debe usar su código en producción**.

## Instalación

No se requieren pasos específicos de instalación. Para comenzar a usar el proyecto, simplemente instalelo desde la URL
del mismo.

Asegúrate de tener el **JDK 23 de Temurin** instalado y configurado en tu entorno de desarrollo. Si deseas usar 
cualquier otro JDK compatible con JDBC, recuerda modificar y actualizar el pom.xml para reflejar el cambio.

## Uso ##
Cada commit corresponde a un paso del proceso de conexión, configuración y uso de JDBC.

1. ***Commit Inicial*** - Se crea el proyecto sin código ni configuración.
   - Se crea una rama para MySQL y otra para Postgres y se añade el conector de Java a BBDD en el pom.xml de cada una.
2. ***Paso 1*** - Se crea la clase que contiene el main para realizar las pruebas.
   - Se definen las variables de conexión.
   - Se crea la conexión.
   - Se ejecuta una consulta.
   - Se deja la conexión abierta.
   - Se muestra el resultado en pantalla.
3. ***Paso 2*** -   
    - Se cierra la conexión que quedo abierta antes.
4. ***Paso 3*** - Se utiliza un bloque `finally` para cerrar la conexión.
5. ***Paso 4*** - Se utiliza un `try` con recursos.
6. ***Paso 5*** - Se aplica el patrón _Singleton_ a la conexión a la BBDD.
7. ***Paso 6*** - Se crean otras consultas como ejemplo de requisitos de la aplicación para ver su efecto en el `try` 
con recursos.
8. ***Paso 7*** - Se desacopla el código para separar definición de datos, acceso a los mismos, procesamiento de estos
y visualización al usuario.

Como BBDD se utiliza "jardineria". Para crearla y cargar los datos, ejecutar el script jardineria.sql desde la consola 
de MySQL o cualquier administrador de MySQL (DBeaver, WorkBench, ...).
El scrip puede necesitar ajustes para ejecutarlo en PostgreSQL.

## Tecnologías utilizadas

- **Lenguaje**: Java
- **SDK**: JDK 23 (Temurin)
- **Herramienta de construcción**: Maven
- **Conector de base de datos**: JDBC
- **Librería Lombok**: para eliminar _código repetitivo_ (por ejemplo, _Getters_ y _Setters_)

## Licencia

Este proyecto está publicado bajo la licencia **GPL**. Para más información, consulta el archivo `LICENSE` (si está disponible).

---
