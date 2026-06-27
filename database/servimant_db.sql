-- ServiMant S.R.L. - Base de datos final para instalación limpia
-- Compatible con MySQL/MariaDB y codificación UTF-8 (utf8mb4)

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `servimant_db`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE `servimant_db`;

DROP TABLE IF EXISTS `asignacion`;
DROP TABLE IF EXISTS `intervencion`;
DROP TABLE IF EXISTS `solicitud`;
DROP TABLE IF EXISTS `usuario`;
DROP TABLE IF EXISTS `tecnico`;
DROP TABLE IF EXISTS `categoria`;
DROP TABLE IF EXISTS `prioridad`;
DROP TABLE IF EXISTS `estado`;
DROP TABLE IF EXISTS `sector`;
DROP TABLE IF EXISTS `rol`;

START TRANSACTION;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion`
--

CREATE TABLE `asignacion` (
  `id_asignacion` int(11) NOT NULL,
  `id_solicitud` int(11) NOT NULL,
  `id_tecnico` int(11) NOT NULL,
  `fecha_asignacion` datetime DEFAULT current_timestamp(),
  `observacion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignacion`
--

INSERT INTO `asignacion` (`id_asignacion`, `id_solicitud`, `id_tecnico`, `fecha_asignacion`, `observacion`) VALUES
(1, 1, 2, '2026-06-06 15:57:59', 'Se asigna técnico informático para revisar el equipo.'),
(2, 2, 1, '2026-06-06 15:57:59', 'Se asigna técnico electricista para revisar la iluminación.'),
(3, 4, 2, '2026-06-06 16:06:23', 'Asignación de prueba para validar el prototipo Java'),
(4, 5, 2, '2026-06-27 18:21:27', 'Asignación realizada durante la prueba integral del TP4.'),
(5, 6, 2, '2026-06-27 18:34:59', 'Asignación para validar la codificación UTF-8.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `activo`) VALUES
(1, 'Informática', 'Problemas relacionados con equipos informáticos o conectividad', 1),
(2, 'Electricidad', 'Problemas eléctricos o de iluminación', 1),
(3, 'Infraestructura', 'Problemas edilicios, mobiliario o instalaciones', 1),
(4, 'Limpieza técnica', 'Solicitudes relacionadas con limpieza específica o mantenimiento preventivo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id_estado` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id_estado`, `nombre`, `descripcion`) VALUES
(1, 'Pendiente', 'Solicitud registrada pendiente de análisis o asignación'),
(2, 'Asignada', 'Solicitud asignada a un técnico responsable'),
(3, 'En proceso', 'Solicitud en etapa de atención técnica'),
(4, 'Resuelta', 'Solicitud solucionada por el técnico'),
(5, 'Cerrada', 'Solicitud finalizada y cerrada por el coordinador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intervencion`
--

