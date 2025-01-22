DROP SCHEMA `BD_JPA_EJ_01` ;
COMMIT;

CREATE SCHEMA IF NOT EXISTS `BD_JPA_EJ_01` DEFAULT CHARACTER SET utf8 ;
USE `BD_JPA_EJ_01` ;

CREATE TABLE IF NOT EXISTS `BD_JPA_EJ_01`.`Autores` (
  `idAutor` INT NOT NULL AUTO_INCREMENT,
  `nomAutor` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idAutor`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `nomAutor_UNIQUE` ON `BD_JPA_EJ_01`.`Autores` (`nomAutor` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `BD_JPA_EJ_01`.`Libros` (
  `idLibros` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NULL,
  `fechaPublicacion` DATETIME NULL,
  `precio` DECIMAL(12,2) NOT NULL,
  `autor` INT NOT NULL,
  PRIMARY KEY (`idLibros`),
  CONSTRAINT `fk_Libros_Autores`
    FOREIGN KEY (`autor`)
    REFERENCES `BD_JPA_EJ_01`.`Autores` (`idAutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Libros_Autores_idx` ON `BD_JPA_EJ_01`.`Libros` (`autor` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `BD_JPA_EJ_01`.`Categorias` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `nomCategoria` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `nomCategoria_UNIQUE` ON `BD_JPA_EJ_01`.`Categorias` (`nomCategoria` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `BD_JPA_EJ_01`.`Libros_Categorias` (
  `libro` INT NOT NULL,
  `categoria` INT NOT NULL,
  PRIMARY KEY (`libro`, `categoria`),
  CONSTRAINT `fk_Libros_Categorias_01`
    FOREIGN KEY (`libro`)
    REFERENCES `BD_JPA_EJ_01`.`Libros` (`idLibros`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Libros_Categorias_02`
    FOREIGN KEY (`categoria`)
    REFERENCES `BD_JPA_EJ_01`.`Categorias` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Libros_Categorias_01` ON `BD_JPA_EJ_01`.`Libros_Categorias` (`categoria` ASC) VISIBLE;

CREATE INDEX `fk_Libros_Categorias_02` ON `BD_JPA_EJ_01`.`Libros_Categorias` (`libro` ASC) VISIBLE;

COMMIT;

-- Inserción de categorías
INSERT INTO `BD_JPA_EJ_01`.`Categorias` (`nomCategoria`) VALUES
('Novela Contemporánea'),
('Histórica'),
('Ficción'),
('Ensayo'),
('Misterio'),
('Romántica'),
('Ciencia Ficción'),
('Poesía'),
('Biografía'),
('Aventura');

-- Inserción de autores
INSERT INTO `BD_JPA_EJ_01`.`Autores` (`nomAutor`) VALUES
('Carmen Laforet'), ('Ana María Matute'), ('Camilo José Cela'), ('Eduardo Mendoza'),
('Lorenzo Silva'), ('Juan Marsé'), ('Antonio Gala'), ('Fernando Savater'),
('Almudena Grandes'), ('María Dueñas'), ('Rosa Montero'), ('Julia Navarro'),
('Carlos Ruiz Zafón'), ('Isabel Allende'), ('Javier Cercas'), ('Antonio Muñoz Molina'),
('Álvaro Pombo'), ('Luis Mateo Díez'), ('Santiago Posteguillo'), ('Juan Manuel de Prada'),
('José María Gironella'), ('Carmen Martín Gaite'), ('Manuel Vázquez Montalbán'),
('Blanca Andreu'), ('Fernando Aramburu'), ('Rafael Chirbes'), ('Soledad Puértolas'),
('Luis Landero'), ('Luz Gabás'), ('Marta Sanz'), ('María Barbal'), ('Javier Marías'),
('Rosa Regàs'), ('Julia Uceda'), ('Miguel Delibes'), ('Ana Alcolea'), 
('Mercedes Salisachs'), ('Alberto Méndez'), ('Maruja Torres');
COMMIT;

SELECT * FROM CATEGORIAS ORDER BY IDCATEGORIA;
SELECT * FROM AUTORES ORDER BY IDAUTOR;

INSERT INTO Libros (titulo, fechaPublicacion, precio, autor) VALUES
('El tiempo entre costuras', '2009-01-01', 24.99, 10),
('Misión olvido', '2012-01-01', 22.99, 10),
('Las hijas del capitán', '2018-01-01', 25.99, 10),

('La ridícula idea de no volver a verte', '2013-01-01', 19.50, 11),
('El peso del corazón', '2015-01-01', 18.50, 11),
('La carne', '2016-01-01', 20.50, 11),

('Dime quién soy', '2010-01-01', 23.99, 12),
('Historia de un canalla', '2016-01-01', 22.50, 12),
('Dispara, yo ya estoy muerto', '2013-01-01', 24.00, 12);

SELECT * FROM LIBROS ORDER BY idLibros;

-- Insertar relaciones entre libros y categorías
INSERT INTO Libros_Categorias (libro, categoria) VALUES
-- Relación para libros de María Dueñas
(1, 2), (1, 3), (1, 4),  -- El tiempo entre costuras
(2, 1), (2, 5),         -- Misión olvido
(3, 3),                 -- Las hijas del capitán

-- Relación para libros de Rosa Montero
(4, 1),                 -- La ridícula idea de no volver a verte
(5, 4), (5, 7),         -- El peso del corazón
(6, 2),                 -- La carne

-- Relación para libros de Julia Navarro
(7, 2), (7, 3),         -- Dime quién soy
(8, 1),                 -- Historia de un canalla
(9, 3), (9, 6);         -- Dispara, yo ya estoy muerto

SELECT * FROM Libros_Categorias;

INSERT INTO Libros (titulo, fechaPublicacion, precio, autor) VALUES
('La sombra del viento', '2001-01-01', 19.99, 13),
('El juego del ángel', '2008-01-01', 21.99, 13),
('El laberinto de los espíritus', '2016-01-01', 23.50, 13),

('El jinete polaco', '1991-01-01', 24.99, 16),
('Sefarad', '2001-01-01', 22.50, 16),
('Todo lo que era sólido', '2013-01-01', 23.50, 16),

('La tempestad', '1997-01-01', 20.99, 20),
('Las máscaras del héroe', '1996-01-01', 21.50, 20),
('Me hallará la muerte', '2012-01-01', 22.00, 20),

('Patria', '2016-01-01', 24.99, 25),
('Los peces de la amargura', '2006-01-01', 21.50, 25),
('Años lentos', '2011-01-01', 22.50, 25),

('Crematorio', '2007-01-01', 23.99, 26),
('En la orilla', '2013-01-01', 22.50, 26),
('Paris-Austerlitz', '2016-01-01', 20.99, 26),

('El camino', '1950-01-01', 20.99, 35),
('Los santos inocentes', '1981-01-01', 22.50, 35),
('Cinco horas con Mario', '1966-01-01', 23.99, 35),

('Farándula', '2015-01-01', 20.99, 30),
('Clavícula', '2017-01-01', 21.50, 30),
('Daniela Astor y la caja negra', '2013-01-01', 22.99, 30);

INSERT INTO Libros_Categorias (libro, categoria) VALUES
-- Relación para libros de Carlos Ruiz Zafón
(10, 3), (10, 5), -- La sombra del viento
(11, 3),          -- El juego del ángel
(12, 2),          -- El laberinto de los espíritus

-- Relación para libros de Antonio Muñoz Molina
(13, 3), (13, 4), -- El jinete polaco
(14, 1),          -- Sefarad
(15, 4),          -- Todo lo que era sólido

-- Relación para libros de Juan Manuel de Prada
(16, 1),          -- La tempestad
(17, 3),          -- Las máscaras del héroe
(18, 2),          -- Me hallará la muerte

-- Relación para libros de Fernando Aramburu
(19, 1), (19, 5), -- Patria
(20, 4),          -- Los peces de la amargura
(21, 3),          -- Años lentos

-- Relación para libros de Rafael Chirbes
(22, 1),          -- Crematorio
(23, 4),          -- En la orilla
(24, 2),          -- Paris-Austerlitz

-- Relación para libros de Miguel Delibes
(25, 1),          -- El camino
(26, 2), (26, 5), -- Los santos inocentes
(27, 3),          -- Cinco horas con Mario

-- Relación para libros de Marta Sanz
(28, 1), (28, 4), -- Farándula
(29, 4),          -- Clavícula
(30, 3);          -- Daniela Astor y la caja negra

INSERT INTO Libros (titulo, fechaPublicacion, precio, autor) VALUES
('Queda la noche', '1989-01-01', 20.50, 27),
('La señora Berg', '1999-01-01', 21.99, 27),
('Historia de un abrigo', '2005-01-01', 19.99, 27),

('Juegos de la edad tardía', '1989-01-01', 21.99, 28),
('El balcón en invierno', '2014-01-01', 20.50, 28),
('Lluvia fina', '2019-01-01', 22.50, 28),

('Palmeras en la nieve', '2012-01-01', 23.99, 29),
('Regreso a tu piel', '2014-01-01', 21.50, 29),
('Como fuego en el hielo', '2017-01-01', 24.00, 29),

('Canto rodado', '1985-01-01', 19.50, 31),
('Camins de quietud', '1994-01-01', 20.50, 31),
('Tàndem', '2021-01-01', 22.00, 31),

('La gangrena', '1975-01-01', 21.99, 37),
('El secreto de las flores', '1984-01-01', 20.50, 37),
('Adagio confidencial', '1995-01-01', 23.50, 37);

INSERT INTO Libros_Categorias (libro, categoria) VALUES
-- Relación para libros de Soledad Puértolas
(31, 1),          -- Queda la noche
(32, 2), (32, 3), -- La señora Berg
(33, 4),          -- Historia de un abrigo

-- Relación para libros de Luis Landero
(34, 1),          -- Juegos de la edad tardía
(35, 4),          -- El balcón en invierno
(36, 3), (36, 5), -- Lluvia fina

-- Relación para libros de Luz Gabás
(37, 2), (37, 3), -- Palmeras en la nieve
(38, 1),          -- Regreso a tu piel
(39, 4),          -- Como fuego en el hielo

-- Relación para libros de María Barbal
(40, 3),          -- Canto rodado
(41, 1),          -- Camins de quietud
(42, 2), (42, 4), -- Tàndem

-- Relación para libros de Mercedes Salisachs
(43, 1),          -- La gangrena
(44, 5),          -- El secreto de las flores
(45, 3), (45, 4); -- Adagio confidencial

INSERT INTO Libros (titulo, fechaPublicacion, precio, autor) VALUES
('Los cipreses creen en Dios', '1953-01-01', 23.99, 21),
('Un millón de muertos', '1961-01-01', 22.50, 21),
('Ha estallado la paz', '1966-01-01', 24.00, 21),

('Yo, Julia', '2018-01-01', 24.99, 19),
('Julia retada por los dioses', '2020-01-01', 25.50, 19),
('Africanus, el hijo del cónsul', '2006-01-01', 23.50, 19),

('Sin noticias de Gurb', '1991-01-01', 18.00, 4),
('El año del diluvio', '1992-01-01', 19.50, 4),
('La ciudad de los prodigios', '1986-01-01', 20.00, 4),

('Marina', '1999-01-01', 19.99, 13),
('El príncipe de la niebla', '1993-01-01', 18.50, 13),
('Las luces de septiembre', '1995-01-01', 19.00, 13),

('Anatomía de un instante', '2009-01-01', 21.50, 15),
('Soldados de Salamina', '2001-01-01', 22.00, 15),
('Independencia', '2021-01-01', 23.50, 15);

INSERT INTO Libros_Categorias (libro, categoria) VALUES
-- Relación para libros de José María Gironella
(46, 2),          -- Los cipreses creen en Dios
(47, 1),          -- Un millón de muertos
(48, 3),          -- Ha estallado la paz

-- Relación para libros de Santiago Posteguillo
(49, 2), (49, 3), -- Yo, Julia
(50, 1),          -- Julia retada por los dioses
(51, 4), (51, 5), -- Africanus, el hijo del cónsul

-- Relación para libros de Eduardo Mendoza
(52, 7),          -- Sin noticias de Gurb
(53, 3), (53, 4), -- El año del diluvio
(54, 2),          -- La ciudad de los prodigios

-- Relación para libros de Carlos Ruiz Zafón
(55, 5),          -- Marina
(56, 7),          -- El príncipe de la niebla
(57, 4), (57, 6), -- Las luces de septiembre

-- Relación para libros de Javier Cercas
(58, 1),          -- Anatomía de un instante
(59, 3), (59, 5), -- Soldados de Salamina
(60, 2);          -- Independencia


INSERT INTO Libros (titulo, fechaPublicacion, precio, autor) VALUES
('Ética para Amador', '1991-01-01', 20.99, 8),
('El valor de educar', '1997-01-01', 19.50, 8),
('Política para Amador', '1992-01-01', 18.99, 8),

('El olvido que seremos', '2006-01-01', 23.50, 19),
('La fragilidad del tiempo', '2010-01-01', 21.99, 19),
('Las legiones malditas', '2008-01-01', 24.99, 19),

('La velocidad de la luz', '2005-01-01', 20.50, 15),
('Las leyes de la frontera', '2012-01-01', 21.99, 15),
('Terra Alta', '2019-01-01', 22.50, 15),

('Los renglones torcidos de Dios', '1979-01-01', 22.99, 22),
('El manuscrito encontrado en Zaragoza', '1804-01-01', 19.99, 22),
('La habitación de Fermat', '1998-01-01', 20.99, 22),

('El universo en tu mano', '2015-01-01', 23.99, 14),
('Breve historia de casi todo', '2003-01-01', 21.50, 14),
('Las partículas elementales', '1998-01-01', 22.99, 14);

INSERT INTO Libros_Categorias (libro, categoria) VALUES
-- Relación para libros de Fernando Savater
(61, 4),          -- Ética para Amador
(62, 9),          -- El valor de educar
(63, 9), (63, 1), -- Política para Amador

-- Relación para libros de Héctor Abad Faciolince
(64, 4), (64, 3), -- El olvido que seremos
(65, 1),          -- La fragilidad del tiempo
(66, 2),          -- Las legiones malditas

-- Relación para libros de Javier Cercas
(67, 3), (67, 5), -- La velocidad de la luz
(68, 2), (68, 4), -- Las leyes de la frontera
(69, 1), (69, 3), -- Terra Alta

-- Relación para libros de Torcuato Luca de Tena
(70, 5),          -- Los renglones torcidos de Dios
(71, 2),          -- El manuscrito encontrado en Zaragoza
(72, 3), (72, 4), -- La habitación de Fermat

-- Relación para libros de Christophe Galfard
(73, 7), (73, 8), -- El universo en tu mano
(74, 8),          -- Breve historia de casi todo
(75, 7), (75, 9); -- Las partículas elementales


INSERT INTO Libros (titulo, fechaPublicacion, precio, autor) VALUES
('El hereje', '1998-01-01', 24.99, 35),
('La mortaja', '1970-01-01', 21.99, 35),
('Viejas historias de Castilla la Vieja', '1964-01-01', 19.99, 35),

('La casa de los espíritus', '1982-01-01', 22.99, 23),
('De amor y de sombra', '1984-01-01', 20.99, 23),
('Paula', '1994-01-01', 23.50, 23),

('La tía Julia y el escribidor', '1977-01-01', 23.99, 24),
('Pantaleón y las visitadoras', '1973-01-01', 21.99, 24),
('La ciudad y los perros', '1963-01-01', 22.50, 24),

('El amante japonés', '2015-01-01', 24.50, 23),
('Más allá del invierno', '2017-01-01', 23.50, 23),
('El infinito en un junco', '2019-01-01', 25.50, 33),

('Un amor', '2020-01-01', 21.50, 34),
('Intemperie', '2013-01-01', 22.99, 34),
('La tierra que pisamos', '2016-01-01', 23.99, 34);

INSERT INTO Libros_Categorias (libro, categoria) VALUES
-- Relación para libros de Miguel Delibes
(76, 2), (76, 3), -- El hereje
(77, 5),          -- La mortaja
(78, 4), (78, 7), -- Viejas historias de Castilla la Vieja

-- Relación para libros de Isabel Allende
(79, 3),          -- La casa de los espíritus
(80, 2), (80, 4), -- De amor y de sombra
(81, 1),          -- Paula

-- Relación para libros de Mario Vargas Llosa
(82, 4), (82, 7), -- La tía Julia y el escribidor
(83, 3),          -- Pantaleón y las visitadoras
(84, 5),          -- La ciudad y los perros

-- Relación adicional para Isabel Allende
(85, 2), (85, 6), -- El amante japonés
(86, 3),          -- Más allá del invierno
(87, 7), (87, 8), -- El infinito en un junco

-- Relación para libros de Jesús Carrasco
(88, 3),          -- Un amor
(89, 4),          -- Intemperie
(90, 2);          -- La tierra que pisamos

INSERT INTO Libros (titulo, fechaPublicacion, precio, autor) VALUES
('Los girasoles ciegos', '2004-01-01', 22.99, 38),
('El año de la muerte de Ricardo Reis', '2007-01-01', 21.50, 38),
('Memoria de un tiempo difícil', '2003-01-01', 23.50, 38);

-- Relaciones entre los libros de Alberto Méndez y sus categorías
INSERT INTO Libros_Categorias (libro, categoria) VALUES
(91, 3), (91, 5), -- Los girasoles ciegos
(92, 4),          -- El año de la muerte de Ricardo Reis
(93, 1), (93, 2); -- Memoria de un tiempo difícil

commit;