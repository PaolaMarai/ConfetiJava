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

  PRIMARY KEY (idPregunta));

INSERT INTO pregunta VALUES (
DEFAULT,
'¿Cuál es el mes de los reyes magos?',
'Enero',
'Mayo',
'Agosto',
'Enero',
DEFAULT
)


-- -----------------------------------------------------
-- Table `mydb`.`Emision`
-- -----------------------------------------------------
CREATE TABLE Emision (
  idEmision INT GENERATED ALWAYS AS IDENTITY NOT NULL,
  fecha DATE NOT NULL,
  fechaFin DATE NOT NULL,
  horaInicio TIME NOT NULL,
  horaFin TIME NOT NULL,
  enEmision INT NOT NULL DEFAULT 0,
  PRIMARY KEY (idEmision));

INSERT INTO emision VALUES (
DEFAULT,
'28/05/2019',
'29/05/2019',
'19:30:00',
'19:50:00',
0
)


-- -----------------------------------------------------
-- Table `mydb`.`Pregunta_has_Emision`
-- -----------------------------------------------------
CREATE TABLE Pregunta_has_Emision (
  "Pregunta_idPregunta" INT NOT NULL,
  "Emision_idEmision" INT NOT NULL,
  PRIMARY KEY ("Pregunta_idPregunta", "Emision_idEmision"),
  CONSTRAINT "fk_Pregunta_has_Emision_Pregunta1"
    FOREIGN KEY ("Pregunta_idPregunta")
    REFERENCES Pregunta ("idPregunta")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_Pregunta_has_Emision_Emision1"
    FOREIGN KEY ("Emision_idEmision")
    REFERENCES Emision ("idEmision")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`Participante`
-- -----------------------------------------------------
CREATE TABLE Participante (
  "Usuario_idUsuario" INT NOT NULL,
  "Emision_idEmision" INT NOT NULL,
  "isWinner" INT NOT NULL DEFAULT 1,
  "puntaje" INT NOT NULL DEFAULT 0,
  PRIMARY KEY ("Usuario_idUsuario", "Emision_idEmision"),
  CONSTRAINT "fk_Usuario_has_Emision_Usuario1"
    FOREIGN KEY ("Usuario_idUsuario")
    REFERENCES Usuario ("idUsuario")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_Usuario_has_Emision_Emision1"
    FOREIGN KEY ("Emision_idEmision")
    REFERENCES Emision ("idEmision")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
