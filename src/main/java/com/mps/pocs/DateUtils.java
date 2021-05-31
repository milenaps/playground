package com.mps.pocs;

public class DateUtils {

	/*
	 * *** RULES ***
	 * 
	 * Params:
	 * - date: A date as String in the format dd/MM/yyyy HH24:min;
	 * - op: Can be only '+' | '-';
	 * - value: the value that should be incremented/decremented. It will be expressed in minutes;
	 * 
	 * Constraints:
	 * - You shall not work with non-native classes / libraries;
	 * - You shall not make use of neither Data nor Calendar classes;
	 * - If the op is not valid an exception must be thrown;
	 * - If the value is smaller than zero, you should ignore its signal;
	 * - If the result sum is bigger than max value to the field, you should increment its immediate bigger field;
	 * - Ignore the fact that February have 28/29 days and always consider only 28 days;
	 * - Ignore the daylight save time rules.
	 * 
	 * Example: changeDate("01/03/2010 23:00", '+', 4000) = "04/03/2010 17:40"
	 */
	public String changeDate(String date, char op, long value) throws Exception {
		if (op != '+' && op != '-') {
			throw new Exception(
				"The only allowed values for the 2nd param are '+' and '-'");
		}
		/*
		 * Nada foi dito em relacao a quando a data fosse invalida, entao optou-se
		 * por devolve-la como tiver vindo se isso ocorrer.
		 */
		if (value == 0 || date == null || date.length() < 16) {
			return date;
		}
		try {
			int day = Integer.parseInt(date.substring(0, 2));
			int month = Integer.parseInt(date.substring(3, 5));
			int year = Integer.parseInt(date.substring(6, 10));
			int hours = Integer.parseInt(date.substring(11, 13));
			int mins = Integer.parseInt(date.substring(14, 16));

			long dateInMins = (year - 1) * 365 * 24 * 60;
			dateInMins += month > 1 ? convertMonthToMins(month - 1) : 0;
			dateInMins += day * 24 * 60;
			dateInMins += hours * 60;
			dateInMins += mins;

			if (op == '+')
				return convertMinsToDate(dateInMins + Math.abs(value));
			else
				return convertMinsToDate(dateInMins - Math.abs(value));
		} catch (Exception e) {
			/*
			 * Se a data informada vier com o minimo de 16 caracteres, mas fora
			 * do formato esperado, tambem sera devolvida.
			 */
			return date;
		}
	}

	private long convertMonthToMins(int month) {
		long result = 0;
		switch (month) {
			case 1: result = 31; break;
			case 2: result = 31 + 28; break;
			case 3: result = 31 * 2 + 28; break;
			case 4: result = 31 * 2 + 28 + 30; break;
			case 5: result = 31 * 3 + 28 + 30; break;
			case 6: result = 31 * 3 + 28 + 30 * 2; break;
			case 7: result = 31 * 4 + 28 + 30 * 2; break;
			case 8: result = 31 * 5 + 28 + 30 * 2; break;
			case 9: result = 31 * 5 + 28 + 30 * 3; break;
			case 10:result = 31 * 6 + 28 + 30 * 3; break;
			case 11:result = 31 * 6 + 28 + 30 * 4; break;
			case 12:result = 31 * 7 + 28 + 30 * 4;
		}
		return result * 24 * 60;
	}

	private String convertMinsToDate(long value) throws Exception {
		StringBuilder date = new StringBuilder();
		long aux = value;

		long year = aux / (365 * 24 * 60);
		aux -= year * 365 * 24 * 60;
		year++;

		long month = 0;
		for (int i = 1; i <= 12; i++) {
			long totMonthMins = convertMonthToMins(i);
			if (totMonthMins > aux) {
				month = i;
				aux -= convertMonthToMins(i - 1);
				break;
			}
		}

		long day = aux / (24 * 60);
		aux -= day * 60 * 24;

		long hours = aux / 60;
		aux -= hours * 60;

		long mins = aux;

		date.append(formatString(day, 2));
		date.append("/");
		date.append(formatString(month, 2));
		date.append("/");
		date.append(formatString(year, 4));
		date.append(" ");
		date.append(formatString(hours, 2));
		date.append(":");
		date.append(formatString(mins, 2));

		return date.toString();
	}

	private String formatString(long value, int size) {
		String val = String.valueOf(value);
		if (val.length() < size) {
			String aux = "";
			for (int i = val.length(); i < size; i++)
				aux += "0";
			return aux + val;
		} else
			return val;
	}

	public static void main(String[] args) throws Exception {
		DateUtils util = new DateUtils();

		String date = "01/03/2010 23:00";
		long value = 4000;
		System.out.println("TEST 1 - It has to succeed and give \"04/03/2010 17:40\" as result");
		System.out.println("\n  Date.....: "+date+"\n  Value....: "+value+"\n  Operation: '+'\n  RESULT...: "+util.changeDate(date, '+', value));

		value = -4000;
		System.out.println("\n\nTEST 2 - It also has to succeed giving \"04/03/2010 17:40\" as result");
		System.out.println("\n  Date.....: "+date+"\n  Value....: "+value+"\n  Operation: '+'\n  RESULT...: "+util.changeDate(date, '+', value));

		System.out.println("\n\nTEST 3 - It also has to succeed giving \"04/03/2010 17:40\" as result");
		System.out.println("\n  Date.....: "+date+"\n  Value....: "+value+"\n  Operation: '-'\n  RESULT...: "+util.changeDate(date, '-', value));

		//System.out.println("\n\nTEST 4 - It has to throw an exception");
		//System.out.println(util.changeDate("", '1', 1000));
	}
}