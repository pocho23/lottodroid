<?php

class View
{
	private $vars;

	function __construct() {}
	
     public function setData( $index, $value ) {
		$this->vars[$index] = $value;
    }

	public function render($name, $module = null) 
	{
		//Traemos una instancia de nuestra clase de configuracion.
		$config = Config::getInstance();
                
		$module = (!empty($module)) ? $module : $config->get('module');
		$path = $config->get( $module . 'ViewsDir') . $name;

		//Si no existe el fichero en cuestion, tiramos un 404
		if (file_exists($path) == false) 
		{
			include $config->get($config->get('module') . 'ViewsDir') . 'error404.php';
			return false;
		}
		
		//Si hay variables para asignar, las pasamos una a una.
		if(is_array($this->vars))
		{
			foreach ($this->vars as $key => $value) 
			{	
                $$key = $value;
            }
		}
                
		//Finalmente, incluimos la plantilla.
		include $path;
	}
	
	public function renderJSON($metainfo, $array)
	{
		$config = Config::getInstance();
		include_once $config->get( 'zendDir') . 'Json.php';
		
		Zend_Json::$useBuiltinEncoderDecoder = true;
		
		$info = (is_string($array)) ? 'error' : $metainfo;
		
		header('Cache-Control: no-cache, must-revalidate');
		if(isset($_GET['pretty']))
			header('Content-type: text/plain');
		else
			header('Content-type: application/json');
		
		try {
			echo Zend_Json::encode( array('info' => $info, 'data' => $array) );
		}
		catch(Exception $e)
		{
			 echo Zend_Json::encode( array ('info' => 'error', 'data' => 
			 							'Caught exception: ' . $e->getMessage() . "\n"));
		} 
	}
	

}