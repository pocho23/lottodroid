-- phpMyAdmin SQL Dump
-- version 2.11.8.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 14, 2009 at 12:24 PM
-- Server version: 5.0.67
-- PHP Version: 5.2.6-2ubuntu4.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `loteria`
--

-- --------------------------------------------------------

--
-- Table structure for table `Bonoloto`
--

CREATE TABLE IF NOT EXISTS `Bonoloto` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `num1` tinyint(4) NOT NULL,
  `num2` tinyint(4) NOT NULL,
  `num3` tinyint(4) NOT NULL,
  `num4` tinyint(4) NOT NULL,
  `num5` tinyint(4) NOT NULL,
  `num6` tinyint(4) NOT NULL,
  `complementario` tinyint(4) NOT NULL,
  `reintegro` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `Bonoloto`
--

INSERT INTO `Bonoloto` (`id`, `num1`, `num2`, `num3`, `num4`, `num5`, `num6`, `complementario`, `reintegro`, `fecha`, `numSorteo`) VALUES
(1, 4, 7, 13, 30, 33, 37, 42, 7, '2009-02-18', 8),
(2, 2, 3, 4, 5, 6, 7, 2, 1, '2009-02-02', 312),
(3, 8, 14, 16, 23, 25, 32, 29, 8, '2009-03-04', 10),
(4, 4, 8, 15, 28, 31, 32, 42, 0, '2009-03-20', 12),
(5, 9, 10, 25, 27, 29, 37, 31, 8, '2009-04-10', 15),
(6, 12, 15, 16, 19, 36, 49, 20, 6, '2009-04-13', 16);

-- --------------------------------------------------------

--
-- Table structure for table `Euromillon`
--

CREATE TABLE IF NOT EXISTS `Euromillon` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `num1` tinyint(4) NOT NULL,
  `num2` tinyint(4) NOT NULL,
  `num3` tinyint(4) NOT NULL,
  `num4` tinyint(4) NOT NULL,
  `num5` tinyint(4) NOT NULL,
  `estrella1` tinyint(4) NOT NULL,
  `estrella2` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `Euromillon`
--

INSERT INTO `Euromillon` (`id`, `num1`, `num2`, `num3`, `num4`, `num5`, `estrella1`, `estrella2`, `fecha`, `numSorteo`) VALUES
(1, 22, 33, 36, 40, 42, 1, 2, '2009-02-13', 7),
(2, 5, 9, 37, 44, 45, 6, 9, '2009-02-27', 9),
(3, 12, 16, 23, 31, 35, 4, 6, '2009-03-20', 12),
(4, 9, 14, 16, 37, 46, 2, 4, '2009-04-10', 15);

-- --------------------------------------------------------

--
-- Table structure for table `GordoPrimitiva`
--

CREATE TABLE IF NOT EXISTS `GordoPrimitiva` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `num1` tinyint(4) NOT NULL,
  `num2` tinyint(4) NOT NULL,
  `num3` tinyint(4) NOT NULL,
  `num4` tinyint(4) NOT NULL,
  `num5` tinyint(4) NOT NULL,
  `reintegro` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `GordoPrimitiva`
--

INSERT INTO `GordoPrimitiva` (`id`, `num1`, `num2`, `num3`, `num4`, `num5`, `reintegro`, `fecha`, `numSorteo`) VALUES
(1, 16, 19, 44, 53, 54, 2, '2009-02-15', 7),
(2, 15, 21, 29, 47, 54, 8, '2009-03-01', 9),
(3, 9, 35, 45, 46, 49, 0, '2009-03-15', 11),
(4, 1, 2, 34, 40, 46, 0, '2009-04-05', 14),
(5, 14, 15, 22, 26, 37, 9, '2009-04-12', 15);

-- --------------------------------------------------------

--
-- Table structure for table `LoteriaNacional`
--

CREATE TABLE IF NOT EXISTS `LoteriaNacional` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `premio1` mediumint(9) NOT NULL,
  `fraccion` tinyint(4) NOT NULL,
  `serie` tinyint(4) NOT NULL,
  `premio2` mediumint(9) NOT NULL,
  `reintegro1` tinyint(4) NOT NULL,
  `reintegro2` tinyint(4) NOT NULL,
  `reintegro3` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `LoteriaNacional`
--

