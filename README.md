# 🏥 Sistema de Gestión de Turnos Médicos

Sistema de backend para la administración integral de turnos médicos en clínicas y hospitales. Permite gestionar pacientes, profesionales médicos, consultorios, especialidades, obras sociales y turnos. Diseñado para optimizar la asignación de turnos, controlar la disponibilidad de profesionales y mejorar la experiencia del paciente mediante una API REST robusta, escalable y segura.

---

## 🌟 Características del Sistema

- **Gestión inteligente de turnos** con validación de disponibilidad de profesionales.
- **Control de horarios y consultorios** por profesional y día de la semana.
- **Sistema de disponibilidad** con estados configurables (Disponible, Vacaciones, Licencia).
- **Relaciones complejas entre entidades**: pacientes, profesionales, consultorios, turnos y obras sociales.
- **Validaciones integradas** en el modelo con mensajes personalizados.
- **DTOs especializados** para diferentes operaciones y vistas.
- **Auditoría automática** de fechas de creación y actualización.

---

## 🛠️ Tecnologías Utilizadas

### Back-end (API REST)
- **Java 17**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA**
- **Bean Validation**
- **Lombok**
- **MySQL** / PostgreSQL (configurable)
- **Maven**
- **ModelMapper**

---
## 📋 Configuración de Base de Datos

### Constraints Únicas (Ejecutar una vez en la BD)

Para prevenir la superposición de turnos, ejecutar las siguientes constraints en la base de datos:

```sql
-- Prevenir que un profesional tenga múltiples turnos en la misma fecha/hora
ALTER TABLE turno 
ADD CONSTRAINT uk_turno_profesional_fecha_activo 
UNIQUE (profesional_id, fecha_hora, activo);

-- Prevenir que un consultorio tenga múltiples turnos en la misma fecha/hora  
ALTER TABLE turno 
ADD CONSTRAINT uk_turno_consultorio_fecha_activo 
UNIQUE (consultorio_id, fecha_hora, activo);
```
---

## 📝 Requerimientos Funcionales

1. **Pacientes**:
   - Registro completo con datos personales y obra social.
   - Historial de turnos asociados.
   - Validación de DNI y datos de contacto.

2. **Profesionales Médicos**:
   - Gestión de profesionales con matrícula única.
   - Control de disponibilidad y horarios laborales.
   - Especialización médica y duración de turnos configurable.
   - Asignación múltiple de consultorios con horarios específicos.

3. **Turnos**:
   - Agendamiento con validación de disponibilidad.
   - Estados de turno (Pendiente, Confirmado, Atendido, Cancelado, No Presentado).
   - Tipos de consulta (Primera Vez, Control, Urgencia).
   - Reagendamiento y cancelación con observaciones.
   - Prevención de duplicados mediante validación en servicio y constraints de BD.

4. **Consultorios**:
   - Gestión de espacios físicos con ubicación por piso.
   - Asignación dinámica a profesionales.

5. **Especialidades Médicas**:
   - Catálogo de especialidades médicas.
   - Asociación con profesionales.

6. **Obras Sociales**:
   - Administración de obras sociales y planes.
   - Afiliación de pacientes.

7. **Disponibilidad**:
   - Control centralizado del estado de profesionales.
   - Prevención de conflictos de horarios.

---

## ⚙️ Requerimientos No Funcionales

- **Validaciones robustas** en entidades y DTOs con mensajes personalizados.
- **Arquitectura escalable** preparada para integración con front-end y aplicaciones móviles.
- **Prevención de conflictos** en horarios y consultorios.
- **Separación clean** entre capas de dominio y presentación.
- **Manejo de excepciones** controlado y estandarizado.
- **Código mantenible** siguiendo principios SOLID y mejores prácticas.
- **Integridad de datos** garantizada a nivel de base de datos.
---
