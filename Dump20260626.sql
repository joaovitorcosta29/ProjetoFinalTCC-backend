CREATE DATABASE  IF NOT EXISTS `db_gestao_frota` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `db_gestao_frota`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_gestao_frota
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `senha` varchar(80) NOT NULL,
  `cargo` enum('MOTORISTA','ADMIN') NOT NULL DEFAULT 'MOTORISTA',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'João Vitor Costa','joaocosta@gmail.com','senha123','ADMIN'),(2,'Matheus Henrique Martins','matheus.henrique@gmail.com','40028922','MOTORISTA');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_veiculos`
--

DROP TABLE IF EXISTS `tb_veiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_veiculos` (
  `id_veiculo` int(11) NOT NULL AUTO_INCREMENT,
  `placa` varchar(7) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `ano_fabricacao` int(11) NOT NULL,
  `km_atual` decimal(11,2) NOT NULL DEFAULT 0.00,
  `km_ultima_manutencao` decimal(11,2) DEFAULT 0.00,
  `status` enum('DISPONIVEL','EM_USO','EM_MANUTENÇÃO') NOT NULL DEFAULT 'DISPONIVEL',
  PRIMARY KEY (`id_veiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_veiculos`
--

LOCK TABLES `tb_veiculos` WRITE;
/*!40000 ALTER TABLE `tb_veiculos` DISABLE KEYS */;
INSERT INTO `tb_veiculos` VALUES (1,'AZA1D23','Marcopolo Torino',2014,180000.00,85000.00,'DISPONIVEL'),(2,'AUZ9K87','Marcopolo Torino',2025,22000.00,NULL,'DISPONIVEL');
/*!40000 ALTER TABLE `tb_veiculos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_viagens`
--

DROP TABLE IF EXISTS `tb_viagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_viagens` (
  `id_viagem` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_veiculo` int(11) NOT NULL,
  `destino` varchar(100) NOT NULL,
  `km_inicial` decimal(7,2) NOT NULL,
  `km_final` decimal(7,2) DEFAULT NULL,
  `status_viagem` enum('EM_ANDAMENTO','FINALIZADA') NOT NULL DEFAULT 'EM_ANDAMENTO',
  `alerta_manutencao` enum('OK','REVISAO_NECESSARIA') NOT NULL DEFAULT 'OK',
  PRIMARY KEY (`id_viagem`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_veiculo` (`id_veiculo`),
  CONSTRAINT `tb_viagens_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  CONSTRAINT `tb_viagens_ibfk_2` FOREIGN KEY (`id_veiculo`) REFERENCES `tb_veiculos` (`id_veiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_viagens`
--

LOCK TABLES `tb_viagens` WRITE;
/*!40000 ALTER TABLE `tb_viagens` DISABLE KEYS */;
INSERT INTO `tb_viagens` VALUES (1,2,1,'Fábrica Centro-Oeste',1500.00,NULL,'EM_ANDAMENTO','OK');
/*!40000 ALTER TABLE `tb_viagens` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-26 17:23:43
