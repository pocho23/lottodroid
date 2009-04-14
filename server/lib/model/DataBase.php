<?php

/**
 * Class that connects to the database with all the SQL queries needed for 
 * mydasty abstracted as methods
 * 
 * @package mydasty
 */

class DataBase {
	private static $instance;

	public $log = '';
	private $cnn; 

	private $sql; 			// last SQL query
	private $numRows; 		// Number of rows of last query
	private $affectedRows; 	// Number of affected rows of last query
	private $lastIdInserted;// Last id in the INSERT's query
	private $result; 		// Result in an associate array
	

	function __construct() 
	{
		$config = Config::getInstance();
			
		/* Connect to the database system */
		$this->log .= "Connecting to the database [mysql_connect]<br/>";	
		if (! $this->cnn = @mysql_connect($config->get('dbhost'), $config->get('dbuser'), $config->get('dbpass')) )
			$this->log .= mysql_error();
	
		/* Select the specific database  */
		$this->log .= "Selecting database [mysql_select_db]<br/>";
		if (! @mysql_select_db($config->get('dbname')) )
			$this->log .= mysql_error();
	}
	
	public static function getInstance()
    {
        if (!self::$instance)
            self::$instance = new DataBase();

        return self::$instance;
    } 

	public function query($sql = "") 
	{
		$this->log .= "Executing query [query()]<br/>";
				
		if (!$sql) 
			return "Fatal error in database";

		if(!$this->cnn) 
			return "Connection not established";

		$this->sql = $sql;
		$this->log .= "SQL: ". $sql ."<br />";

		/* Run the query */		
		if (! $result = @mysql_query($sql) )
			//return $this->log . mysql_error();
			return "Fatal error in database";

		/* Retrieve the results */
		$this->numRows = @mysql_num_rows($result);
		if($this->numRows)
		{
			while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) 
          		$data[] = $row;

			mysql_free_result($result);
		}
		else 
			$data = null;
		
		return $data;
	}

	public function exec($sql="") 
	{
		$this->log .= "Executing query [exec()]<br/>";
		
		if (!$sql) 
			return $this->log ."You need to pass a SQL query!<br/>";
  
		if(!$this->cnn) 
			return $this->log . "Connection not established<br />";

		$this->sql = $sql;
		$this->log .= "SQL: ". $sql ."<br />";

		/* Run the query */		
		if (! $result = @mysql_query($sql) )
			return $this->log . mysql_error();

		/* Set last id inserted property (only for INSERTS)	*/
		//$this->lastIdInserted = mysql_insert_id();
		$this->rowsAffected = mysql_affected_rows($this->cnn);
		
		$this->log .= $this->rowsAffected." rows affected<br />";
		
		return $this->rowsAffected;   
  	}

  	function getLastInsertedId()
  	{
  		return mysql_insert_id();
  	}
  	
	function dump($data) 
	{
		$this->log .= "Showing table with data [dump() ]<br />";
		
		if (!$data) 
			$this->error("No data passed <br />" );
		
		echo "<style>table.dump { font-family:Arial; font-size:8pt; }</style>";
		echo "<table class=\"dump\" border=\"1\" cellpadding=\"1\" cellspacing=\"0\">\n";
		echo "<tr>";
		echo "<th>#</th>";
		foreach($data[0] as $key=>$val) 
		{
			echo "<th><b>";
			echo $key;
			echo "</b></th>";
		}
		
		echo "</tr>\n";
		$rowCnt = 0;
		foreach($data as $row) 
		{
		  	$rowCnt++;
		  	echo "<tr align='center'>";
		  	echo "<td>".$rowCnt."</td>";
		  	
			foreach($row as $val) 
			{
		    	echo "<td>";
		    	echo $val;
		    	echo "</td>";
		  	}
			
		  	echo"</tr>\n";
		}
		echo "</table>\n";
	}
	

	function close(){
		if(isset($this->cnn))
			@mysql_close($this->cnn);
	}

	function __destruct(){
		$this->close();
	}

	private function error( $msg ){
		$this->close();
		die ($this->log . $msg);
	}
}

?>