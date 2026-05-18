USE servimant_db;

SELECT * FROM solicitud;

SELECT
    s.id_solicitud,
    s.descripcion,
    u.nombre AS usuario,
    c.nombre AS categoria,
    p.nombre AS prioridad,
    e.nombre AS estado,
    se.nombre AS sector
FROM solicitud s
INNER JOIN usuario u ON s.id_usuario = u.id_usuario
INNER JOIN categoria c ON s.id_categoria = c.id_categoria
INNER JOIN prioridad p ON s.id_prioridad = p.id_prioridad
INNER JOIN estado e ON s.id_estado = e.id_estado
INNER JOIN sector se ON s.id_sector = se.id_sector;

SELECT
    s.id_solicitud,
    s.descripcion,
    e.nombre AS estado
FROM solicitud s
INNER JOIN estado e ON s.id_estado = e.id_estado
WHERE e.nombre = 'Pendiente';

SELECT
    a.id_asignacion,
    s.descripcion AS solicitud,
    CONCAT(t.nombre, ' ', t.apellido) AS tecnico,
    a.fecha_asignacion,
    a.observacion
FROM asignacion a
INNER JOIN solicitud s ON a.id_solicitud = s.id_solicitud
INNER JOIN tecnico t ON a.id_tecnico = t.id_tecnico;

SELECT
    i.id_intervencion,
    s.descripcion AS solicitud,
    CONCAT(t.nombre, ' ', t.apellido) AS tecnico,
    i.descripcion AS intervencion,
    i.fecha_intervencion,
    e.nombre AS estado_resultante
FROM intervencion i
INNER JOIN solicitud s ON i.id_solicitud = s.id_solicitud
INNER JOIN tecnico t ON i.id_tecnico = t.id_tecnico
INNER JOIN estado e ON i.id_estado_resultante = e.id_estado
WHERE s.id_solicitud = 1;

SELECT
    e.nombre AS estado,
    COUNT(s.id_solicitud) AS cantidad_solicitudes
FROM estado e
LEFT JOIN solicitud s ON e.id_estado = s.id_estado
GROUP BY e.nombre;

UPDATE solicitud
SET id_estado = 3,
    fecha_actualizacion = NOW()
WHERE id_solicitud = 1;

UPDATE tecnico
SET activo = FALSE
WHERE id_tecnico = 3;

DELETE FROM solicitud
WHERE id_solicitud = 3;
