package com.androidsx.lottodroid.util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
  private static final SimpleDateFormat formatter;
  private static final SimpleDateFormat formatterLotoluck;

  static {
    DateFormatSymbols symbols = new DateFormatSymbols();
    String[] daysES = { "", "Domingo", "Lunes", "Martes", "Mi\u00E9rcoles", "Jueves", "Viernes", "S\u00E1bado" };
    symbols.setWeekdays(daysES);
    formatter = new SimpleDateFormat("EEEE, d/MM/yyyy", symbols);
    
    formatterLotoluck = new SimpleDateFormat("yyyyMMdd");
  }
  
  private DateFormatter() {
    // This class is not to be instantiated
  }

  public static String toSpanishString(Date date) {
    return formatter.format(date);
  }
  
  public static long toLotoluckString(Date date) {
	return Long.parseLong(formatterLotoluck.format(date));
  }
  
  public static long toLotoluckString(int year, int month, int day) {
	StringBuilder date = new StringBuilder();
	date.append(year);
	
	if(month < 10)
		date.append(0);
	date.append(month);
	
	if(day < 10) 
		date.append(0);
	date.append(day);
	
	return Long.parseLong(date.toString());
}
  
}
