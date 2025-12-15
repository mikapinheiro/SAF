SET FOREIGN_KEY_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = 1;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;

CREATE DATABASE IF NOT EXISTS `analisefalhas_petro` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `analisefalhas_petro`;

-- Copiando estrutura para tabela analisefalhas_petro.tb_acoplamento
CREATE TABLE IF NOT EXISTS `tb_acoplamento` (
  `id_acoplamento` int(11) NOT NULL AUTO_INCREMENT,
  `nome_acoplamento` varchar(100) NOT NULL,
  PRIMARY KEY (`id_acoplamento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_acoplamento: ~5 rows (aproximadamente)
INSERT INTO `tb_acoplamento` (`id_acoplamento`, `nome_acoplamento`) VALUES
	(1, 'Desconectável'),
	(2, 'Fixo'),
	(3, 'Flexível'),
	(4, 'Hidráulico'),
	(5, 'Magnético');

-- Copiando estrutura para tabela analisefalhas_petro.tb_ativo
CREATE TABLE IF NOT EXISTS `tb_ativo` (
  `id_ativo` int(11) NOT NULL AUTO_INCREMENT,
  `nome_ativo` varchar(100) NOT NULL,
  PRIMARY KEY (`id_ativo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_ativo: ~1 rows (aproximadamente)
INSERT INTO `tb_ativo` (`id_ativo`, `nome_ativo`) VALUES
	(1, 'IMI');

-- Copiando estrutura para tabela analisefalhas_petro.tb_cadastro_usuario
CREATE TABLE IF NOT EXISTS `tb_cadastro_usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome_completo` varchar(200) NOT NULL,
  `matricula` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha_hash` varchar(255) NOT NULL,
  `id_tipo_usuario` int(11) NOT NULL,
  `id_cargo` int(11) NOT NULL,
  `id_unidade` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `matricula` (`matricula`),
  UNIQUE KEY `email` (`email`),
  KEY `id_tipo_usuario` (`id_tipo_usuario`),
  KEY `id_cargo` (`id_cargo`),
  KEY `id_unidade` (`id_unidade`),
  CONSTRAINT `tb_cadastro_usuario_ibfk_1` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tb_tipo_usuario` (`id_tipo_usuario`),
  CONSTRAINT `tb_cadastro_usuario_ibfk_2` FOREIGN KEY (`id_cargo`) REFERENCES `tb_cargo` (`id_cargo`),
  CONSTRAINT `tb_cadastro_usuario_ibfk_4` FOREIGN KEY (`id_unidade`) REFERENCES `tb_plataforma` (`id_plataforma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_cadastro_usuario: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela analisefalhas_petro.tb_cargo
CREATE TABLE IF NOT EXISTS `tb_cargo` (
  `id_cargo` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cargo` varchar(100) NOT NULL,
  PRIMARY KEY (`id_cargo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_cargo: ~5 rows (aproximadamente)
INSERT INTO `tb_cargo` (`id_cargo`, `nome_cargo`) VALUES
	(1, 'Engenharia'),
	(2, 'Inspeção de Equipamentos'),
	(3, 'Manutenção'),
	(4, 'Operação'),
	(5, 'SMS');

-- Copiando estrutura para tabela analisefalhas_petro.tb_categoria_equipamento
CREATE TABLE IF NOT EXISTS `tb_categoria_equipamento` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome_categoria` varchar(100) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_categoria_equipamento: ~10 rows (aproximadamente)
INSERT INTO `tb_categoria_equipamento` (`id_categoria`, `nome_categoria`) VALUES
	(1, 'Complementação de Poço'),
	(2, 'Elétricos'),
	(3, 'Intervenção de Poço'),
	(4, 'Maritímo'),
	(5, 'Mêcanico'),
	(6, 'Perfuração'),
	(7, 'Produção Submarina'),
	(8, 'Rotativos'),
	(9, 'Segurança e Controle'),
	(10, 'Utilidades');

-- Copiando estrutura para tabela analisefalhas_petro.tb_causa_falha
CREATE TABLE IF NOT EXISTS `tb_causa_falha` (
  `id_causa_falha` int(11) NOT NULL AUTO_INCREMENT,
  `nome_causa_falha` varchar(100) NOT NULL,
  PRIMARY KEY (`id_causa_falha`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_causa_falha: ~6 rows (aproximadamente)
INSERT INTO `tb_causa_falha` (`id_causa_falha`, `nome_causa_falha`) VALUES
	(1, 'Fabricação/ Instalação'),
	(2, 'Gestão'),
	(3, 'Operação/ Manutenção'),
	(4, 'Projeto'),
	(5, 'Miscelâneas'),
	(99, 'Não Informado / Desconhecido');

-- Copiando estrutura para tabela analisefalhas_petro.tb_classe_equipamento
CREATE TABLE IF NOT EXISTS `tb_classe_equipamento` (
  `id_classe_equipamento` int(11) NOT NULL AUTO_INCREMENT,
  `nome_classe_equipamento` varchar(100) NOT NULL,
  PRIMARY KEY (`id_classe_equipamento`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_classe_equipamento: ~63 rows (aproximadamente)
INSERT INTO `tb_classe_equipamento` (`id_classe_equipamento`, `nome_classe_equipamento`) VALUES
	(1, 'Aquecedores: Aquecedores elétricos, caldeiras, fornos.'),
	(2, 'Acoplamentos: Acoplamentos rígidos, flexíveis.'),
	(3, 'Atuador de Válvula'),
	(4, 'Bomba'),
	(5, 'Braço de Carregamento'),
	(6, 'Cabo de força e terminações'),
	(7, 'Canhão Lançador de PIG'),
	(8, 'Compressor'),
	(9, 'Conversor de frequência'),
	(10, 'Correias: Correias de transmissão, correias dentadas.'),
	(11, 'Detector de incêndio e gás'),
	(12, 'Dispositivos de Alarme: Alarmes sonoros, visuais, sistemas de alarme de incêndio.'),
	(13, 'Dispositivos de Proteção: Fusíveis, disjuntores, relés de proteção.'),
	(14, 'Eixos: Eixos de transmissão, eixos de comando.'),
	(15, 'Elevadores e Escadas Rolantes: Equipamentos para transporte vertical de pessoas ou materiais.'),
	(16, 'Elevadores: Elevadores de carga, elevadores de passageiros.'),
	(17, 'Extintores de Incêndio: Extintores portáteis, sistemas de sprinklers.'),
	(18, 'Filtro'),
	(19, 'Gerador Elétrico'),
	(20, 'Geradores Elétricos: Geradores de corrente alternada (AC), geradores de corrente contínua (DC).'),
	(21, 'Guindaste'),
	(22, 'Guindastes: Guindastes de pórtico, guindastes de braço articulado.'),
	(23, 'Iluminação: Luminárias, refletores, sistemas de iluminação de emergência.'),
	(24, 'Instrumento ou Chave de controle'),
	(25, 'Instrumentos de Medição: Medidores de vazão, analisadores de gás, medidores de nível.'),
	(26, 'Medidores de Ruído: Medidores de nível de pressão sonora.'),
	(27, 'Misturador'),
	(28, 'Motor de Combustão Interna'),
	(29, 'Motor Elétrico'),
	(30, 'Motor Hidráulico'),
	(31, 'Motores Elétricos: Motores de indução, motores síncronos.'),
	(32, 'Motores: Motores elétricos, motores a combustão.'),
	(33, 'Painéis de Controle: Painéis de distribuição de energia, quadros de comandos.'),
	(34, 'Painel / Quadro de distribuição'),
	(35, 'PLC - Controlador Lógico Programável'),
	(36, 'Reatores: Reatores químicos, bioreatores, etc.'),
	(37, 'Redes de Comunicação Industrial: Redes de fibra ótica, Ethernet industrial.'),
	(38, 'Redutores: Redutores de velocidade, redutores planetários.'),
	(39, 'RTU - Unid. de Transmissão Remota'),
	(40, 'Sensores de Gás: Sensores de gás explosivo, sensores de CO2, sensores de monóxido de carbono.'),
	(41, 'Sensores de Partículas: Sensores de poeira no ar, medidores de partículas em suspensão.'),
	(42, 'Sensores de Pressão: Manômetros, transdutores de pressão.'),
	(43, 'Sensores de Temperatura: Termopares, RTDs.'),
	(44, 'Separadores: Separadores de fase (óleo, gás, água).'),
	(45, 'Sistemas de Controle Remoto: Sistemas de controle remoto para operação de válvulas ou equipamentos.'),
	(46, 'Sistemas de Drenagem: Bombas de drenagem, sistemas de esgoto.'),
	(47, 'Sistemas de Rádio: Rádio de comunicação de duas vias.'),
	(48, 'Sistemas de Refrigeração: Condensadores, evaporadores, chillers, torres de resfriamento.'),
	(49, 'Sistemas de Supressão de Incêndio: Sprinklers automáticos, sistemas de CO2.'),
	(50, 'Sistemas de Ventilação: Ventiladores, exaustores, dutos de ventilação.'),
	(51, 'Soprador / ventilador'),
	(52, 'Tanque de armazenamento'),
	(53, 'Transformador'),
	(54, 'Transformadores: Transformadores de potência, transformadores de distribuição.'),
	(55, 'Transportadores: Transportadores de correia, roscas transportadoras, elevadores de caneca.'),
	(56, 'Trocador de Calor'),
	(57, 'Tubulação'),
	(58, 'Turbina a Gas'),
	(59, 'Turbina a Vapor'),
	(60, 'Turboexpansor'),
	(61, 'UPS - Sist. Ininterrupto de Energia'),
	(62, 'Válvula'),
	(63, 'Vaso de Pressão');

-- Copiando estrutura para tabela analisefalhas_petro.tb_fabricante
CREATE TABLE IF NOT EXISTS `tb_fabricante` (
  `id_fabricante` int(11) NOT NULL AUTO_INCREMENT,
  `nome_fabricante` varchar(100) NOT NULL,
  PRIMARY KEY (`id_fabricante`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_fabricante: ~26 rows (aproximadamente)
INSERT INTO `tb_fabricante` (`id_fabricante`, `nome_fabricante`) VALUES
	(1, 'AAB'),
	(2, 'AIRMARINE'),
	(3, 'ALFA LAVAL'),
	(4, 'Alfalaval'),
	(5, 'BS&B'),
	(6, 'BUSCH'),
	(7, 'EBARA'),
	(8, 'FLOWSERVE'),
	(9, 'GARO'),
	(10, 'HBR'),
	(11, 'Hilliard'),
	(12, 'HSV'),
	(13, 'Ingersoll-Rand'),
	(14, 'KSB'),
	(15, 'LEWA'),
	(16, 'MCFARLAND-TRITAN, LLC'),
	(17, 'NETZCH'),
	(18, 'Peroni'),
	(19, 'Rolls Royce'),
	(20, 'Rotork'),
	(21, 'SULZER'),
	(22, 'Tormene'),
	(23, 'Wartsila'),
	(24, 'WEG'),
	(25, 'WEIR VALVES'),
	(99, 'Não Informado / Desconhecido');

-- Copiando estrutura para tabela analisefalhas_petro.tb_historico_acao
CREATE TABLE IF NOT EXISTS `tb_historico_acao` (
  `id_acao` int(11) NOT NULL AUTO_INCREMENT,
  `id_falha` int(11) NOT NULL,
  `id_usuario_acao` int(11) NOT NULL,
  `data_hora_acao` datetime NOT NULL DEFAULT current_timestamp(),
  `descricao_acao` text NOT NULL,
  PRIMARY KEY (`id_acao`),
  KEY `id_falha` (`id_falha`),
  KEY `id_usuario_acao` (`id_usuario_acao`),
  CONSTRAINT `tb_historico_acao_ibfk_1` FOREIGN KEY (`id_falha`) REFERENCES `tb_registro_falha` (`id_falha`),
  CONSTRAINT `tb_historico_acao_ibfk_2` FOREIGN KEY (`id_usuario_acao`) REFERENCES `tb_cadastro_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_historico_acao: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela analisefalhas_petro.tb_instalacao
CREATE TABLE IF NOT EXISTS `tb_instalacao` (
  `id_instalacao` int(11) NOT NULL AUTO_INCREMENT,
  `nome_instalacao` varchar(100) NOT NULL,
  PRIMARY KEY (`id_instalacao`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_instalacao: ~8 rows (aproximadamente)
INSERT INTO `tb_instalacao` (`id_instalacao`, `nome_instalacao`) VALUES
	(1, 'UN-BS/ATP-IMI/OP-P66'),
	(2, 'UN-BS/ATP-IMI/OP-P67'),
	(3, 'UN-BS/ATP-IMI/OP-P68'),
	(4, 'UN-BS/ATP-IMI/OP-P69'),
	(5, 'UN-BS/ATP-IMI/OP-P70'),
	(6, 'UN-BS/ATP-IMI/OP-P71'),
	(7, 'UN-BS/ATP-IMI/OP-PMLZ'),
	(8, 'UN-BS/ATP-IMI/OP-PMXL');

-- Copiando estrutura para tabela analisefalhas_petro.tb_mecanismo_falha
CREATE TABLE IF NOT EXISTS `tb_mecanismo_falha` (
  `id_mecanismo_falha` int(11) NOT NULL AUTO_INCREMENT,
  `nome_mecanismo_falha` varchar(100) NOT NULL,
  PRIMARY KEY (`id_mecanismo_falha`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_mecanismo_falha: ~7 rows (aproximadamente)
INSERT INTO `tb_mecanismo_falha` (`id_mecanismo_falha`, `nome_mecanismo_falha`) VALUES
	(1, 'Falha Elétrica'),
	(2, 'Falha de Material'),
	(3, 'Falha de Instrumento'),
	(4, 'Falha Mecânica'),
	(5, 'Influência Externa'),
	(6, 'Miscelâneas'),
	(99, 'Não Informado / Desconhecido');

-- Copiando estrutura para tabela analisefalhas_petro.tb_metodo_detectacao
CREATE TABLE IF NOT EXISTS `tb_metodo_detectacao` (
  `id_metodo_detectacao` int(11) NOT NULL AUTO_INCREMENT,
  `nome_metodo_detectacao` varchar(100) NOT NULL,
  PRIMARY KEY (`id_metodo_detectacao`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_metodo_detectacao: ~12 rows (aproximadamente)
INSERT INTO `tb_metodo_detectacao` (`id_metodo_detectacao`, `nome_metodo_detectacao`) VALUES
	(1, 'Inspeção'),
	(2, 'Interferência da Produção'),
	(3, 'Manutenção Corretiva'),
	(4, 'Manutenção Periódica'),
	(5, 'Monitoração Contínua da Condição'),
	(6, 'Monitoração Periódica da Condição'),
	(7, 'Observação Casual'),
	(8, 'Sob Demanda'),
	(9, 'Teste de Pressão'),
	(10, 'Teste Funcional'),
	(11, 'Outros'),
	(99, 'Não Informado / Desconhecido');

-- Copiando estrutura para tabela analisefalhas_petro.tb_origem_falha
CREATE TABLE IF NOT EXISTS `tb_origem_falha` (
  `id_origem_falha` int(11) NOT NULL AUTO_INCREMENT,
  `nome_origem_falha` varchar(100) NOT NULL,
  PRIMARY KEY (`id_origem_falha`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_origem_falha: ~6 rows (aproximadamente)
INSERT INTO `tb_origem_falha` (`id_origem_falha`, `nome_origem_falha`) VALUES
	(1, 'Fabricação'),
	(2, 'Instalação'),
	(3, 'Manutenção'),
	(4, 'Operação'),
	(5, 'Projeto'),
	(99, 'Não Informado / Desconhecido');

-- Copiando estrutura para tabela analisefalhas_petro.tb_planta
CREATE TABLE IF NOT EXISTS `tb_planta` (
  `id_planta` int(11) NOT NULL AUTO_INCREMENT,
  `nome_planta` varchar(100) NOT NULL,
  PRIMARY KEY (`id_planta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_planta: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela analisefalhas_petro.tb_plataforma
CREATE TABLE IF NOT EXISTS `tb_plataforma` (
  `id_plataforma` int(11) NOT NULL AUTO_INCREMENT,
  `nome_plataforma` varchar(100) NOT NULL,
  `sigla` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_plataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_plataforma: ~8 rows (aproximadamente)
INSERT INTO `tb_plataforma` (`id_plataforma`, `nome_plataforma`, `sigla`) VALUES
	(1, 'Plataforma 66', 'P-66'),
	(2, 'Plataforma 67', 'P-67'),
	(3, 'Plataforma 68', 'P-68'),
	(4, 'Plataforma 69', 'P-69'),
	(5, 'Plataforma 70', 'P-70'),
	(6, 'Plataforma 71', 'P-71'),
	(7, 'Plataforma PMXL', 'PMXL'),
	(8, 'Plataforma PMLZ', 'PMLZ');

-- Copiando estrutura para tabela analisefalhas_petro.tb_posicao
CREATE TABLE IF NOT EXISTS `tb_posicao` (
  `id_posicao` int(11) NOT NULL AUTO_INCREMENT,
  `nome_posicao` varchar(100) NOT NULL,
  PRIMARY KEY (`id_posicao`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_posicao: ~2 rows (aproximadamente)
INSERT INTO `tb_posicao` (`id_posicao`, `nome_posicao`) VALUES
	(1, 'Horizontal'),
	(2, 'Vertical');

-- Copiando estrutura para tabela analisefalhas_petro.tb_registro_equipamento
CREATE TABLE IF NOT EXISTS `tb_registro_equipamento` (
  `id_equipamento` int(11) NOT NULL AUTO_INCREMENT,
  `tag_equipamento` varchar(1000) NOT NULL,
  `descricao_equipamento` text DEFAULT NULL,
  `numero_serie` varchar(100) DEFAULT NULL,
  `data_instalacao` date DEFAULT NULL,
  `id_plataforma` int(11) NOT NULL,
  `id_sistema` int(11) NOT NULL,
  `id_instalacao` int(11) NOT NULL,
  `id_planta` int(11) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `id_classe_equipamento` int(11) DEFAULT NULL,
  `id_selagem` int(11) DEFAULT NULL,
  `id_tipo_corpo` int(11) DEFAULT NULL,
  `id_acoplamento` int(11) DEFAULT NULL,
  `id_tipo_mancal` int(11) DEFAULT NULL,
  `id_posicao` int(11) DEFAULT NULL,
  `id_tipo_transmissao` int(11) DEFAULT NULL,
  `fluido_bombeado` varchar(100) DEFAULT NULL,
  `tipo_servico` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `ano_fabricacao` int(11) DEFAULT NULL,
  `vazao` varchar(50) DEFAULT NULL,
  `potencia` varchar(50) DEFAULT NULL,
  `head` varchar(50) DEFAULT NULL,
  `npsha` varchar(50) DEFAULT NULL,
  `pressao_succao` varchar(50) DEFAULT NULL,
  `pressao_descarga` varchar(50) DEFAULT NULL,
  `diferenca_pressao` varchar(50) DEFAULT NULL,
  `norma_fabricacao` varchar(100) DEFAULT NULL,
  `numero_equipamento_pfceo` varchar(100) NOT NULL,
  `numero_equipamento_sap` varchar(100) NOT NULL,
  `centro_planejamento` varchar(100) NOT NULL,
  `local_instalacao_sap` varchar(100) NOT NULL,
  PRIMARY KEY (`id_equipamento`),
  UNIQUE KEY `tag_equipamento` (`tag_equipamento`) USING HASH,
  KEY `id_plataforma` (`id_plataforma`),
  KEY `id_sistema` (`id_sistema`),
  KEY `id_instalacao` (`id_instalacao`),
  KEY `id_planta` (`id_planta`),
  KEY `id_categoria` (`id_categoria`),
  KEY `id_classe_equipamento` (`id_classe_equipamento`),
  KEY `id_selagem` (`id_selagem`),
  KEY `id_tipo_corpo` (`id_tipo_corpo`),
  KEY `id_acoplamento` (`id_acoplamento`),
  KEY `id_tipo_mancal` (`id_tipo_mancal`),
  KEY `id_posicao` (`id_posicao`),
  KEY `id_tipo_transmissao` (`id_tipo_transmissao`),
  CONSTRAINT `tb_registro_equipamento_ibfk_1` FOREIGN KEY (`id_plataforma`) REFERENCES `tb_plataforma` (`id_plataforma`),
  CONSTRAINT `tb_registro_equipamento_ibfk_10` FOREIGN KEY (`id_tipo_mancal`) REFERENCES `tb_tipo_mancal` (`id_tipo_mancal`),
  CONSTRAINT `tb_registro_equipamento_ibfk_11` FOREIGN KEY (`id_posicao`) REFERENCES `tb_posicao` (`id_posicao`),
  CONSTRAINT `tb_registro_equipamento_ibfk_12` FOREIGN KEY (`id_tipo_transmissao`) REFERENCES `tb_tipo_transmissao` (`id_tipo_transmissao`),
  CONSTRAINT `tb_registro_equipamento_ibfk_2` FOREIGN KEY (`id_sistema`) REFERENCES `tb_sistema` (`id_sistema`),
  CONSTRAINT `tb_registro_equipamento_ibfk_3` FOREIGN KEY (`id_instalacao`) REFERENCES `tb_instalacao` (`id_instalacao`),
  CONSTRAINT `tb_registro_equipamento_ibfk_4` FOREIGN KEY (`id_planta`) REFERENCES `tb_planta` (`id_planta`),
  CONSTRAINT `tb_registro_equipamento_ibfk_5` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categoria_equipamento` (`id_categoria`),
  CONSTRAINT `tb_registro_equipamento_ibfk_6` FOREIGN KEY (`id_classe_equipamento`) REFERENCES `tb_classe_equipamento` (`id_classe_equipamento`),
  CONSTRAINT `tb_registro_equipamento_ibfk_7` FOREIGN KEY (`id_selagem`) REFERENCES `tb_selagem` (`id_selagem`),
  CONSTRAINT `tb_registro_equipamento_ibfk_8` FOREIGN KEY (`id_tipo_corpo`) REFERENCES `tb_tipo_corpo` (`id_tipo_corpo`),
  CONSTRAINT `tb_registro_equipamento_ibfk_9` FOREIGN KEY (`id_acoplamento`) REFERENCES `tb_acoplamento` (`id_acoplamento`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_registro_equipamento: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela analisefalhas_petro.tb_registro_falha
CREATE TABLE IF NOT EXISTS `tb_registro_falha` (
  `id_falha` int(11) NOT NULL AUTO_INCREMENT,
  `RTA` varchar(50) DEFAULT NULL,
  `data_ocorrencia` date NOT NULL,
  `descricao_falha` varchar(255) DEFAULT NULL,
  `TAG` varchar(50) NOT NULL,
  `id_ativo` int(11) NOT NULL,
  `id_usuario_registro` int(11) DEFAULT NULL,
  `id_plataforma` int(11) NOT NULL,
  `id_sistema` int(11) NOT NULL,
  `id_fabricante` int(11) NOT NULL,
  `id_classe_equipamento` int(11) NOT NULL,
  `id_mecanismo_falha` int(11) NOT NULL,
  `id_submecanismo_falha` int(11) NOT NULL,
  `id_causa_falha` int(11) NOT NULL,
  `id_subdivisao_causafalha` int(11) NOT NULL,
  `id_metodo_detectacao` int(11) NOT NULL,
  `id_origem_falha` int(11) NOT NULL,
  `id_status` INT(11),
  PRIMARY KEY (`id_falha`),
  KEY `id_usuario_registro` (`id_usuario_registro`),
  KEY `id_plataforma` (`id_plataforma`),
  KEY `id_sistema` (`id_sistema`),
  KEY `id_fabricante` (`id_fabricante`),
  KEY `id_classe_equipamento` (`id_classe_equipamento`),
  KEY `id_mecanismo_falha` (`id_mecanismo_falha`),
  KEY `id_submecanismo_falha` (`id_submecanismo_falha`),
  KEY `id_causa_falha` (`id_causa_falha`),
  KEY `id_subdivisao_causafalha` (`id_subdivisao_causafalha`),
  KEY `id_metodo_detectacao` (`id_metodo_detectacao`),
  KEY `id_origem_falha` (`id_origem_falha`),
  KEY `fk_falha_ativo_equipamento` (`id_ativo`),
  CONSTRAINT `tb_registro_falha_ibfk_10` FOREIGN KEY (`id_subdivisao_causafalha`) REFERENCES `tb_subdivisa_causafalha` (`id_subdivisao_causafalha`),
  CONSTRAINT `tb_registro_falha_ibfk_11` FOREIGN KEY (`id_metodo_detectacao`) REFERENCES `tb_metodo_detectacao` (`id_metodo_detectacao`),
  CONSTRAINT `tb_registro_falha_ibfk_12` FOREIGN KEY (`id_origem_falha`) REFERENCES `tb_origem_falha` (`id_origem_falha`),
  CONSTRAINT `tb_registro_falha_ibfk_2` FOREIGN KEY (`id_usuario_registro`) REFERENCES `tb_cadastro_usuario` (`id_usuario`),
  CONSTRAINT `tb_registro_falha_ibfk_3` FOREIGN KEY (`id_plataforma`) REFERENCES `tb_plataforma` (`id_plataforma`),
  CONSTRAINT `tb_registro_falha_ibfk_4` FOREIGN KEY (`id_sistema`) REFERENCES `tb_sistema` (`id_sistema`),
  CONSTRAINT `tb_registro_falha_ibfk_5` FOREIGN KEY (`id_fabricante`) REFERENCES `tb_fabricante` (`id_fabricante`),
  CONSTRAINT `tb_registro_falha_ibfk_6` FOREIGN KEY (`id_classe_equipamento`) REFERENCES `tb_classe_equipamento` (`id_classe_equipamento`),
  CONSTRAINT `tb_registro_falha_ibfk_7` FOREIGN KEY (`id_mecanismo_falha`) REFERENCES `tb_mecanismo_falha` (`id_mecanismo_falha`),
  CONSTRAINT `tb_registro_falha_ibfk_8` FOREIGN KEY (`id_submecanismo_falha`) REFERENCES `tb_submecanismo_falha` (`id_submecanismo_falha`),
  CONSTRAINT `tb_registro_falha_ibfk_9` FOREIGN KEY (`id_causa_falha`) REFERENCES `tb_causa_falha` (`id_causa_falha`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_registro_falha: ~37 rows (aproximadamente)
INSERT INTO `tb_registro_falha` (`id_falha`, `RTA`, `data_ocorrencia`, `descricao_falha`, `TAG`, `id_ativo`, `id_usuario_registro`, `id_plataforma`, `id_sistema`, `id_fabricante`, `id_classe_equipamento`, `id_mecanismo_falha`, `id_submecanismo_falha`, `id_causa_falha`, `id_subdivisao_causafalha`, `id_metodo_detectacao`, `id_origem_falha`) VALUES
	(1, NULL, '2024-10-24', 'Falha Acoplamento', 'B-1251001C', 1, NULL, 5, 40, 8, 4, 2, 13, 2, 14, 1, 3),
	(2, '20204', '2024-02-13', 'Travamento do Estator', 'B-5336001A', 1, NULL, 3, 37, 17, 4, 2, 16, 3, 11, 11, 1),
	(3, '22312', '2024-05-06', 'Contaminação', 'B-UQ-1261001-02B', 1, NULL, 5, 53, 15, 4, 2, 10, 1, 5, 10, 1),
	(4, '24441', '2024-07-04', 'Corrosão', 'B-1223003C', 1, NULL, 5, 58, 21, 4, 2, 10, 2, 4, 1, 3),
	(5, '25026', '2024-07-05', 'Corrosão', 'B-1251001B', 1, NULL, 5, 40, 8, 4, 2, 10, 2, 4, 1, 3),
	(6, '22331', '2024-05-05', 'Vazamento', 'B-1251001C', 1, NULL, 5, 40, 8, 4, 4, 16, 2, 1, 2, 5),
	(7, '24384', '2023-08-17', 'Fratura do eixo', 'B-Z-UT-1251002B', 1, NULL, 3, 40, 6, 4, 4, 39, 2, 4, 3, 3),
	(8, '24401', '2024-03-24', 'Corrosão', 'B-5124002B', 1, NULL, 3, 34, 14, 4, 2, 10, 2, 4, 6, 3),
	(9, '25356', '2014-07-03', 'Vazamento', 'C-UC-5134501B', 1, NULL, 5, 15, 13, 8, 4, 2, 2, 4, 8, 3),
	(10, '24396', '2024-06-13', 'Vibração', 'B-1251001A', 1, NULL, 3, 40, 14, 4, 4, 39, 2, 4, 3, 3),
	(11, '26047', '2024-08-05', 'Emperramento da SDV', 'C-UC-1231001B', 1, NULL, 6, 42, 20, 8, 4, 7, 4, 2, 8, 3),
	(12, '25201', '2024-07-29', 'Sinal de feedback da válvula de controle de óleo lubrificante', 'GE-TG-5147001C', 1, NULL, 6, 26, 19, 19, 2, 12, 3, 11, 6, 3),
	(13, '26491', '2024-06-16', 'Corrosão', 'B-542002A/B', 1, NULL, 8, 21, 7, 4, 2, 10, 4, 3, 8, 5),
	(14, '29358', '2014-10-13', 'Bomba carga TK CO 5S está com perda de óleo no sistema de lubrificação', 'B-1358501-05S', 1, NULL, 6, 54, 12, 4, 2, 39, 3, 10, 9, 3),
	(15, '29334', '2024-11-08', 'Perda de fluido de arrefecimento', 'C-UC-5134501A', 1, NULL, 6, 15, 99, 8, 2, 12, 3, 11, 1, 5),
	(16, '29767', '2024-11-18', NULL, 'B-1223003B', 1, NULL, 6, 58, 99, 4, 99, 99, 99, 99, 99, 99),
	(17, '3133', '2024-06-03', 'FUGA TERRA', 'B-5111001D', 1, NULL, 3, 21, 14, 4, 1, 29, 2, 4, 8, 3),
	(18, '25174', '2024-07-27', 'Falta de sobressalente', 'GG-5241501A', 1, NULL, 3, 50, 23, 25, 3, 17, 2, 4, 8, 3),
	(19, '25295', '2024-07-25', 'Chave Aterramento', 'C-UC-1231002A', 1, NULL, 3, 9, 1, 34, 4, 6, 1, 6, 8, 2),
	(20, '25621', '2024-08-09', 'Rolamento com folga', 'C-UC-5134501A', 1, NULL, 3, 15, 10, 8, 4, 39, 2, 4, 3, 3),
	(21, '26891', '2024-09-13', 'Corrosão', 'UD-5122501B', 1, NULL, 3, 2, 4, 63, 4, 7, 4, 3, 8, 3),
	(22, '26897', '2024-09-17', 'Corrosão', 'FT-UT-1251001-01C', 1, NULL, 3, 40, 22, 18, 5, 7, 2, 4, 3, 3),
	(23, '33250', '2024-10-25', 'Falta de sobressalente', 'C-UC-5134501B', 1, NULL, 3, 15, 10, 8, 3, 18, 2, 4, 8, 3),
	(24, '22310', '2024-05-06', 'Travamento de bomba', 'B-UH-1210001-01B', 1, NULL, 5, 54, 16, 4, 4, 7, 3, 8, 8, 4),
	(25, '22471', '2024-05-07', 'Vazamento', 'AC-5252002B', 1, NULL, 5, 20, 2, 48, 4, 2, 1, 5, 8, 1),
	(26, '23550', '2024-06-08', 'Vazamento', 'UD-5122501A', 1, NULL, 5, 2, 3, 63, 4, 2, 4, 3, 8, 5),
	(27, '24166', '2024-06-24', 'Vibração', 'UC-Z-5412001B', 1, NULL, 5, 48, 9, 8, 4, 39, 3, 8, 8, 3),
	(28, '25037', '2024-07-16', 'Contaminação', 'GE-TG-5147001D', 1, NULL, 5, 26, 19, 19, 2, 31, 5, 18, 8, 3),
	(29, '26510', '2024-09-08', 'Ruido', 'GE-5261501', 1, NULL, 5, 24, 24, 28, 2, 12, 1, 6, 8, 1),
	(30, '28502', '2024-09-25', 'Falha no senson de velocidade da embreagem', 'Z-TS-UC-1254001A', 1, NULL, 5, 41, 11, 8, 3, 20, 1, 4, 8, 2),
	(31, '28504', '2024-09-03', 'Rompimento', 'PSE-5412821', 1, NULL, 5, 48, 5, 13, 2, 16, 4, 2, 8, 5),
	(32, '28590', '2024-10-25', 'Falha do conjunto da excitatriz', 'GE-TG-5147001B', 1, NULL, 3, 26, 24, 19, 4, 25, 3, 7, 8, 3),
	(33, '29984', '2024-11-26', 'Falha do sistema de lubrificação de óleo sintético', 'GE-TG-5147001B', 1, NULL, 5, 26, 19, 19, 3, 19, 3, 7, 8, 3),
	(34, '31456', '2024-02-06', 'Falha no eixo da bomba na região do rasgo da chaveta', 'B-1227001A', 1, NULL, 7, 57, 18, 4, 2, 13, 3, 10, 8, 3),
	(35, '31500', '2025-01-04', 'Trinca no tubing do sistema de distribuição de diesel', 'GE-TG-5147001A', 1, NULL, 5, 26, 19, 19, 2, 13, 4, 3, 8, 5),
	(36, '31125', '2024-12-22', 'Vazamento', 'B-1251002B', 1, NULL, 5, 40, 25, 62, 4, 2, 5, 16, 8, 3),
	(37, '26355', '2024-09-02', 'Falha acoplamento', 'UC-Z-5412001A', 1, NULL, 3, 48, 9, 2, 2, 6, 2, 4, 8, 3);

-- Copiando estrutura para tabela analisefalhas_petro.tb_selagem
CREATE TABLE IF NOT EXISTS `tb_selagem` (
  `id_selagem` int(11) NOT NULL AUTO_INCREMENT,
  `nome_selagem` varchar(100) NOT NULL,
  PRIMARY KEY (`id_selagem`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_selagem: ~7 rows (aproximadamente)
INSERT INTO `tb_selagem` (`id_selagem`, `nome_selagem`) VALUES
	(1, 'Combinada'),
	(2, 'Gland. Selo Seco'),
	(3, 'Gás Seco'),
	(4, 'Labirinto'),
	(5, 'Mêcanica'),
	(6, 'Packed'),
	(7, 'Selagem á Óleo');

-- Copiando estrutura para tabela analisefalhas_petro.tb_sistema
CREATE TABLE IF NOT EXISTS `tb_sistema` (
  `id_sistema` int(11) NOT NULL AUTO_INCREMENT,
  `nome_sistema` varchar(100) NOT NULL,
  `sigla_sistema` varchar(20) NOT NULL,
  PRIMARY KEY (`id_sistema`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_sistema: ~76 rows (aproximadamente)
INSERT INTO `tb_sistema` (`id_sistema`, `nome_sistema`, `sigla_sistema`) VALUES
	(1, 'Sistema de Água Industrial', 'FAIN'),
	(2, 'Sistema de Água Potável', 'FAPO'),
	(3, 'Sistema de Estruturas, Convés e Casco', 'FCOE'),
	(4, 'Sistema de Corrente Contínua ou UPS', 'FECC'),
	(5, 'Sistema de Retificação de Potência (SCR)', 'FECR'),
	(6, 'Sistema de Distribuição Essencial', 'FEDE'),
	(7, 'Sistema de Distribuição Normal', 'FEDN'),
	(8, 'Sistema de Distribuição de Emergência', 'FEEM'),
	(9, 'Sistema de Distribuição Principal', 'FEEP'),
	(10, 'Sistema de Iluminação de Emergência', 'FEIE'),
	(11, 'Sistema de Iluminação Normal', 'FEIN'),
	(12, 'Sistema de Iluminação Essencial', 'FEIS'),
	(13, 'Sistema de Proteção Catódica', 'FEPC'),
	(14, 'Sistema de Abastecimento de Aeronaves (QAV)', 'FQAV'),
	(15, 'Sistema de Ar Comprim de Serv e Instrumento', 'FSAC'),
	(16, 'Sistema de Acomodações, Oficinas e Outros', 'FSAL'),
	(17, 'Sistema de Ancoragem', 'FSAN'),
	(18, 'Sistema de Ar de Partida', 'FSAP'),
	(19, 'Sistema de Água Quente de Processo', 'FSAQ'),
	(20, 'Sistema de Condicionamento de Ar', 'FSAR'),
	(21, 'Sistema de Captação de Água Salgada', 'FSCA'),
	(22, 'Sistema de Combate a Incêndio', 'FSCI'),
	(23, 'Sistema de Detecção de Fogo e Gás', 'FSFG'),
	(24, 'Sistema de Geração de Emergência', 'FSGE'),
	(25, 'Sistema de Geração Auxiliar', 'FSGA'),
	(26, 'Sistema de Geração Principal', 'FSGP'),
	(27, 'Sistema de Geração de Vapor', 'FSGV'),
	(28, 'Sistema Hidráulico Centralizado', 'FSHI'),
	(29, 'Sistema de Inspeção Submarina', 'FSIS'),
	(30, 'Sistema de Lastreamento', 'FSLE'),
	(31, 'Sistema de Esgotamento', 'FSES'),
	(32, 'Sistema de Movimentação de Cargas', 'FSMC'),
	(33, 'Sistema de Óleo Diesel', 'FSOD'),
	(34, 'Sistema de Água de Resfriamento', 'FSRA'),
	(35, 'Sistema de Câmara Frigorífica', 'FSRF'),
	(36, 'Sistema de Salvatagem', 'FSSV'),
	(37, 'Sistema de Tratamento Dejetos Sanitários', 'FSTD'),
	(38, 'Sistema de Telecomunicação e Informática', 'FSTI'),
	(39, 'Sistema de Ventilação e Exaustão', 'FSVE'),
	(40, 'Sistema de Injeção de Água', 'PSAI'),
	(41, 'Sistema de Compressão de CO2', 'PSCC'),
	(42, 'Sistema de Compressão/Recompressão de Gás', 'PSCG'),
	(43, 'Sistema de Coleta de Superfície', 'PSCO'),
	(44, 'Sistema de Separação de Fluídos', 'PSSE'),
	(45, 'Sistema Controle Poços - Un. Hidráulica', 'PSCP'),
	(46, 'Sistema de Escoamento de Gás', 'PSEG'),
	(47, 'Sistema de Escoamento de Óleo', 'PSEP'),
	(48, 'Sistema de Flare', 'PSFL'),
	(49, 'Sistema de Gás Combustível', 'PSGC'),
	(50, 'Sistema de Gás Inerte', 'PSGI'),
	(51, 'Sistema de Distribuição de Gás Lift', 'PSGL'),
	(52, 'Sistema de Distribuição de Gás para Injeção', 'PSIG'),
	(53, 'Sistema de Injeção Química', 'PSIQ'),
	(54, 'Sistema de Offloading', 'PSOF'),
	(55, 'Sistema de Automação Industrial', 'PSSC'),
	(56, 'Sistema de Tratamento de Água Produzida', 'PSTA'),
	(57, 'Sistema de Tratamento de Gás', 'PSTG'),
	(58, 'Sistema de Tratamento de Petróleo', 'PSTP'),
	(59, 'Sistema de Medição de Fluidos', 'PSMF'),
	(60, 'Sistema do Turret', 'PSTR'),
	(61, 'Sistema de Vent Atmosférico', 'PSVA'),
	(62, 'Sistema Drenagem Fech Área Classif Recup Cond', 'PDFC'),
	(63, 'Sistema Drenagem Aberta Área Classificada', 'PDAC'),
	(64, 'Sistema de Elevação', 'PSEL'),
	(65, 'Unidade Recuperadora de Vapor', 'PURV'),
	(66, 'Sistema de Ferramentas e Coluna de Perfuração', 'SCPP'),
	(67, 'Sistema de Dados Meteorológicos', 'SDMT'),
	(68, 'Sistema de Granéis', 'SGRA'),
	(69, 'Sistema de Posicionamentos', 'SPDN'),
	(70, 'Segurança-Proteção Coletiva e Individual', 'SSEG'),
	(71, 'Sistema de Elevação e Rotação', 'SSER'),
	(72, 'Sistema de Lama', 'SSLA'),
	(73, 'Sistema de Manuseio de Tubos', 'SSMT'),
	(74, 'Sistema de Segurança de Poço', 'SSSP'),
	(75, 'Sistema de Tensionam. e Compens. Movimento', 'SSTC'),
	(76, 'Sistema de Pull in/Pull out', 'PSPU');

-- Copiando estrutura para tabela analisefalhas_petro.tb_status_falha
CREATE TABLE IF NOT EXISTS `tb_status_falha` (
  `id_status` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_status` varchar(50) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_status_falha: ~4 rows (aproximadamente)
INSERT INTO `tb_status_falha` (`id_status`, `nome_status`) VALUES
	(1, 'Em Análise'),
	(2, 'Aprovada'),
	(3, 'Recusada'),
	(4, 'Rascunho');

-- Copiando estrutura para tabela analisefalhas_petro.tb_subdivisa_causafalha
CREATE TABLE IF NOT EXISTS `tb_subdivisa_causafalha` (
  `id_subdivisao_causafalha` int(11) NOT NULL AUTO_INCREMENT,
  `nome_subdivisao_causafalha` varchar(100) NOT NULL,
  PRIMARY KEY (`id_subdivisao_causafalha`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_subdivisa_causafalha: ~22 rows (aproximadamente)
INSERT INTO `tb_subdivisa_causafalha` (`id_subdivisao_causafalha`, `nome_subdivisao_causafalha`) VALUES
	(1, '1.0 - Projeto - Geral'),
	(2, '1.1 - Projeto - Capacidade inadequada'),
	(3, '1.2 - Projeto - Material inadequado'),
	(4, '2.0 - Fabricação / Instalação - Geral'),
	(5, '2.1 - Fabricação / Instalação - Erro de Fabricação'),
	(6, '2.2 - Fabricação / Instalação - Erro de Instalação'),
	(7, '3.0 - Operação / Manutenção - Geral'),
	(8, '3.1 - Operação / Manutenção - Serviço fora das condições de projeto'),
	(9, '3.2 - Operação / Manutenção - Erro de operação'),
	(10, '3.3 - Operação / Manutenção - Erro de manutenção'),
	(11, '3.4 - Operação / Manutenção - Desgaste e deterioração esperados'),
	(12, '4.0 - Gestão - Geral'),
	(13, '4.1 - Gestão - Erro de documentação'),
	(14, '4.2 - Gestão - Erro de gestão'),
	(15, '5.0 - Miscelâneas - Geral'),
	(16, '5.1 - Miscelâneas - Nenhuma causa encontrada'),
	(17, '5.2 - Miscelâneas - Causa comum'),
	(18, '5.3 - Miscelâneas - Causas combinadas'),
	(19, '5.4 - Miscelâneas - Outra unidade/falha em cascata'),
	(20, '5.5 - Miscelâneas - Outras'),
	(21, '5.6 - Miscelâneas - Desconhecida'),
	(99, 'Não Informado / Desconhecido');

-- Copiando estrutura para tabela analisefalhas_petro.tb_submecanismo_falha
CREATE TABLE IF NOT EXISTS `tb_submecanismo_falha` (
  `id_submecanismo_falha` int(11) NOT NULL AUTO_INCREMENT,
  `nome_submecanismo_falha` varchar(100) NOT NULL,
  PRIMARY KEY (`id_submecanismo_falha`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_submecanismo_falha: ~40 rows (aproximadamente)
INSERT INTO `tb_submecanismo_falha` (`id_submecanismo_falha`, `nome_submecanismo_falha`) VALUES
	(1, '1.0 - Falha Mecânica- Geral'),
	(2, '1.1 - Falha Mecânica - Vazamento'),
	(3, '1.2 - Falha Mecânica - Alinhamento/folga'),
	(4, '1.3 - Falha Mecânica - Geral'),
	(5, '1.4 - Falha Mecânica - Deformação'),
	(6, '1.5 - Falha Mecânica - Afrouxamento'),
	(7, '1.6 - Falha Mecânica - Emperramento'),
	(8, '2.0 - Falha de material - Geral'),
	(9, '2.1 - Falha de material - Cavitação'),
	(10, '2.2 - Falha de material - Corrosão'),
	(11, '2.3 - Falha de material - Erosão'),
	(12, '2.4 - Falha de material - Desgaste'),
	(13, '2.5 - Falha de material - Quebra'),
	(14, '2.6 - Falha de material - Fadiga'),
	(15, '2.7 - Falha de material - Sobreaquecimento'),
	(16, '2.8 - Falha de material - Rompimento'),
	(17, '3.0 - Falha no instrumento - Geral'),
	(18, '3.1 - Falha no instrumento - Falha de Controle'),
	(19, '3.2 - Falha no instrumento - Sem sinal/indicação/alarme'),
	(20, '3.3 - Falha no instrumento - Sinal/indicação/alarme falso'),
	(21, '3.4 - Falha no instrumento - Fora de ajuste'),
	(22, '3.5 - Falha no instrumento - Falha de software'),
	(23, '3.6 - Falha no instrumento - Falha de causa/modo comum'),
	(24, '4.0 - Falha elétrica - Geral'),
	(25, '4.1 - Falha elétrica - Curto-circuito'),
	(26, '4.2 - Falha elétrica - Circuito aberto'),
	(27, '4.3 - Falha elétrica - Sem energia/tensão'),
	(28, '4.4 - Falha elétrica - Energia/tensão incorreta'),
	(29, '4.5 - Falha elétrica - Falha de aterramento/falha de isolação'),
	(30, '5.0 - Influência externa - Geral'),
	(31, '5.1 - Influência externa - Bloqueio/entupimento'),
	(32, '5.2 - Influência externa - Contaminação'),
	(33, '5.3 - Influência externa - Influências externas diversas'),
	(34, '6.0 - Misselâneas - Geral'),
	(35, '6.1 - Misselâneas - Nenhuma causa encontrada'),
	(36, '6.2 - Misselâneas - Causas combinadas'),
	(37, '6.3 - Misselâneas - Outros'),
	(38, '6.4 - Misselâneas - Desconhecido'),
	(39, '1.2.1 - Mêcanica - Vibração'),
	(99, 'Não Informado / Desconhecido');

-- Copiando estrutura para tabela analisefalhas_petro.tb_tag
CREATE TABLE IF NOT EXISTS `tb_tag` (
  `id_tag` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tag` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_tag: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela analisefalhas_petro.tb_tipo_corpo
CREATE TABLE IF NOT EXISTS `tb_tipo_corpo` (
  `id_tipo_corpo` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tipo_corpo` varchar(100) NOT NULL,
  PRIMARY KEY (`id_tipo_corpo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_tipo_corpo: ~4 rows (aproximadamente)
INSERT INTO `tb_tipo_corpo` (`id_tipo_corpo`, `nome_tipo_corpo`) VALUES
	(1, 'Barril'),
	(2, 'Carcaça Axial'),
	(3, 'Carcaça Bipartida'),
	(4, 'Cartucho');

-- Copiando estrutura para tabela analisefalhas_petro.tb_tipo_mancal
CREATE TABLE IF NOT EXISTS `tb_tipo_mancal` (
  `id_tipo_mancal` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tipo_mancal` varchar(100) NOT NULL,
  PRIMARY KEY (`id_tipo_mancal`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_tipo_mancal: ~2 rows (aproximadamente)
INSERT INTO `tb_tipo_mancal` (`id_tipo_mancal`, `nome_tipo_mancal`) VALUES
	(1, 'Escora'),
	(2, 'Radial');

-- Copiando estrutura para tabela analisefalhas_petro.tb_tipo_transmissao
CREATE TABLE IF NOT EXISTS `tb_tipo_transmissao` (
  `id_tipo_transmissao` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tipo_transmissao` varchar(100) NOT NULL,
  PRIMARY KEY (`id_tipo_transmissao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_tipo_transmissao: ~3 rows (aproximadamente)
INSERT INTO `tb_tipo_transmissao` (`id_tipo_transmissao`, `nome_tipo_transmissao`) VALUES
	(1, 'Direta'),
	(2, 'Engrenagem'),
	(3, 'Integral');

-- Copiando estrutura para tabela analisefalhas_petro.tb_tipo_usuario
CREATE TABLE IF NOT EXISTS `tb_tipo_usuario` (
  `id_tipo_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Copiando dados para a tabela analisefalhas_petro.tb_tipo_usuario: ~3 rows (aproximadamente)
INSERT INTO `tb_tipo_usuario` (`id_tipo_usuario`, `nome_tipo`) VALUES
	(1, 'Comum'),
	(2, 'Gerente'),
	(3, 'Administrador');


SET FOREIGN_KEY_CHECKS = 1;