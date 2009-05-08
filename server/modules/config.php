<?php
	//-------------------------------------------------------------------------
	// CONFIG FILE FOR RFIA
	//-------------------------------------------------------------------------	
    $config = Config::getInstance();
    $rootPath = dirname(dirname(__FILE__));
    
    $config->set('dataControllersDir', $rootPath . '/modules/data/controllers/');
    $config->set('dataModelsDir', $rootPath . '/modules/data/models/');
    
    $config->set('frontendControllersDir', $rootPath . '/modules/frontend/controllers/');
    $config->set('frontendViewsDir', $rootPath . '/modules/frontend/views/');

    // Retrieval module
    $config->set('magpierssDir', $rootPath . '/lib/external/magpierss/');
    $config->set('zendDir', $rootPath . '/lib/external/Zend/');
    //$config->set('rssURL', 'http://localhost/lottery/rss');
    $config->set('rssURL', 'http://www.meh.es/_layouts/RssLae.aspx?hiloId=2');
    
    
    $config->set('rootDir', $rootPath );
    
    $config->set('dbhost', 'localhost');
    $config->set('dbname', 'loteria');
    $config->set('dbuser', 'root');
    $config->set('dbpass', 'root');
    
    //$config->set('dbhost', 'mysql5-56.90');
    //$config->set('dbname', 'androidslotto');
    //$config->set('dbuser', 'androidslotto');
    //$config->set('dbpass', 'primitiva');
    
    
    $config->set('debug', true);
		