CREATE TABLE `intervencion` (
  `id_intervencion` int(11) NOT NULL,
  `id_solicitud` int(11) NOT NULL,
  `id_tecnico` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_intervencion` datetime DEFAULT current_timestamp(),
  `id_estado_resultante` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `intervencion`
--

INSERT INTO `intervencion` (`id_intervencion`, `id_solicitud`, `id_tecnico`, `descripcion`, `fecha_intervencion`, `id_estado_resultante`) VALUES
(1, 1, 2, 'Se revisó la fuente de alimentación y se realizó prueba de encendido.', '2026-06-06 15:57:59', 3),
(2, 2, 1, 'Se verificó el tablero eléctrico y se reemplazó una luminaria defectuosa.', '2026-06-06 15:57:59', 4),
(3, 4, 2, 'Se realiza prueba de intervención para validar el registro desde el prototipo Java', '2026-06-06 16:08:13', 3),
(4, 5, 2, 'Se realizó la intervención técnica y se verificó el funcionamiento correcto.', '2026-06-27 18:22:14', 3),
(5, 6, 2, 'Se realizó una revisión técnica y se verificó la solución.', '2026-06-27 18:35:27', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prioridad`
--

CREATE TABLE `prioridad` (
  `id_prioridad` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `nivel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prioridad`
--

INSERT INTO `prioridad` (`id_prioridad`, `nombre`, `descripcion`, `nivel`) VALUES
(1, 'Baja', 'Solicitud que puede ser atendida sin urgencia', 1),
(2, 'Media', 'Solicitud que requiere atención en un plazo moderado', 2),
(3, 'Alta', 'Solicitud que requiere atención prioritaria', 3),
(4, 'Crítica', 'Solicitud que afecta el funcionamiento normal del sector', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre`, `descripcion`) VALUES
(1, 'Usuario solicitante', 'Usuario autorizado para registrar solicitudes y consultar su estado'),
(2, 'Coordinador', 'Responsable de asignar técnicos y controlar el avance de solicitudes'),
(3, 'Técnico', 'Responsable de atender solicitudes y registrar intervenciones'),
(4, 'Administrador', 'Responsable de administrar usuarios, técnicos y parámetros del sistema');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sector`
--

CREATE TABLE `sector` (
  `id_sector` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `ubicacion` varchar(120) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sector`
--

INSERT INTO `sector` (`id_sector`, `nombre`, `ubicacion`, `descripcion`) VALUES
(1, 'Administración', 'Planta baja', 'Sector administrativo de la organización'),
(2, 'Soporte técnico', 'Primer piso', 'Área destinada a soporte informático'),
(3, 'Depósito', 'Sector posterior', 'Área de almacenamiento y materiales'),
(4, 'Sala de reuniones', 'Primer piso', 'Espacio utilizado para reuniones internas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `id_solicitud` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_creacion` datetime DEFAULT current_timestamp(),
  `fecha_actualizacion` datetime DEFAULT current_timestamp(),
  `id_usuario` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_prioridad` int(11) NOT NULL,
  `id_estado` int(11) NOT NULL,
  `id_sector` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `solicitud`
--

INSERT INTO `solicitud` (`id_solicitud`, `descripcion`, `fecha_creacion`, `fecha_actualizacion`, `id_usuario`, `id_categoria`, `id_prioridad`, `id_estado`, `id_sector`) VALUES
(1, 'La computadora del sector no enciende correctamente.', '2026-06-06 15:57:59', '2026-06-06 15:57:59', 1, 1, 2, 3, 2),
(2, 'Falla en la iluminación del área administrativa.', '2026-06-06 15:57:59', '2026-06-06 15:57:59', 1, 2, 3, 4, 1),
(3, 'Pérdida de agua cerca del depósito.', '2026-06-06 15:57:59', '2026-06-06 15:57:59', 1, 3, 4, 1, 3),
(4, 'Prueba de funcionamiento del prototipo Java desde VS Code.', '2026-06-06 16:03:51', '2026-06-27 18:18:35', 2, 1, 2, 5, 1),
(5, 'Prueba integral del sistema ServiMant para la entrega final.', '2026-06-27 18:20:48', '2026-06-27 18:23:01', 2, 1, 2, 5, 1),
(6, 'Prueba de codificación: revisión técnica, intervención y solución.', '2026-06-27 18:33:25', '2026-06-27 18:35:27', 2, 1, 2, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tecnico`
--

CREATE TABLE `tecnico` (
  `id_tecnico` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `especialidad` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tecnico`
--

INSERT INTO `tecnico` (`id_tecnico`, `nombre`, `apellido`, `especialidad`, `email`, `activo`) VALUES
(1, 'Juan', 'López', 'Electricidad', 'juan.lopez@servimant.com', 1),
(2, 'Lucía', 'Fernández', 'Informática', 'lucia.fernandez@servimant.com', 1),
(3, 'Diego', 'Martínez', 'Mantenimiento edilicio', 'diego.martinez@servimant.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `activo` tinyint(1) DEFAULT 1,
  `id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `email`, `nombre_usuario`, `contrasena`, `activo`, `id_rol`) VALUES
(1, 'María', 'Gómez', 'maria.gomez@servimant.com', 'mgomez', '1234', 1, 1),
(2, 'Carlos', 'Pérez', 'carlos.perez@servimant.com', 'cperez', '1234', 1, 2),
(3, 'Ana', 'Ruiz', 'ana.ruiz@servimant.com', 'aruiz', '1234', 1, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD PRIMARY KEY (`id_asignacion`),
  ADD KEY `fk_asignacion_solicitud` (`id_solicitud`),
  ADD KEY `fk_asignacion_tecnico` (`id_tecnico`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `intervencion`
--
ALTER TABLE `intervencion`
  ADD PRIMARY KEY (`id_intervencion`),
  ADD KEY `fk_intervencion_solicitud` (`id_solicitud`),
  ADD KEY `fk_intervencion_tecnico` (`id_tecnico`),
  ADD KEY `fk_intervencion_estado` (`id_estado_resultante`);

--
-- Indices de la tabla `prioridad`
--
ALTER TABLE `prioridad`
  ADD PRIMARY KEY (`id_prioridad`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `sector`
--
ALTER TABLE `sector`
  ADD PRIMARY KEY (`id_sector`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id_solicitud`),
  ADD KEY `fk_solicitud_usuario` (`id_usuario`),
  ADD KEY `fk_solicitud_categoria` (`id_categoria`),
  ADD KEY `fk_solicitud_prioridad` (`id_prioridad`),
  ADD KEY `fk_solicitud_estado` (`id_estado`),
  ADD KEY `fk_solicitud_sector` (`id_sector`);

--
-- Indices de la tabla `tecnico`
--
ALTER TABLE `tecnico`
  ADD PRIMARY KEY (`id_tecnico`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `nombre_usuario` (`nombre_usuario`),
  ADD KEY `fk_usuario_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  MODIFY `id_asignacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `intervencion`
--
ALTER TABLE `intervencion`
  MODIFY `id_intervencion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `prioridad`
--
ALTER TABLE `prioridad`
  MODIFY `id_prioridad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `sector`
--
ALTER TABLE `sector`
  MODIFY `id_sector` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id_solicitud` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tecnico`
--
ALTER TABLE `tecnico`
  MODIFY `id_tecnico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD CONSTRAINT `fk_asignacion_solicitud` FOREIGN KEY (`id_solicitud`) REFERENCES `solicitud` (`id_solicitud`),
  ADD CONSTRAINT `fk_asignacion_tecnico` FOREIGN KEY (`id_tecnico`) REFERENCES `tecnico` (`id_tecnico`);

--
-- Filtros para la tabla `intervencion`
--
ALTER TABLE `intervencion`
  ADD CONSTRAINT `fk_intervencion_estado` FOREIGN KEY (`id_estado_resultante`) REFERENCES `estado` (`id_estado`),
  ADD CONSTRAINT `fk_intervencion_solicitud` FOREIGN KEY (`id_solicitud`) REFERENCES `solicitud` (`id_solicitud`),
  ADD CONSTRAINT `fk_intervencion_tecnico` FOREIGN KEY (`id_tecnico`) REFERENCES `tecnico` (`id_tecnico`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `fk_solicitud_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`),
  ADD CONSTRAINT `fk_solicitud_estado` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`),
  ADD CONSTRAINT `fk_solicitud_prioridad` FOREIGN KEY (`id_prioridad`) REFERENCES `prioridad` (`id_prioridad`),
  ADD CONSTRAINT `fk_solicitud_sector` FOREIGN KEY (`id_sector`) REFERENCES `sector` (`id_sector`),
  ADD CONSTRAINT `fk_solicitud_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
