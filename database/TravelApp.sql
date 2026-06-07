-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: travelapp
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `alojamiento`
--

DROP TABLE IF EXISTS `alojamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alojamiento` (
  `idAlojamiento` int NOT NULL AUTO_INCREMENT,
  `nombre` char(150) NOT NULL,
  `tipo` enum('HOTEL','HOSTAL','APARTAMENTO','CASA_CULTURAL','CAMPING','OTRO') NOT NULL,
  `direccion` char(200) NOT NULL,
  `ciudad` char(100) NOT NULL,
  `pais` char(100) NOT NULL,
  PRIMARY KEY (`idAlojamiento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alojamiento`
--

LOCK TABLES `alojamiento` WRITE;
/*!40000 ALTER TABLE `alojamiento` DISABLE KEYS */;
INSERT INTO `alojamiento` VALUES (1,'Hotel Lumière','HOTEL','Rue de Lyon','Paris','Francia'),(2,'Roma Suites','APARTAMENTO','Via Nazionale','Roma','Italia'),(6,'Hotel París Center','HOTEL','Rue de Rivoli 123','París','Francia');
/*!40000 ALTER TABLE `alojamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gasto`
--

DROP TABLE IF EXISTS `gasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gasto` (
  `idGasto` int NOT NULL AUTO_INCREMENT,
  `idViaje` int NOT NULL,
  `concepto` char(150) NOT NULL,
  `categoria` enum('COMIDA','TRANSPORTE','ALOJAMIENTO','OCIO','COMPRAS','OTROS') NOT NULL,
  `importe` double NOT NULL,
  `fecha` date NOT NULL,
  `lugar` char(150) DEFAULT NULL,
  `metodoPago` enum('EFECTIVO','TARJETA','BIZUM','TRANSFERENCIA') NOT NULL,
  `estado` enum('PENDIENTE','PAGADO','CANCELADO','REEMBOLSADO') NOT NULL,
  `notas` char(255) DEFAULT NULL,
  PRIMARY KEY (`idGasto`),
  KEY `gasto_ibfk_1` (`idViaje`),
  CONSTRAINT `gasto_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`) ON DELETE CASCADE,
  CONSTRAINT `gasto_chk_1` CHECK ((`importe` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gasto`
--

LOCK TABLES `gasto` WRITE;
/*!40000 ALTER TABLE `gasto` DISABLE KEYS */;
INSERT INTO `gasto` VALUES (4,5,'Entrada Museo Louvre','OTROS',25,'2024-06-11','Paris','TARJETA','PAGADO','Entrada general sin guia'),(5,5,'Cena en restaurante','COMIDA',400,'2024-06-12','Paris','TARJETA','PAGADO','Cena en bistro cerca del hotel');
/*!40000 ALTER TABLE `gasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporte`
--

DROP TABLE IF EXISTS `transporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transporte` (
  `idTransporte` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('AVION','TREN','AUTOBUS','COCHE','BARCO') NOT NULL,
  `origen` char(100) NOT NULL,
  `destino` char(100) NOT NULL,
  `fechaSalida` datetime NOT NULL,
  `fechaLlegada` datetime NOT NULL,
  `precio` double NOT NULL,
  `estado` enum('PENDIENTE','CONFIRMADO','CANCELADO') NOT NULL,
  PRIMARY KEY (`idTransporte`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporte`
--

LOCK TABLES `transporte` WRITE;
/*!40000 ALTER TABLE `transporte` DISABLE KEYS */;
INSERT INTO `transporte` VALUES (1,'AVION','Madrid','Paris','2026-06-10 00:00:00','2026-06-12 00:00:00',120,'CONFIRMADO'),(2,'TREN','Paris','Versalles','2024-06-12 00:00:00','2024-06-12 00:00:00',15,'CONFIRMADO'),(3,'AVION','Madrid','Roma','2024-07-05 00:00:00','2024-07-05 00:00:00',140,'CONFIRMADO'),(6,'AVION','Madrid','Paris','2024-06-10 00:00:00','2024-06-10 00:00:00',150,'CONFIRMADO'),(7,'COCHE','Charles de Gaulle','Centro Paris','2024-06-10 00:00:00','2026-06-10 00:00:00',12,'CONFIRMADO');
/*!40000 ALTER TABLE `transporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` char(100) NOT NULL,
  `email` char(150) NOT NULL,
  `password` char(255) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Usuario','ana@gmail.com','1234');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viaje`
--

DROP TABLE IF EXISTS `viaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viaje` (
  `idViaje` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `idAlojamiento` int DEFAULT NULL,
  `nombre` char(150) NOT NULL,
  `destino` char(150) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `presupuesto` double NOT NULL,
  `notas` char(255) DEFAULT NULL,
  `tipo` enum('AVENTURA','RELAX','CULTURAL','NEGOCIOS','ROMANTICO','FAMILIAR','OTROS') NOT NULL,
  `imagen` char(255) DEFAULT NULL,
  PRIMARY KEY (`idViaje`),
  KEY `idUsuario` (`idUsuario`),
  KEY `viaje_ibfk_2` (`idAlojamiento`),
  CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `viaje_ibfk_2` FOREIGN KEY (`idAlojamiento`) REFERENCES `alojamiento` (`idAlojamiento`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje`
--

LOCK TABLES `viaje` WRITE;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` VALUES (5,1,6,'Viaje a Paris','Francia','2024-06-10','2024-06-15',1200,'Visita a museos y gastronomia','CULTURAL',NULL),(6,1,NULL,'Escapada a Malaga','España','2024-07-05','2024-07-08',450,'Explorar el centro historico','ROMANTICO',NULL);
/*!40000 ALTER TABLE `viaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viajetransporte`
--

DROP TABLE IF EXISTS `viajetransporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viajetransporte` (
  `idViajeTransporte` int NOT NULL AUTO_INCREMENT,
  `idViaje` int NOT NULL,
  `idTransporte` int NOT NULL,
  `notas` char(255) DEFAULT NULL,
  PRIMARY KEY (`idViajeTransporte`),
  KEY `viajetransporte_ibfk_1` (`idViaje`),
  KEY `viajetransporte_ibfk_2` (`idTransporte`),
  CONSTRAINT `viajetransporte_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`) ON DELETE CASCADE,
  CONSTRAINT `viajetransporte_ibfk_2` FOREIGN KEY (`idTransporte`) REFERENCES `transporte` (`idTransporte`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viajetransporte`
--

LOCK TABLES `viajetransporte` WRITE;
/*!40000 ALTER TABLE `viajetransporte` DISABLE KEYS */;
INSERT INTO `viajetransporte` VALUES (6,5,6,NULL),(7,5,7,NULL);
/*!40000 ALTER TABLE `viajetransporte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-07 19:47:32
