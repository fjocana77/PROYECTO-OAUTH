# Sistema de Gestión de Empleados

Este proyecto implementa una arquitectura de microservicios para la gestión de empleados, asistencia y evaluaciones.

## Estructura del Proyecto

### Microservicios
- **empleados-service**: Gestión de información de empleados
- **asistencia-service**: Control de asistencia
- **evaluacion-service**: Gestión de evaluaciones
- **gateway-service**: API Gateway para enrutamiento

### Frontend
- **frontend**: Aplicación React para la interfaz de usuario

### Base de Datos
- MySQL

### Tecnologías
- Spring Boot
- React
- Docker
- MySQL

## Requisitos
- Java 17
- Node.js
- Docker
- MySQL

## Instalación
1. Clonar el repositorio
2. Configurar las variables de entorno
3. Ejecutar los servicios con Docker Compose
4. Iniciar el frontend

## Estructura de Directorios
```
├── empleados-service/
├── asistencia-service/
├── evaluacion-service/
├── gateway-service/
├── frontend/
└── docker-compose.yml
``` 