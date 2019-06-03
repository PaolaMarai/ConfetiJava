CREATE TABLE Usuario (
  idUsuario INT GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombre VARCHAR(60) NOT NULL,
  apellido VARCHAR(60) NOT NULL,
  correo VARCHAR(100) NOT NULL,
  clave VARCHAR(500) NOT NULL,
  usuario VARCHAR(45) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  pin VARCHAR(3) NOT NULL,
  autenticado INT NOT NULL DEFAULT 0,
  isAdmin INT NOT NULL DEFAULT 0,

  PRIMARY KEY (idUsuario));


-- -----------------------------------------------------
-- Table `mydb`.`Pregunta`
-- -----------------------------------------------------
CREATE TABLE Pregunta (
  
  idPregunta INT GENERATED ALWAYS AS IDENTITY  NOT NULL,
  pregunta VARCHAR(150) NOT NULL,
  respuestaFalsa1 VARCHAR(100) NOT NULL,
  respuestaFalsa2 VARCHAR(100) NOT NULL,
  respuestaFalsa3 VARCHAR(100) NOT NULL,
  respuestaCorrecta VARCHAR(100) NOT NULL,
  idEmision int NOT NULL,

  PRIMARY KEY (idPregunta),
  FOREIGN KEY (idEmision) REFERENCES Emision(idEmision)
);

-- -----------------------------------------------------
-- Table `mydb`.`Emision`
-- -----------------------------------------------------
CREATE TABLE Emision (
  idEmision INT GENERATED ALWAYS AS IDENTITY NOT NULL,
  fecha DATE NOT NULL,
  horaInicio TIME NOT NULL,
  horaFin TIME NOT NULL,
  enEmision INT NOT NULL DEFAULT 0,
  PRIMARY KEY (idEmision));

 -----------------------------------------------------
-- Table `mydb`.`Participante`
-- -----------------------------------------------------
CREATE TABLE Participante (
  idParticipante INT GENERATED ALWAYS AS IDENTITY NOT NULL,
  idUsuario INT NOT NULL,
  idEmision INT NOT NULL,
  isWinner INT NOT NULL DEFAULT 1,
  puntaje INT NOT NULL DEFAULT 0,
  PRIMARY KEY (idParticipante),
  FOREIGN KEY (idEmision) REFERENCES Emision(idEmision),
  FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);
