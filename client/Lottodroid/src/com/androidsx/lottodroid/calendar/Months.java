package com.androidsx.lottodroid.calendar;

public class Months {
	
	static String getMonth(int numMonth) {
		
		switch (numMonth) {
		
		case 0:
			return "Enero";

		case 1:
			return "Febrero";

		case 2:
			return "Marzo";

		case 3:
			return "Abril";

		case 4:
			return "Mayo";

		case 5:
			return "Junio";

		case 6:
			return "Julio";

		case 7:
			return "Agosto";

		case 8:
			return "Setiembre";

		case 9:
			return "Octubre";

		case 10:
			return "Noviembre";
			
		case 11:
			return "Dicicembre";

		default:
			throw new IndexOutOfBoundsException();
		}
	}
	

}
