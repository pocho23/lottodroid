<?php

class ModelBase {
	protected $tableName;
	protected $pkList = array();
	protected $handler;
	protected $dateAttr;

	function __construct($tableName, $pkList, $dateAttr = 'fecha') 
	{
		$this->handler = DataBase::getInstance();
		$this->tableName = $tableName;
		$this->pkList  = $pkList;
		$this->dateAttr = $dateAttr;
	}
	
	public function insertRecord($fields)
	{
		if (!is_array($fields)) {
			return false;
		}
		
		foreach ($fields as $field => $value) 
		{
			if ( is_numeric($value)  )
				$fieldValue = $value;	
			else
			{
				if ($value == 'on')
					$fields[$field] = "TRUE";
				else
				{
					if ( $value == 'off')
						$fields[$field] = 'FALSE';
					else
					{
						if ( empty($value) )
							$fields[$field] = 'NULL';
						else
							$fields[$field] =  "'" . $value . "'";
					}	
				}	
			}
		} 
		
	  	$sql = "INSERT INTO " . $this->tableName . "(";
		$sql .= implode(",", array_keys($fields));
		
		$sql .= ") VALUES (";
    	$sql .= implode(",", array_values($fields));
    	
		$sql .= ")";
		
		return $this->handler->exec($sql);
	}
	
	public function updateRecord($fields)
	{
		$pkList = $this->pkList;
		$fieldListStr = "";
		$pkListStr = "";
		
		/* Fields have the values to update and the PK of the 
			table, so we filter to search the pk to search the record */
		foreach ($fields as $field => $value) 
		{
			if ( is_numeric($value)  )
				$fieldValue = $value;
			else
			{
				if ($value == 'on')
					$fieldValue = "TRUE";
				else
				{
					if ( $value == 'off')
						$fieldValue = 'FALSE';
					else
					{
						if ( empty($value))
							$fieldValue = 'NULL';
						else
							$fieldValue = "'" . $value . "'";
					}	
				}	
			}
		
			if ( in_array($field, $pkList))
				$pkListStr .= $field . "=" . $fieldValue . " AND ";
			else
				$fieldListStr .= $field . "=" . $fieldValue . ", ";
		} 
		
		$pkListStr  = rtrim($pkListStr, ' AND ');
      	$fieldListStr = rtrim($fieldListStr, ', ');
		
		$sql = 'UPDATE '. $this->tableName . ' SET ' . $fieldListStr . ' WHERE  ' . $pkListStr;
  
		$record =  $this->handler->exec($sql);
		if (is_numeric($record) && $record == 0)
			return true;
		
		return $record;
	}

	public function deleteRecord($pkFieldValues)
	{
		$fieldListStr = "";
	
		foreach ($pkFieldValues as $field => $value) 
		{
			if ( !is_numeric($value))
				$value = "'" . $value . "'";
		
			$fieldListStr .= $field . "=" . $value . " AND ";
		} 
		
      	$fieldListStr = rtrim($fieldListStr, ' AND ');
		$sql = 'DELETE FROM '. $this->tableName . ' WHERE ' . $fieldListStr . ';';
  
		return $this->handler->exec($sql);
	}

	public function getAllRecords()
	{
		$sql = 'SELECT * FROM ' . $this->tableName . ";";
		return $this->handler->query($sql);
	}
	
	public function getNumRecords()
	{
		$sql = 'SELECT count(*) FROM ' . $this->tableName . ";";
		$records = $this->handler->query($sql);
		$record = $records[0];
		return $record['count(*)'];
	}
	
	public function get1RecordByPk($pkListValues)
	{
		foreach ($pkListValues as $field => $value) 
		{
			$fieldListStr .= $field . "='" . $value . "' AND ";
		} 
		
		$fieldListStr = rtrim($fieldListStr, ' AND ');
		$sql = 'SELECT * FROM ' . $this->tableName . ' WHERE ' . $fieldListStr . ' LIMIT 1;';
		$records = $this->handler->query($sql);
		return $records[0];
	}
	
	public function existsRecord($field, $data)
	{
		$sql = 'SELECT * FROM ' . $this->tableName . ' WHERE ' . $field . ' LIKE "'. $data .'" LIMIT 1;';
		return $this->handler->query($sql);
	}
	
	public function getLastRecord($start = 0, $limit = 1)
	{
		$sql = 'SELECT * FROM ' . $this->tableName . ' ORDER BY ' .
		 		$this->dateAttr . ' DESC LIMIT ' . $start .', '. $limit;
		 		
		return $this->handler->query($sql);
	}
	
  	function getLastInsertedId()
  	{
  		return $this->handler->getLastInsertedId();
  	}
	
	private function escape( $value ){
		$magic_quotes_active = get_magic_quotes_gpc();
		$new_enough_php 	 = function_exists("mysql_real_escape_string");

		if( $new_enough_php )
		{
			/* Undo any magic quote effects */
			if($magic_quotes_active)
				$value = stripslashes($value);
			
			$value = mysql_real_escape_string($value);
		}
		elseif(!$magic_quotes_active){
			/* If magic quotes aren't already on this add slashes manually */			
			$value = addslashes($value);
		}
		return $value; 
	}
}