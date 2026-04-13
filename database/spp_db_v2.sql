-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: spp_db
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `id_actividad` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `fecha_limite` datetime NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `id_profesor` int NOT NULL,
  PRIMARY KEY (`id_actividad`),
  KEY `actividad_profesor_idx` (`id_profesor`),
  CONSTRAINT `actividad_profesor` FOREIGN KEY (`id_profesor`) REFERENCES `profesor` (`id_profesor`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividad_asignada_practicante`
--

DROP TABLE IF EXISTS `actividad_asignada_practicante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad_asignada_practicante` (
  `id_actividad_asignada_practicante` int NOT NULL AUTO_INCREMENT,
  `observaciones` mediumtext NOT NULL,
  `calificacion` decimal(4,2) NOT NULL,
  `ruta_actividad` varchar(255) NOT NULL,
  `id_practicante` int NOT NULL,
  `id_actividad` int NOT NULL,
  PRIMARY KEY (`id_actividad_asignada_practicante`),
  KEY `asignacion_actividad_idx` (`id_actividad`),
  KEY `asignacion_practicante_idx` (`id_practicante`),
  CONSTRAINT `asignacion_actividad` FOREIGN KEY (`id_actividad`) REFERENCES `actividad` (`id_actividad`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `asignacion_practicante` FOREIGN KEY (`id_practicante`) REFERENCES `practicante` (`id_practicante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad_asignada_practicante`
--

LOCK TABLES `actividad_asignada_practicante` WRITE;
/*!40000 ALTER TABLE `actividad_asignada_practicante` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividad_asignada_practicante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividad_proyecto`
--

DROP TABLE IF EXISTS `actividad_proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad_proyecto` (
  `id_actividad_proyecto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fecha_apertura` decimal(4,1) NOT NULL COMMENT 'Fecha de cuando se habilita la actividad',
  `fecha_cierre` decimal(4,1) NOT NULL COMMENT 'Fecha de cuando se deshabilita la actividad',
  `tiempo_planeado` int NOT NULL COMMENT 'Tiempo en el que se planea la realización de la actividad.\nIMPORTANTE: el tipo de dato es INT ya que se almacenará en minutos (demuestra exactitud)',
  `fecha_inicio_real` datetime NOT NULL COMMENT 'Calcula tiempo real que tardó la elaboración de la actividad.\nNecesita fecha_fin_real',
  `fecha_fin_real` datetime NOT NULL COMMENT 'Calcula tiempo real que tardó la elaboración de la actividad.\nNecesita fecha_inicio_real',
  `id_proyecto` int NOT NULL,
  PRIMARY KEY (`id_actividad_proyecto`),
  KEY `actividad_proyecto_idx` (`id_proyecto`),
  CONSTRAINT `actividad_proyecto` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad_proyecto`
--

LOCK TABLES `actividad_proyecto` WRITE;
/*!40000 ALTER TABLE `actividad_proyecto` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividad_proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id_administrador` int NOT NULL AUTO_INCREMENT,
  `cuenta_usuario` varchar(15) NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_administrador`),
  UNIQUE KEY `nombre_usuario_UNIQUE` (`cuenta_usuario`),
  KEY `admin_usuario_idx` (`id_usuario`),
  CONSTRAINT `admin_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autoevaluacion`
--

DROP TABLE IF EXISTS `autoevaluacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autoevaluacion` (
  `id_autoevaluacion` int NOT NULL AUTO_INCREMENT,
  `fecha_registro` datetime NOT NULL,
  `id_practicante` int NOT NULL,
  PRIMARY KEY (`id_autoevaluacion`),
  KEY `autoeval_practicante_idx` (`id_practicante`),
  CONSTRAINT `autoeval_practicante` FOREIGN KEY (`id_practicante`) REFERENCES `practicante` (`id_practicante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autoevaluacion`
--

LOCK TABLES `autoevaluacion` WRITE;
/*!40000 ALTER TABLE `autoevaluacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `autoevaluacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buzon_mensaje`
--

DROP TABLE IF EXISTS `buzon_mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buzon_mensaje` (
  `id_buzon_mensaje` int NOT NULL AUTO_INCREMENT,
  `rol` enum('emisor','receptor') NOT NULL,
  `estado` enum('enviado','borrador') NOT NULL,
  `id_mensaje` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_buzon_mensaje`),
  KEY `buzon_mensaje_idx` (`id_mensaje`),
  KEY `buzon_usuario_idx` (`id_usuario`),
  CONSTRAINT `buzon_mensaje` FOREIGN KEY (`id_mensaje`) REFERENCES `mensaje` (`id_mensaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `buzon_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buzon_mensaje`
--

LOCK TABLES `buzon_mensaje` WRITE;
/*!40000 ALTER TABLE `buzon_mensaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `buzon_mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordinador`
--

DROP TABLE IF EXISTS `coordinador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coordinador` (
  `id_coordinador` int NOT NULL AUTO_INCREMENT,
  `numero_personal` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `tiempo_servicio_meses` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_coordinador`),
  KEY `coordinaor_usuario_idx` (`id_usuario`),
  CONSTRAINT `coordinaor_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordinador`
--

LOCK TABLES `coordinador` WRITE;
/*!40000 ALTER TABLE `coordinador` DISABLE KEYS */;
/*!40000 ALTER TABLE `coordinador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criterio_autoevaluacion`
--

DROP TABLE IF EXISTS `criterio_autoevaluacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criterio_autoevaluacion` (
  `id_criterio_autoevaluacion` int NOT NULL AUTO_INCREMENT,
  `concepto` varchar(255) NOT NULL,
  `puntaje` int NOT NULL,
  `id_autoevaluacion` int NOT NULL,
  PRIMARY KEY (`id_criterio_autoevaluacion`),
  KEY `criterioautoeval_autoeval_idx` (`id_autoevaluacion`),
  CONSTRAINT `criterioautoeval_autoeval` FOREIGN KEY (`id_autoevaluacion`) REFERENCES `autoevaluacion` (`id_autoevaluacion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chk_puntaje_rango` CHECK ((`puntaje` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criterio_autoevaluacion`
--

LOCK TABLES `criterio_autoevaluacion` WRITE;
/*!40000 ALTER TABLE `criterio_autoevaluacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `criterio_autoevaluacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion_organizacion`
--

DROP TABLE IF EXISTS `direccion_organizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion_organizacion` (
  `id_direccion_organizacion` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(100) NOT NULL,
  `numero_exterior` varchar(10) NOT NULL,
  `numero_interior` varchar(10) DEFAULT NULL,
  `colonia` varchar(100) NOT NULL,
  `codigo_postal` varchar(10) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `id_organizacion_vinculada` int NOT NULL,
  PRIMARY KEY (`id_direccion_organizacion`),
  KEY `dirorgvinculada_orgvinculada_idx` (`id_organizacion_vinculada`),
  CONSTRAINT `dirorgvinculada_orgvinculada` FOREIGN KEY (`id_organizacion_vinculada`) REFERENCES `organizacion_vinculada` (`id_organizacion_vinculada`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion_organizacion`
--

LOCK TABLES `direccion_organizacion` WRITE;
/*!40000 ALTER TABLE `direccion_organizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion_organizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento_soporte`
--

DROP TABLE IF EXISTS `documento_soporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documento_soporte` (
  `id_documento_soporte` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `ruta_archivo` varchar(255) NOT NULL,
  `extension` varchar(10) NOT NULL,
  `tamaño` bigint NOT NULL COMMENT 'Presición en el tamaño con BIGINT para validación',
  `fecha` datetime NOT NULL,
  `calificacion` decimal(4,2) DEFAULT NULL,
  `observaciones` varchar(45) DEFAULT NULL,
  `id_practicante` int NOT NULL,
  `id_profesor` int NOT NULL,
  PRIMARY KEY (`id_documento_soporte`),
  KEY `docsoporte_practicante_idx` (`id_practicante`),
  KEY `docsoporte_profesor_idx` (`id_profesor`),
  CONSTRAINT `docsoporte_practicante` FOREIGN KEY (`id_practicante`) REFERENCES `practicante` (`id_practicante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `docsoporte_profesor` FOREIGN KEY (`id_profesor`) REFERENCES `profesor` (`id_profesor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento_soporte`
--

LOCK TABLES `documento_soporte` WRITE;
/*!40000 ALTER TABLE `documento_soporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `documento_soporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expediente`
--

DROP TABLE IF EXISTS `expediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expediente` (
  `id_expediente` int NOT NULL AUTO_INCREMENT,
  `promedio_final` decimal(4,2) NOT NULL,
  `id_practicante` int NOT NULL,
  `id_proyecto` int DEFAULT NULL,
  `id_profesor` int DEFAULT NULL,
  `id_periodo_escolar` int NOT NULL,
  PRIMARY KEY (`id_expediente`),
  UNIQUE KEY `id_profesor_UNIQUE` (`id_profesor`),
  UNIQUE KEY `id_proyecto_UNIQUE` (`id_proyecto`),
  KEY `expediente_practicante_idx` (`id_practicante`),
  KEY `expediente_proyecto_idx` (`id_proyecto`),
  KEY `expediente_profesor_idx` (`id_profesor`),
  KEY `expediente_periodoescolar_idx` (`id_periodo_escolar`),
  CONSTRAINT `expediente_periodoescolar` FOREIGN KEY (`id_periodo_escolar`) REFERENCES `periodo_escolar` (`id_periodo_escolar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `expediente_practicante` FOREIGN KEY (`id_practicante`) REFERENCES `practicante` (`id_practicante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `expediente_profesor` FOREIGN KEY (`id_profesor`) REFERENCES `profesor` (`id_profesor`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `expediente_proyecto` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expediente`
--

LOCK TABLES `expediente` WRITE;
/*!40000 ALTER TABLE `expediente` DISABLE KEYS */;
/*!40000 ALTER TABLE `expediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensaje` (
  `id_mensaje` int NOT NULL AUTO_INCREMENT,
  `asunto` varchar(150) NOT NULL,
  `cuerpo` text NOT NULL,
  PRIMARY KEY (`id_mensaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizacion_vinculada`
--

DROP TABLE IF EXISTS `organizacion_vinculada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organizacion_vinculada` (
  `id_organizacion_vinculada` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `correo_electronico` varchar(254) NOT NULL,
  `sector` varchar(100) NOT NULL,
  PRIMARY KEY (`id_organizacion_vinculada`),
  UNIQUE KEY `correo_UNIQUE` (`correo_electronico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizacion_vinculada`
--

LOCK TABLES `organizacion_vinculada` WRITE;
/*!40000 ALTER TABLE `organizacion_vinculada` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizacion_vinculada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo_escolar`
--

DROP TABLE IF EXISTS `periodo_escolar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodo_escolar` (
  `id_periodo_escolar` int NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  PRIMARY KEY (`id_periodo_escolar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo_escolar`
--

LOCK TABLES `periodo_escolar` WRITE;
/*!40000 ALTER TABLE `periodo_escolar` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodo_escolar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practicante`
--

DROP TABLE IF EXISTS `practicante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `practicante` (
  `id_practicante` int NOT NULL AUTO_INCREMENT,
  `matricula` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `edad` int NOT NULL,
  `sexo` enum('Masculino','Femenino') NOT NULL,
  `lengua_indigena` varchar(50) NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_practicante`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`),
  KEY `practicante_usuario_idx` (`id_usuario`),
  CONSTRAINT `practicante_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practicante`
--

LOCK TABLES `practicante` WRITE;
/*!40000 ALTER TABLE `practicante` DISABLE KEYS */;
/*!40000 ALTER TABLE `practicante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `id_profesor` int NOT NULL AUTO_INCREMENT,
  `numero_personal` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `tiempo_servicio_meses` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_profesor`),
  KEY `profesor_usuario_idx` (`id_usuario`),
  CONSTRAINT `profesor_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyecto` (
  `id_proyecto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(120) NOT NULL,
  `capacidad_practicantes` int NOT NULL,
  `disponibilidad` tinyint NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `id_organizacion_vinculada` int NOT NULL,
  PRIMARY KEY (`id_proyecto`),
  KEY `proyecto_orgvinculada_idx` (`id_organizacion_vinculada`),
  CONSTRAINT `proyecto_orgvinculada` FOREIGN KEY (`id_organizacion_vinculada`) REFERENCES `organizacion_vinculada` (`id_organizacion_vinculada`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto`
--

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporte`
--

DROP TABLE IF EXISTS `reporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reporte` (
  `id_reporte` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(75) NOT NULL,
  `fecha_entrega` datetime NOT NULL,
  `nrc` varchar(5) DEFAULT NULL,
  `periodo_abarca` enum('Parcial','Mensual','Final') DEFAULT NULL,
  `horas_cubiertas` int DEFAULT NULL,
  `descripcion` varchar(255) NOT NULL,
  `id_practicante` int NOT NULL,
  `id_periodo_escolar` int NOT NULL,
  PRIMARY KEY (`id_reporte`),
  KEY `reporte_practicante_idx` (`id_practicante`),
  KEY `reporte_periodoescolar_idx` (`id_periodo_escolar`),
  CONSTRAINT `reporte_periodoescolar` FOREIGN KEY (`id_periodo_escolar`) REFERENCES `periodo_escolar` (`id_periodo_escolar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `reporte_practicante` FOREIGN KEY (`id_practicante`) REFERENCES `practicante` (`id_practicante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte`
--

LOCK TABLES `reporte` WRITE;
/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsable_proyecto`
--

DROP TABLE IF EXISTS `responsable_proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsable_proyecto` (
  `id_responsable_proyecto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `cargo` varchar(50) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `correo_electronico` varchar(254) NOT NULL,
  `id_proyecto` int NOT NULL,
  PRIMARY KEY (`id_responsable_proyecto`),
  KEY `responsable_proyecto_idx` (`id_proyecto`),
  CONSTRAINT `responsable_proyecto` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsable_proyecto`
--

LOCK TABLES `responsable_proyecto` WRITE;
/*!40000 ALTER TABLE `responsable_proyecto` DISABLE KEYS */;
/*!40000 ALTER TABLE `responsable_proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_proyecto`
--

DROP TABLE IF EXISTS `solicitud_proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud_proyecto` (
  `id_solicitud_proyecto` int NOT NULL AUTO_INCREMENT,
  `prioridad` int NOT NULL,
  `id_practicante` int NOT NULL,
  `id_proyecto` int NOT NULL,
  PRIMARY KEY (`id_solicitud_proyecto`),
  UNIQUE KEY `uq_practicante_prioridad` (`id_practicante`,`prioridad`),
  KEY `solicitudpro_practicante_idx` (`id_practicante`),
  KEY `solicitudpro_proyecto_idx` (`id_proyecto`),
  CONSTRAINT `solicitudpro_practicante` FOREIGN KEY (`id_practicante`) REFERENCES `practicante` (`id_practicante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `solicitudpro_proyecto` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_proyecto`
--

LOCK TABLES `solicitud_proyecto` WRITE;
/*!40000 ALTER TABLE `solicitud_proyecto` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitud_proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno`
--

DROP TABLE IF EXISTS `turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turno` (
  `id_turno` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL COMMENT 'Un profesor puede impartir en varios bloques o turnos',
  PRIMARY KEY (`id_turno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno_profesor`
--

DROP TABLE IF EXISTS `turno_profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turno_profesor` (
  `id_turno_profesor` int NOT NULL AUTO_INCREMENT,
  `id_turno` int NOT NULL,
  `id_profesor` int NOT NULL,
  PRIMARY KEY (`id_turno_profesor`),
  KEY `turnoprof_profesor_idx` (`id_profesor`),
  KEY `turnoprof_turno_idx` (`id_turno`),
  CONSTRAINT `turnoprof_profesor` FOREIGN KEY (`id_profesor`) REFERENCES `profesor` (`id_profesor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `turnoprof_turno` FOREIGN KEY (`id_turno`) REFERENCES `turno` (`id_turno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno_profesor`
--

LOCK TABLES `turno_profesor` WRITE;
/*!40000 ALTER TABLE `turno_profesor` DISABLE KEYS */;
/*!40000 ALTER TABLE `turno_profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `contraseña` varchar(255) NOT NULL COMMENT 'guarda un hash',
  `cuenta` varchar(15) NOT NULL,
  `estado` enum('Activo','Inactivo') NOT NULL,
  `fecha_registro` datetime NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `cuenta_UNIQUE` (`cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-12 19:10:41
