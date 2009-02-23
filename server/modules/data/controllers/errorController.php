<?php

class errorController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		$this->view->renderJSON('error 404', 'Source not found');
	}
}