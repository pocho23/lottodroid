<?php
		require_once '../lib/Config.php'; //de configuracion
		require_once '../lib/controller/ControllerBase.php'; //Clase controlador base
		require_once '../lib/model/DataBase.php';
		require_once '../lib/model/ModelBase.php'; //Clase modelo base
		require_once '../lib/view/View.php'; //Mini motor de plantillas
		
		require_once '../modules/config.php'; //Archivo con configuraciones.
		

		/* Include the controller */
		require $config->get('dataControllersDir') . 'retrievalController.php';

		$controller = new retrievalController();
		$controller->dojob();
?>