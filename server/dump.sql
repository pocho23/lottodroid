-- phpMyAdmin SQL Dump
-- version 2.11.8.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 23, 2009 at 09:01 PM
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `Quiniela`
--

CREATE TABLE IF NOT EXISTS `Quiniela` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=16 ;

-- --------------------------------------------------------

--
-- Table structure for table `Quinigol`
--

CREATE TABLE IF NOT EXISTS `Quinigol` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `fecha` date NOT NULL,
  `numSorteo` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

