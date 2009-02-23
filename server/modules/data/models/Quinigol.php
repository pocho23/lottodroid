<?php

class Quinigol extends ModelBase
{
    function __construct()
    {
  		parent::__construct('Quinigol', array('id'));
    } 
    
   public function getQuinigol($start = 0, $limit = 1)
    {
		$sql = 'SELECT * FROM ' . $this->tableName . ' ORDER BY ' .
		 		$this->dateAttr . ' DESC LIMIT ' . $start .', '. $limit;
		 		
		$records = $this->handler->query($sql);	

		if (empty($records) || is_string($records))
			return $records;
		
    	$numRecords = count($records);    
	    for($i = 0; $i < $numRecords ;$i++)
    	{
    		$sql = "SELECT local, visitante, marcadorLocal, marcadorVisitante FROM QuinigolResultado
    					 WHERE QuinigolResultado.idQuinigol = {$records[$i]['id']}";
    	        	
    					 
        	$res = $this->handler->query($sql);
        		
        	if ( !empty($res) && !is_string($res))
    			$records[$i]['results'] = $res;
    	}

		return $records;
    }
    
  	public function insertQuinigol($info, $dateToInsert, $numLottery)
    {
    	$msg = $this->insertRecord(array ("fecha" => $dateToInsert,
    									"numSorteo" => $numLottery));

    	if ( is_string($msg) || empty($msg) )
    		return $msg;
    			
    	// Insert results	
    	$config = Config::getInstance();
    	require $config->get('dataModelsDir') . 'QuinigolResultado.php';
    	
    	$idQuinigol = $this->getLastInsertedId();
    	
    	$quinigolResultado = new QuinigolResultado();
    	$numMatches = count($info);
    	
    	for ($i=0; $i < $numMatches;$i++)
    	{
    		$result = array (
	    		"local" => $info[$i][0],
	    		"visitante" => $info[$i][1],
    			"marcadorLocal" => $info[$i][2],
	    		"marcadorVisitante" => $info[$i][3],
	    		"idQuinigol" => $idQuinigol
	    	);
    		
	    	$msg = $quinigolResultado->insertRecord($result);
	    	if ( is_string($msg) || empty($msg) )
	    		return $msg;
    	}
    	
    	return true;
    }
} 