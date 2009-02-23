<?php

class ControllerBase {
    
    protected $view;
    protected $config;
    
    function __construct()
    {
        $this->view = new View();
        $this->config = Config::getInstance();
    }
    
    public function redirect($controller, $action, $helper, $module = 'frontend')
    {
		/* Get the current host for redirections*/
        $isHTTPS = (isset($_SERVER["HTTPS"]) && $_SERVER["HTTPS"] == "on");
        $port = (isset($_SERVER["SERVER_PORT"]) && 
        		((!$isHTTPS && $_SERVER["SERVER_PORT"] != "80") || 
        		($isHTTPS && $_SERVER["SERVER_PORT"] != "443")));
        $port = ($port) ? ':'.$_SERVER["SERVER_PORT"] : '';
        $url = ($isHTTPS ? 'https://' : 'http://').$_SERVER["SERVER_NAME"].$port;
		

        $config = Config::getInstance();
        if ( $config->get('debug') )
        {
        	$db = DataBase::getInstance();
        	$helper .= '<br><br>Current queries:<br><br>' . $db->log;
        }	
        
        $url .= $_SERVER["SCRIPT_NAME"] . 
       			 '?controller=' . $controller . 
  				'&action=' . $action . 
				'&helper=' . $helper .
        		'&module=' . $module;
        
		header('Location: ' . $url);	
		exit();
    }
}
