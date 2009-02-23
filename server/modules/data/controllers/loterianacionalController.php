<?php

class loterianacionalController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		// Include the model
		require_once $this->config->get('dataModelsDir') . 'LoteriaNacional.php';
		$lottery = new LoteriaNacional();
		
		$start = (!isset($_GET[ 'start' ]) ) ? 0 : $_GET['start'];
		$limit = (!isset($_GET[ 'limit' ]) ) ? 1 : $_GET['limit'];
		
		$list = $lottery->getLastRecord($start, $limit);
		$this->view->renderJSON('loterianacional', $list);
	}
}