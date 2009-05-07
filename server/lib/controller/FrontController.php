<?php

class FrontController {
	
	public static function run() 
	{
		require_once 'lib/Config.php'; //de configuracion
		require_once 'lib/controller/ControllerBase.php'; //Clase controlador base
		require_once 'lib/model/DataBase.php';
		require_once 'lib/model/ModelBase.php'; //Clase modelo base
		require_once 'lib/view/View.php'; //Mini motor de plantillas
		
		require_once 'modules/config.php'; //Archivo con configuraciones.
		
		/* We get the specific controller */
		if(!empty($_GET['module']))
		      $moduleName = $_GET['module'];
		else
		      $moduleName = 'frontend';
		
		/* We get the specific controller */
		if(! empty($_GET['controller']))
		      $controllerName = $_GET['controller'];
		else
		      $controllerName = 'home';
		
		/* Action for the current controller */
		if(! empty($_GET['action']))
		      $actionName = $_GET['action'];
		else
		      $actionName = 'view';

		$config->set('module', $moduleName);
		$config->set('controller', $controllerName);
		$config->set('action', $actionName);
		
		/* Include the controller */
		$controllerName .= 'Controller';
		$controllerPath = $config->get($moduleName . 'ControllersDir') . $controllerName . '.php';
		
		if(is_file($controllerPath))
			require $controllerPath;
		else
		{
			// Controller and action by default
			require $config->get($moduleName . 'ControllersDir') . 'errorController.php';
			$error = new errorController();
			$error->view();
			return false;
		}
			
		if (is_callable(array($controllerName, $actionName)) == false) 
		{
			require $config->get($moduleName . 'ControllersDir') . 'errorController.php';
			$controllerName = 'errorController';
			$actionName = 'view';
		}

		$controller = new $controllerName();
		$controller->$actionName();
	} 	
}