-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 01-Abr-2019 às 05:35
-- Versão do servidor: 8.0.13-4
-- versão do PHP: 7.2.15-0ubuntu0.18.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `BNlpvcY5fn`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `Filme`
--

CREATE TABLE `Filme` (
  `id` int(11) NOT NULL,
  `titulo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ano` int(11) NOT NULL,
  `genero` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_diretor` int(11) NOT NULL,
  `id_ator` int(11) NOT NULL,
  `id_atriz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Pessoa`
--

CREATE TABLE `Pessoa` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cargo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nascimento` date NOT NULL,
  `genero` varchar(2) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Premiacao`
--

CREATE TABLE `Premiacao` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ano` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `Premio`
--

CREATE TABLE `Premio` (
  `id` int(11) NOT NULL,
  `categoria` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_premiacao` int(11) NOT NULL,
  `id_vencedor` int(11) DEFAULT NULL,
  `id_filme` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Filme`
--
ALTER TABLE `Filme`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_diretor` (`id_diretor`),
  ADD KEY `id_ator` (`id_ator`),
  ADD KEY `id_atriz` (`id_atriz`);

--
-- Indexes for table `Pessoa`
--
ALTER TABLE `Pessoa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Premiacao`
--
ALTER TABLE `Premiacao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Premio`
--
ALTER TABLE `Premio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_premiacao` (`id_premiacao`),
  ADD KEY `id_vencedor` (`id_vencedor`),
  ADD KEY `id_filme` (`id_filme`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Filme`
--
ALTER TABLE `Filme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Pessoa`
--
ALTER TABLE `Pessoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Premiacao`
--
ALTER TABLE `Premiacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Premio`
--
ALTER TABLE `Premio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `Filme`
--
ALTER TABLE `Filme`
  ADD CONSTRAINT `Filme_ibfk_1` FOREIGN KEY (`id_ator`) REFERENCES `Pessoa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Filme_ibfk_2` FOREIGN KEY (`id_atriz`) REFERENCES `Pessoa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Filme_ibfk_3` FOREIGN KEY (`id_diretor`) REFERENCES `Pessoa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `Premio`
--
ALTER TABLE `Premio`
  ADD CONSTRAINT `Premio_ibfk_1` FOREIGN KEY (`id_filme`) REFERENCES `Filme` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Premio_ibfk_2` FOREIGN KEY (`id_vencedor`) REFERENCES `Pessoa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Premio_ibfk_3` FOREIGN KEY (`id_premiacao`) REFERENCES `Premiacao` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
