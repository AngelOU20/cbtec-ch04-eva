# Practica 04: Sistema de Gestión de Tareas con Spring Boot

**Curso:** Java BackEnd Developer 2024
---

## Descripción

El proyecto consiste en desarrollar un sistema básico de gestión de tareas con logueo, permitiendo operaciones CRUD (Crear, Leer, Actualizar, Eliminar, listar) sobre entidades de tareas. Los datos se almacenarán y recuperarán utilizando Spring Boot con una configuración centralizada.

## Características

### Configuración de la Aplicación
- **Framework:** Spring Boot
- **Dependencias necesarias:**
    - `spring-boot-starter-data-jpa`
    - `spring-boot-starter-web`
    - `spring-boot-devtools`
    - `spring-boot-starter-logging`
    - `lombok`

### Aspecto de Auditoría
- Implementación de un aspecto utilizando Spring AOP que capture y registre automáticamente cada operación realizada sobre las tareas.
- Utilización de consejos (Before, Around, etc.) para registrar los detalles y el tiempo de ejecución de cada operación.

### Estructura del Proyecto

| Componente          | Descripción                                                   |
|---------------------|---------------------------------------------------------------|
| **Modelos**         |                                                               |
| `User`              | Representa la información del usuario (`username`, `email`, `password`). |
| `Task`              | Representa una tarea (`id`, `title`, `description`, `status`).|
| **Controladores**   |                                                               |
| `UserController`    | Maneja las solicitudes relacionadas con el usuario.           |
| `TaskController`    | Maneja las solicitudes relacionadas con las tareas.           |
| **Servicios**       |                                                               |
| `UserService`       | Contiene la lógica de negocio relacionada con el usuario.     |
| `TaskService`       | Contiene la lógica de negocio relacionada con las tareas.     |
| **Repositorios**    |                                                               |
| `TaskRepository`    | Simula el acceso a la base de datos para las tareas.          |
| `UserRepository`    | Simula la validación de un usuario en BBDD.                   |
| **Aspecto**         |                                                               |
| `LoggingAspect`     | Registra las actividades de creación y actualización de tareas.|

### Vista
- Utilización de HTML, JSP, Thymeleaf para las vistas.
- Incorporación de estilos y scripts propios o de Bootstrap para mejorar la apariencia visual.

### Persistencia
- Utilización de Spring Data JPA para la interacción con MySQL.
- Consultas paginadas y ordenadas según criterios de búsqueda.

### Logger
- Configuración de `RollingFileAppender` para el trazado de las operaciones realizadas.

### Lombok
- Utilización de Lombok para la creación de entidades.

## Requisitos

1. **Configuración de la Aplicación:**
    - Spring Boot con dependencias: `spring-boot-starter-data-jpa`, `spring-boot-starter-web`, `spring-boot-devtools`, `spring-boot-starter-logging`, `lombok`.
2. **Aspecto de Auditoría:**
    - Implementar un aspecto con Spring AOP para registrar operaciones CRUD y medir el tiempo de ejecución.
3. **Estructura del Proyecto:**
    - Modelos: `User`, `Task`
    - Controladores: `UserController`, `TaskController`
    - Servicios: `UserService`, `TaskService`
    - Repositorios: `TaskRepository`, `UserRepository`
    - Aspecto: `LoggingAspect`
4. **Vista:**
    - Utilización de HTML, JSP, Thymeleaf y Bootstrap.
5. **Persistencia:**
    - Spring Data JPA y MySQL, con consultas paginadas y ordenadas.
6. **Logger:**
    - Configuración de `RollingFileAppender`.
7. **Lombok:**
    - Utilización de Lombok para las entidades.

---
