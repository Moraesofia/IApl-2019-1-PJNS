SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;

-----------------------------------------------------------
-- Criação das tabelas
-----------------------------------------------------------

CREATE TABLE `Filme` (
  `id` int(5) NOT NULL,
  `titulo` varchar(31) COLLATE utf8_unicode_ci NOT NULL,
  `ano` int(5) NOT NULL,
  `genero` varchar(21) COLLATE utf8_unicode_ci NOT NULL,
  `id_diretor` int(5) NOT NULL,
  `id_ator` int(5) NOT NULL,
  `id_atriz` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Pessoa` (
  `id` int(5) NOT NULL,
  `nome` varchar(51) COLLATE utf8_unicode_ci NOT NULL,
  `cargo` varchar(21) COLLATE utf8_unicode_ci NOT NULL,
  `nascimento` date NOT NULL,
  `genero` varchar(2) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Premiacao` (
  `id` int(5) NOT NULL,
  `nome` varchar(21) COLLATE utf8_unicode_ci NOT NULL,
  `ano` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Premio` (
  `id` int(5) NOT NULL,
  `categoria` varchar(21) COLLATE utf8_unicode_ci NOT NULL,
  `id_premiacao` int(5) NOT NULL,
  `id_vencedor` int(5) DEFAULT NULL,
  `id_filme` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------
-- Adição das chaves
-----------------------------------------------------------

ALTER TABLE `Filme`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_diretor` (`id_diretor`),
  ADD KEY `id_ator` (`id_ator`),
  ADD KEY `id_atriz` (`id_atriz`);

ALTER TABLE `Pessoa`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `Premiacao`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `Premio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_premiacao` (`id_premiacao`),
  ADD KEY `id_vencedor` (`id_vencedor`),
  ADD KEY `id_filme` (`id_filme`);

-----------------------------------------------------------
-- Adição dos auto increments
-----------------------------------------------------------

ALTER TABLE `Filme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `Pessoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `Premiacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `Premio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-----------------------------------------------------------
-- Adição dos constraints
-----------------------------------------------------------

ALTER TABLE `Filme`
  ADD CONSTRAINT `Filme_ibfk_1` FOREIGN KEY (`id_ator`) REFERENCES `Pessoa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Filme_ibfk_2` FOREIGN KEY (`id_atriz`) REFERENCES `Pessoa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Filme_ibfk_3` FOREIGN KEY (`id_diretor`) REFERENCES `Pessoa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Premio`
  ADD CONSTRAINT `Premio_ibfk_1` FOREIGN KEY (`id_filme`) REFERENCES `Filme` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Premio_ibfk_2` FOREIGN KEY (`id_vencedor`) REFERENCES `Pessoa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Premio_ibfk_3` FOREIGN KEY (`id_premiacao`) REFERENCES `Premiacao` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
  
COMMIT;
