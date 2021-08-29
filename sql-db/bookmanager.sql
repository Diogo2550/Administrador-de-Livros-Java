-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 19-Jun-2021 às 18:43
-- Versão do servidor: 10.4.18-MariaDB
-- versão do PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bookmanager`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(40) DEFAULT NULL,
  `synopsis` text DEFAULT NULL,
  `author` varchar(40) DEFAULT NULL,
  `genre` smallint(6) DEFAULT NULL,
  `releaseDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `books`
--

INSERT INTO `books` (`id`, `title`, `synopsis`, `author`, `genre`, `releaseDate`) VALUES
(1, 'Alice no País das Maravilhas', 'Era uma vez Alice', 'Charles Lutwidge Dodgson', 1, '2021-06-17'),
(2, 'Harry Potter e a Pedra Filosofal', 'O livro que alguém aí gosta, talvez vc goste também', 'J. K. Rowling', 1, '2015-05-21'),
(3, 'Harry Potter e a Câmara Secreta', 'O livro que alguém aí gosta, talvez vc goste também', 'J. K. Rowling', 1, '2015-05-21'),
(6, 'Harry Potter e o Prisioneiro de Azkaban', 'Harry Potter e o Prisioneiro de Azkaban (no original em ingl\0s Harry Potter and the Prisoner of Azkaban) \0 o terceiro livro dos sete volumes da s\0rie de fantasia Harry Potter, tanto em termos cronol\0gicos como em ordem de publica\0\0o, da autora inglesa J. K. Rowling. Foi primeiramente publicado no Reino Unido pela editora londrina Bloomsbury em 1999.', 'J. K. Rowling', 4, '1999-07-08'),
(7, 'Programacao para idiotas', 'Um livro escrito com o intuito de ensinar programa\0\0o para leigos em computa\0\0o.', 'Diogo Alves', 6, '2021-01-01'),
(9, 'O Senhor dos An\0is', 'O Senhor dos An\0is (The Lord of the Rings, no original) \0 um livro de alta fantasia, escrito pelo escritor brit\0nico J. R. R. Tolkien. Escrita entre 1937 e 1949, com muitas partes criadas durante a Segunda Guerra Mundial.', 'J. R. R. Tolkien', 4, '1954-07-29');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullName` varchar(30) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `fullName`, `username`, `password`, `admin`) VALUES
(1, 'Diogo Alves', 'Diogo', '123', 1),
(2, 'Alice Gonçalves', 'lice', '12345678', 1),
(3, 'João Mayall', 'mayall', '123456', 1),
(4, 'Carlos Augusto', 'carlos', '12345', 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
