<?php

class bonolotoController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		// Include the model
		require_once $this->config->get('dataModelsDir') . 'Bonoloto.php';
		$bonoloto = new Bonoloto();
		
		$start = (!isset($_GET[ 'start' ]) ) ? 0 : $_GET['start'];
		$limit = (!isset($_GET[ 'limit' ]) ) ? 1 : $_GET['limit'];
		
		$list = $bonoloto->getLastRecord($start, $limit);
		$this->view->renderJSON('bonoloto', $list);
	}
}