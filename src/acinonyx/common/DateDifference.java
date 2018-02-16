package acinonyx.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateDifference {

	public long getDaysDiff(){

		String dateStart = "01/01/1970";
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		long diffDays=0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dtf.format(localDate));

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffDays;
	}

}