INSERT INTO `LoteriaNacional` (`id`, `premio1`, `fraccion`, `serie`, `premio2`, `reintegro1`, `reintegro2`, `reintegro3`, `fecha`, `numSorteo`) VALUES
(1, 93965, 10, 9, 44721, 1, 9, 5, '2009-02-14', 14),
(2, 78396, 7, 2, 67378, 2, 3, 6, '2009-03-05', 19),
(3, 78397, 9, 5, 33984, 5, 8, 7, '2009-03-21', 24),
(4, 87486, 10, 3, 44983, 2, 1, 6, '2009-04-11', 30);

-- --------------------------------------------------------

--
-- Table structure for table `LoteriaPrimitiva`
--

CREATE TABLE IF NOT EXISTS `LoteriaPrimitiva` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `num1` tinyint(4) NOT NULL,
  `num2` tinyint(4) NOT NULL,
  `num3` tinyint(4) NOT NULL,
  `num4` tinyint(4) NOT NULL,
  `num5` tinyint(4) NOT NULL,
  `num6` tinyint(4) NOT NULL,
  `complementario` tinyint(4) NOT NULL,
  `reintegro` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `LoteriaPrimitiva`
--

INSERT INTO `LoteriaPrimitiva` (`id`, `num1`, `num2`, `num3`, `num4`, `num5`, `num6`, `complementario`, `reintegro`, `fecha`, `numSorteo`) VALUES
(1, 6, 10, 25, 29, 33, 44, 38, 8, '2009-02-14', 7),
(2, 4, 7, 29, 44, 45, 46, 30, 6, '2009-03-05', 10),
(3, 5, 27, 29, 32, 41, 45, 39, 2, '2009-03-19', 12),
(4, 6, 15, 27, 35, 37, 47, 42, 2, '2009-04-09', 15),
(5, 2, 6, 18, 28, 31, 38, 3, 1, '2009-04-11', 15);

-- --------------------------------------------------------

--
-- Table structure for table `Lototurf`
--

CREATE TABLE IF NOT EXISTS `Lototurf` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `num1` tinyint(4) NOT NULL,
  `num2` tinyint(4) NOT NULL,
  `num3` tinyint(4) NOT NULL,
  `num4` tinyint(4) NOT NULL,
  `num5` tinyint(4) NOT NULL,
  `num6` tinyint(4) NOT NULL,
  `caballoGanador` tinyint(4) NOT NULL,
  `reintegro` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `Lototurf`
--

INSERT INTO `Lototurf` (`id`, `num1`, `num2`, `num3`, `num4`, `num5`, `num6`, `caballoGanador`, `reintegro`, `fecha`, `numSorteo`) VALUES
(1, 8, 9, 12, 13, 27, 28, 7, 2, '2009-02-15', 7),
(2, 5, 7, 8, 12, 16, 27, 6, 9, '2009-03-01', 9),
(3, 9, 17, 20, 24, 25, 28, 2, 0, '2009-03-15', 11),
(4, 17, 20, 22, 24, 25, 28, 8, 1, '2009-04-10', 15),
(5, 4, 12, 17, 23, 25, 27, 1, 6, '2009-04-12', 16);

-- --------------------------------------------------------

--
-- Table structure for table `Quiniela`
--

CREATE TABLE IF NOT EXISTS `Quiniela` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `Quiniela`
--

INSERT INTO `Quiniela` (`id`, `fecha`, `numSorteo`) VALUES
(1, '2009-02-15', 35),
(2, '2009-03-01', 37),
(3, '2009-03-15', 39),
(4, '2009-04-05', 43),
(5, '2009-04-12', 44);

-- --------------------------------------------------------

--
-- Table structure for table `QuinielaResultado`
--

CREATE TABLE IF NOT EXISTS `QuinielaResultado` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `local` varchar(200) collate utf8_unicode_ci NOT NULL,
  `visitante` varchar(200) collate utf8_unicode_ci NOT NULL,
  `resultado` char(1) collate utf8_unicode_ci NOT NULL,
  `idQuiniela` mediumint(8) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=76 ;

--
-- Dumping data for table `QuinielaResultado`
--

