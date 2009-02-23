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
	
	private function json_format($json)
	{
	    $tab = "  ";
	    $new_json = "";
	    $indent_level = 0;
	    $in_string = false;
	   
	    $json_obj = json_decode($json);
	   
	    if(!$json_obj)
	        return false;
	   
	    $json = json_encode($json_obj);
	    $len = strlen($json);
	   
	    for($c = 0; $c < $len; $c++)
	    {
	        $char = $json[$c];
	        switch($char)
	        {
	            case '{':
	            case '[':
	                if(!$in_string)
	                {
	                    $new_json .= $char . "\n" . str_repeat($tab, $indent_level+1);
	                    $indent_level++;
	                }
	                else
	                {
	                    $new_json .= $char;
	                }
	                break;
	            case '}':
	            case ']':
	                if(!$in_string)
	                {
	                    $indent_level--;
	                    $new_json .= "\n" . str_repeat($tab, $indent_level) . $char;
	                }
	                else
	                {
	                    $new_json .= $char;
	                }
	                break;
	            case ',':
	                if(!$in_string)
	                {
	                    $new_json .= ",\n" . str_repeat($tab, $indent_level);
	                }
	                else
	                {
	                    $new_json .= $char;
	                }
	                break;
	            case ':':
	                if(!$in_string)
	                {
	                    $new_json .= ": ";
	                }
	                else
	                {
	                    $new_json .= $char;
	                }
	                break;
	            case '"':
	                $in_string = !$in_string;
	            default:
	                $new_json .= $char;
	                break;                   
	        }
	    }
	   
	    return $new_json;
	}
	
	public function renderJSON($metainfo, $array)
	{
		if (is_string($array))
			echo $this->json_format( json_encode( array('info' => 'error', 'data' => $array) ) );
		else
		{
			echo $this->json_format( json_encode( array('info' => $metainfo, 'data' => $array) ) );
		}
	}
	

}