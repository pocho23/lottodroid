<?php

class contactController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		$this->view->render("contact.php");
	}
}