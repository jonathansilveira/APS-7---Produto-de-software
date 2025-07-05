-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 10-Maio-2025 às 15:22
-- Versão do servidor: 8.0.31
-- versão do PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `aps_7`
--

CREATE DATABASE IF NOT EXISTS aps_7;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `status` enum('Ativo','Inativo') DEFAULT NULL,
  `modalidade` enum('Presencial','Online','Híbrido') DEFAULT NULL,
  `professor` varchar(100) DEFAULT NULL,
  `descricao` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `cursos`
--

INSERT INTO `cursos` (`id`, `nome`, `status`, `modalidade`, `professor`, `descricao`) VALUES
(1, 'Inglês Instrumental', 'Ativo', 'Online', 'Patrícia Soares Gomes', 'Inglês focado em leitura e interpretação de textos para fins acadêmicos.'),
(2, 'Eletrotécnica', 'Ativo', 'Híbrido', 'Antônio Carlos Ferreira', 'Conceitos básicos de eletricidade e instalações elétricas residenciais.'),
(3, 'Gastronomia Básica', 'Inativo', 'Presencial', 'Juliana Martins Rocha', 'Curso de técnicas culinárias fundamentais (turma temporariamente suspensa).'),
(4, 'Design Gráfico', 'Ativo', 'Online', 'Fernando Lima Pereira', 'Introdução ao design gráfico utilizando ferramentas digitais como Photoshop e Illustrator.'),
(5, 'Enfermagem Básica', 'Ativo', 'Híbrido', 'Ana Paula Rodrigues', 'Noções básicas de primeiros socorros e cuidados de enfermagem.'),
(6, 'Mecânica Automotiva', 'Ativo', 'Presencial', 'Roberto Almeida Costa', 'Formação em fundamentos da mecânica de veículos e manutenção preventiva.'),
(7, 'Administração Financeira', 'Ativo', 'Online', 'Mariana Oliveira Santos', 'Curso sobre gestão financeira pessoal e empresarial, com enfoque em planejamento e controle.'),
(8, 'Informática Básica', 'Ativo', 'Presencial', 'Carlos Eduardo Silva', 'Curso introdutório de informática com noções de sistema operacional, pacote Office e internet.'),
(9, 'Marketing Digital', 'Ativo', 'Híbrido', 'Ricardo Augusto Dias', 'Introdução às principais ferramentas e estratégias de marketing digital.'),
(10, 'Programação Web', 'Ativo', 'Presencial', 'Luciana Campos Freitas', 'Fundamentos de HTML, CSS e JavaScript para desenvolvimento web front-end.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `jovens`
--

DROP TABLE IF EXISTS `jovens`;
CREATE TABLE IF NOT EXISTS `jovens` (
  `matricula` varchar(20) NOT NULL,
  `rg` varchar(20) DEFAULT NULL,
  `status` enum('Ativo','Inativo') DEFAULT NULL,
  `data_ingresso` varchar(30) DEFAULT NULL,
  `curso` varchar(100) DEFAULT NULL,
  `responsavel` varchar(100) DEFAULT NULL,
  `nome_completo` varchar(100) DEFAULT NULL,
  `data_nascimento` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cpf` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `genero` enum('Masculino','Feminino','Outro') DEFAULT NULL,
  `observacoes` text,
  PRIMARY KEY (`matricula`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `jovens`
--

INSERT INTO `jovens` (`matricula`, `rg`, `status`, `data_ingresso`, `curso`, `responsavel`, `nome_completo`, `data_nascimento`, `email`, `cpf`, `genero`, `observacoes`) VALUES
('A12BC34', '12.345.678-9', 'Ativo', '15/03/2023', 'Informática Básica', 'Maria Silva', 'João Pedro Silva', '05/08/2005', 'joao.silva@email.com', '123.456.789-01', 'Masculino', 'Aluno dedicado'),
('D56EF78', '23.456.789-0', 'Ativo', '20/02/2023', 'Informática Básica', 'Carlos Oliveira', 'Ana Clara Oliveira', '12/11/2004', 'ana.oliveira@email.com', '234.567.890-12', 'Feminino', NULL),
('G90HI12', '34.567.890-1', 'Inativo', '10/01/2022', 'Informática Básica', 'Antônio Santos', 'Lucas Santos', '30/04/2003', 'lucas.santos@email.com', '345.678.901-23', 'Masculino', 'Transferido'),
('J34KL56', '4', 'Ativo', '22/07/2006', 'Informática Básica', '4', 'Mariana Costa', '22/07/2006', 'mariana.costa@email.com', '4', 'Feminino', 'Bolsista'),
('M78NO90', '56.789.012-3', 'Ativo', '18/06/2023', 'Administração Financeira', 'Roberto Alves', 'Pedro Alves', '14/09/2005', 'pedro.alves@email.com', '567.890.123-45', 'Masculino', NULL),
('P12QR34', '67.890.123-4', 'Ativo', '22/08/2023', 'Administração Financeira', 'Patrícia Lima', 'Gabriel Lima', '03/12/2004', 'gabriel.lima@email.com', '678.901.234-56', 'Masculino', NULL),
('S56TU78', '78.901.234-5', 'Ativo', '30/09/2023', 'Administração Financeira', 'José Pereira', 'Juliana Pereira', '25/01/2006', 'juliana.pereira@email.com', '789.012.345-67', 'Feminino', 'Monitora'),
('V90WX12', '89.012.345-6', 'Inativo', '12/10/2022', 'Administração Financeira', 'Márcia Rocha', 'Felipe Rocha', '19/05/2003', 'felipe.rocha@email.com', '890.123.456-78', 'Masculino', 'Desistente'),
('Y34ZA56', '90.123.456-7', 'Ativo', '08/11/2023', 'Administração Financeira', 'Ricardo Nunes', 'Carolina Nunes', '07/03/2005', 'carolina.nunes@email.com', '901.234.567-89', 'Feminino', NULL),
('B78CD90', '01.234.567-8', 'Ativo', '25/12/2023', 'Administração Financeira', 'Sandra Gomes', 'Rafael Gomes', '11/06/2006', 'rafael.gomes@email.com', '012.345.678-90', 'Masculino', 'Destaque'),
('E12FG34', '11.222.333-4', 'Ativo', '03/01/2024', 'Mecânica Automotiva', 'Luiz Henrique', 'Camila Souza', '28/02/2005', 'camila.souza@email.com', '111.222.333-44', 'Feminino', NULL),
('H56IJ78', '22.333.444-5', 'Ativo', '14/05/2023', 'Mecânica Automotiva', 'André Martins', 'Bruno Martins', '09/10/2004', 'bruno.martins@email.com', '222.333.444-55', 'Masculino', 'Estagiário'),
('K90LM12', '33.444.555-6', 'Inativo', '19/07/2022', 'Mecânica Automotiva', 'Cristina Dias', 'Amanda Dias', '17/04/2003', 'amanda.dias@email.com', '333.444.555-66', 'Feminino', 'Transferida'),
('N34OP56', '44.555.666-7', 'Ativo', '22/08/2023', 'Enfermagem Básica', 'Marcos Tavares', 'Diego Tavares', '23/08/2005', 'diego.tavares@email.com', '444.555.666-77', 'Masculino', NULL),
('Q78RS90', '55.666.777-8', 'Ativo', '30/09/2023', 'Enfermagem Básica', 'Vanessa Lopes', 'Patrícia Lopes', '05/12/2006', 'patricia.lopes@email.com', '555.666.777-88', 'Feminino', 'Bolsista'),
('T12UV34', '66.777.888-9', 'Ativo', '15/10/2023', 'Enfermagem Básica', 'Fábio Castro', 'Vinícius Castro', '31/01/2005', 'vinicius.castro@email.com', '666.777.888-99', 'Masculino', NULL),
('W56XY78', '77.888.999-0', 'Ativo', '01/11/2023', 'Enfermagem Básica', 'Luciana Freitas', 'Laura Freitas', '14/03/2006', 'laura.freitas@email.com', '777.888.999-00', 'Feminino', 'Monitora'),
('Z90AB12', '88.999.000-1', 'Inativo', '12/12/2022', 'Enfermagem Básica', 'Gustavo Mendes', 'Thiago Mendes', '27/07/2004', 'thiago.mendes@email.com', '888.999.000-11', 'Masculino', 'Formado'),
('C34DE56', '99.000.111-2', 'Ativo', '05/01/2024', 'Enfermagem Básica', 'Isabela Campos', 'Daniel Campos', '08/05/2005', 'daniel.campos@email.com', '999.000.111-22', 'Masculino', NULL),
('F78GH90', '00.111.222-3', 'Ativo', '10/02/2024', 'Enfermagem Básica', 'Hugo Ribeiro', 'Beatriz Ribeiro', '21/09/2006', 'beatriz.ribeiro@email.com', '000.111.222-33', 'Feminino', 'Voluntária'),
('I12JK34', '12.345.679-0', 'Ativo', '15/03/2023', 'Design Gráfico', 'Sônia Almeida', 'Marcos Almeida', '03/04/2005', 'marcos.almeida@email.com', '123.456.790-12', 'Masculino', NULL),
('L56MN78', '23.456.780-1', 'Ativo', '20/04/2023', 'Design Gráfico', 'Paulo Júnior', 'Tatiane Júnior', '18/06/2004', 'tatiane.junior@email.com', '234.567.801-23', 'Feminino', 'Participa de competições'),
('O90PQ12', '34.567.891-2', 'Inativo', '25/05/2022', 'Design Gráfico', 'Renata Vieira', 'Eduardo Vieira', '29/11/2003', 'eduardo.vieira@email.com', '345.678.912-34', 'Masculino', 'Transferido'),
('R34ST56', '45.678.902-3', 'Ativo', '30/06/2023', 'Design Gráfico', 'Alexandre Moura', 'Fernanda Moura', '12/02/2006', 'fernanda.moura@email.com', '456.789.023-45', 'Feminino', NULL),
('U78VW90', '56.789.013-4', 'Ativo', '05/07/2023', 'Design Gráfico', 'Célia Teixeira', 'Rodrigo Teixeira', '07/08/2005', 'rodrigo.teixeira@email.com', '567.890.134-56', 'Masculino', 'Bolsista'),
('X12YZ34', '67.890.124-5', 'Inativo', '10/08/2022', 'Gastronomia Básica', 'Otávio Correia', 'Jéssica Correia', '24/10/2004', 'jessica.correia@email.com', '678.901.245-67', 'Feminino', 'Turma encerrada'),
('A56BC78', '78.901.235-6', 'Inativo', '15/09/2022', 'Gastronomia Básica', 'Lúcia Helena', 'Ricardo Helena', '16/12/2003', 'ricardo.helena@email.com', '789.012.356-78', 'Masculino', 'Transferido'),
('D90EF12', '89.012.346-7', 'Ativo', '20/10/2023', 'Eletrotécnica', 'Mauro Salles', 'Vanessa Salles', '09/01/2006', 'vanessa.salles@email.com', '890.123.467-89', 'Feminino', NULL),
('G34HI56', '90.123.457-8', 'Ativo', '25/11/2023', 'Eletrotécnica', 'Elaine Costa', 'Leonardo Costa', '22/03/2005', 'leonardo.costa@email.com', '901.234.578-90', 'Masculino', NULL),
('J78KL90', '01.234.568-9', 'Ativo', '30/12/2023', 'Eletrotécnica', 'Ivan Rodrigues', 'Cláudia Rodrigues', '05/05/2006', 'claudia.rodrigues@email.com', '012.345.689-01', 'Feminino', 'Monitora'),
('M12NO34', '11.222.334-5', 'Ativo', '04/01/2024', 'Eletrotécnica', 'Rosângela Lima', 'Fábio Lima', '19/07/2005', 'fabio.lima@email.com', '111.222.334-45', 'Masculino', NULL),
('P56QR78', '22.333.445-6', 'Ativo', '09/02/2024', 'Eletrotécnica', 'Sérgio Nascimento', 'Aline Nascimento', '28/09/2006', 'aline.nascimento@email.com', '222.333.445-56', 'Feminino', NULL),
('S90TU12', '33.444.556-7', 'Ativo', '14/03/2024', 'Eletrotécnica', 'Teresa Soares', 'André Soares', '11/11/2005', 'andre.soares@email.com', '333.444.556-67', 'Masculino', 'Estagiário'),
('V34WX56', '44.555.667-8', 'Inativo', '19/04/2023', 'Eletrotécnica', 'Valter Gomes', 'Simone Gomes', '30/04/2004', 'simone.gomes@email.com', '444.555.667-78', 'Feminino', 'Transferida'),
('Y78ZA90', '55.666.778-9', 'Ativo', '24/05/2023', 'Eletrotécnica', 'Helena Machado', 'Roberto Machado', '13/06/2005', 'roberto.machado@email.com', '555.666.778-89', 'Masculino', NULL),
('B12CD34', '66.777.889-0', 'Ativo', '29/06/2023', 'Inglês Instrumental', 'Gilberto Santos', 'Carla Santos', '07/02/2006', 'carla.santos@email.com', '666.777.889-90', 'Feminino', 'Bolsista'),
('E56FG78', '77.888.990-1', 'Ativo', '04/07/2023', 'Inglês Instrumental', 'Adriana Ferreira', 'Marcelo Ferreira', '25/08/2005', 'marcelo.ferreira@email.com', '777.888.990-11', 'Masculino', NULL),
('H90IJ12', '88.999.001-2', 'Inativo', '09/08/2022', 'Inglês Instrumental', 'Nelson Rocha', 'Priscila Rocha', '10/10/2004', 'priscila.rocha@email.com', '888.999.001-22', 'Feminino', 'Formada'),
('K34LM56', '99.000.112-3', 'Ativo', '14/09/2023', 'Marketing Digital', 'Catarina Alves', 'Henrique Alves', '01/12/2005', 'henrique.alves@email.com', '999.000.112-33', 'Masculino', 'Destaque'),
('N78OP90', '00.111.223-4', 'Ativo', '19/10/2023', 'Marketing Digital', 'Décio Carvalho', 'Larissa Carvalho', '14/01/2006', 'larissa.carvalho@email.com', '000.111.223-44', 'Feminino', NULL),
('Q12RS34', '12.345.680-1', 'Ativo', '24/11/2023', 'Marketing Digital', 'Lorena Peixoto', 'Gustavo Peixoto', '27/03/2005', 'gustavo.peixoto@email.com', '123.456.801-23', 'Masculino', NULL),
('T56UV78', '23.456.791-2', 'Ativo', '29/12/2023', 'Marketing Digital', 'Wilson Duarte', 'Natália Duarte', '08/05/2006', 'natalia.duarte@email.com', '234.567.912-34', 'Feminino', 'Cria conteúdo'),
('W90XY12', '34.567.892-3', 'Ativo', '03/01/2024', 'Marketing Digital', 'Vera Lúcia', 'Alexandre Lúcia', '21/07/2005', 'alexandre.lucia@email.com', '345.678.923-45', 'Masculino', 'Estagiário'),
('Z34AB56', '45.678.903-4', 'Inativo', '08/02/2023', 'Marketing Digital', 'Raul Mendonça', 'Débora Mendonça', '04/09/2004', 'debora.mendonca@email.com', '456.789.034-56', 'Feminino', 'Desistente'),
('C78DE90', '56.789.014-5', 'Ativo', '13/03/2024', 'Programação Web', 'Miriam Fonseca', 'Rogério Fonseca', '17/11/2005', 'rogerio.fonseca@email.com', '567.890.145-67', 'Masculino', NULL),
('F12GH34', '67.890.125-6', 'Ativo', '18/04/2023', 'Programação Web', 'Artur Barbosa', 'Tânia Barbosa', '26/02/2006', 'tania.barbosa@email.com', '678.901.256-78', 'Feminino', 'Hackathons'),
('I56JK78', '78.901.236-7', 'Ativo', '23/05/2023', 'Programação Web', 'Heitor Gonçalves', 'Erick Gonçalves', '09/04/2005', 'erick.goncalves@email.com', '789.012.367-89', 'Masculino', NULL),
('L90MN12', '89.012.347-8', 'Ativo', '28/06/2023', 'Programação Web', 'Iara Souza', 'Renata Souza', '22/08/2006', 'renata.souza@email.com', '890.123.478-90', 'Feminino', 'Bolsista'),
('O34PQ56', '90.123.458-9', 'Inativo', '03/07/2022', 'Programação Web', 'Djalma Andrade', 'Paulo Andrade', '15/10/2004', 'paulo.andrade@email.com', '901.234.589-01', 'Masculino', 'Transferido'),
('R78ST90', '01.234.569-0', 'Ativo', '08/08/2023', 'Programação Web', 'Selma Pires', 'Caio Pires', '30/12/2005', 'caio.pires@email.com', '012.345.690-12', 'Masculino', 'Projeto próprio');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

DROP TABLE IF EXISTS `produtos`;
CREATE TABLE IF NOT EXISTS `produtos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `status` enum('Ativo','Inativo') DEFAULT NULL,
  `tipo` enum('Educativo','Construção','Faz de Conta','Atividades Físicas','Encaixe','Interativos','Artísticos','Musicais','Puzzle','Colecionável') DEFAULT NULL,
  `faixa_etaria` enum('Bebê (0-1)','Infante (1-3)','Pré-escolares (4-6)','Crianças (7-12)','Pré-adolescente (12-14)','Adolescente (15-17)','Adultos (18+)') DEFAULT NULL,
  `materiais` varchar(255) DEFAULT NULL,
  `preco` decimal(10,2) DEFAULT NULL,
  `custo_producao` decimal(10,2) DEFAULT NULL,
  `estoque` varchar(10) DEFAULT NULL,
  `tempo_producao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`id`, `nome`, `status`, `tipo`, `faixa_etaria`, `materiais`, `preco`, `custo_producao`, `estoque`, `tempo_producao`) VALUES
(1, 'Miniaturas Ecológicas', 'Inativo', 'Colecionável', 'Adultos (18+)', 'Madeira Compensada', '149.00', '95.00', '5', '08'),
(2, 'Cubo Mágico Ecológico', 'Ativo', 'Puzzle', 'Adultos (18+)', 'Plástico Biodegradável', '49.90', '25.00', '90', '03'),
(3, 'Bonecos Históricos Sustentáveis', 'Ativo', 'Colecionável', 'Adolescente (15-17)', 'Fibras Naturais', '199.90', '120.00', '12', '10'),
(4, 'Labirinto Sustentável', 'Ativo', 'Puzzle', 'Infante (1-3)', 'Madeira Compensada', '34.90', '16.00', '70', '02'),
(5, 'Xilofone de Bambu', 'Ativo', 'Musicais', 'Pré-escolares (4-6)', 'Fibras Naturais', '39.90', '20.00', '55', '03'),
(6, 'Kit Bateria Reciclada', 'Ativo', 'Musicais', 'Crianças (7-12)', 'Plástico Reciclado', '89.90', '50.00', '30', '04'),
(7, 'Argila 100% Natural', 'Inativo', 'Artísticos', 'Pré-adolescente (12-14)', 'Argila', '49.90', '25.00', '8', '02'),
(8, 'Kit Pintura Natural', 'Ativo', 'Artísticos', 'Pré-escolares (4-6)', 'Argila', '29.90', '12.00', '100', '01'),
(9, 'Mesa de Luz Ecológica', 'Ativo', 'Artísticos', 'Crianças (7-12)', 'Madeira Compensada', '159.90', '90.00', '22', '05'),
(10, 'Brinquedo Sensorial Natural', 'Ativo', 'Interativos', 'Bebê (0-1)', 'Algodão Orgânico', '59.90', '30.00', '40', '03'),
(11, 'Robô Programável Verde', 'Ativo', 'Interativos', 'Pré-adolescente (12-14)', 'Plástico Biodegradável', '249.90', '150.00', '18', '08'),
(12, 'Cubo Magnético Sustentável', 'Ativo', 'Encaixe', 'Adultos (18+)', 'Fibras Naturais', '119.90', '70.00', '20', '06'),
(13, 'Quebra-Cabeça 3D Ecológico', 'Ativo', 'Encaixe', 'Adolescente (15-17)', 'Madeira Compensada', '79.90', '40.00', '35', '05'),
(14, 'Cama Elástica Sustentável', 'Ativo', 'Atividades Físicas', 'Pré-adolescente (12-14)', 'Tecido Reciclado', '299.90', '180.00', '15', '07'),
(15, 'Kit Ponte de Papelão', 'Ativo', 'Construção', 'Adolescente (15-17)', 'Papelão Reciclado', '29.90', '12.00', '200', '01'),
(16, 'Torre de Madeira Sustentável', 'Ativo', 'Construção', 'Infante (1-3)', 'Madeira Compensada', '59.90', '28.00', '60', '03'),
(17, 'Kit Médico Natural', 'Ativo', 'Faz de Conta', 'Pré-escolares (4-6)', 'Tecido Reciclado', '39.90', '18.00', '75', '02'),
(18, 'Fogão de Brinquedo Ecológico', 'Ativo', 'Faz de Conta', 'Crianças (7-12)', 'Plástico Reciclado', '79.90', '42.00', '25', '04'),
(19, 'Caixa Registradora Verde', 'Inativo', 'Faz de Conta', 'Pré-escolares (4-6)', 'Madeira Compensada', '49.90', '25.00', '10', '03'),
(20, 'Bola de Ginástica Natural', 'Ativo', 'Atividades Físicas', 'Crianças (7-12)', 'Borracha Natural', '69.90', '32.00', '50', '03'),
(21, 'Blocos de Montar Ecológicos', 'Ativo', 'Construção', 'Crianças (7-12)', 'Plástico Biodegradável', '129.90', '65.00', '45', '06'),
(22, 'Globo Terrestre Ecológico', 'Ativo', 'Educativo', 'Pré-adolescente (12-14)', 'Plástico Reciclado', '199.90', '110.00', '30', '05'),
(23, 'Tabuada de Bambu', 'Inativo', 'Educativo', 'Crianças (7-12)', 'Fibras Naturais', '69.90', '38.00', '0', '04'),
(24, 'Alfabeto Ecológico', 'Ativo', 'Educativo', 'Pré-escolares (4-6)', 'Madeira Compensada', '49.90', '22.50', '85', '02'),
(25, 'Kit Ciências Sustentável', 'Ativo', 'Educativo', 'Crianças (7-12)', 'Papelão Reciclado', '89.90', '45.00', '120', '03');

-- --------------------------------------------------------

--
-- Estrutura da tabela `professores`
--

DROP TABLE IF EXISTS `professores`;
CREATE TABLE IF NOT EXISTS `professores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `atuacao` varchar(100) DEFAULT NULL,
  `formacao` varchar(100) DEFAULT NULL,
  `status` enum('Ativo','Inativo') DEFAULT NULL,
  `horario` enum('Manhã','Tarde','Noite','Integral') DEFAULT NULL,
  `curso` varchar(100) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `nascimento` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cpf` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `genero` enum('Masculino','Feminino','Outro') DEFAULT NULL,
  `observacoes` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `professores`
--

INSERT INTO `professores` (`id`, `atuacao`, `formacao`, `status`, `horario`, `curso`, `nome`, `nascimento`, `email`, `cpf`, `genero`, `observacoes`) VALUES
(1, 'Desenvolvimento Web', 'Pós-graduação em Full Stack', 'Ativo', 'Noite', 'Programação Web', 'Lucas Gonçalves Oliveira', '03/10/1989', 'lucas.oliveira@escola.com', '000.111.222-33', 'Masculino', 'Desenvolvedor sênior na Apple'),
(2, 'Comunicação Digital', 'MBA em Marketing Digital', 'Ativo', 'Tarde', 'Marketing Digital', 'Camila Duarte Santos', '14/03/1986', 'camila.santos@escola.com', '999.000.111-22', 'Feminino', 'Ex-gerente de mídias sociais'),
(3, 'Linguística Aplicada', 'Mestrado em Inglês Técnico', 'Ativo', 'Manhã', 'Inglês Instrumental', 'Patrícia Soares Ribeiro', '08/09/1981', 'patricia.ribeiro@escola.com', '888.999.000-11', 'Feminino', 'Tradutora juramentada'),
(4, 'Engenharia Elétrica', 'Mestrado em Sistemas Elétricos', 'Ativo', 'Noite', 'Eletrotécnica', 'Ricardo Augusto Dias', '27/02/1979', 'ricardo.dias@escola.com', '777.888.999-00', 'Masculino', 'Engenheiro da concessionária local'),
(5, 'Culinária Internacional', 'Curso Superior em Gastronomia', 'Ativo', 'Tarde', 'Gastronomia Básica', 'Lucas da Silva Alves', '25/03/1988', 'lucas.alves@escola.com', '689.725.800-94', 'Masculino', 'Substituto'),
(6, 'Design Digital', 'Bacharelado em Design Gráfico', 'Ativo', 'Noite', 'Design Gráfico', 'Fernanda Lima Rocha', '05/08/1987', 'fernanda.rocha@escola.com', '555.666.777-88', 'Feminino', 'Sócia de agência de publicidade'),
(7, 'Culinária Internacional', 'Curso Superior em Gastronomia', 'Inativo', 'Tarde', 'Gastronomia Básica', 'Juliano Martins Alves', '19/05/1984', 'juliano.alves@escola.com', '666.777.888-99', 'Masculino', 'Afastado para capacitação'),
(8, 'Saúde Coletiva', 'Mestrado em Enfermagem', 'Ativo', 'Manhã', 'Enfermagem Básica', 'Ana Paula Castro', '12/11/1982', 'ana.castro@escola.com', '444.555.666-77', 'Feminino', 'Plantonista no Hospital Municipal'),
(9, 'Engenharia Mecânica', 'Graduação em Engenharia Automotiva', 'Ativo', 'Integral', 'Mecânica Automotiva', 'Roberto Almeida Souza', '30/04/1978', 'roberto.souza@escola.com', '333.444.555-66', 'Masculino', 'Especialista em motores elétricos'),
(10, 'Gestão Empresarial', 'MBA em Finanças Corporativas', 'Ativo', 'Tarde', 'Administração Financeira', 'Mariana Oliveira Pires', '22/07/1985', 'mariana.pires@escola.com', '222.333.444-55', 'Feminino', 'Ex-consultora do Banco Central'),
(11, 'Tecnologia da Informação', 'Mestrado em Ciência da Computação', 'Ativo', 'Manhã', 'Informática Básica', 'Carlos Eduardo Fernandes', '18/05/1982', 'carlos.fernandes@escola.com', '111.222.333-44', 'Masculino', 'Coordenador do laboratório de TI');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

DROP TABLE IF EXISTS `vendas`;
CREATE TABLE IF NOT EXISTS `vendas` (
  `pedido` varchar(20) NOT NULL,
  `data_venda` varchar(15) DEFAULT NULL,
  `hora` varchar(15) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `produto` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `preco` decimal(10,2) DEFAULT NULL,
  `desconto` int DEFAULT NULL,
  `pagamento` enum('Débito','Pix','Dinheiro','Crédito - 1x sem juros','Crédito - 2x sem juros','Crédito - 3x sem juros','Crédito - 4x sem juros','Crédito - 5x sem juros','Crédito - 6x sem juros') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `modo` enum('Retirada no local','Entrega via Correios') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prazo` varchar(50) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `numero` int DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pedido`)
) ENGINE=MyISAM AUTO_INCREMENT=309 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `vendas`
--

INSERT INTO `vendas` (`pedido`, `data_venda`, `hora`, `nome`, `cpf`, `telefone`, `email`, `produto`, `quantidade`, `preco`, `desconto`, `pagamento`, `modo`, `prazo`, `rua`, `numero`, `cep`, `bairro`, `cidade`) VALUES
('#88', '22/02/2024', '13:30', 'Sandro Lopes', '091.234.567-89', '(11) 94567-8901', 'sandro.lopes@email.com', 'Blocos de Montar Ecológicos', 3, '389.70', 20, 'Crédito - 5x sem juros', 'Entrega via Correios', '05', 'Rua das Margaridas', 155, '45678-012', 'Margaridas', 'Goiânia'),
('#87', '21/02/2024', '09:05', 'Rafaela Muniz', '980.123.456-78', '(19) 93456-7890', 'rafaela.muniz@email.com', 'Xilofone de Bambu', 1, '39.90', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#86', '20/01/2024', '16:40', 'Quintino Castro', '879.012.345-67', '(91) 92345-6789', 'quintino.castro@email.com', 'Torre de Madeira Sustentável', 2, '119.80', 0, 'Crédito - 4x sem juros', 'Entrega via Correios', '03', 'Alameda das Violetas', 147, '34567-901', 'Violetas', 'Belém'),
('#85', '19/01/2024', '12:15', 'Paola Ribeiro', '768.901.234-56', '(81) 91234-5678', 'paola.ribeiro@email.com', 'Fogão de Brinquedo Ecológico', 1, '79.90', 10, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#84', '17/12/2023', '10:25', 'Nina Souza', '546.789.012-34', '(61) 99012-3456', 'nina.souza@email.com', 'Tabuada de Bambu', 1, '69.90', 5, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#83', '18/12/2023', '14:50', 'Otávio Campos', '657.890.123-45', '(71) 90123-4567', 'otavio.campos@email.com', 'Caixa Registradora Verde', 1, '49.90', 0, 'Crédito - 1x sem juros', 'Entrega via Correios', '03', 'Rua das Hortênsias', 139, '23456-890', 'Hortênsias', 'Manaus'),
('#82', '16/11/2023', '16:00', 'Marcos Prado', '435.678.901-23', '(51) 98901-2345', 'marcos.prado@email.com', 'Kit Ponte de Papelão', 4, '119.60', 0, 'Crédito - 2x sem juros', 'Entrega via Correios', '04', 'Avenida dos Lírios', 131, '12345-789', 'Lírios', 'Fortaleza'),
('#81', '15/11/2023', '11:35', 'Larissa Montenegro', '324.567.890-12', '(41) 97890-1234', 'larissa.montenegro@email.com', 'Globo Terrestre Ecológico', 1, '199.90', 10, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#80', '14/10/2023', '14:10', 'Kaique Rios', '213.456.789-01', '(31) 96789-0123', 'kaique.rios@email.com', 'Mesa de Luz Ecológica', 1, '159.90', 15, 'Crédito - 3x sem juros', 'Entrega via Correios', '05', 'Rua dos Girassóis', 123, '01234-678', 'Girassóis', 'Salvador'),
('#79', '13/10/2023', '09:45', 'Juliana Vasconcelos', '102.345.678-90', '(21) 95678-9012', 'juliana.vasconcelos@email.com', 'Kit Pintura Natural', 3, '89.70', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#78', '12/09/2023', '16:20', 'Ivan Ferreira', '091.234.567-89', '(11) 94567-8901', 'ivan.ferreira@email.com', 'Bonecos Históricos Sustentáveis', 1, '199.90', 25, 'Crédito - 6x sem juros', 'Entrega via Correios', '09', 'Avenida das Oliveiras', 115, '90123-567', 'Oliveiras', 'Recife'),
('#77', '11/09/2023', '11:55', 'Heloísa Pinheiro', '990.123.456-78', '(19) 93456-7890', 'heloisa.pinheiro@email.com', 'Labirinto Sustentável', 1, '34.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#76', '10/08/2023', '14:30', 'Gustavo Medeiros', '889.012.345-67', '(91) 92345-6789', 'gustavo.medeiros@email.com', 'Kit Bateria Reciclada', 1, '89.90', 10, 'Crédito - 1x sem juros', 'Entrega via Correios', '04', 'Alameda dos Pássaros', 107, '89012-456', 'Pássaros', 'Curitiba'),
('#75', '09/08/2023', '10:05', 'Fernanda Tavares', '778.901.234-56', '(81) 91234-5678', 'fernanda.tavares@email.com', 'Cubo Mágico Ecológico', 2, '99.80', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#74', '08/07/2023', '17:40', 'Eduardo Sampaio', '667.890.123-45', '(71) 90123-4567', 'eduardo.sampaio@email.com', 'Miniaturas Ecológicas', 1, '149.90', 20, 'Crédito - 4x sem juros', 'Entrega via Correios', '08', 'Rua dos Jasmins', 93, '78901-345', 'Jardim Primavera', 'Belo Horizonte'),
('#72', '05/06/2023', '16:15', 'Vitória Peixoto', '334.567.890-12', '(41) 97890-1234', 'vitoria.peixoto@email.com', 'Alfabeto Ecológico', 1, '49.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#73', '07/07/2023', '13:25', 'Daniela Queiroz', '556.789.012-34', '(61) 99012-3456', 'daniela.queiroz@email.com', 'Brinquedo Sensorial Natural', 1, '59.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#71', '06/06/2023', '09:50', 'Alexandre Dantas', '445.678.901-23', '(51) 98901-2345', 'alexandre.dantas@email.com', 'Quebra-Cabeça 3D Ecológico', 1, '79.90', 5, 'Crédito - 2x sem juros', 'Entrega via Correios', '05', 'Avenida das Garças', 85, '67890-234', 'Beira Rio', 'Porto Alegre'),
('#70', '04/05/2023', '11:30', 'Renato Barbosa', '223.456.789-01', '(31) 96789-0123', 'renato.barbosa@email.com', 'Kit Ciências Sustentável', 2, '179.80', 10, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#69', '03/05/2023', '14:45', 'Larissa Castro', '112.345.678-90', '(21) 95678-9012', 'larissa.castro@email.com', 'Cama Elástica Sustentável', 1, '299.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#68', '02/05/2023', '10:20', 'Márcio Nogueira', '001.234.567-89', '(11) 94567-8901', 'marcio.nogueira@email.com', 'Robô Programável Verde', 1, '249.90', 15, 'Crédito - 3x sem juros', 'Entrega via Correios', '07', 'Rua das Andorinhas', 72, '56789-123', 'Vila Nova', 'São Paulo'),
('#67', '05/04/2023', '17:35', 'Leonardo Vieira', '890.123.456-27', '(81) 98901-2351', 'leonardo.vieira@email.com', 'Mesa de Luz Ecológica', 1, '159.90', 20, 'Crédito - 5x sem juros', 'Entrega via Correios', '06', 'Rua das Begônias', 7071, '45678-903', 'Begônias', 'Palmas'),
('#66', '05/04/2023', '11:15', 'Juliana Gonçalves', '789.012.345-16', '(71) 97890-1240', 'juliana.goncalves@email.com', 'Kit Médico Natural', 1, '39.90', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#65', '04/04/2023', '19:00', 'Igor Martins', '678.901.234-05', '(61) 96789-0129', 'igor.martins@email.com', 'Bola de Ginástica Natural', 2, '139.80', 10, 'Crédito - 2x sem juros', 'Entrega via Correios', '03', 'Avenida das Hortênsias', 6869, '34567-892', 'Hortênsias', 'Aracaju'),
('#64', '04/04/2023', '12:40', 'Helena Ribeiro', '567.890.123-94', '(51) 95678-9018', 'helena.ribeiro@email.com', 'Cubo Magnético Sustentável', 1, '119.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#63', '03/04/2023', '17:25', 'Gabriel Alves', '456.789.012-83', '(41) 94567-8907', 'gabriel.alves@email.com', 'Caixa Registradora Verde', 1, '49.90', 5, 'Crédito - 3x sem juros', 'Entrega via Correios', '03', 'Rua das Tulipas', 6667, '23456-781', 'Tulipas', 'Macapá'),
('#62', '03/04/2023', '11:05', 'Flávia Costa', '345.678.901-72', '(31) 93456-7896', 'flavia.costa@email.com', 'Tabuada de Bambu', 1, '69.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#61', '02/04/2023', '19:50', 'Eduardo Duarte', '234.567.890-61', '(21) 92345-6785', 'eduardo.duarte@email.com', 'Kit Ponte de Papelão', 4, '119.60', 0, 'Crédito - 4x sem juros', 'Entrega via Correios', '04 ', 'Avenida das Margaridas', 6465, '12345-680', 'Margaridas', 'Rio Branco'),
('#60', '02/04/2023', '12:30', 'Daniela Silva', '123.456.789-40', '(11) 91234-5683', 'daniela.silva@email.com', 'Globo Terrestre Ecológico', 1, '199.90', 15, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#59', '01/04/2023', '18:15', 'Caio Campos', '012.345.678-39', '(19) 90123-4572', 'caio.campos@email.com', 'Torre de Madeira Sustentável', 1, '59.90', 10, 'Crédito - 1x sem juros', 'Entrega via Correios', '03', 'Rua das Violetas', 6263, '01234-569', 'Violetas', 'Porto Velho'),
('#58', '01/04/2023', '09:55', 'Bianca Dias', '901.234.567-28', '(91) 99012-3461', 'bianca.dias@email.com', 'Brinquedo Sensorial Natural', 1, '59.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#57', '05/03/2023', '16:40', 'André Mendes', '890.123.456-17', '(81) 98901-2350', 'andre.mendes@email.com', 'Quebra-Cabeça 3D Ecológico', 1, '79.90', 5, 'Crédito - 2x sem juros', 'Entrega via Correios', '05', 'Avenida dos Coqueiros', 6061, '90123-457', 'Coqueiros', 'Florianópolis'),
('#56', '05/03/2023', '10:20', 'Zélia Nunes', '789.012.345-06', '(71) 97890-1239', 'zelia.nunes@email.com', 'Kit Médico Natural', 3, '119.70', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#55', '04/03/2023', '18:25', 'Yago Costa', '678.901.234-95', '(61) 96789-0128', 'yago.costa@email.com', 'Bonecos Históricos Sustentáveis', 1, '199.90', 25, 'Crédito - 3x sem juros', 'Entrega via Correios', '09', 'Rua das Orquídeas', 5859, '89012-346', 'Orquídeas', 'Cuiabá'),
('#54', '04/03/2023', '12:05', 'Xênia Oliveira', '567.890.123-84', '(51) 95678-9017', 'xenia.oliveira@email.com', 'Labirinto Sustentável', 1, '34.90', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#52', '03/03/2023', '11:10', 'Valéria Santos', '345.678.901-62', '(31) 93456-7895', 'valeria.santos@email.com', 'Cubo Mágico Ecológico', 2, '99.80', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#53', '03/03/2023', '17:30', 'Wagner Rocha', '456.789.012-73', '(41) 94567-8906', 'wagner.rocha@email.com', 'Kit Bateria Reciclada', 1, '89.90', 20, 'Crédito - 6x sem juros', 'Entrega via Correios', '08', 'Avenida das Rosas', 5657, '78901-235', 'Rosas', 'Vitória'),
('#51', '02/03/2023', '18:55', 'Ubirajara Alves', '234.567.890-51', '(21) 92345-6784', 'ubirajara.alves@email.com', 'Fogão de Brinquedo Ecológico', 1, '79.90', 15, 'Crédito - 1x sem juros', 'Entrega via Correios', '04', 'Rua das Acácias', 5455, '67890-124', 'Jardim das Acácias', 'Campo Grande'),
('#50', '02/03/2023', '12:35', 'Tatiane Ferreira', '123.456.789-30', '(11) 91234-5682', 'tatiane.ferreira@email.com', 'Xilofone de Bambu', 1, '39.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#49', '01/03/2023', '16:20', 'Sérgio Lima', '012.345.678-29', '(19) 90123-4571', 'sergio.lima@email.com', 'Blocos de Montar Ecológicos', 2, '259.80', 10, 'Crédito - 4x sem juros', 'Entrega via Correios', '05', 'Avenida das Águias', 5253, '56789-013', 'Alto da Boa Vista', 'Teresina'),
('#48', '01/03/2023', '10:00', 'Rafaela Souza', '901.234.567-18', '(91) 99012-3460', 'rafaela.souza@email.com', 'Alfabeto Ecológico', 1, '49.90', 5, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#47', '10/02/2023', '18:45', 'Quésia Vieira', '890.123.456-07', '(81) 98901-2349', 'quesia.vieira@email.com', 'Mesa de Luz Ecológica', 1, '159.90', 20, 'Crédito - 5x sem juros', 'Entrega via Correios', '06', 'Rua das Palmas', 5051, '45678-902', 'Jardim das Palmeiras', 'Maceió'),
('#46', '10/02/2023', '12:25', 'Paulo Gonçalves', '789.012.345-96', '(71) 97890-1238', 'paulo.goncalves@email.com', 'Kit Médico Natural', 1, '39.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#45', '09/02/2023', '16:10', 'Olivia Martins', '678.901.234-85', '(61) 96789-0127', 'olivia.martins@email.com', 'Bola de Ginástica Natural', 2, '139.80', 10, 'Crédito - 2x sem juros', 'Entrega via Correios', '3 dias úteis', 'Avenida dos Estados', 4849, '34567-891', 'Centro', 'João Pessoa'),
('#44', '09/02/2023', '09:50', 'Nicolas Ribeiro', '567.890.123-74', '(51) 95678-9016', 'nicolas.ribeiro@email.com', 'Cubo Magnético Sustentável', 1, '119.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#43', '08/02/2023', '18:35', 'Marina Alves', '456.789.012-63', '(41) 94567-8905', 'marina.alves@email.com', 'Caixa Registradora Verde', 1, '49.90', 5, 'Crédito - 3x sem juros', 'Entrega via Correios', '03', 'Rua dos Lírios', 4647, '23456-780', 'Jardim das Flores', 'Natal'),
('#42', '08/02/2023', '12:15', 'Leonardo Costa', '345.678.901-52', '(31) 93456-7894', 'leonardo.costa@email.com', 'Tabuada de Bambu', 1, '69.90', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#40', '07/02/2023', '10:45', 'Igor Silva', '123.456.789-20', '(11) 91234-5681', 'igor.silva@email.com', 'Globo Terrestre Ecológico', 1, '199.90', 15, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#41', '07/02/2023', '16:00', 'Juliana Duarte', '234.567.890-41', '(21) 92345-6783', 'juliana.duarte@email.com', 'Kit Ponte de Papelão', 3, '89.70', 0, 'Crédito - 4x sem juros', 'Entrega via Correios', '04', 'Avenida das Flores', 4445, '12345-679', 'Centro', 'Manaus'),
('#39', '06/02/2023', '17:30', 'Helena Campos', '012.345.678-19', '(19) 90123-4570', 'helena.campos@email.com', 'Torre de Madeira Sustentável', 2, '119.80', 10, 'Crédito - 1x sem juros', 'Entrega via Correios', '03', 'Rua das Azaleias', 4243, '01234-568', 'Jardim Botânico', 'Curitiba'),
('#38', '06/02/2023', '11:10', 'Gustavo Dias', '901.234.567-08', '(91) 99012-3459', 'gustavo.dias@email.com', 'Brinquedo Sensorial Natural', 1, '59.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#37', '05/02/2023', '15:55', 'Flávia Mendes', '890.123.456-97', '(81) 98901-2348', 'flavia.mendes@email.com', 'Quebra-Cabeça 3D Ecológico', 1, '79.90', 5, 'Crédito - 2x sem juros', 'Entrega via Correios', '05', 'Avenida Central', 4041, '90123-456', 'Centro', 'Fortaleza'),
('#36', '05/02/2023', '09:40', 'Eduardo Nunes', '789.012.345-86', '(71) 97890-1237', 'eduardo.nunes@email.com', 'Kit Médico Natural', 1, '39.90', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#35', '04/02/2023', '18:25', 'Daniela Costa', '678.901.234-75', '(61) 96789-0126', 'daniela.costa@email.com', 'Bonecos Históricos Sustentáveis', 2, '399.80', 25, 'Crédito - 3x sem juros', 'Entrega via Correios', '10', 'Rua das Camélias', 3839, '89012-345', 'Boa Vista', 'Recife'),
('#34', '04/02/2023', '12:05', 'Caio Oliveira', '567.890.123-64', '(51) 95678-9015', 'caio.oliveira@email.com', 'Labirinto Sustentável', 1, '34.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#33', '03/02/2023', '17:50', 'Bianca Rocha', '456.789.012-53', '(41) 94567-8904', 'bianca.rocha@email.com', 'Kit Bateria Reciclada', 1, '89.90', 20, 'Crédito - 6x sem juros', 'Entrega via Correios', '08', 'Alameda dos Anjos', 3637, '78901-234', 'Centro', 'Salvador'),
('#32', '03/02/2023', '10:35', 'André Santos', '345.678.901-42', '(31) 93456-7893', 'andre.santos@email.com', 'Cubo Mágico Ecológico', 1, '49.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#31', '02/02/2023', '16:20', 'Zélia Alves', '234.567.890-31', '(21) 92345-6782', 'zelia.alves@email.com', 'Fogão de Brinquedo Ecológico', 1, '79.90', 10, 'Crédito - 1x sem juros', 'Entrega via Correios', '04', 'Rua das Violetas', 3435, '67890-123', 'Jardim América', 'Goiânia'),
('#30', '02/02/2023', '11:00', 'Yasmin Ferreira', '123.456.789-10', '(11) 91234-5680', 'yasmin.ferreira@email.com', 'Xilofone de Bambu', 2, '79.80', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#28', '01/02/2023', '09:30', 'Vanessa Souza', '901.234.567-98', '(91) 99012-3457', 'vanessa.souza@email.com', 'Alfabeto Ecológico', 1, '49.90', 5, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#29', '01/02/2023', '14:45', 'Wagner Lima', '012.345.678-09', '(19) 90123-4568', 'wagner.lima@email.com', 'Blocos de Montar Ecológicos', 3, '389.70', 15, 'Crédito - 4x sem juros', 'Entrega via Correios', '05', 'Avenida das Torres', 3233, '56789-012', 'Centro', 'Brasília'),
('#27', '15/01/2023', '18:15', 'Tiago Martins', '890.123.456-87', '(81) 98901-2346', 'tiago.martins@email.com', 'Mesa de Luz Ecológica', 1, '159.90', 20, 'Crédito - 5x sem juros', 'Entrega via Correios', '06', 'Rua das Jabuticabeiras', 3031, '45678-901', 'Trindade', 'Florianópolis'),
('#26', '15/01/2023', '12:55', 'Sandra Vieira', '789.012.345-76', '(71) 97890-1235', 'sandra.vieira@email.com', 'Kit Médico Natural', 2, '79.80', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#25', '14/01/2023', '15:40', 'Rafael Gonçalves', '678.901.234-65', '(61) 96789-0124', 'rafael.goncalves@email.com', 'Bola de Ginástica Natural', 1, '69.90', 10, 'Crédito - 2x sem juros', 'Entrega via Correios', '03', 'Avenida das Nações', 2829, '34567-890', 'Interlagos', 'São Paulo'),
('#24', '14/01/2023', '09:20', 'Patrícia Almeida', '567.890.123-54', '(51) 95678-9013', 'patricia.almeida@email.com', 'Cubo Magnético Sustentável', 1, '119.90', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#23', '13/01/2023', '17:55', 'Otávio Ribeiro', '456.789.012-43', '(41) 94567-8902', 'otavio.ribeiro@email.com', 'Caixa Registradora Verde', 1, '49.90', 15, 'Crédito - 3x sem juros', 'Entrega via Correios', '04', 'Rua das Hortênsias', 2627, '23456-789', 'Petrópolis', 'Belo Horizonte'),
('#22', '13/01/2023', '11:45', 'Natália Costa', '345.678.901-32', '(31) 93456-7891', 'natalia.costa@email.com', 'Tabuada de Bambu', 1, '69.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#21', '12/01/2023', '16:30', 'Marcos Duarte', '234.567.890-21', '(21) 92345-6781', 'marcos.duarte@email.com', 'Kit Ponte de Papelão', 4, '119.60', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#20', '12/01/2023', '10:10', 'Laura Mendes', '123.456.789-09', '(11) 91234-5679', 'laura.mendes@email.com', 'Globo Terrestre Ecológico', 1, '199.90', 10, 'Crédito - 4x sem juros', 'Entrega via Correios', '07', 'Rua dos Pinheiros', 2425, '12345-678', 'Pinheiros', 'São Paulo'),
('#18', '11/01/2023', '09:35', 'Isabela Campos', '901.234.567-89', '(91) 99012-3456', 'isabela.campos@email.com', 'Brinquedo Sensorial Natural', 2, '119.80', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#19', '11/01/2023', '14:50', 'João Pedro Silva', '012.345.678-90', '(19) 90123-4567', 'joao.silva@email.com', 'Torre de Madeira Sustentável', 1, '59.90', 5, 'Crédito - 1x sem juros', 'Entrega via Correios', '03', 'Alameda Rio Negro', 2223, '01234-567', 'Alphaville', 'Barueri'),
('#17', '10/01/2023', '18:00', 'Henrique Alves', '890.123.456-78', '(81) 98901-2345', 'henrique.alves@email.com', 'Quebra-Cabeça 3D Ecológico', 1, '79.90', 10, 'Crédito - 2x sem juros', 'Entrega via Correios', '05', 'Rua das Margaridas', 2021, '90123-456', 'Boa Viagem', 'Recife'),
('#16', '10/01/2023', '12:40', 'Gabriela Freitas', '789.012.345-67', '(71) 97890-1234', 'gabriela.freitas@email.com', 'Kit Médico Natural', 3, '119.70', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#15', '09/01/2023', '15:25', 'Fábio Lima', '678.901.234-56', '(61) 96789-0123', 'fabio.lima@email.com', 'Bonecos Históricos Sustentáveis', 1, '199.90', 20, 'Crédito - 6x sem juros', 'Entrega via Correios', '09', 'Avenida Atlântica', 1819, '89012-345', 'Copacabana', 'Rio de Janeiro'),
('#14', '09/01/2023', '10:05', 'Elaine Costa', '567.890.123-45', '(51) 95678-9012', 'elaine.costa@email.com', 'Labirinto Sustentável', 1, '34.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#13', '08/01/2023', '17:45', 'Diego Oliveira', '456.789.012-34', '(41) 94567-8901', 'diego.oliveira@email.com', 'Kit Bateria Reciclada', 1, '89.90', 15, 'Crédito - 3x sem juros', 'Entrega via Correios', '06', 'Rua das Orquídeas', 1617, '78901-234', 'Lourdes', 'Belo Horizonte'),
('#12', '08/01/2023', '11:30', 'Carla Rocha', '345.678.901-23', '(31) 93456-7890', 'carla.rocha@email.com', 'Cubo Mágico Ecológico', 2, '99.80', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#10', '07/01/2023', '09:55', 'Amanda Dias', '123.456.789-01', '(11) 91234-5678', 'amanda.dias@email.com', 'Alfabeto Ecológico', 1, '49.90', 5, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#11', '07/01/2023', '14:15', 'Bruno Souza', '234.567.890-12', '(21) 92345-6789', 'bruno.souza@email.com', 'Fogão de Brinquedo Ecológico', 1, '79.90', 0, 'Crédito - 1x sem juros', 'Entrega via Correios', '04', 'Avenida Paulista', 1415, '67890-123', 'Bela Vista', 'São Paulo'),
('#09', '06/01/2023', '16:40', 'Lucas Martins', '000.111.222-33', '(19) 97890-1234', 'lucas.martins@email.com', 'Miniaturas Ecológicas', 1, '149.90', 10, 'Crédito - 2x sem juros', 'Entrega via Correios', '08', 'Rua das Acácias', 1213, '56789-012', 'Vila Madalena', 'Curitiba'),
('#08', '06/01/2023', '10:25', 'Juliana Pereira', '999.000.111-22', '(91) 96789-0123', 'juliana.pereira@email.com', 'Argila 100% Natural', 5, '249.50', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#07', '05/01/2023', '15:10', 'Gustavo Henrique', '888.999.000-11', '(81) 95678-9012', 'gustavo.henrique@email.com', 'Cama Elástica Sustentável', 1, '299.90', 25, 'Crédito - 5x sem juros', 'Entrega via Correios', '10', 'Alameda Santos', 1011, '45678-901', 'Moema', 'Porto Alegre'),
('#06', '05/01/2023', '08:50', 'Patrícia Gomes', '777.888.999-00', '(71) 94567-8901', 'patricia.gomes@email.com', 'Bola de Ginástica Natural', 2, '139.80', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#05', '04/01/2023', '17:35', 'Ricardo Nunes', '666.777.888-99', '(61) 93456-7890', 'ricardo.nunes@email.com', 'Robô Programável Verde', 1, '249.90', 20, 'Crédito - 4x sem juros', 'Entrega via Correios', '07', 'Rua das Palmeiras', 789, '34567-890', 'Barra', 'Belo Horizonte'),
('#04', '04/01/2023', '11:10', 'Fernanda Costa', '555.666.777-88', '(51) 92345-6789', 'fernanda.costa@email.com', 'Kit Pintura Natural', 3, '89.70', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#03', '03/01/2023', '16:20', 'Roberto Alves', '444.555.666-77', '(41) 91234-5678', 'roberto.alves@email.com', 'Mesa de Luz Ecológica', 1, '159.90', 15, 'Crédito - 6x sem juros', 'Entrega via Correios', '05', 'Avenida Brasil', 456, '23456-789', 'Jardins', 'Rio de Janeiro'),
('#01', '02/01/2023', '09:15', 'Ana Clara Santos', '111.222.333-44', '(11) 98765-4321', 'ana.santos@email.com', 'Kit Ciências Sustentável', 1, '89.90', 5, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#02', '03/01/2023', '10:45', 'Mariana Oliveira', '333.444.555-66', '(31) 98765-1234', 'mariana.oliveira@email.com', 'Xilofone de Bambu', 1, '39.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#89', '23/03/2024', '10:55', 'Tatiana Dias', '102.345.678-90', '(21) 95678-9012', 'tatiana.dias@email.com', 'Argila 100% Natural', 5, '249.50', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#90', '24/03/2024', '15:20', 'Ubiratã Moraes', '213.456.789-01', '(31) 96789-0123', 'ubirata.moraes@email.com', 'Bola de Ginástica Natural', 1, '69.90', 5, 'Crédito - 2x sem juros', 'Entrega via Correios', '03', 'Avenida das Orquídeas', 163, '56789-123', 'Orquídeas', 'Vitória'),
('#91', '25/04/2024', '11:45', 'Valentina Pires', '324.567.890-12', '(41) 97890-1234', 'valentina.pires@email.com', 'Kit Médico Natural', 2, '79.80', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#92', '26/04/2024', '16:10', 'William Teixeira', '435.678.901-23', '(51) 98901-2345', 'william.teixeira@email.com', 'Cubo Magnético Sustentável', 1, '119.90', 15, 'Crédito - 3x sem juros', 'Entrega via Correios', '06', 'Rua dos Cravos', 171, '67890-234', 'Cravos', 'Natal'),
('#93', '27/05/2024', '09:35', 'Ximena Andrade', '546.789.012-34', '(61) 99012-3456', 'ximena.andrade@email.com', 'Robô Programável Verde', 1, '249.90', 25, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#94', '28/05/2024', '14:00', 'Yago Santana', '657.890.123-45', '(71) 90123-4567', 'yago.santana@email.com', 'Cama Elástica Sustentável', 1, '299.90', 0, 'Crédito - 1x sem juros', 'Entrega via Correios', '10', 'Alameda das Azaleias', 179, '78901-345', 'Azaleias', 'Maceió'),
('#95', '29/06/2024', '10:25', 'Zélia Duarte', '768.901.234-56', '(81) 91234-5678', 'zelia.duarte@email.com', 'Alfabeto Ecológico', 1, '49.90', 0, 'Dinheiro', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#96', '30/06/2024', '14:50', 'Arthur Nascimento', '879.012.345-67', '(91) 92345-6789', 'arthur.nascimento@email.com', 'Quebra-Cabeça 3D Ecológico', 1, '79.90', 5, 'Crédito - 4x sem juros', 'Entrega via Correios', '05', 'Rua das Gardênias', 187, '89012-456', 'Gardênias', 'João Pessoa'),
('#97', '01/07/2024', '12:15', 'Bianca Fonseca', '980.123.456-78', '(19) 93456-7890', 'bianca.fonseca@email.com', 'Brinquedo Sensorial Natural', 1, '59.90', 0, 'Pix', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#98', '02/07/2024', '16:40', 'Caio Rocha', '091.234.567-89', '(11) 94567-8901', 'caio.rocha@email.com', 'Miniaturas Ecológicas', 1, '149.90', 20, 'Crédito - 6x sem juros', 'Entrega via Correios', '08', 'Avenida das Tulipas', 195, '90123-567', 'Tulipas', 'Cuiabá'),
('#99', '03/08/2024', '10:05', 'Daniel Medeiros', '102.345.678-90', '(21) 95678-9012', 'daniel.medeiros@email.com', 'Cubo Mágico Ecológico', 2, '99.80', 0, 'Débito', 'Retirada no local', NULL, NULL, NULL, NULL, NULL, NULL),
('#100', '04/09/2024', '14:30', 'Eliana Castro', '203.456.789-01', '(31) 96789-0123', 'eliana.castro@email.com', 'Kit Ponte de Papelão', 3, '89.70', 0, 'Crédito - 3x sem juros', 'Entrega via Correios', '04', 'Rua das Acácias', 203, '01234-567', 'Jardim América', 'Belo Horizonte');
COMMIT;

-- --------------------------------------------------------

--
-- Estrutura da tabela `AlunoCurso`
--

CREATE TABLE AlunoCurso (
    id_aluno INT,
    id_curso INT,
    PRIMARY KEY (id_aluno, id_curso),
    FOREIGN KEY (id_aluno) REFERENCES Jovens(matricula),
    FOREIGN KEY (id_curso) REFERENCES Cursos(id)
);

--
-- Estrutura da tabela `ItemVenda`
--

CREATE TABLE ItemVenda (
    id_venda INT,
    id_produto INT,
    PRIMARY KEY (id_venda, id_produto),
    FOREIGN KEY (id_venda) REFERENCES Vendas(pedido),
    FOREIGN KEY (id_produto) REFERENCES Produtos(id)
);



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
