CREATE DATABASE  IF NOT EXISTS `db_gestao_frota` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_gestao_frota`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_gestao_frota
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `tb_manutencoes`
--

DROP TABLE IF EXISTS `tb_manutencoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_manutencoes` (
  `id_manutencao` int NOT NULL AUTO_INCREMENT,
  `id_veiculo` int NOT NULL,
  `descricao` varchar(300) COLLATE utf8mb4_general_ci NOT NULL,
  `status_manutencao` enum('PENDENTE','EM_ANDAMENTO','CONCLUIDA','CANCELADA') COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_manutencao`),
  KEY `id_veiculo` (`id_veiculo`),
  CONSTRAINT `tb_manutencoes_ibfk_1` FOREIGN KEY (`id_veiculo`) REFERENCES `tb_veiculos` (`id_veiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_manutencoes`
--

LOCK TABLES `tb_manutencoes` WRITE;
/*!40000 ALTER TABLE `tb_manutencoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_manutencoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `senha` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cargo` enum('MOTORISTA','ADMIN','GESTOR_FROTA') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'MOTORISTA',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'João Vitor Costa','joaocosta@gmail.com','senha123','ADMIN'),(2,'Matheus Henrique Martins','matheus.henrique@gmail.com','40028922','MOTORISTA'),(3,'a','a@gmail.com','12345678','MOTORISTA'),(5,'Luiz felipe','Luizfelipe@email.com','12345678','GESTOR_FROTA'),(6,'Gustavo Maia','gustavomaia@gmail.com','12345678','GESTOR_FROTA'),(7,'b','b@gmail.com','12345678','GESTOR_FROTA');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario_seq`
--

DROP TABLE IF EXISTS `tb_usuario_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario_seq`
--

LOCK TABLES `tb_usuario_seq` WRITE;
/*!40000 ALTER TABLE `tb_usuario_seq` DISABLE KEYS */;
INSERT INTO `tb_usuario_seq` VALUES (1);
/*!40000 ALTER TABLE `tb_usuario_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_veiculos`
--

DROP TABLE IF EXISTS `tb_veiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_veiculos` (
  `id_veiculo` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `modelo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ano_fabricacao` int NOT NULL,
  `km_atual` double DEFAULT NULL,
  `km_ultima_manutencao` double DEFAULT NULL,
  `status` enum('DISPONIVEL','EM_USO','MANUTENCAO','DESATIVADO') COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_veiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_veiculos`
--

LOCK TABLES `tb_veiculos` WRITE;
/*!40000 ALTER TABLE `tb_veiculos` DISABLE KEYS */;
INSERT INTO `tb_veiculos` VALUES (1,'AZA1D23','Marcopolo Torino',2014,180000,85000,'MANUTENCAO'),(2,'AUZ9K87','Marcopolo Torino',2025,22000,NULL,'DISPONIVEL'),(3,'AUZ-3168','Marcopolo Torino',2026,0,0,'DESATIVADO'),(6,'ASA-0921','Marcopolo Torino',2020,500000,450000,'EM_USO'),(7,'PHP','Marcopolo Torino',2026,0,0,'EM_USO');
/*!40000 ALTER TABLE `tb_veiculos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_veiculos_seq`
--

DROP TABLE IF EXISTS `tb_veiculos_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_veiculos_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_veiculos_seq`
--

LOCK TABLES `tb_veiculos_seq` WRITE;
/*!40000 ALTER TABLE `tb_veiculos_seq` DISABLE KEYS */;
INSERT INTO `tb_veiculos_seq` VALUES (1);
/*!40000 ALTER TABLE `tb_veiculos_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_viagens`
--

DROP TABLE IF EXISTS `tb_viagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_viagens` (
  `id_viagem` bigint NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `id_veiculo` int NOT NULL,
  `cidade_destino` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `km_inicial` double DEFAULT NULL,
  `km_final` double DEFAULT NULL,
  `status_viagem` enum('DISPONIVEL','EM_ANDAMENTO','FINALIZADA') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DISPONIVEL',
  `alerta_manutencao` enum('OK','REVISAO_NECESSARIA') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'OK',
  `estado_destino` varchar(2) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_viagem`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_veiculo` (`id_veiculo`),
  CONSTRAINT `tb_viagens_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  CONSTRAINT `tb_viagens_ibfk_2` FOREIGN KEY (`id_veiculo`) REFERENCES `tb_veiculos` (`id_veiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_viagens`
--

LOCK TABLES `tb_viagens` WRITE;
/*!40000 ALTER TABLE `tb_viagens` DISABLE KEYS */;
INSERT INTO `tb_viagens` VALUES (1,2,1,'Fábrica Centro-Oeste',1500,2200,'FINALIZADA','OK',NULL),(2,NULL,1,'asdsad',NULL,NULL,'DISPONIVEL','OK','AM'),(3,NULL,6,'Ibiporã',500000,NULL,'EM_ANDAMENTO','OK','PR'),(4,NULL,7,'Arapongas',0,NULL,'EM_ANDAMENTO','OK','PR');
/*!40000 ALTER TABLE `tb_viagens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_viagens_seq`
--

DROP TABLE IF EXISTS `tb_viagens_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_viagens_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_viagens_seq`
--

LOCK TABLES `tb_viagens_seq` WRITE;
/*!40000 ALTER TABLE `tb_viagens_seq` DISABLE KEYS */;
INSERT INTO `tb_viagens_seq` VALUES (1);
/*!40000 ALTER TABLE `tb_viagens_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-31 17:23:49
