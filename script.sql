CREATE DATABASE IF NOT EXISTS awards;

USE awards;

CREATE TABLE filme(
  nome VARCHAR(255),
  ano INT,
  genero VARCHAR(255),
  premio_id VARCHAR(255),
  FOREIGN KEY premio_id
  REFERENCES premio(id)
  PRIMARY KEY (nome)
);

CREATE TABLE premio(
  id INT,
  categoria VARCHAR(255),
  ano INT,
  PRIMARY KEY (id)
);

CREATE TABLE pessoa(
  nome VARCHAR(255),
  cargo VARCHAR(255),
  nascimento DATE,
  genero VARCHAR(255),
  premio_id VARCHAR(255),
  FOREIGN KEY premio_id
  REFERENCES premio(id),
  PRIMARY KEY (nome)
);

CREATE TABLE filme-pessoa(
  id INT,
  nome_filme VARCHAR(255),
  nome_pessoa VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY nome_filme
  REFERENCES filme(nome),
  FOREIGN KEY nome_pessoa
  REFERENCES pessoa(nome)
);