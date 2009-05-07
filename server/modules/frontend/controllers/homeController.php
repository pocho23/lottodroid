<?php

class homeController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		$this->view->render("home.php");
	}
}