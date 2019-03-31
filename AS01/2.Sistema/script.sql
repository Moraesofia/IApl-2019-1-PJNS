CREATE DATABASE IF NOT EXISTS awards;

USE awards;

CREATE TABLE filme(
  nome VARCHAR(255),
  ano INT,
  genero VARCHAR(255),
  PRIMARY KEY (nome)
);

CREATE TABLE premio(
  id INT,
  categoria VARCHAR(255),
  local_cerimonia VARCHAR(255),
  ano INT,
  filme_id INT,
  ator INT,
  atriz INT,
  PRIMARY KEY (id),
  FOREIGN KEY filme REFERENCES filme(id),
  FOREIGN KEY ator REFERENCES pessoa(id),
);

CREATE TABLE pessoa(
  id INT,
  nome VARCHAR(255),
  cargo VARCHAR(255),
  nascimento DATE,
  genero VARCHAR(255),
  PRIMARY KEY (id)
);