INSERT INTO `QuinielaResultado` (`id`, `local`, `visitante`, `resultado`, `idQuiniela`) VALUES
(1, 'Valencia', 'Málaga', 'X', 1),
(2, 'Almería', 'Valladolid', '1', 1),
(3, 'Athletic Club', 'Recreativo', 'X', 1),
(4, 'At. Madrid', 'Getafe', 'X', 1),
(5, 'Espanyol', 'Sevilla', '2', 1),
(6, 'Betis', 'Barcelona', 'X', 1),
(7, 'Sporting', 'R. Madrid', '2', 1),
(8, 'Numancia', 'Mallorca', '2', 1),
(9, 'Deportivo', 'Osasuna', 'X', 1),
(10, 'Zaragoza', 'Las Palmas', '1', 1),
(11, 'Murcia', 'R. Sociedad', '1', 1),
(12, 'Hércules', 'Levante', '1', 1),
(13, 'Xerez', 'Salamanca', '1', 1),
(14, 'Gimnástic', 'Celta', 'X', 1),
(15, 'Racing', 'Villarreal', 'X', 1),
(16, 'Valencia', 'Valladolid', '2', 2),
(17, 'Málaga', 'Recreativo', '2', 2),
(18, 'Almería', 'Getafe', '1', 2),
(19, 'Athletic Club', 'Sevilla', '2', 2),
(20, 'Espanyol', 'R. Madrid', '2', 2),
(21, 'Betis', 'Villarreal', 'X', 2),
(22, 'Sporting', 'Mallorca', '2', 2),
(23, 'Racing', 'Osasuna', 'X', 2),
(24, 'Numancia', 'Deportivo', '2', 2),
(25, 'Murcia', 'Zaragoza', '2', 2),
(26, 'Hércules', 'Las Palmas', '1', 2),
(27, 'Alavés', 'R. Sociedad', '1', 2),
(28, 'Girona', 'Córdoba', 'X', 2),
(29, 'Castellón', 'Salamanca', '1', 2),
(30, 'At. Madrid', 'Barcelona', '1', 2),
(31, 'Valencia', 'Recreativo', 'X', 3),
(32, 'Valladolid', 'Getafe', '1', 3),
(33, 'Almería', 'Barcelona', '2', 3),
(34, 'Athletic Club', 'R. Madrid', '2', 3),
(35, 'At. Madrid', 'Villarreal', '1', 3),
(36, 'Espanyol', 'Mallorca', 'X', 3),
(37, 'Betis', 'Osasuna', 'X', 3),
(38, 'Sporting', 'Deportivo', '1', 3),
(39, 'Racing', 'Numancia', '1', 3),
(40, 'Hércules', 'Murcia', '1', 3),
(41, 'Alavés', 'Zaragoza', 'X', 3),
(42, 'Xerez', 'R. Sociedad', '2', 3),
(43, 'Gimnástic', 'Córdoba', 'X', 3),
(44, 'Tenerife', 'Albacete', 'X', 3),
(45, 'Málaga', 'Sevilla', 'X', 3),
(46, 'Valencia', 'Getafe', '1', 4),
(47, 'Recreativo', 'Sevilla', '2', 4),
(48, 'Valladolid', 'Barcelona', '2', 4),
(49, 'Málaga', 'R. Madrid', '2', 4),
(50, 'Almería', 'Villarreal', '1', 4),
(51, 'At. Madrid', 'Osasuna', '2', 4),
(52, 'Espanyol', 'Deportivo', '1', 4),
(53, 'Betis', 'Numancia', 'X', 4),
(54, 'Sporting', 'Racing', '2', 4),
(55, 'Murcia', 'Xerez', '2', 4),
(56, 'Zaragoza', 'Girona', '1', 4),
(57, 'Las Palmas', 'Castellón', '1', 4),
(58, 'R. Sociedad', 'Gimnástic', 'X', 4),
(59, 'Salamanca', 'Celta', '1', 4),
(60, 'Athletic Club', 'Mallorca', '1', 4),
(61, 'Sevilla', 'Getafe', '2', 5),
(62, 'Barcelona', 'Recreativo', '1', 5),
(63, 'R. Madrid', 'Valladolid', '1', 5),
(64, 'Villarreal', 'Málaga', '2', 5),
(65, 'Mallorca', 'Almería', '1', 5),
(66, 'Osasuna', 'Athletic Club', '1', 5),
(67, 'Numancia', 'Espanyol', 'X', 5),
(68, 'Racing', 'Betis', '2', 5),
(69, 'Sporting', 'Valencia', '2', 5),
(70, 'Xerez', 'Hércules', '1', 5),
(71, 'Girona', 'Murcia', '1', 5),
(72, 'Castellón', 'Zaragoza', 'X', 5),
(73, 'Tenerife', 'Levante', '1', 5),
(74, 'Celta', 'Córdoba', '1', 5),
(75, 'Deportivo', 'At. Madrid', '2', 5);

-- --------------------------------------------------------

--
-- Table structure for table `Quinigol`
--

