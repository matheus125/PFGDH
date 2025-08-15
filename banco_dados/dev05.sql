CREATE DATABASE  IF NOT EXISTS `dev05` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dev05`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: dev05
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `tb_dependentes`
--

DROP TABLE IF EXISTS `tb_dependentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_dependentes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_titular` int DEFAULT NULL,
  `nome_dependente` varchar(100) DEFAULT NULL,
  `rg` varchar(10) DEFAULT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `Idade` int DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `dependencia_cliente` varchar(50) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_dependentes_ibfk_1` (`id_titular`),
  CONSTRAINT `tb_dependentes_ibfk_1` FOREIGN KEY (`id_titular`) REFERENCES `tb_titular` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_dependentes`
--


--
-- Table structure for table `tb_endereco`
--

DROP TABLE IF EXISTS `tb_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_endereco` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cep` varchar(20) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `numero` varchar(20) DEFAULT NULL,
  `referencia` varchar(100) DEFAULT NULL,
  `nacionalidade` varchar(50) DEFAULT NULL,
  `naturalidade` varchar(50) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `tempoDeMoradia_cliente` varchar(30) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_endereco`
--


--
-- Table structure for table `tb_familia`
--

DROP TABLE IF EXISTS `tb_familia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_familia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_titular` int NOT NULL,
  `nome_familia` varchar(150) DEFAULT NULL,
  `grupo_familiar` varchar(100) DEFAULT NULL,
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `tb_familia_ibfk_1` (`id_titular`),
  CONSTRAINT `tb_familia_ibfk_1` FOREIGN KEY (`id_titular`) REFERENCES `tb_titular` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_familia`
--

--
-- Table structure for table `tb_frequencia_diaria`
--

DROP TABLE IF EXISTS `tb_frequencia_diaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_frequencia_diaria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cliente_id` int DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `data` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_frequencia_diaria`
--


--
-- Table structure for table `tb_frequencia_geral`
--

DROP TABLE IF EXISTS `tb_frequencia_geral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_frequencia_geral` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cliente_id` int DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `data` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=777 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_frequencia_geral`
--


--
-- Table structure for table `tb_funcionario`
--

DROP TABLE IF EXISTS `tb_funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_funcionario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `funcao` varchar(50) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_funcionario`
--

LOCK TABLES `tb_funcionario` WRITE;
/*!40000 ALTER TABLE `tb_funcionario` DISABLE KEYS */;
INSERT INTO `tb_funcionario` VALUES (1,'Administrador','ASSESSOR','(92) 98201-0100','2024-10-30 02:27:17',NULL);
/*!40000 ALTER TABLE `tb_funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_refeicoes_vendidas`
--

DROP TABLE IF EXISTS `tb_refeicoes_vendidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_refeicoes_vendidas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total_servido` int NOT NULL,
  `data` varchar(20) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_refeicoes_vendidas`
--

--
-- Table structure for table `tb_relatorios`
--

