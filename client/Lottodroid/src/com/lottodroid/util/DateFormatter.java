package com.lottodroid.util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
  private static final SimpleDateFormat formatter; 

  static {
    DateFormatSymbols symbols = new DateFormatSymbols();
    String[] daysES = { "", "Domingo", "Lunes", "Martes", "Mi\u00E9rcoles", "Jueves", "Viernes", "S\u00E1bado" };
    symbols.setWeekdays(daysES);
    formatter = new SimpleDateFormat("EEEE, d/MM/yyyy", symbols);
  }
  
  private DateFormatter() {
    // This class is not to be instantiated
  }

  public static String toSpanishString(Date date) {
    return formatter.format(date);
  }
}
