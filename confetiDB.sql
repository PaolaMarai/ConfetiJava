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
-- Table `mydb`.`Emision`
-- -----------------------------------------------------
CREATE TABLE Emision (
  idEmision INT GENERATED ALWAYS AS IDENTITY NOT NULL,
  fecha varchar(10) NOT NULL,
  fechaFin varchar(10) NOT NULL,
  horaInicio varchar(8) NOT NULL,
  horaFin varchar(9) NOT NULL,
  enEmision INT NOT NULL DEFAULT 0,
  PRIMARY KEY (idEmision));
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
  CONSTRAINT fk_Emision_has_Pregunta_Pregunta1
    FOREIGN KEY (idEmision)
    REFERENCES Emision (idEmision)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `mydb`.`Participante`
-- -----------------------------------------------------
CREATE TABLE Participante (
  Usuario_idUsuario INT NOT NULL,
  Emision_idEmision INT NOT NULL,
  isWinner INT NOT NULL DEFAULT 1,
  puntaje INT NOT NULL DEFAULT 0,
  PRIMARY KEY (Usuario_idUsuario, Emision_idEmision),
  CONSTRAINT fk_Usuario_has_Emision_Usuario1
    FOREIGN KEY (Usuario_idUsuario)
    REFERENCES Usuario (idUsuario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Usuario_has_Emision_Emision1
    FOREIGN KEY (Emision_idEmision)
    REFERENCES Emision (idEmision)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO emision VALUES (
 DEFAULT,
 '03/06/2019',
 '03/06/2019',
 '17:00:00',
 '19:00:00',
 0
 );

 INSERT INTO pregunta VALUES (
 DEFAULT,
 '¿Quién descubio la penicilina?',
 'Alexander Flemin',
 'Maluma',
 'Trump',
 'Alexander Flemin',
 1
 );
