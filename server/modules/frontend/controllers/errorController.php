<?php

class errorController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		$this->view->render("error404.php");
	}
}