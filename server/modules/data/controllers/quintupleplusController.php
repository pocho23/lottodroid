<?php

class quintupleplusController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		// Include the model
		require_once $this->config->get('dataModelsDir') . 'QuintuplePlus.php';
		$lottery = new QuintuplePlus();
		
		$start = (!isset($_GET[ 'start' ]) ) ? 0 : $_GET['start'];
		$limit = (!isset($_GET[ 'limit' ]) ) ? 1 : $_GET['limit'];
		
		$list = $lottery->getLastRecord($start, $limit);
		$this->view->renderJSON('quintupleplus', $list);
	}
}