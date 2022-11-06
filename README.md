# PRÁCTICA VIDEOCLUB SUAR PL

_Práctica de Acceso a datos correspondiente a la Unidad 3_

## Resumen del proyecto 🚀

_El proyecto simula la gestión de una BDD de un videoclub, esta vez adaptado a ORM con Hibernate en un proyecto Maven, con más tablas relacionadas y funcionalidades, siguiendo la estructura solicitada, haciendo uso de POJOs, DAOs y mapeo por anotaciones entre otros._

En el pdf adjunto se detalla la estructura y el funcionamiento de sus métodos, además de disponer de un enlace a un vídeo demostrativo.

### Pre-requisitos 📋

_Antes de poner en marcha el proyecto, necesitaremos:_

_ -JAVA JRE funcional, en el ejemplo se usa Java 8, JRE1.8._
_ -Servidor MySQL funcional._

_El script Mysql que se ha utilizado para crear la base de datos al completo es el siguiente:_


```
-- ---------------
-- Creando la BDD
-- ---------------
DROP DATABASE if exists VIDEOCLUBspl;
CREATE DATABASE VIDEOCLUBspl;
-- Creando usuarios y otorgándoles permisos de admin
drop user if exists 'suar'@'127.0.0.1';
CREATE USER 'suar'@'127.0.0.1' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON *.* TO 'suar'@'127.0.0.1';

USE VIDEOCLUBspl;

DROP TABLE IF EXISTS usuarios;
-- Creamos tabla
CREATE TABLE usuarios (
    IDUSUARIO INT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(30) NOT NULL,
    APELLIDOS VARCHAR(50) NOT NULL,
    EDAD INT NOT NULL,
    DIRECCION VARCHAR(50) NOT NULL,
    TELEFONO INT NOT NULL,
    PRIMARY KEY (IDUSUARIO)
    
)ENGINE = InnoDB;

DROP TABLE IF EXISTS detallesusuarios;
-- Creamos tabla
CREATE TABLE detallesusuarios (
	IDDETALLESUSUARIO INT NOT NULL auto_increment,
    IDUSUARIO INT NOT NULL unique,
    EMAIL VARCHAR(40) DEFAULT NULL,
    NEWSLETTER CHAR DEFAULT 'N',
    PRIMARY KEY (IDDETALLESUSUARIO),
    CONSTRAINT IDUSUARIODETALLES foreign key (IDUSUARIO) references usuarios(IDUSUARIO) ON UPDATE cascade ON delete cascade,
    CONSTRAINT CHKVIP CHECK (NEWSLETTER like 'N' or NEWSLETTER like 'S')
)ENGINE = InnoDB;


DROP TABLE IF EXISTS peliculas;
-- Creamos tabla
CREATE TABLE peliculas (
    IDPELICULA INT NOT NULL AUTO_INCREMENT,
    TITULO VARCHAR(50) NOT NULL,
    GENERO VARCHAR(30) NOT NULL,
    PRIMARY KEY (IDPELICULA)
    
)ENGINE = InnoDB;

DROP TABLE IF EXISTS alquileres;
-- Creamos tabla
CREATE TABLE alquileres (
    IDALQUILER INT NOT NULL AUTO_INCREMENT,
    IDUSUARIO INT NOT NULL,
    IDPELICULA INT NOT NULL,
    DEVUELTA CHAR DEFAULT 'N',
    PRIMARY KEY (IDALQUILER),
    CONSTRAINT PELICULA foreign key (IDPELICULA) references peliculas(IDPELICULA) on update cascade  on delete cascade,
    CONSTRAINT USUARIO foreign key (IDUSUARIO) references usuarios(IDUSUARIO) on update cascade on delete cascade,
    CONSTRAINT CHKDEVUELTA CHECK (DEVUELTA like 'N' or DEVUELTA like 'S')
)ENGINE = InnoDB;

-- Insertamos valores
INSERT INTO usuarios (nombre, apellidos, edad, direccion, telefono)
  VALUES ('Jose','Perez Lopez',22,'Calle Melon',667289392),
		 ('Eva','Valls',38,'Calle Pera',661282392),
         ('Maria','Martinez',51,'Calle Melocoton',611238294),
         ('Julian','Martinez',59,'Calle Manzana',617288394),
         ('Silvia','Amador',29,'Calle Platano',637218304),
         ('Roberto','Gomez',42,'Calle Kiwi',687255398),
         ('David','Salvador',59,'Calle Sandia',687228172),
         ('Ana Belén','Cortes',28,'Calle Paredes',689783931),
         ('María Luisa','Lopez',39,'Camino Rafael',687228172),
         ('Hugo','Calvo',46,'Plaza san Juan',947334653),
         ('Celia','Aguilar',25,'Calle Jose Espadero',687228172),
         ('Montserrat','Gimenez',52,'Calle Paredes',687228172),
         ('Rosario','Cano',19,'Calle Miranda',687228172),
         ('Nicolas','Duran',18,'Calle Paredes',687228172),
         ('Victor','Diez',19,'Calle Cervantes',687228172),
         ('Sebastian','Santiago',18,'Calle Valenzuela',687228172),
         ('Rosa','Ortiz ',18,'Calle Casanova',687228172),
         ('Olga','Bravo',23,'Calle Zarate',986741397);
         -- Insertamos valores
INSERT INTO detallesusuarios (idusuario, email, newsletter)
  VALUES (1,'email1@gmail.com','S'),
		 (12,'email12@gmail.com','S'),
         (17,'email17@gmail.com','N'),
         (9,'email9@gmail.com','S'),
         (15,'email15@gmail.com','S'),
         (11,'email11@gmail.com','N'),
         (18,'email18@gmail.com','S'),
		 (4,'email4@gmail.com','S');
         
INSERT INTO peliculas (titulo, genero)
  VALUES ('El padrino','Drama'),
		 ('12 hombres sin piedad','Drama'),
         ('Blade Runner','Ciencia-Ficcion'),
         ('Gladiator','Aventuras'),
         ('La lista de Schindler','Drama'),
         ('Gran Torino','Drama'),
         ('El caballero oscuro','Thriller'),
         ('El pianista','Drama'),
         ('El señor de los anillos: El retorno del rey','Aventuras'),
         ('Intocable','Comedia'),
         ('Million Dollar Baby','Drama'),
         ('Origen','Ciencia-Ficcion'),
         ('Snatch. Cerdos y diamantes','Comedia'),
         ('La lista de Schindler','Drama'),
         ('Pulp Fiction','Suspense'),
         ('Uno de los nuestros','Suspense'),
         ('Alguien volo sobre el nido del cuco','Comedia Dramática'),
         ('Seven','Suspense'),
         ('El silencio de los corderos','Suspense'),
         ('¡Que bello es vivir!','Drama Fantastico'),
         ('Psicosis','Terror'),
         ('Casablanca','Romance');
         
         
INSERT INTO alquileres (idusuario,idpelicula,devuelta)
  VALUES (1,4,'S'),
		 (2,1,'S'),
         (2,13,'S'),  
         (10,11,'S'),
         (7,5,'S'),
         (11,15,'S'),
         (11,4,'S'),
         (4,5,'S'),
         (17,14,'S'),
         (18,9,'S'),
         (13,9,'S'),
         (16,16,'S'),
         (14,14,'S'),
         (10,9,'N'),
         (2,21,'N'),
         (12,20,'N'),
         (3,12,'N');
         
         
```

## Ejecutando el programa ⚙️

_Los pasos están descritos con más detalle en el pdf adjunto y en el vídeo demostrativo:_
_Creamos la BDD en Workbench utilizando el script anterior._
_Importamos el proyecto Maven adjunto, preferentemente en Eclipse o en tu IDE Java._
_Ejecutamos el proyecto desde el IDE y manejamos el menú mostrado en consola introduciendo los valores solicitados._


## Construido con 🛠️

_Las herramientas utilizadas para la creación del proyecto son:_

* [Eclipse](https://https://www.eclipse.org//) - IDE Java, versión 2022 09
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [MySQL](https://www.mysql.com/) - Gestor de DBB, versión 8.0CE

## Link al proyecto en Github ✒️

* [Práctica 3](https://github.com/Suarking/ada.Practica3.git)

## Autores ✒️

_Proyecto realizado por:_

* **Suar PL**



---
