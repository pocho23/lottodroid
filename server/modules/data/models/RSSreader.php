<?php

class RSSreader
{
	private $rss;
	private $config;
	
	function __construct()
	{
		$this->config = Config::getInstance();
		require_once( $this->config->get('magpierssDir') . 'rss_fetch.inc');
		$this->rss = fetch_rss( $this->config->get('rssURL') );		
	}
	
	public function parseAndInsertInfo()
	{
		$rss = $this->rss;	
	
		if ( !$rss )
			return false;	
	
		foreach ($rss->items as $item) 
		{
			$title = $item['title'];
			$pubDate = $item['pubdate'];
			$description = $item['description'];
			
			$dateToInsert = $this->parseDate($pubDate);
			$numLottery   = $this->parseNumLottery($title);
			
			echo "<h3>$title</h3>";
			echo "<p>Date: $dateToInsert  NumLottery:  $numLottery</p>";
			
			if (strpos($title, 'LOTERIA PRIMITIVA') != false)
			{
				$result = $this->parsePrimitiva($description);
				if ($result)
				{
					$msg = $this->insertPrimitiva($result, $dateToInsert, $numLottery);
					if (  is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email
			}
			
			if (strpos($title, 'BONOLOTO') != false)
			{
				$result = $this->parseBonoloto($description);
				if ($result)
				{
					$msg = $this->insertBonoloto($result, $dateToInsert, $numLottery);
					if ( is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email				
			}
			
			if (strpos($title, 'EL GORDO') != false)
			{
				$result = $this->parseGordoPrimitiva($description);
				if ($result)
				{
					$msg = $this->insertGordoPrimitiva($result, $dateToInsert, $numLottery);
					if (  is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email	
			}
			
			if (strpos($title, 'NACIONAL') != false)
			{
				$result = $this->parseLoteriaNacional($description);
				if ($result)
				{
					$msg = $this->insertLoteriaNacional($result, $dateToInsert, $numLottery);
					if (  is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email	
			}
			
			if (strpos($title, 'EUROMILLONES') != false)
			{
				$result = $this->parseEuromillon($description);
				if ($result)
				{
					$msg = $this->insertEuromillon($result, $dateToInsert, $numLottery);
					if (  is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email	
			}
			
			if (strpos($title, 'LOTOTURF') != false)
			{
				$result = $this->parseLototurf($description);
				if ($result)
				{
					$msg = $this->insertLototurf($result, $dateToInsert, $numLottery);
					if (  is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email	
			}
			
			if (strpos($title, 'QUINTUPLE PLUS') != false)
			{
				$result = $this->parseQuintuplePlus($description);
				if ($result)
				{
					$msg = $this->insertQuintuplePlus($result, $dateToInsert, $numLottery);
					if (  is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email	
			}
			
			if (strpos($title, 'APUESTA DEPORTIVA') != false)
			{
				$result = $this->parseQuiniela($description);
				if ($result)
				{
					$msg = $this->insertQuiniela($result, $dateToInsert, $numLottery);
					if (  is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email	
			}
			
			if (strpos($title, 'EL QUINIGOL') != false)
			{
				$result = $this->parseQuinigol($description);
				if ($result)
				{
					$msg = $this->insertQuinigol($result, $dateToInsert, $numLottery);
					if (  is_string($msg) || empty($msg))
					{
						echo '<p>Error inserting</p>';// Send email
					}
				}
				else
					echo '<p>Error parsing</p>';//send email	
			}
		}
	}
	
	private function pregMatch($regex, $text)
	{
		if ( preg_match(
					$regex, 
					trim($text), 
					$result) )
			return $result;
		else
			// send email
			return false;
	}
	
	private function parseNumLottery($title)
	{
		$result = $this->pregMatch('/sorteo:\s*(\d+)\s*$/i', $title);
		if ($result)
			return $result[1];
		else
			return false;
	}
	
	private function parseDate($pubDate)
	{
		return date('Y-m-d', strtotime($pubDate));
	}
	
	private function parsePrimitiva($description)
	{
		return $this->pregMatch('/^Combinacion:\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+);\s*Complementario:\s*(\d+);\s*Reintegro:\s*(\d+)\s*$/i', 
		 				$description);
	}
	
	private function insertPrimitiva($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'LoteriaPrimitiva.php';
		$lottery = new LoteriaPrimitiva();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
			$values = array (
    			"num1" => $info[1],
				"num2" => $info[2],
    			"num3" => $info[3],
				"num4" => $info[4],
    			"num5" => $info[5],
				"num6" => $info[6],						
    			"complementario" => $info[7],
    			"reintegro" => $info[8],
				"numSorteo" => $numLottery,
				"fecha" => $dateToInsert
    		);
 
    		$msg = $lottery->insertRecord($values);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		
		return true;
	}
	
	private function parseLoteriaNacional($description)
	{
		return $this->pregMatch('/^Primer.*:\s*(\d+);\s*Premio.*:\s*(\d+),\s*Serie:\s*(\d+)\s*;\s*Segundo.*:\s*(\d+)\s*;\s*Reintegros:\s*(\d+)\s*-\s*(\d+)\s*-\s*(\d+)\s*/',  
		 				$description);
	}
	
	private function insertLoteriaNacional($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'LoteriaNacional.php';
		$lottery = new LoteriaNacional();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
			$values = array (
    			"premio1" => $info[1],
				"fraccion" => $info[2],
    			"serie" => $info[3],
				"premio2" => $info[4],
    			"reintegro1" => $info[5],
				"reintegro2" => $info[6],						
    			"reintegro3" => $info[7],
				"numSorteo" => $numLottery,
				"fecha" => $dateToInsert
    		);
 
    		$msg = $lottery->insertRecord($values);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		return true;
	}
	
	private function parseBonoloto($description)
	{
		return $this->pregMatch('/^Combinacion:\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+);\s*Complementario:\s*(\d+);\s*Reintegro:\s*(\d+)\s*$/i', 
		 				$description);
	}
	
	private function insertBonoloto($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'Bonoloto.php';
		$lottery = new Bonoloto();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
			$values = array (
    			"num1" => $info[1],
				"num2" => $info[2],
    			"num3" => $info[3],
				"num4" => $info[4],
    			"num5" => $info[5],
				"num6" => $info[6],						
    			"complementario" => $info[7],
    			"reintegro" => $info[8],
				"numSorteo" => $numLottery,
				"fecha" => $dateToInsert
    		);
 
    		$msg = $lottery->insertRecord($values);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		return true;
	}
	
	private function parseGordoPrimitiva($description)
	{
		return $this->pregMatch('/^Combinacion:\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+);\s*Reintegro:\s*(\d+)\s*$/i', 
		 				$description);
	}
	
	private function insertGordoPrimitiva($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'GordoPrimitiva.php';
		$lottery = new GordoPrimitiva();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
			$values = array (
    			"num1" => $info[1],
				"num2" => $info[2],
    			"num3" => $info[3],
				"num4" => $info[4],
    			"num5" => $info[5],					
    			"reintegro" => $info[6],
				"numSorteo" => $numLottery,
				"fecha" => $dateToInsert
    		);
 
    		$msg = $lottery->insertRecord($values);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		return true;
	}
	
	private function parseEuromillon($description)
	{
		return $this->pregMatch('/^Combinacion:\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+);\s*Estrellas:\s*(\d),\s*(\d)\s*$/i', 
		 				$description);
	}
	
	private function insertEuromillon($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'Euromillon.php';
		$lottery = new Euromillon();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
			$values = array (
    			"num1" => $info[1],
				"num2" => $info[2],
    			"num3" => $info[3],
				"num4" => $info[4],
    			"num5" => $info[5],			
    			"estrella1" => $info[6],
    			"estrella2" => $info[7],
				"numSorteo" => $numLottery,
				"fecha" => $dateToInsert
    		);
 
    		$msg = $lottery->insertRecord($values);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		return true;
	}
	
	private function parseLototurf($description)
	{
		return $this->pregMatch('/^N.mero 1: \s*(\d+);\s*N.mero 2:\s*(\d+);\s*N.mero 3: (\d+);\s*N.mero 4:\s*(\d+);\s*N.mero 5:\s*(\d+);\s*N.mero 6:\s*(\d+);\s*Caballo ganador:\s*(\d+);\s*Reintegro:\s*(\d+)\s*$/i', 
		 				$description);
	}
	
	private function insertLototurf($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'Lototurf.php';
		$lottery = new Lototurf();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
			$values = array (
    			"num1" => $info[1],
				"num2" => $info[2],
    			"num3" => $info[3],
				"num4" => $info[4],
    			"num5" => $info[5],
				"num6" => $info[6],						
    			"caballoGanador" => $info[7],
    			"reintegro" => $info[8],
				"numSorteo" => $numLottery,
				"fecha" => $dateToInsert
    		);
 
    		$msg = $lottery->insertRecord($values);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		return true;
	}
	
	private function parseQuintuplePlus($description)
	{
		return $this->pregMatch('/^Carrera 1:\s*(\d+);\s*Carrera 2:\s*(\d+);\s*Carrera 3:\s*(\d+);\s*Carrera 4:\s*(\d+);\s*Carrera 5,\s*Caballo 1.:\s*(\d+);\s*Carrera 5,\s*Caballo 2.:\s*(\d+)\s*$/i',  
		 				$description);
	}
	
	private function insertQuintuplePlus($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'QuintuplePlus.php';
		$lottery = new QuintuplePlus();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
			$values = array (
    			"carrera1" => $info[1],
				"carrera2" => $info[2],
    			"carrera3" => $info[3],
				"carrera4" => $info[4],
    			"carrera5_1" => $info[5],
				"carrera5_2" => $info[6],						
				"numSorteo" => $numLottery,
				"fecha" => $dateToInsert
    		);
 
    		$msg = $lottery->insertRecord($values);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		return true;
	}
	
	private function parseQuiniela($description)
	{
		$quiniela = array(15);
	
		$matches = explode(',',$description);
		$numMatches = count ($matches);
		
		if ($numMatches != 15)
		{
			return false;
		}
		else
		{
			for ( $i=0; $i < $numMatches;$i++)
			{
				$quiniela[$i] = array(3);
				
				$expMatch = explode(':',$matches[$i]);
				$expTeams = explode('-',$expMatch[0]);
				
				$quiniela[$i][0] = trim($expTeams[0]);
				$quiniela[$i][1] = trim($expTeams[1]);
				$quiniela[$i][2] = trim($expMatch[1]);
			}
			
			return $quiniela;
		}
	}
	
	private function insertQuiniela($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'Quiniela.php';
		$lottery = new Quiniela();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
    		$msg = $lottery->insertQuiniela($info, $dateToInsert, $numLottery);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		return true;
	}
	
	private function parseQuinigol($description)
	{
		$quinigol = array();
		
		$matches = explode(',',$description);
		$numMatches = count ($matches);
	
		if (!$numMatches)
		{
			return false;
		}
		else
		{
			for ( $i=0; $i<$numMatches;$i++)
			{
				$quinigol[$i] = array(4);
				
				$expMatch = explode(':',$matches[$i]);
				$expTeams = explode('-',$expMatch[0]);
				$expResults = explode('-',$expMatch[1]);
				
				$quinigol[$i][0] = trim($expTeams[0]);
				$quinigol[$i][1] = trim($expTeams[1]);
				$quinigol[$i][2] = trim($expResults[0]);
				$quinigol[$i][3] = trim($expResults[1]);
			}
			
			return $quinigol;
		}
	}
	
	private function insertQuinigol($info, $dateToInsert, $numLottery)
	{
		require_once $this->config->get('dataModelsDir') . 'Quinigol.php';
		$lottery = new Quinigol();
	
		if ( !$lottery->existsRecord('fecha', $dateToInsert) )
		{
    		$msg = $lottery->insertQuinigol($info, $dateToInsert, $numLottery);
    		if ( is_string($msg) || empty($msg) )
    			return $msg;
		}	
		return true;

	}
}

?>