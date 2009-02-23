<?php

class Quiniela extends ModelBase
{
    function __construct()
    {
  		parent::__construct('Quiniela', array('id'));
    } 
    
    public function getQuiniela($start = 0, $limit = 1)
    {
		$sql = 'SELECT * FROM ' . $this->tableName . ' ORDER BY ' .
		 		$this->dateAttr . ' DESC LIMIT ' . $start .', '. $limit;
		 		
		$records = $this->handler->query($sql);	

		if (empty($records) || is_string($records))
			return $records;
		
    	$numRecords = count($records);    
	    for($i = 0; $i < $numRecords ;$i++)
    	{
    		$sql = "SELECT local, visitante, resultado FROM QuinielaResultado
    					 WHERE QuinielaResultado.idQuiniela = {$records[$i]['id']}";
    	        	
    					 
        	$res = $this->handler->query($sql);
        		
        	if ( !empty($res) && !is_string($res))
    			$records[$i]['results'] = $res;
    	}

		return $records;
    }
    
  	public function insertQuiniela($info, $dateToInsert, $numLottery)
    {
    	$msg = $this->insertRecord(array ("fecha" => $dateToInsert,
    									"numSorteo" => $numLottery));
    	
    	if ( is_string($msg) || empty($msg) )
    		return $msg;
    			
    	// Insert results	
    	$config = Config::getInstance();
    	require $config->get('dataModelsDir') . 'QuinielaResultado.php';
    	
    	$idQuiniela = $this->getLastInsertedId();
    	
    	$quinielaResultado = new QuinielaResultado();
    	$numMatches = count($info);
    	
    	for ($i=0; $i < $numMatches;$i++)
    	{
    		$result = array (
	    		"local" => $info[$i][0],
	    		"visitante" => $info[$i][1],
	    		"resultado" => $info[$i][2],
	    		"idQuiniela" => $idQuiniela
	    	);
    		
	    	$msg = $quinielaResultado->insertRecord($result);
	    	if ( is_string($msg) || empty($msg) )
	    		return $msg;
    	}
    	
    	return true;
    }
} 