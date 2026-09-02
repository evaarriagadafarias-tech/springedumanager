# SpringEduManager

Proyecto desarrollado para la evaluación del **Módulo 6: Desarrollo de aplicaciones JEE con Spring Framework**.

## Descripción

SpringEduManager es una aplicación web educativa desarrollada con Java y Spring Boot que permite gestionar estudiantes y cursos.

La aplicación utiliza una arquitectura MVC, persistencia de datos mediante Spring Data JPA y H2, seguridad con Spring Security y servicios REST para interoperabilidad.

## Tecnologías utilizadas

- Java 17
- Spring Boot 4.0.8
- Maven
- Spring MVC
- Thymeleaf
- Spring Data JPA
- Hibernate
- H2 Database
- Spring Security
- REST API
- Thunder Client
- Git
- GitHub

## Funcionalidades

### Estudiantes

- Registrar estudiantes desde la aplicación web.
- Listar estudiantes registrados.
- Consultar estudiantes mediante API REST.
- Buscar estudiantes por ID mediante API REST.
- Crear estudiantes mediante API REST.
- Actualizar estudiantes mediante API REST.
- Eliminar estudiantes mediante API REST.

### Cursos

- Registrar cursos desde la aplicación web.
- Listar cursos registrados.
- Consultar cursos mediante API REST.
- Buscar cursos por ID mediante API REST.
- Crear cursos mediante API REST.
- Actualizar cursos mediante API REST.
- Eliminar cursos mediante API REST.

## Arquitectura del proyecto

El proyecto utiliza una arquitectura organizada en diferentes capas:

- **Model:** contiene las entidades JPA `Estudiante` y `Curso`.
- **Repository:** contiene los repositorios que utilizan Spring Data JPA.
- **Service:** contiene la lógica de acceso y gestión de los datos.
- **Controller:** controla las solicitudes de la aplicación web mediante Spring MVC.
- **REST:** expone los servicios REST para estudiantes y cursos.
- **Config:** contiene la configuración de Spring Security.
- **Templates:** contiene las vistas desarrolladas con Thymeleaf.

El flujo principal de la aplicación es:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Base de datos H2
```

## Base de datos

La aplicación utiliza **H2 Database** en memoria.

Configuración principal:

```text
jdbc:h2:mem:springedudb
```

Usuario:

```text
sa
```

La contraseña se encuentra vacía.

La consola H2 está habilitada en:

```text
http://localhost:8080/h2-console
```

## Seguridad

La aplicación implementa **Spring Security** con autenticación y autorización basada en roles.

### ADMIN

Usuario:

```text
admin
```

Contraseña:

```text
admin123
```

Rol:

```text
ADMIN
```

El administrador puede:

- Consultar estudiantes.
- Registrar estudiantes.
- Consultar cursos.
- Registrar cursos.
- Acceder a la API REST.

### USER

Usuario:

```text
usuario
```

Contraseña:

```text
user123
```

Rol:

```text
USER
```

El usuario puede consultar estudiantes y cursos y acceder a los recursos autorizados de la API REST.

## Acceso a la aplicación

Una vez iniciado el proyecto, ingresar desde el navegador a:

```text
http://localhost:8080/login
```

Después de iniciar sesión correctamente, la aplicación dirige al listado de estudiantes.

Listado de estudiantes:

```text
http://localhost:8080/estudiantes
```

Listado de cursos:

```text
http://localhost:8080/cursos
```

## API REST

### Estudiantes

```text
GET     /api/estudiantes
GET     /api/estudiantes/{id}
POST    /api/estudiantes
PUT     /api/estudiantes/{id}
DELETE  /api/estudiantes/{id}
```

### Cursos

```text
GET     /api/cursos
GET     /api/cursos/{id}
POST    /api/cursos
PUT     /api/cursos/{id}
DELETE  /api/cursos/{id}
```

La API REST se encuentra protegida mediante Spring Security y puede probarse utilizando **Thunder Client**.

## Ejemplos JSON

### Crear estudiante

```json
{
  "nombre": "Eva",
  "apellido": "Arriagada",
  "email": "eva@example.com"
}
```

### Crear curso

```json
{
  "nombre": "Desarrollo Web con Spring",
  "descripcion": "Curso de desarrollo de aplicaciones utilizando Spring Boot",
  "docente": "Profesor"
}
```

## Ejecución del proyecto

### Windows

Desde una terminal ubicada en la carpeta principal del proyecto ejecutar:

```bash
.\mvnw.cmd spring-boot:run
```

También se puede compilar el proyecto con:

```bash
.\mvnw.cmd clean package
```

Si la compilación y las pruebas son correctas, Maven mostrará:

```text
BUILD SUCCESS
```

## Pruebas

El proyecto incluye una prueba básica de contexto utilizando:

- JUnit 5
- Spring Boot Test
- `@SpringBootTest`

La prueba verifica que el contexto de Spring Boot pueda iniciarse correctamente.

## Estructura principal

```text
src
├── main
│   ├── java
│   │   └── cl.eva.springedumanager
│   │       ├── config
│   │       ├── controller
│   │       ├── model
│   │       ├── repository
│   │       ├── rest
│   │       ├── service
│   │       └── SpringedumanagerApplication.java
│   │
│   └── resources
│       ├── templates
│       │   ├── estudiante-form.html
│       │   ├── estudiantes.html
│       │   ├── curso-form.html
│       │   └── cursos.html
│       └── application.properties
│
└── test
    └── java
        └── cl.eva.springedumanager
            └── SpringedumanagerApplicationTests.java
```

## Repositorio

El código fuente del proyecto se encuentra publicado en GitHub:

https://github.com/evaarriagadafarias-tech/springedumanager

## Autor

**Eva Arriagada Farías**

Proyecto académico desarrollado como parte del proceso formativo en desarrollo de aplicaciones con Java y Spring Framework.