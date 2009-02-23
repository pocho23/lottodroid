<?php

class quinigolController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		// Include the model
		require_once $this->config->get('dataModelsDir') . 'Quinigol.php';
		$lottery = new Quinigol();
		
		$start = (!isset($_GET[ 'start' ]) ) ? 0 : $_GET['start'];
		$limit = (!isset($_GET[ 'limit' ]) ) ? 1 : $_GET['limit'];
		
		$list = $lottery->getQuinigol($start, $limit);
		$this->view->renderJSON('quinigol', $list);
	}
}