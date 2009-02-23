<?php

class retrievalController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function dojob()
	{
		// Include the model
		$path = $this->config->get('dataModelsDir');
		
		require_once $path . 'RSSreader.php';
		$rss = new RSSreader();
		$rss->parseAndInsertInfo();
	
	}
}