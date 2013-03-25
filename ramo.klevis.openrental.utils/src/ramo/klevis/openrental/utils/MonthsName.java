package ramo.klevis.openrental.utils;

import java.util.Calendar;

public class MonthsName {

	public static String getMonthName(int i) {

		if (i == 0) {
			return "JANAR";
		} else if (i == 1) {

			return "SHKURT";
		} else if (i == Calendar.MARCH) {

			return "MARS";

		} else if (i == Calendar.APRIL) {
			return "PRILL";
		} else if (i == Calendar.MAY) {

			return "MAJ";
		} else if (i == Calendar.JUNE) {

			return "QERSHOR";
		} else if (i == Calendar.JULY) {

			return "KORRIK";
		} else if (i == Calendar.AUGUST) {

			return "GUSHT";
		} else if (i == Calendar.SEPTEMBER) {

			return "SHTATOR";
		} else if (i == Calendar.OCTOBER) {

			return "TETOR";
		} else if (i == Calendar.NOVEMBER) {

			return "NENTOR";
		} else if (i == Calendar.DECEMBER) {

			return "DHJETOR";
		}
		
		System.err.println("Null return "+i);
		return null;

	}
}
