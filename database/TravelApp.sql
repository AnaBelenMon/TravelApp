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
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `idActividad` int NOT NULL AUTO_INCREMENT,
  `idViaje` int NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `precio` double DEFAULT NULL,
  `notas` text,
  `valoracion` int DEFAULT NULL,
  `duracionMinutos` int DEFAULT NULL,
  `reservada` tinyint(1) DEFAULT NULL,
  `lugar` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idActividad`),
  KEY `idViaje` (`idViaje`),
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
INSERT INTO `actividad` VALUES (1,1,'Tour por el centro histórico','CULTURAL','2025-03-10',20,'Guía local por el centro de Madrid',5,120,1,'Centro de Madrid'),(2,1,'Cena de tapas','GASTRONOMIA','2025-03-11',30,'Tapas en La Latina',4,90,0,'Barrio de La Latina'),(3,2,'Crucero por el Sena','OCIO','2025-04-02',50,'Paseo nocturno en barco',5,60,1,'Río Sena');
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alojamiento`
--

DROP TABLE IF EXISTS `alojamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alojamiento` (
  `idAlojamiento` int NOT NULL AUTO_INCREMENT,
  `idViaje` int NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `precioTotal` double NOT NULL,
  `fechaCheckin` date DEFAULT NULL,
  `fechaCheckout` date DEFAULT NULL,
  `valoracion` int DEFAULT NULL,
  PRIMARY KEY (`idAlojamiento`),
  KEY `idViaje` (`idViaje`),
  CONSTRAINT `alojamiento_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alojamiento`
--

LOCK TABLES `alojamiento` WRITE;
/*!40000 ALTER TABLE `alojamiento` DISABLE KEYS */;
INSERT INTO `alojamiento` VALUES (1,1,'Hotel Madrid Centro','Calle Mayor 10',400,'2025-03-10','2025-03-15',4),(2,2,'Hotel París Lumière','Rue Rivoli 22',600,'2025-04-01','2025-04-05',5);
/*!40000 ALTER TABLE `alojamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento`
--

DROP TABLE IF EXISTS `documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documento` (
  `idDocumento` int NOT NULL AUTO_INCREMENT,
  `idViaje` int NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `rutaArchivo` varchar(300) NOT NULL,
  PRIMARY KEY (`idDocumento`),
  KEY `idViaje` (`idViaje`),
  CONSTRAINT `documento_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento`
--

LOCK TABLES `documento` WRITE;
/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
INSERT INTO `documento` VALUES (1,1,'Billete AVE','BILLETE','/docs/billete_ave.pdf'),(2,1,'Reserva Hotel','RESERVA','/docs/reserva_hotel_madrid.pdf'),(3,2,'Billete Avión','BILLETE','/docs/billete_paris.pdf');
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elementocultural`
--

DROP TABLE IF EXISTS `elementocultural`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elementocultural` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elementocultural`
--

LOCK TABLES `elementocultural` WRITE;
/*!40000 ALTER TABLE `elementocultural` DISABLE KEYS */;
INSERT INTO `elementocultural` VALUES (1,'Museo del Prado','Museo nacional de España en Madrid'),(2,'Museo Reina Sofía','Museo de arte contemporáneo en Madrid'),(3,'La Gioconda','Obra de Leonardo da Vinci'),(4,'Guernica','Obra de Pablo Picasso');
/*!40000 ALTER TABLE `elementocultural` ENABLE KEYS */;
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
  `categoriaGasto` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `importe` double NOT NULL,
  `notas` text,
  PRIMARY KEY (`idGasto`),
  KEY `idViaje` (`idViaje`),
  CONSTRAINT `gasto_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gasto`
--

LOCK TABLES `gasto` WRITE;
/*!40000 ALTER TABLE `gasto` DISABLE KEYS */;
INSERT INTO `gasto` VALUES (1,1,'COMIDA','2025-03-10',25.5,'Tapas en el centro'),(2,1,'OCIO','2025-03-11',15,'Entrada a exposición'),(3,2,'TRANSPORTE','2025-04-02',2.5,'Metro en París'),(4,2,'REGALOS','2025-04-03',30,'Souvenirs');
/*!40000 ALTER TABLE `gasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `museo`
--

DROP TABLE IF EXISTS `museo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `museo` (
  `idMuseo` int NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `precioEntrada` double NOT NULL,
  `horario` varchar(200) NOT NULL,
  `webOficial` varchar(300) NOT NULL,
  PRIMARY KEY (`idMuseo`),
  CONSTRAINT `museo_ibfk_1` FOREIGN KEY (`idMuseo`) REFERENCES `elementocultural` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `museo`
--

LOCK TABLES `museo` WRITE;
/*!40000 ALTER TABLE `museo` DISABLE KEYS */;
INSERT INTO `museo` VALUES (1,'Madrid','España',15,'10:00-20:00','https://www.museodelprado.es'),(2,'Madrid','España',12,'10:00-21:00','https://www.museoreinasofia.es');
/*!40000 ALTER TABLE `museo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obra`
--

DROP TABLE IF EXISTS `obra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obra` (
  `idObra` int NOT NULL,
  `autor` varchar(150) DEFAULT NULL,
  `estilo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idObra`),
  CONSTRAINT `obra_ibfk_1` FOREIGN KEY (`idObra`) REFERENCES `elementocultural` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obra`
--

LOCK TABLES `obra` WRITE;
/*!40000 ALTER TABLE `obra` DISABLE KEYS */;
INSERT INTO `obra` VALUES (3,'Leonardo da Vinci','Renacimiento'),(4,'Pablo Picasso','Cubismo');
/*!40000 ALTER TABLE `obra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recuerdo`
--

DROP TABLE IF EXISTS `recuerdo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recuerdo` (
  `idRecuerdo` int NOT NULL AUTO_INCREMENT,
  `idViaje` int NOT NULL,
  `rutaArchivo` varchar(300) NOT NULL,
  `descripcion` text,
  `ubicacion` varchar(200) DEFAULT NULL,
  `fecha` date NOT NULL,
  `emocion` varchar(50) DEFAULT NULL,
  `tipo` varchar(50) NOT NULL,
  `favorito` tinyint(1) NOT NULL,
  `rutaMiniatura` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idRecuerdo`),
  KEY `idViaje` (`idViaje`),
  CONSTRAINT `recuerdo_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recuerdo`
--

LOCK TABLES `recuerdo` WRITE;
/*!40000 ALTER TABLE `recuerdo` DISABLE KEYS */;
INSERT INTO `recuerdo` VALUES (1,1,'/img/plaza_mayor.jpg','Foto en la Plaza Mayor','Madrid','2025-03-10','FELICIDAD','FOTO',1,'/thumbs/plaza_mayor.jpg'),(2,1,'/img/prado.jpg','Entrada al Museo del Prado','Madrid','2025-03-11','EUFORIA','FOTO',0,NULL),(3,2,'/img/torre_eiffel.jpg','Foto en la Torre Eiffel','París','2025-04-02','SORPRESA','FOTO',1,'/thumbs/torre_eiffel.jpg');
/*!40000 ALTER TABLE `recuerdo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporte`
--

DROP TABLE IF EXISTS `transporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transporte` (
  `idTransporte` int NOT NULL AUTO_INCREMENT,
  `idViaje` int NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `precio` double NOT NULL,
  `tipoDocumento` varchar(50) NOT NULL,
  `rutaDocumento` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idTransporte`),
  KEY `idViaje` (`idViaje`),
  CONSTRAINT `transporte_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporte`
--

LOCK TABLES `transporte` WRITE;
/*!40000 ALTER TABLE `transporte` DISABLE KEYS */;
INSERT INTO `transporte` VALUES (1,1,'TREN','2025-03-10',45,'BILLETE','/docs/billete_ave.pdf'),(2,2,'AVION','2025-04-01',150,'BILLETE','/docs/billete_paris.pdf');
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
  `nombre` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Ana García','ana@gmail.com','ana'),(2,'Luis Pérez','luis@gmail.com','luis'),(3,'María López','maria@gmail.com','maria');
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
  `nombre` varchar(150) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `tipoViaje` varchar(50) NOT NULL,
  `imagenPortada` varchar(300) DEFAULT NULL,
  `notasGenerales` text,
  `presupuestoEstimado` double NOT NULL,
  `destinoPais` varchar(100) NOT NULL,
  `destinoCiudad` varchar(100) NOT NULL,
  PRIMARY KEY (`idViaje`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje`
--

LOCK TABLES `viaje` WRITE;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` VALUES (1,1,'Escapada a Madrid','2025-03-10','2025-03-15','CULTURAL',NULL,'Museos y tapas',500,'España','Madrid'),(2,2,'Viaje romántico a París','2025-04-01','2025-04-05','ROMANTICO',NULL,'Paseos y fotos',900,'Francia','París'),(3,3,'Aventura en Lisboa','2025-05-10','2025-05-14','AVENTURA',NULL,'Explorar la ciudad',600,'Portugal','Lisboa');
/*!40000 ALTER TABLE `viaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viajemuseo`
--

DROP TABLE IF EXISTS `viajemuseo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viajemuseo` (
  `idViaje` int NOT NULL,
  `idMuseo` int NOT NULL,
  `fechaVisita` date NOT NULL,
  `emocion` varchar(50) NOT NULL,
  PRIMARY KEY (`idViaje`,`idMuseo`),
  KEY `idMuseo` (`idMuseo`),
  CONSTRAINT `viajemuseo_ibfk_1` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`),
  CONSTRAINT `viajemuseo_ibfk_2` FOREIGN KEY (`idMuseo`) REFERENCES `museo` (`idMuseo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viajemuseo`
--

LOCK TABLES `viajemuseo` WRITE;
/*!40000 ALTER TABLE `viajemuseo` DISABLE KEYS */;
INSERT INTO `viajemuseo` VALUES (1,1,'2025-03-11','FELICIDAD'),(1,2,'2025-03-12','SORPRESA'),(2,1,'2025-04-02','NOSTALGIA');
/*!40000 ALTER TABLE `viajemuseo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-24 22:58:38
