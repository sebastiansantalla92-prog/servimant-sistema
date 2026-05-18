DROP DATABASE IF EXISTS servimant_db;
CREATE DATABASE servimant_db;
USE servimant_db;

CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(150)
);

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    email VARCHAR(100) NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    id_rol INT NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE tecnico (
    id_tecnico INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    descripcion VARCHAR(150),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE prioridad (
    id_prioridad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(150),
    nivel INT NOT NULL
);

CREATE TABLE estado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(150)
);

CREATE TABLE sector (
    id_sector INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    ubicacion VARCHAR(120),
    descripcion VARCHAR(150)
);

CREATE TABLE solicitud (
    id_solicitud INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    id_categoria INT NOT NULL,
    id_prioridad INT NOT NULL,
    id_estado INT NOT NULL,
    id_sector INT NOT NULL,
    CONSTRAINT fk_solicitud_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_solicitud_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    CONSTRAINT fk_solicitud_prioridad FOREIGN KEY (id_prioridad) REFERENCES prioridad(id_prioridad),
    CONSTRAINT fk_solicitud_estado FOREIGN KEY (id_estado) REFERENCES estado(id_estado),
    CONSTRAINT fk_solicitud_sector FOREIGN KEY (id_sector) REFERENCES sector(id_sector)
);

CREATE TABLE asignacion (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    id_solicitud INT NOT NULL,
    id_tecnico INT NOT NULL,
    fecha_asignacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    observacion VARCHAR(200),
    CONSTRAINT fk_asignacion_solicitud FOREIGN KEY (id_solicitud) REFERENCES solicitud(id_solicitud),
    CONSTRAINT fk_asignacion_tecnico FOREIGN KEY (id_tecnico) REFERENCES tecnico(id_tecnico)
);

CREATE TABLE intervencion (
    id_intervencion INT AUTO_INCREMENT PRIMARY KEY,
    id_solicitud INT NOT NULL,
    id_tecnico INT NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_intervencion DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_estado_resultante INT NOT NULL,
    CONSTRAINT fk_intervencion_solicitud FOREIGN KEY (id_solicitud) REFERENCES solicitud(id_solicitud),
    CONSTRAINT fk_intervencion_tecnico FOREIGN KEY (id_tecnico) REFERENCES tecnico(id_tecnico),
    CONSTRAINT fk_intervencion_estado FOREIGN KEY (id_estado_resultante) REFERENCES estado(id_estado)
);

INSERT INTO rol (nombre, descripcion) VALUES
('Usuario solicitante', 'Usuario autorizado para registrar solicitudes y consultar su estado'),
('Coordinador', 'Responsable de asignar técnicos y controlar el avance de solicitudes'),
('Técnico', 'Responsable de atender solicitudes y registrar intervenciones'),
('Administrador', 'Responsable de administrar usuarios, técnicos y parámetros del sistema');

INSERT INTO usuario (nombre, apellido, email, nombre_usuario, contrasena, activo, id_rol) VALUES
('María', 'Gómez', 'maria.gomez@servimant.com', 'mgomez', '1234', TRUE, 1),
('Carlos', 'Pérez', 'carlos.perez@servimant.com', 'cperez', '1234', TRUE, 2),
('Ana', 'Ruiz', 'ana.ruiz@servimant.com', 'aruiz', '1234', TRUE, 4);

INSERT INTO tecnico (nombre, apellido, especialidad, email, activo) VALUES
('Juan', 'López', 'Electricidad', 'juan.lopez@servimant.com', TRUE),
('Lucía', 'Fernández', 'Informática', 'lucia.fernandez@servimant.com', TRUE),
('Diego', 'Martínez', 'Mantenimiento edilicio', 'diego.martinez@servimant.com', TRUE);

INSERT INTO categoria (nombre, descripcion, activo) VALUES
('Informática', 'Problemas relacionados con equipos informáticos o conectividad', TRUE),
('Electricidad', 'Problemas eléctricos o de iluminación', TRUE),
('Infraestructura', 'Problemas edilicios, mobiliario o instalaciones', TRUE),
('Limpieza técnica', 'Solicitudes relacionadas con limpieza específica o mantenimiento preventivo', TRUE);

INSERT INTO prioridad (nombre, descripcion, nivel) VALUES
('Baja', 'Solicitud que puede ser atendida sin urgencia', 1),
('Media', 'Solicitud que requiere atención en un plazo moderado', 2),
('Alta', 'Solicitud que requiere atención prioritaria', 3),
('Crítica', 'Solicitud que afecta el funcionamiento normal del sector', 4);

INSERT INTO estado (nombre, descripcion) VALUES
('Pendiente', 'Solicitud registrada pendiente de análisis o asignación'),
('Asignada', 'Solicitud asignada a un técnico responsable'),
('En proceso', 'Solicitud en etapa de atención técnica'),
('Resuelta', 'Solicitud solucionada por el técnico'),
('Cerrada', 'Solicitud finalizada y cerrada por el coordinador');

INSERT INTO sector (nombre, ubicacion, descripcion) VALUES
('Administración', 'Planta baja', 'Sector administrativo de la organización'),
('Soporte técnico', 'Primer piso', 'Área destinada a soporte informático'),
('Depósito', 'Sector posterior', 'Área de almacenamiento y materiales'),
('Sala de reuniones', 'Primer piso', 'Espacio utilizado para reuniones internas');

INSERT INTO solicitud (descripcion, id_usuario, id_categoria, id_prioridad, id_estado, id_sector) VALUES
('La computadora del sector no enciende correctamente.', 1, 1, 2, 1, 2),
('Falla en la iluminación del área administrativa.', 1, 2, 3, 1, 1),
('Pérdida de agua cerca del depósito.', 1, 3, 4, 1, 3);

INSERT INTO asignacion (id_solicitud, id_tecnico, observacion) VALUES
(1, 2, 'Se asigna técnico informático para revisar el equipo.'),
(2, 1, 'Se asigna técnico electricista para revisar la iluminación.');

UPDATE solicitud SET id_estado = 2 WHERE id_solicitud IN (1, 2);

INSERT INTO intervencion (id_solicitud, id_tecnico, descripcion, id_estado_resultante) VALUES
(1, 2, 'Se revisó la fuente de alimentación y se realizó prueba de encendido.', 3),
(2, 1, 'Se verificó el tablero eléctrico y se reemplazó una luminaria defectuosa.', 4);

UPDATE solicitud SET id_estado = 3 WHERE id_solicitud = 1;
UPDATE solicitud SET id_estado = 4 WHERE id_solicitud = 2;
