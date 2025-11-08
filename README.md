<h1 align="center">
  🏥 Sistema de Gestión de Turnos Médicos
</h1>

<p align="center">
  <b>Sistema backend completo para administración integral de turnos médicos</b>
  <br>
  <em>Desarrollado con Spring Boot • MySQL • OpenAPI 3</em>
</p>

<p align="center">
  <a href="http://localhost:8080/swagger-ui/index.html">
    <img src="https://img.shields.io/badge/Documentación-SwaggerUI-brightgreen?style=for-the-badge&logo=swagger" alt="Swagger UI">
  </a>
  <a href="http://localhost:8080/v3/api-docs">
    <img src="https://img.shields.io/badge/API-OpenAPI3-orange?style=for-the-badge&logo=openapi-initiative" alt="OpenAPI 3">
  </a>
  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=openjdk" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.4.5-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot">
</p>

---

## 🌟 Características del Sistema

<div align="center">

| Característica | Icono | Descripción |
|----------------|-------|-------------|
| **Gestión Inteligente de Turnos** | 📅 | Validación automática de disponibilidad de profesionales |
| **Control de Estados** | 📊 | Seguimiento detallado de turnos (Pendiente, Confirmado, Atendido, Cancelado) |
| **Confirmación y Anulación** | 🔄 | Actualización automática de disponibilidad |
| **Relaciones Sólidas** | 🔗 | Entidades interconectadas: pacientes, profesionales, consultorios |
| **Validaciones Integradas** | ✅ | Modelo con mensajes personalizados y robustos |
| **DTOs Personalizados** | 🎯 | Vistas específicas para diferentes respuestas |
| **Prevención de Conflictos** | 🛡️ | Constraints de BD para evitar superposición de turnos |

</div>

---

## 📦 Módulos del Sistema

<div align="center">

| Módulo | Icono | Descripción | Endpoints |
|--------|-------|-------------|-----------|
| **Pacientes** | 👤 | Gestión completa de pacientes | `GET/POST/PUT/DELETE /pacientes` |
| **Profesionales** | 👨‍⚕️ | Administración de profesionales médicos | `GET/POST/PUT/DELETE /profesionales` |
| **Turnos** | 📅 | Procesos de agendamiento y gestión | `GET/POST/PUT /turnos` |
| **Consultorios** | 🏢 | Control de espacios físicos | `GET/POST/PUT/DELETE /consultorios` |
| **Especialidades** | 🎯 | Catálogo de especialidades médicas | `GET/POST/PUT/DELETE /especialidades` |
| **Obras Sociales** | 💼 | Gestión de obras sociales y planes | `GET/POST/PUT/DELETE /obras-sociales` |

</div>

---

## 🛠️ Tecnologías Utilizadas

<div align="center">

### Back-end (API REST)

| Tecnología | Icono | Uso |
|------------|-------|-----|
| **Java 17** | <img src="https://img.shields.io/badge/Java-17-blue?style=flat&logo=openjdk" alt="Java 17"> | Lenguaje de programación principal |
| **Spring Boot** | <img src="https://img.shields.io/badge/Spring_Boot-3.4.5-brightgreen?style=flat&logo=springboot" alt="Spring Boot"> | Framework principal de desarrollo |
| **Spring Data JPA** | <img src="https://img.shields.io/badge/JPA-Hibernate-59666C?style=flat&logo=hibernate" alt="Spring Data JPA"> | Persistencia y mapeo ORM |
| **MySQL** | <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql" alt="MySQL"> | Base de datos relacional |
| **Bean Validation** | <img src="https://img.shields.io/badge/Validation-JSR380-orange?style=flat" alt="Bean Validation"> | Validación de datos y modelos |
| **Maven** | <img src="https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven" alt="Maven"> | Gestión de dependencias y build |

</div>

---

## 📝 Requerimientos Funcionales

<div align="center">

| Módulo | Funcionalidades | Estado |
|--------|-----------------|--------|
| **👤 Pacientes** | Registro completo • Historial de turnos • Validación de DNI • Obra social | ✅ Implementado |
| **👨‍⚕️ Profesionales** | Gestión con matrícula única • Control de disponibilidad • Especialización • Consultorios múltiples | ✅ Implementado |
| **📅 Turnos** | Agendamiento con validación • Múltiples estados • Tipos de consulta • Reagendamiento | ✅ Implementado |
| **🏢 Consultorios** | Gestión de espacios físicos • Ubicación por piso • Asignación dinámica | ✅ Implementado |
| **🎯 Especialidades** | Catálogo médico completo • Asociación con profesionales | ✅ Implementado |
| **💼 Obras Sociales** | Administración de planes • Afiliación de pacientes | ✅ Implementado |

</div>

---

## 📄 Documentación Técnica

<div align="center">

| Recurso | Enlace | Descripción |
|---------|--------|-------------|
| **📖 Swagger UI** | [Swagger](http://localhost:8080/swagger-ui/index.html) | Documentación interactiva completa de la API |
| **🔧 Endpoints** | Ver tabla de módulos | Lista completa de endpoints disponibles |

</div>

---

## ⚙️ Requerimientos No Funcionales

<div align="center">

| Categoría | Especificación | Estado |
|-----------|----------------|--------|
| **🛡️ Validaciones** | Entidades con mensajes claros y personalizados | ✅ Implementado |
| **📐 Modularidad** | Arquitectura escalable para futuras integraciones (web, mobile) | ✅ Implementado |
| **💻 Código Limpio** | Principios SOLID y buenas prácticas de desarrollo | ✅ Implementado |
| **🔒 Seguridad** | Validación de datos y relaciones consistentes | ✅ Implementado |
| **📊 Performance** | Consultas optimizadas y gestión eficiente de recursos | ✅ Implementado |

</div>

---

<div align="center">

## 🚀 ¿Listo para Comenzar?

[**📖 Ir a la Documentación Interactiva**](http://localhost:8080/swagger-ui/index.html) •

**⭐ ¡No olvides darle una estrella al repo si te fue útil!**

---
*Desarrollado con ❤️ usando Spring Boot y Java 17*

</div>