# Sistema SpringBoot-FlaskAPI

Con este sistema puedes registrarte y hacer login de usuarios, invocar APIs externas simuladas desde Flask y gestionar una base de datos MySQL.
Permite detectar y mostrar errores de forma controlada tanto en llamadas externas como en acceso a datos.
Todo el sistema se ejecuta integrado dentro de un único contenedor Docker.

## 🛠️ Sistema Spring Boot + Flask + MySQL
Este proyecto es una aplicación de ejemplo que integra:

- Spring Boot: Backend principal y Frontend (con Thymeleaf).

- Flask API: API en Python que expone servicios simulados.

- MySQL: Base de datos para la API Flask y la aplicación Java.

- Todo el sistema corre dentro de un contenedor Docker.

### 📋 ¿Qué incluye?
- Página principal (/)

- Login de usuarios

- Registro de usuarios

- Simulación de invocaciones a APIs de terceros (Pokémon)

- Manejo de excepciones (archivos, base de datos, llamadas externas)

- Traducción de errores para mostrarlos en el Frontend

## 🚀 ¿Cómo levantarlo?
Compilar el proyecto y construir el contenedor:
```
make build
```
Ejecutar el sistema:
```
make run
```
El sistema expondrá:

- Spring Boot: http://localhost:8080

- Flask API: http://localhost:5000

MySQL: localhost:3308 (usuario root, contraseña root1234)

Este sistema está diseñado para ejecutarse en un único contenedor Docker, combinando Spring Boot, Flask y MySQL en un solo módulo para facilitar su despliegue y pruebas.
Sin embargo, en un entorno real lo ideal sería separar cada componente en su propio servicio para mejorar la escalabilidad, el mantenimiento y la independencia de cada sistema.
Esta separación permitiría actualizar o reiniciar cada parte del sistema sin afectar al resto.

## 📦 Requisitos

- Docker

- Make

- Maven

- JDK 8
