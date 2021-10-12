-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 13-05-2021 a las 04:49:39
-- Versión del servidor: 8.0.17
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `avecsdb`
--
CREATE DATABASE IF NOT EXISTS `avecsdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `avecsdb`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura`
--

CREATE TABLE `asignatura` (
  `id_asignatura` int(10) NOT NULL,
  `descripcion_asignatura` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `codigo_asignatura` int(7) NOT NULL,
  `semestre` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asignatura`
--

INSERT INTO `asignatura` (`id_asignatura`, `descripcion_asignatura`, `codigo_asignatura`, `semestre`) VALUES
(1, 'Planeación de la Comunicación', 1330605, 6),
(2, 'Estrategias de la Comunicación', 1330703, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cronograma`
--

CREATE TABLE `cronograma` (
  `id` int(10) NOT NULL,
  `actividad` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `observacion` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `fec_inicio` date NOT NULL,
  `fec_fin` date NOT NULL,
  `id_solicitud` int(10) NOT NULL,
  `isDone` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cronograma`
--

INSERT INTO `cronograma` (`id`, `actividad`, `descripcion`, `observacion`, `fec_inicio`, `fec_fin`, `id_solicitud`, `isDone`) VALUES
(24, 'Actividad 1', 'Descripcion 1', 'observacion 11212', '2020-06-01', '2020-06-01', 5, 1),
(25, 'actividad 2', 'Descripcion 2', 'observacion 2', '2020-06-02', '2020-06-02', 5, 1),
(26, 'actividad 3', 'Descripcion 1', 'ob', '2020-06-03', '2020-06-03', 5, 0),
(28, 'Actividad 1', 'descripcion de la activ 1', '', '2020-06-19', '2020-06-30', 6, 0),
(29, 'Actividad 1', 'descripcion', '', '2021-06-01', '2021-06-19', 7, 0),
(40, 'ac', '', '', '2020-06-05', '2020-06-07', 8, 1),
(41, 'a1', 'd1', '', '2021-04-07', '2021-04-07', 9, 0),
(42, 'a2', 's2', '', '2021-04-07', '2021-04-07', 9, 0),
(44, 'actividad1', 'Primera actividad a realizar ', '', '2021-04-15', '2021-04-15', 10, 0),
(46, 'actividad1', 'inicia la actividad ', '', '2021-04-22', '2021-04-22', 11, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docente`
--

CREATE TABLE `docente` (
  `id_docente` int(10) NOT NULL,
  `codigo_docente` int(7) NOT NULL,
  `id_persona` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `docente`
--

INSERT INTO `docente` (`id_docente`, `codigo_docente`, `id_persona`) VALUES
(1, 1112020, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documento`
--

CREATE TABLE `documento` (
  `id_doc` int(10) NOT NULL,
  `descripcion_doc` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `id_tipo_doc` int(10) NOT NULL,
  `ruta_doc` varchar(200) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fecha_doc` date NOT NULL,
  `fecha_subida_doc` timestamp NOT NULL,
  `id_persona` int(10) DEFAULT NULL,
  `id_user` int(10) NOT NULL,
  `is_public` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `documento`
--

INSERT INTO `documento` (`id_doc`, `descripcion_doc`, `id_tipo_doc`, `ruta_doc`, `fecha_doc`, `fecha_subida_doc`, `id_persona`, `id_user`, `is_public`) VALUES
(14, 'subida de doc1', 1, 'Files/version 1.2.sql', '2021-04-14', '2021-04-15 02:50:52', NULL, 1, 1),
(15, 'subida de doc2', 2, 'Files/exportar base de datos.txt', '2021-04-14', '2021-04-15 02:53:14', NULL, 1, 1),
(17, 'actividad', 4, 'Files/analisis de algoritmo actividad 1.docx', '2021-04-14', '2021-04-15 04:34:17', NULL, 1, 1),
(18, 'subida de doc22', 4, 'Files/Comprobante de pago en linea INTERNET.pdf', '2021-04-15', '2021-04-15 15:15:32', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id_empresa` int(10) NOT NULL,
  `nit_empresa` varchar(15) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `nombre_empresa` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `dpto_empresa` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `ciudad_empresa` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `direccion_empresa` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `telefono1_empresa` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `telefono2_empresa` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `email_empresa` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `persona_contacto` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `tel_persona_contacto` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `email_persona_contacto` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fec_creacion` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id_empresa`, `nit_empresa`, `nombre_empresa`, `dpto_empresa`, `ciudad_empresa`, `direccion_empresa`, `telefono1_empresa`, `telefono2_empresa`, `email_empresa`, `persona_contacto`, `tel_persona_contacto`, `email_persona_contacto`, `fec_creacion`) VALUES
(1, '555555', 'INGENIEROS SAS', 'ANTIOQUIA', 'Medellin', 'Av1 Cl2', '3110000000', '3110000000', 'ingenieros-soporte@ingenierios.com', 'Leonardo', '3112235065', 'leonardo@ingenieros.com', '0000-00-00 00:00:00'),
(2, '1234564564', 'Tecnologia HyS', 'Cauca', 'Cucuta', 'Calle 1 Av 2 #1-23', '2525', '2', 'angel.vivas94@gmail.com', 'Camilo Rojas', '23456', 'angel.vivas94@gmail.com', '2020-06-13 22:21:52');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estatus_postulante`
--

CREATE TABLE `estatus_postulante` (
  `id` int(10) NOT NULL,
  `descripcion` varchar(15) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estatus_postulante`
--

INSERT INTO `estatus_postulante` (`id`, `descripcion`) VALUES
(1, 'Pendiente'),
(2, 'Aprobado'),
(3, 'No Aprobado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estatus_solicitud`
--

CREATE TABLE `estatus_solicitud` (
  `id` int(10) NOT NULL,
  `descripcion` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `des_detallada` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estatus_solicitud`
--

INSERT INTO `estatus_solicitud` (`id`, `descripcion`, `des_detallada`) VALUES
(1, 'Proceso', 'La solicitud aún no se ha enviado a dirección.'),
(2, 'Pendiente', 'La solicitud fué enviada a dirección, y está en espera de aprobación.'),
(3, 'Aprobada', 'La solicitud fué aprobada por la dirección.'),
(4, 'No Aprobada', 'La solicitud no fué aprobada por la dirección.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `id_estudiante` int(10) NOT NULL,
  `codigo_estudiante` int(7) NOT NULL,
  `id_persona` int(10) NOT NULL,
  `nombre_contacto` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `numero_contacto` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `isAprobado` bit(1) NOT NULL DEFAULT b'0',
  `arl` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`id_estudiante`, `codigo_estudiante`, `id_persona`, `nombre_contacto`, `numero_contacto`, `isAprobado`, `arl`) VALUES
(1, 1151646, 3, 'angel eduardo rozo vmknm', '123123', b'0', 'LIBERTY SEGUROS DE VIDA SPARK');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE `grupo` (
  `id` int(10) NOT NULL,
  `letra` varchar(1) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `id_asignatura` int(10) NOT NULL,
  `id_estudiante` int(10) NOT NULL,
  `id_docente` int(10) NOT NULL,
  `periodo` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id_persona` int(10) NOT NULL,
  `nombres` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `apellido1` varchar(15) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(15) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fec_nacimiento` date NOT NULL,
  `tipo_documento` varchar(2) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `num_documento` int(10) NOT NULL,
  `genero` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(150) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `telefono1` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `telefono2` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id_persona`, `nombres`, `apellido1`, `apellido2`, `fec_nacimiento`, `tipo_documento`, `num_documento`, `genero`, `direccion`, `telefono1`, `telefono2`, `email`) VALUES
(1, 'Usuario Administrador', 'apellido1', 'apellido2', '1994-06-03', 'CC', 123456789, 'Masculino', 'El escobal', '3112235065', '', 'angelleonardovian@ufps.edu.co'),
(2, 'Usuario Docente', 'apellido1', 'apellido2', '1996-01-01', 'CC', 123467895, 'Masculino', 'cucuta', '3112223336', '', 'joseeduardorm@ufps.edu.co'),
(3, 'Usuario Estudiante', 'apellido1', 'apellido2', '2005-07-13', 'CC', 1090475312, 'Masculino', 'Bella Vista cucuta la libertad', '3112223336', '', 'angelleonardovian@ufps.edu.co'),
(4, 'migueleduardopa@ufps.edu.co', '', '', '1996-01-01', 'CC', 0, 'Masculino', 'cucuta', '', '', 'migueleduardopa@ufps.edu.co'),
(7, 'anagabrielacaom@ufps.edu.co', '', '', '1996-01-01', 'CC', 1, 'Femenino', 'cucuta', '', '', 'anagabrielacaom@ufps.edu.co'),
(8, 'yeisonbs@ufps.edu.co', '', '', '1996-01-01', 'CC', 2, 'Masculino', 'cucuta', '', '', 'yeisonbs@ufps.edu.co'),
(9, 'juanpablooj@ufps.edu.co', '', '', '1998-06-27', 'CC', 3, 'Masculino', 'cucuta', '', '', 'juanpablooj@ufps.edu.co'),
(10, 'karenlisethom@ufps.edu.co', '', '', '1996-01-01', 'CC', 4, 'Femenino', 'cucuta', '', '', 'karenlisethom@ufps.edu.co'),
(11, 'angelamildredacro@ufps.edu.co', '', '', '1996-01-01', 'CC', 6, 'Femenino', 'cucuta', '', '', 'angelamildredacro@ufps.edu.co');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `postulante`
--

CREATE TABLE `postulante` (
  `id` int(10) NOT NULL,
  `id_estudiante` int(10) NOT NULL,
  `fec_postulacion` timestamp NOT NULL,
  `id_solicitud` int(10) NOT NULL,
  `estatus` int(10) NOT NULL,
  `observacion` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `postulante`
--

INSERT INTO `postulante` (`id`, `id_estudiante`, `fec_postulacion`, `id_solicitud`, `estatus`, `observacion`) VALUES
(33, 1, '2020-06-20 13:50:01', 5, 2, ''),
(34, 1, '2020-06-21 22:58:59', 6, 2, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recovery`
--

CREATE TABLE `recovery` (
  `user` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `token` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `id_solicitud` int(10) NOT NULL,
  `id_empresa` int(10) NOT NULL,
  `id_docente` int(10) NOT NULL,
  `cantidad_participantes` int(2) NOT NULL,
  `cupos_disponibles` int(4) NOT NULL,
  `tematica` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `id_asignatura` int(10) NOT NULL,
  `fec_inicio` date NOT NULL,
  `fec_fin` date NOT NULL,
  `estatus` int(10) NOT NULL,
  `observacion` text CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fec_creacion` date NOT NULL,
  `fec_solicitud_aprob` date NOT NULL,
  `fec_aprobacion` date NOT NULL,
  `id_aprobador` int(10) DEFAULT NULL,
  `periodo` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `solicitud`
--

INSERT INTO `solicitud` (`id_solicitud`, `id_empresa`, `id_docente`, `cantidad_participantes`, `cupos_disponibles`, `tematica`, `id_asignatura`, `fec_inicio`, `fec_fin`, `estatus`, `observacion`, `fec_creacion`, `fec_solicitud_aprob`, `fec_aprobacion`, `id_aprobador`, `periodo`) VALUES
(5, 1, 1, 20, 18, 'TEMATICA 1', 1, '2020-06-01', '2020-06-15', 3, 'Observaciones generales de la visita empresarial 25', '2020-06-17', '2020-06-17', '2020-06-18', 2, '2020-1'),
(6, 2, 1, 30, 28, 'TEMATICA 2', 2, '2020-06-01', '2020-06-30', 3, '                                                                             \r\n                                                                        ', '2020-06-20', '2020-06-20', '2020-06-20', 1, '2020-1'),
(7, 1, 1, 1, 1, 'tematica 2', 1, '2021-06-01', '2021-06-19', 3, '                                                                             \r\n                                                                        ', '2020-06-20', '2020-06-20', '2020-06-20', 1, '2020-1'),
(8, 1, 1, 2, 2, 'alguna2', 1, '2020-06-01', '2020-06-09', 3, '                                                                                                                                                                                                                                                                                                                 \r\n                                                                         \r\n                                                                         \r\n                                                                         \r\n                                                                        ', '2020-06-21', '2020-06-21', '2020-06-22', 1, '2020-1'),
(9, 1, 1, 10, 10, 'alguna2', 2, '2021-04-07', '2021-04-08', 1, 'comentario', '2021-04-07', '2021-04-07', '2021-04-07', 2, '2021-1'),
(10, 1, 1, 14, 14, 'pasar materias ', 1, '2021-04-15', '2021-04-15', 2, '', '2021-04-14', '2021-04-14', '2021-04-14', 2, '2021-1'),
(11, 1, 1, 14, 14, 'visita ', 1, '2021-04-16', '2021-04-23', 2, '', '2021-04-15', '2021-04-15', '2021-04-15', 2, '2021-1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_documento`
--

CREATE TABLE `tipo_documento` (
  `id_tipo_doc` int(10) NOT NULL,
  `descripcion_tipo_doc` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_documento`
--

INSERT INTO `tipo_documento` (`id_tipo_doc`, `descripcion_tipo_doc`) VALUES
(1, 'Documentos universidad'),
(2, 'Documentos programa'),
(3, 'Documentos estudiante'),
(4, 'Documentos docente'),
(5, 'Certificaciones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id_tipo_usuario` int(10) NOT NULL,
  `des_tipo_usuario` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_tipo_usuario`, `des_tipo_usuario`) VALUES
(1, 'Administrador'),
(2, 'Docente'),
(3, 'Estudiante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(10) NOT NULL,
  `user` varchar(70) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fec_creacion` timestamp NOT NULL,
  `id_tipo_usuario` int(1) NOT NULL,
  `id_persona` int(10) NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `user`, `password`, `fec_creacion`, `id_tipo_usuario`, `id_persona`, `activo`) VALUES
(1, 'angelleonardovian@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:54:55', 1, 1, 1),
(2, 'joseeduardorm@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:55:54', 2, 2, 1),
(4, 'estudiante@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:55:54', 3, 3, 1),
(5, 'angelamildredacro@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:54:55', 1, 11, 1),
(6, 'karenlisethom@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:54:55', 1, 10, 1),
(7, 'juanpablooj@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:54:55', 1, 9, 1),
(8, 'yeisonbs@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:54:55', 1, 8, 1),
(9, 'anagabrielacaom@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:54:55', 1, 7, 1),
(10, 'migueleduardopa@ufps.edu.co', '356a192b7913b04c54574d18c28d46e6395428ab', '2020-05-27 17:54:55', 1, 4, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD PRIMARY KEY (`id_asignatura`) USING BTREE,
  ADD UNIQUE KEY `codigo_asignatura` (`codigo_asignatura`);

--
-- Indices de la tabla `cronograma`
--
ALTER TABLE `cronograma`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cronograma_solicitud` (`id_solicitud`);

--
-- Indices de la tabla `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`id_docente`) USING BTREE,
  ADD UNIQUE KEY `codigo_docente` (`codigo_docente`),
  ADD KEY `fk_docente_persona` (`id_persona`);

--
-- Indices de la tabla `documento`
--
ALTER TABLE `documento`
  ADD PRIMARY KEY (`id_doc`),
  ADD KEY `fk_doc_soporte_tipo_doc_soporte` (`id_tipo_doc`),
  ADD KEY `fk_doc_soporte_persona` (`id_persona`),
  ADD KEY `fk_doc_user` (`id_user`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id_empresa`) USING BTREE,
  ADD UNIQUE KEY `nit_empresa` (`nit_empresa`),
  ADD UNIQUE KEY `nombre_empresa` (`nombre_empresa`);

--
-- Indices de la tabla `estatus_postulante`
--
ALTER TABLE `estatus_postulante`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estatus_solicitud`
--
ALTER TABLE `estatus_solicitud`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`id_estudiante`) USING BTREE,
  ADD UNIQUE KEY `codigo_estudiante` (`codigo_estudiante`),
  ADD KEY `fk_estudiante_persona` (`id_persona`);

--
-- Indices de la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_grupo_estudiante` (`id_estudiante`),
  ADD KEY `fk_grupo_asignatura` (`id_asignatura`),
  ADD KEY `fk_grupo_docente` (`id_docente`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`) USING BTREE,
  ADD UNIQUE KEY `num_documento` (`num_documento`),
  ADD KEY `fk_persona_genero` (`genero`),
  ADD KEY `fk_persona_tipo_documento` (`tipo_documento`);

--
-- Indices de la tabla `postulante`
--
ALTER TABLE `postulante`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_postulante_solicitud` (`id_solicitud`),
  ADD KEY `fk_postulante_estudiante` (`id_estudiante`),
  ADD KEY `fk_postulante_estatus` (`estatus`);

--
-- Indices de la tabla `recovery`
--
ALTER TABLE `recovery`
  ADD PRIMARY KEY (`token`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id_solicitud`),
  ADD KEY `fk_solicitud_empresa` (`id_empresa`),
  ADD KEY `fk_solicitud_docente` (`id_docente`),
  ADD KEY `fk_solicitud_persona` (`id_aprobador`),
  ADD KEY `fk_solicitud_asignatura` (`id_asignatura`),
  ADD KEY `fk_solicitud_estatus` (`estatus`);

--
-- Indices de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  ADD PRIMARY KEY (`id_tipo_doc`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id_tipo_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `user` (`user`),
  ADD KEY `fk_usuario_tipo_usuario` (`id_tipo_usuario`),
  ADD KEY `fk_usuario_persona` (`id_persona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  MODIFY `id_asignatura` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cronograma`
--
ALTER TABLE `cronograma`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `docente`
--
ALTER TABLE `docente`
  MODIFY `id_docente` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `documento`
--
ALTER TABLE `documento`
  MODIFY `id_doc` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id_empresa` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `estatus_postulante`
--
ALTER TABLE `estatus_postulante`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `estatus_solicitud`
--
ALTER TABLE `estatus_solicitud`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  MODIFY `id_estudiante` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `grupo`
--
ALTER TABLE `grupo`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id_persona` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `postulante`
--
ALTER TABLE `postulante`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id_solicitud` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  MODIFY `id_tipo_doc` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id_tipo_usuario` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cronograma`
--
ALTER TABLE `cronograma`
  ADD CONSTRAINT `fk_cronograma_solicitud` FOREIGN KEY (`id_solicitud`) REFERENCES `solicitud` (`id_solicitud`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `docente`
--
ALTER TABLE `docente`
  ADD CONSTRAINT `fk_docente_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `documento`
--
ALTER TABLE `documento`
  ADD CONSTRAINT `fk_doc_soporte_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_doc_soporte_tipo_doc_soporte` FOREIGN KEY (`id_tipo_doc`) REFERENCES `tipo_documento` (`id_tipo_doc`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_doc_user` FOREIGN KEY (`id_user`) REFERENCES `usuario` (`id_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD CONSTRAINT `fk_estudiante_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD CONSTRAINT `fk_grupo_asignatura` FOREIGN KEY (`id_asignatura`) REFERENCES `asignatura` (`id_asignatura`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_grupo_docente` FOREIGN KEY (`id_docente`) REFERENCES `docente` (`id_docente`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_grupo_estudiante` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `postulante`
--
ALTER TABLE `postulante`
  ADD CONSTRAINT `fk_postulante_estatus` FOREIGN KEY (`estatus`) REFERENCES `estatus_postulante` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_postulante_estudiante` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_postulante_solicitud` FOREIGN KEY (`id_solicitud`) REFERENCES `solicitud` (`id_solicitud`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `fk_solicitud_asignatura` FOREIGN KEY (`id_asignatura`) REFERENCES `asignatura` (`id_asignatura`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_solicitud_docente` FOREIGN KEY (`id_docente`) REFERENCES `docente` (`id_docente`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_solicitud_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_solicitud_estatus` FOREIGN KEY (`estatus`) REFERENCES `estatus_solicitud` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_solicitud_persona` FOREIGN KEY (`id_aprobador`) REFERENCES `usuario` (`id_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usuario_tipo_usuario` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario` (`id_tipo_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
