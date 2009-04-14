<?php

class sorteosController extends ControllerBase
{
	function __construct()
	{
		parent::__construct();	
	}
	
	public function view()
	{
		// Include the model
		$path = $this->config->get('dataModelsDir');
		
		require_once $path . 'Lototurf.php';
		$lototurf = new Lototurf();
		$list['lototurf'] = $lototurf->getLastRecord();
		
		require_once $path . 'Bonoloto.php';
		$bonoloto = new Bonoloto();
		$list['bonoloto'] = $bonoloto->getLastRecord();
		
		require_once $path . 'Euromillon.php';
		$euromillon = new Euromillon();
		$list['euromillon'] = $euromillon->getLastRecord();
		
		require_once $path . 'GordoPrimitiva.php';
		$gordoPrimitiva = new GordoPrimitiva();
		$list['primitiva'] = $gordoPrimitiva->getLastRecord();
		
		require_once $path . 'LoteriaNacional.php';
		$loteriaNacional = new LoteriaNacional();
		$list['loterianacional'] = $loteriaNacional->getLastRecord();
		
		require_once $path . 'LoteriaPrimitiva.php';
		$loteriaPrimitiva = new LoteriaPrimitiva();
		$list['primitiva'] = $loteriaPrimitiva->getLastRecord();
		
		require_once $path . 'Quinigol.php';
		$quinigol = new Quinigol();		
		$list['quinigol'] = $quinigol->getQuinigol();
		
		require_once $path . 'Quiniela.php';
		$quiniela = new Quiniela();		
		$list['quiniela'] = $quiniela->getQuiniela();
		
		$this->view->renderJSON('sorteos', $list);
	}
}