CREATE TABLE IF NOT EXISTS `Quinigol` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `Quinigol`
--

INSERT INTO `Quinigol` (`id`, `fecha`, `numSorteo`) VALUES
(1, '2009-02-15', 38),
(2, '2009-03-01', 42),
(3, '2009-03-19', 46),
(4, '2009-04-05', 50),
(5, '2009-04-12', 51);

-- --------------------------------------------------------

--
-- Table structure for table `QuinigolResultado`
--

CREATE TABLE IF NOT EXISTS `QuinigolResultado` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `local` varchar(200) collate utf8_unicode_ci NOT NULL,
  `visitante` varchar(200) collate utf8_unicode_ci NOT NULL,
  `marcadorLocal` char(1) collate utf8_unicode_ci NOT NULL,
  `marcadorVisitante` char(1) collate utf8_unicode_ci NOT NULL,
  `idQuinigol` mediumint(8) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=31 ;

--
-- Dumping data for table `QuinigolResultado`
--

INSERT INTO `QuinigolResultado` (`id`, `local`, `visitante`, `marcadorLocal`, `marcadorVisitante`, `idQuinigol`) VALUES
(1, 'Valencia', 'Málaga', '1', '1', 1),
(2, 'Athletic Club', 'Recreativo', '1', '1', 1),
(3, 'Betis', 'Barcelona', '2', '2', 1),
(4, 'Sporting', 'R. Madrid', '0', 'M', 1),
(5, 'Racing', 'Villarreal', '1', '1', 1),
(6, 'Deportivo', 'Osasuna', '0', '0', 1),
(7, 'Valencia', 'Valladolid', '1', '2', 2),
(8, 'Málaga', 'Recreativo', '0', '2', 2),
(9, 'Athletic Club', 'Sevilla', '1', '2', 2),
(10, 'At. Madrid', 'Barcelona', 'M', 'M', 2),
(11, 'Espanyol', 'R. Madrid', '0', '2', 2),
(12, 'Sporting', 'Mallorca', '0', '1', 2),
(13, 'Saint', 'Etienne', '2', '2', 3),
(14, 'Shakhtar', 'CSKA Moskva', '2', '0', 3),
(15, 'Braga', 'PSG', '0', '1', 3),
(16, 'Aalborg', 'Manchester City', '2', '0', 3),
(17, 'Ajax', 'Marseille', '2', '2', 3),
(18, 'Galatasaray', 'Hamburgo', '2', 'M', 3),
(19, 'Valencia', 'Getafe', 'M', '1', 4),
(20, 'Recreativo', 'Sevilla', '0', '1', 4),
(21, 'Valladolid', 'Barcelona', '0', '1', 4),
(22, 'Málaga', 'R. Madrid', '0', '1', 4),
(23, 'Athletic Club', 'Mallorca', '2', '1', 4),
(24, 'Sporting', 'Racing', '0', '2', 4),
(25, 'Barcelona', 'Recreativo', '2', '0', 5),
(26, 'R. Madrid', 'Valladolid', '2', '0', 5),
(27, 'Villarreal', 'Málaga', '0', '2', 5),
(28, 'Deportivo', 'At. Madrid', '1', '2', 5),
(29, 'Racing', 'Betis', '2', 'M', 5),
(30, 'Sporting', 'Valencia', '2', 'M', 5);

-- --------------------------------------------------------

--
-- Table structure for table `QuintuplePlus`
--

CREATE TABLE IF NOT EXISTS `QuintuplePlus` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `carrera1` tinyint(4) NOT NULL,
  `carrera2` tinyint(4) NOT NULL,
  `carrera3` tinyint(4) NOT NULL,
  `carrera4` tinyint(4) NOT NULL,
  `carrera5_1` tinyint(4) NOT NULL,
  `carrera5_2` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `QuintuplePlus`
--

INSERT INTO `QuintuplePlus` (`id`, `carrera1`, `carrera2`, `carrera3`, `carrera4`, `carrera5_1`, `carrera5_2`, `fecha`, `numSorteo`) VALUES
(1, 1, 1, 7, 7, 6, 5, '2009-02-15', 7),
(2, 1, 2, 6, 6, 14, 3, '2009-03-01', 9),
(3, 5, 1, 7, 2, 1, 7, '2009-03-15', 11),
(4, 10, 6, 3, 8, 11, 2, '2009-04-10', 15),
(5, 8, 6, 5, 1, 1, 5, '2009-04-12', 16);