DROP TABLE IF EXISTS `tb_relatorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_relatorios` (
  `Idade_3a17Masculino` int NOT NULL,
  `Idade_3a17Masculino_PCD` int NOT NULL,
  `Idade_3a17Feminino` int NOT NULL,
  `Idade_3a17Feminino_PCD` int NOT NULL,
  `Idade_18a59Masculino` int NOT NULL,
  `Idade_18a59Masculino_PCD` int NOT NULL,
  `Idade_17a59Feminino` int NOT NULL,
  `Idade_17a59Feminino_PCD` int NOT NULL,
  `Idade_60Masculino` int NOT NULL,
  `Idade_60Masculino_PCD` int NOT NULL,
  `Idade_60Feminino` int NOT NULL,
  `Idade_60Feminino_PCD` int NOT NULL,
  `Situacao_risco_masculino` int NOT NULL,
  `Situacao_risco_Feminino` int NOT NULL,
  `Deficientes` int NOT NULL,
  `senhas_genericas` int NOT NULL,
  `Total_pessoas_atendidas` int NOT NULL,
  `data` varchar(20) NOT NULL,
  PRIMARY KEY (`data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_relatorios`
--

--
-- Table structure for table `tb_resumodia`
--

DROP TABLE IF EXISTS `tb_resumodia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_resumodia` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_resumodia`
--

LOCK TABLES `tb_resumodia` WRITE;
/*!40000 ALTER TABLE `tb_resumodia` DISABLE KEYS */;
INSERT INTO `tb_resumodia` VALUES (1);
/*!40000 ALTER TABLE `tb_resumodia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_senhas`
--

DROP TABLE IF EXISTS `tb_senhas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_senhas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cliente` varchar(100) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `Idade` varchar(20) DEFAULT NULL,
  `Genero` varchar(45) DEFAULT NULL,
  `Deficiente` varchar(45) DEFAULT NULL,
  `tipoSenha` varchar(10) DEFAULT NULL,
  `status_cliente` varchar(45) DEFAULT NULL,
  `data_refeicao` varchar(10) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_senhas`
--

--
-- Table structure for table `tb_socio_economico`
--

DROP TABLE IF EXISTS `tb_socio_economico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_socio_economico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_titular` int DEFAULT NULL,
  `escolariedade` varchar(50) DEFAULT NULL,
  `renda_mensal_familia` varchar(50) DEFAULT NULL,
  `programas_sociais` varchar(50) DEFAULT NULL,
  `composicao_familiar` varchar(50) DEFAULT NULL,
  `moradia` varchar(50) DEFAULT NULL,
  `estrutura_Moradia` varchar(50) DEFAULT NULL,
  `qtdPessoas_Trabalhando` int DEFAULT NULL,
  `emprego` varchar(50) DEFAULT NULL,
  `profissao_Responsavel` varchar(50) DEFAULT NULL,
  `AB_Agua` varchar(3) DEFAULT NULL,
  `SN_basico` varchar(3) DEFAULT NULL,
  `Energia_eletrica` varchar(3) DEFAULT NULL,
  `Lixo_Domiciliar` varchar(15) DEFAULT NULL,
  `frequenta_Centro` varchar(5) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_socio_economico_ibfk_1` (`id_titular`),
  CONSTRAINT `tb_socio_economico_ibfk_1` FOREIGN KEY (`id_titular`) REFERENCES `tb_titular` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_socio_economico`
--

--
-- Table structure for table `tb_socio_economico_saude`
--

DROP TABLE IF EXISTS `tb_socio_economico_saude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_socio_economico_saude` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_titular` int DEFAULT NULL,
  `doenca` varchar(100) DEFAULT NULL,
  `outras_Doencas` varchar(100) DEFAULT NULL,
  `deficiencia` varchar(3) DEFAULT NULL,
  `justificativa_Deficiencia` varchar(100) DEFAULT NULL,
  `laudo` varchar(100) DEFAULT NULL,
  `observacao` varchar(100) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_socio_economico_saude_ibfk_1` (`id_titular`),
  CONSTRAINT `tb_socio_economico_saude_ibfk_1` FOREIGN KEY (`id_titular`) REFERENCES `tb_titular` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_socio_economico_saude`
--

--
-- Table structure for table `tb_titular`
--

DROP TABLE IF EXISTS `tb_titular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_titular` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_endereco` int DEFAULT NULL,
  `nome_Completo` varchar(100) DEFAULT NULL,
  `nome_Social` varchar(100) DEFAULT NULL,
  `cor_cliente` varchar(100) DEFAULT NULL,
  `nome_Mae` varchar(100) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `data_Nascimento` varchar(20) DEFAULT NULL,
  `idade_cliente` int DEFAULT NULL,
  `genero_cliente` varchar(30) DEFAULT NULL,
  `estado_Civil` varchar(50) DEFAULT NULL,
  `rg` varchar(20) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `nis` varchar(30) DEFAULT NULL,
  `status_Cliente` varchar(30) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_titular_endereco` (`id_endereco`),
  CONSTRAINT `fk_titular_endereco` FOREIGN KEY (`id_endereco`) REFERENCES `tb_endereco` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_titular`
--

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_funcionario` int DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `password` varchar(100) NOT NULL,
  `perfil` varchar(50) NOT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_funcionario` (`id_funcionario`),
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`id_funcionario`) REFERENCES `tb_funcionario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,1,'000.000.000-00','123','Administrador','2024-10-30 02:27:17',NULL);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_userlogs`
--

DROP TABLE IF EXISTS `tb_userlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_userlogs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `login` varchar(15) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `registration_date_update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_userlogs_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=495 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_userlogs`
--

--
-- Dumping events for database 'dev05'
--

--
-- Dumping routines for database 'dev05'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_deletar_dependente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deletar_dependente`(IN p_id_dependente INT)
BEGIN
    -- Apenas exclui o dependente
    DELETE FROM tb_dependentes
    WHERE id = p_id_dependente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_deletar_titular` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deletar_titular`(IN p_id_titular INT)
BEGIN
    DECLARE v_id_endereco INT;
    DECLARE v_uso_endereco INT;

    -- 1. Obter o id_endereco do titular
    SELECT id_endereco INTO v_id_endereco
    FROM tb_titular
    WHERE id = p_id_titular;

    -- 2. Deletar o titular (isso vai apagar também dependentes, socioeconomico, socioeconomico_saude)
    DELETE FROM tb_titular
    WHERE id = p_id_titular;

    -- 3. Verificar se o endereço está sendo usado por outro titular
    SELECT COUNT(*) INTO v_uso_endereco
    FROM tb_titular
    WHERE id_endereco = v_id_endereco;

    -- 4. Se ninguém mais usar o endereço, deletar da tabela tb_endereco
    IF v_uso_endereco = 0 AND v_id_endereco IS NOT NULL THEN
        DELETE FROM tb_endereco
        WHERE id = v_id_endereco;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_delete_funcionario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_funcionario`(  IN pid_funcionario int  )
BEGIN DECLARE vid_funcionario INT; SET FOREIGN_KEY_CHECKS = 0; SELECT id INTO vid_funcionario FROM tb_funcionario WHERE id = pid_funcionario; DELETE FROM tb_funcionario WHERE id = pid_funcionario; DELETE FROM tb_user WHERE id_funcionario = pid_funcionario; SET FOREIGN_KEY_CHECKS = 1; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_limpar_frequencia_diaria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_limpar_frequencia_diaria`( )
BEGIN truncate tb_frequencia_diaria; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_limpar_relatorios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_limpar_relatorios`( )
BEGIN truncate tb_relatorios; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_limpar_resumo_dia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_limpar_resumo_dia`( )
BEGIN truncate tb_resumodia; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_limpar_senhas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_limpar_senhas`( )
BEGIN truncate tb_senhas; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_listar_dependentes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_dependentes`( )
BEGIN SELECT d.id, d.nome_dependente, d.rg, d.cpf, d.Idade, d.genero, d.dependencia_cliente, d.registration_date FROM tb_titular t INNER JOIN tb_dependentes d on d.id_titular = t.id; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_pesquisarTitular` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pesquisarTitular`(p_nome varchar (100) )
BEGIN select * from tb_titular t INNER JOIN tb_endereco e on e.id = t.id_endereco WHERE nome_Completo like p_nome or cpf like p_nome; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_pesquisar_funcionarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pesquisar_funcionarios`(p_nome varchar (100) )
BEGIN SELECT * FROM tb_user u INNER JOIN tb_funcionario f on u.id_funcionario = f.id WHERE nome like p_nome ; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_cliente_risco` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_cliente_risco`(
pnome varchar (100),
pidade varchar (20)
)
BEGIN
	INSERT INTO tb_clientes_condicoes_risco (nomeCompleto, idade) VALUES (pnome, pidade);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_cliente_situacao_rua` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_cliente_situacao_rua`(
pnome_Completo varchar(100),
pidade_cliente int (12),
pgenero_cliente varchar(30), 
pstatus_Cliente varchar(30)
)
BEGIN 
        
    INSERT INTO tb_titular (nome_Completo, idade_cliente,genero_cliente, status_Cliente) 
    VALUES (pnome_Completo, pidade_cliente,pgenero_cliente, 'PESSOA EM SITUAÇÃO DE RUA'); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_dependentes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_dependentes`(
pidtitular int,
pnome_dependente varchar (100),
prg varchar (10),
pcpf varchar (15),
pIdade int,
pgenero varchar (10),
pdependencia_cliente varchar (50)
)
BEGIN DECLARE vid_dependente int; 
INSERT INTO tb_dependentes (id_titular, nome_dependente, rg, cpf, Idade, genero, dependencia_cliente) 
    VALUES (pidtitular, pnome_dependente, prg, pcpf, pIdade, pgenero, pdependencia_cliente);
    SET vid_dependente = last_insert_id();
    
     
    END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_funcionarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_funcionarios`(pnome varchar (100),pfuncao varchar (100),ptelefone varchar (15),plogin varchar (100),ppassword varchar (250),pperfil varchar (50) )
BEGIN DECLARE vidfuncionario INT; INSERT INTO tb_funcionario (nome, funcao, telefone) VALUES (pnome, pfuncao, ptelefone);
	
    SET vidfuncionario = last_insert_id();
    
    INSERT INTO tb_user (id_funcionario, login, password, perfil) 
    VALUES (vidfuncionario, plogin, ppassword, pperfil); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_refeicoes_vendidas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_refeicoes_vendidas`(ptotal_servido int,pdata varchar (20) )
BEGIN INSERT INTO tb_refeicoes_vendidas (total_servido, data) VALUES (ptotal_servido, pdata); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_senhas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_senhas`(
pCliente varchar (100),
pCpf varchar (45),
pGenero varchar (45),
pIdade varchar (45),
pDeficiente varchar (45),
pstatus_cliente varchar (45),
pdata_refeicao varchar (10) 
)
BEGIN INSERT INTO tb_senhas (cliente, cpf,Genero, Idade, Deficiente, tipoSenha, status_cliente, data_refeicao) 
    VALUES (pCliente, pCpf, pGenero, pIdade, pDeficiente,"NORMAL", pstatus_cliente, pdata_refeicao); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_senhas_genericas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_senhas_genericas`(pdata_refeicao varchar (10) )
BEGIN INSERT INTO tb_senhas (Idade, Genero, tipoSenha, data_refeicao) 
    VALUES (1,1,"GENÉRICO", pdata_refeicao); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_socio_economico` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_socio_economico`(pidtitular int,pescolariedade varchar(50), prenda_mensal_familia varchar(50), pprogramas_sociais varchar(50),pcomposicao_familiar varchar(50),pmoradia varchar(50),pestrutura_Moradia varchar(50), pqtdPessoas_Trabalhando int,pemprego varchar(50),pprofissao_Responsavel varchar(50), pAB_Agua varchar(3),pSN_basico varchar(3), pEnergia_eletrica varchar(3), pLixo_Domiciliar varchar(15),pfrequenta_Centro varchar(5) )
BEGIN 	INSERT INTO tb_socio_economico (id_titular, escolariedade, renda_mensal_familia, programas_sociais,
    composicao_familiar, moradia, estrutura_Moradia, qtdPessoas_Trabalhando, emprego, profissao_Responsavel,
    AB_Agua, SN_basico, Energia_eletrica, Lixo_Domiciliar, frequenta_Centro) 
    VALUES (pidtitular, pescolariedade, prenda_mensal_familia, pprogramas_sociais, pcomposicao_familiar,
    pmoradia, pestrutura_Moradia, pqtdPessoas_Trabalhando, pemprego, pprofissao_Responsavel, pAB_Agua,
    pSN_basico, pEnergia_eletrica, pLixo_Domiciliar, pfrequenta_Centro); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_socio_economico_saude` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_socio_economico_saude`(pidtitular int,pdoenca varchar(100), poutras_Doencas varchar(100), pdeficiencia varchar(3), pjustificativa_Deficiencia varchar(100), plaudo varchar(100), pobservacao varchar(100) )
BEGIN 	INSERT INTO tb_socio_economico_saude (id_titular, doenca, outras_Doencas, deficiencia,
    justificativa_Deficiencia, laudo, observacao) 
    VALUES (pidtitular, pdoenca, poutras_Doencas, pdeficiencia, pjustificativa_Deficiencia,
    plaudo, pobservacao); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_titular` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_titular`(pnome_Completo varchar(100),pnome_Social varchar(100),  
pcor_cliente varchar(100), 
pnome_Mae varchar(100), 
ptelefone varchar(20), 
pdata_Nascimento varchar(20), 
pidade_cliente int (12), 
pgenero_cliente varchar(30), 
pestado_Civil varchar(50), 
prg varchar(20), 
pcpf varchar(20), 
pnis varchar(30), 
pstatus_Cliente varchar(8),
pcep varchar(20),
pbairro varchar(50), 
prua varchar(100), 
pnumero varchar(20), 
preferencia varchar(100), 
pnacionalidade varchar(50), 
pnaturalidade varchar(50),
pcidade varchar(50),
ptempoDeMoradia_cliente varchar(30) )
BEGIN 	DECLARE videndereco, vidtitular int;
    
    INSERT INTO tb_endereco (cep, bairro, rua, numero, referencia, nacionalidade, naturalidade, cidade, tempoDeMoradia_cliente) 
    VALUES (pcep, pbairro, prua, pnumero, preferencia, pnacionalidade, pnaturalidade, pcidade, ptempoDeMoradia_cliente);
    SET videndereco = last_insert_id();
    
    INSERT INTO tb_titular (id_endereco, nome_Completo, nome_Social, cor_cliente, nome_Mae, telefone, data_Nascimento, 
    idade_cliente, genero_cliente, estado_Civil, rg, cpf, nis, status_Cliente) 
    VALUES (videndereco, pnome_Completo, pnome_Social, pcor_cliente, pnome_Mae, ptelefone, pdata_Nascimento, 
    pidade_cliente, pgenero_cliente, pestado_Civil, prg, pcpf, pnis, pstatus_Cliente); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_salvar_titular2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_salvar_titular2`(
pnome_Completo varchar(100),
pidade_cliente int (12),
pgenero_cliente varchar(30), 
pstatus_Cliente varchar(30)
)
BEGIN 
        
    INSERT INTO tb_titular (nome_Completo, idade_cliente,genero_cliente, status_Cliente) 
    VALUES (pnome_Completo, pidade_cliente,pgenero_cliente, 'PESSOA EM SITUAÇÃO DE RUA'); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_update_dependente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_dependente`(IN pid int, IN pid_titular int,   IN pnome_dependente varchar(100),  IN prg varchar(10), IN pcpf varchar(15), IN pIdade int, IN pgenero varchar(10), IN pdependencia_cliente varchar(50) )
BEGIN 	UPDATE tb_dependentes set id_titular = pid_titular, nome_dependente = pnome_dependente, rg = prg, cpf = pcpf, 
    Idade = pIdade, genero = pgenero, dependencia_cliente = pdependencia_cliente, registration_date_update = CURRENT_TIMESTAMP
    WHERE id = pid; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_update_funcionarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_funcionarios`(IN pid int,IN pnome varchar (100),IN pfuncao varchar (100),IN ptelefone varchar (15),IN plogin varchar (100),IN ppassword varchar (250),IN pperfil varchar (50) )
BEGIN 	UPDATE tb_funcionario set nome = pnome, funcao = pfuncao, telefone = ptelefone, registration_date_update = CURRENT_TIMESTAMP WHERE id = pid;
    UPDATE tb_user set id_funcionario = pid, login = plogin , password = ppassword, perfil = pperfil, registration_date_update = CURRENT_TIMESTAMP  WHERE id = pid; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_update_titular` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_titular`(IN pid int,IN pnome_Completo varchar(100), IN pnome_Social varchar(100), IN pcor_cliente varchar(100), IN pnome_Mae varchar(100), IN ptelefone varchar(20), IN pdata_Nascimento varchar(20), IN pidade_cliente int(12), IN pgenero_cliente varchar(30), IN pestado_Civil varchar(50), IN prg varchar(20), IN pcpf varchar(20), IN pnis varchar(30), IN pstatus_Cliente varchar(8),IN pcep varchar(20),IN pbairro varchar(50), IN prua varchar(100), IN pnumero varchar(20), IN preferencia varchar(100), IN pnacionalidade varchar(50), IN pnaturalidade varchar(50),IN pcidade varchar(50),IN ptempoDeMoradia_cliente varchar(30) )
BEGIN 	UPDATE tb_endereco set cep = pcep, bairro = pbairro, rua = prua, numero = pnumero, 
    referencia = preferencia, nacionalidade = pnacionalidade, naturalidade = pnaturalidade, cidade = pcidade,
    tempoDeMoradia_cliente = ptempoDeMoradia_cliente, registration_date_update = CURRENT_TIMESTAMP
    WHERE id = pid;

	UPDATE tb_titular set id_endereco = pid, nome_Completo = pnome_Completo, nome_Social = pnome_Social,
    cor_cliente = pcor_cliente, nome_Mae = pnome_Mae, telefone = ptelefone, data_Nascimento = pdata_Nascimento,
    idade_cliente = pidade_cliente, genero_cliente = pgenero_cliente, estado_Civil = pestado_Civil,
    rg = prg, cpf = pcpf, nis = pnis ,status_Cliente = pstatus_Cliente, registration_date_update = CURRENT_TIMESTAMP
    WHERE id = pid; END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-13 16:30:14
