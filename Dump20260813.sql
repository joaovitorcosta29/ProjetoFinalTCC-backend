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
  `descricao` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status_manutencao` enum('PENDENTE','EM_ANDAMENTO','CONCLUIDA','CANCELADA') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_manutencao`),
  KEY `id_veiculo` (`id_veiculo`),
  CONSTRAINT `tb_manutencoes_ibfk_1` FOREIGN KEY (`id_veiculo`) REFERENCES `tb_veiculos` (`id_veiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_manutencoes`
--

LOCK TABLES `tb_manutencoes` WRITE;
/*!40000 ALTER TABLE `tb_manutencoes` DISABLE KEYS */;
INSERT INTO `tb_manutencoes` VALUES (1,2,'Troca de óleo','EM_ANDAMENTO'),(2,2,'Troca de vela','PENDENTE'),(13,1,'Troca de oleo e filtros e revisao dos freios','CONCLUIDA'),(14,3,'Substituicao da correia dentada e revisao do motor','EM_ANDAMENTO'),(40,18,'Troca de pneus e alinhamento','PENDENTE'),(41,8,'Reparo no sistema eletrico e troca da bateria','CONCLUIDA'),(42,10,'Revisao geral de 480.000 km','EM_ANDAMENTO'),(43,16,'Troca de oleo preventiva','CONCLUIDA'),(44,3,'Troca da embreagem','PENDENTE'),(45,6,'Verificacao do ar-condicionado','CANCELADA'),(46,9,'Revisao dos 115.000 km','CONCLUIDA'),(47,11,'Diagnostico de vazamento de oleo','PENDENTE');
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
  `nome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `senha` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cargo` enum('MOTORISTA','ADMIN','GESTOR_FROTA') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'MOTORISTA',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'João Vitor Costa','joaocosta@gmail.com','senha123','ADMIN'),(2,'Matheus Henrique Martins','matheus.henrique@gmail.com','40028922','MOTORISTA'),(3,'Alysson Ferreira','alyssonferreira@gmail.com','12345678','MOTORISTA'),(5,'Luiz felipe','Luizfelipe@email.com','12345678','GESTOR_FROTA'),(6,'Gustavo Maia','gustavomaia@gmail.com','12345678','GESTOR_FROTA'),(7,'Gabriel Silva','gabrielsilva@gmail.com','12345678','GESTOR_FROTA'),(8,'Carlos Eduardo Silva','carlos.silva@gmail.com','senha123!','ADMIN'),(9,'Mariana Souza Lima','mariana.lima@gmail.com','gestor2024','GESTOR_FROTA'),(10,'Joao Pedro Andrade','joao.andrade@gmail.com','motorista1','MOTORISTA'),(11,'Fernanda Costa Ribeiro','fernanda.ribeiro@gmail.com','fernanda24','MOTORISTA'),(12,'Ricardo Almeida Souza','ricardo.souza@gmail.com','ricardo2024','MOTORISTA'),(13,'Patricia Gomes Nunes','patricia.nunes@gmail.com','patricia123','MOTORISTA'),(14,'Lucas Henrique Martins','lucas.martins@gmail.com','lucas12345','MOTORISTA'),(15,'Aline Beatriz Rocha','aline.rocha@gmail.com','aline2024!','GESTOR_FROTA'),(16,'Bruno Cesar Ferreira','bruno.ferreira@gmail.com','bruno12345','MOTORISTA'),(17,'Camila Rodrigues Dias','camila.dias@gmail.com','camila1998','MOTORISTA');
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
  `placa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `modelo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ano_fabricacao` int NOT NULL,
  `km_atual` double DEFAULT NULL,
  `km_ultima_manutencao` double DEFAULT NULL,
  `status` enum('DISPONIVEL','EM_USO','MANUTENCAO','DESATIVADO') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `alerta_manutencao` enum('OK','REVISAO_NECESSARIA') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'OK',
  PRIMARY KEY (`id_veiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_veiculos`
--

LOCK TABLES `tb_veiculos` WRITE;
/*!40000 ALTER TABLE `tb_veiculos` DISABLE KEYS */;
INSERT INTO `tb_veiculos` VALUES (1,'AZA-1D23','Marcopolo Torino 2014',2013,1200,85000,'DISPONIVEL','OK'),(2,'AUZ-9K87','Marcopolo Torino 2014',2025,22000,0,'EM_USO','OK'),(3,'AUZ-3168','Marcopolo Torino 2014',2026,0,0,'DESATIVADO','OK'),(6,'ASA-0921','Marcopolo Torino 2014',2024,500256,450000,'DISPONIVEL','REVISAO_NECESSARIA'),(7,'PHP-1234','Marcopolo Torino 2014',2026,0,0,'DISPONIVEL','OK'),(8,'ABC-1234','Marcopolo Torino 2014',2022,12334,12000,'DISPONIVEL','OK'),(9,'APA-1K53','Marcopolo Torino 2014',2023,120000,100000,'EM_USO','OK'),(10,'BAT-8J46','Marcopolo Torino 2014',2026,0,0,'DISPONIVEL','OK'),(11,'RTX-2368','Marcopolo Torino 2014',2026,0,0,'DISPONIVEL','OK'),(12,'ABC1D23','Marcopolo Torino 2014',2022,145230,140000,'DISPONIVEL','OK'),(13,'BRA2E19','Marcopolo Torino 2014',2021,278900,265000,'EM_USO','REVISAO_NECESSARIA'),(14,'DEF4G56','Marcopolo Torino 2014',2020,412500,400000,'MANUTENCAO','REVISAO_NECESSARIA'),(15,'GHI7H89','Marcopolo Torino 2014',2023,121000,120000,'DISPONIVEL','OK'),(16,'BRA3F27','Marcopolo Torino 2014',2019,456800,440000,'EM_USO','REVISAO_NECESSARIA'),(17,'JKL0M12','Marcopolo Paradiso G8',2022,133200,130000,'DISPONIVEL','OK'),(18,'MNO3P45','Marcopolo Paradiso G8',2021,298700,290000,'DISPONIVEL','OK'),(19,'BRA5Q81','Marcopolo Paradiso G7',2020,287650,270000,'DESATIVADO','REVISAO_NECESSARIA'),(20,'PQR6S23','Marcopolo Paradiso G8',2023,115400,115000,'DISPONIVEL','OK'),(21,'BRA8T45','Marcopolo Paradiso G7',2018,487300,470000,'MANUTENCAO','REVISAO_NECESSARIA');
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
  `cidade_destino` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `km_inicial` double DEFAULT NULL,
  `km_final` double DEFAULT NULL,
  `status_viagem` enum('DISPONIVEL','EM_ANDAMENTO','FINALIZADA') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DISPONIVEL',
  `estado_destino` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_viagem`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_veiculo` (`id_veiculo`),
  CONSTRAINT `tb_viagens_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  CONSTRAINT `tb_viagens_ibfk_2` FOREIGN KEY (`id_veiculo`) REFERENCES `tb_veiculos` (`id_veiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_viagens`
--

LOCK TABLES `tb_viagens` WRITE;
/*!40000 ALTER TABLE `tb_viagens` DISABLE KEYS */;
INSERT INTO `tb_viagens` VALUES (1,2,1,'Fábrica Centro-Oeste',1500,2200,'FINALIZADA','ES'),(2,3,1,'asdsad',NULL,1200,'FINALIZADA','AM'),(3,3,6,'Ibiporã',500000,5000256,'FINALIZADA','PR'),(4,NULL,7,'Arapongas',0,NULL,'DISPONIVEL','PR'),(5,3,8,'Cambé',10000,12334,'FINALIZADA','PR'),(6,2,2,'Curitiba',22000,NULL,'EM_ANDAMENTO','PR'),(7,NULL,9,'Rolândia',120000,NULL,'DISPONIVEL','PR'),(8,NULL,8,'Maringá',10000,NULL,'DISPONIVEL','PR'),(9,NULL,20,'Porto Alegre',115400,NULL,'DISPONIVEL','RS'),(10,NULL,11,'Cascavel',0,NULL,'DISPONIVEL','PR'),(11,NULL,13,'Florianopolis',278900,NULL,'DISPONIVEL','SC'),(12,NULL,17,'Ribeirão Preto',133200,NULL,'DISPONIVEL','SP');
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

-- Dump completed on 2026-08-13 17:02:21
