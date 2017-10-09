package fineance.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatter {

	   private static Date date = null;
	   private static final ZoneId zoneId = ZoneId.systemDefault();
	   
	   private static DateTimeFormatter dtf;
	   
	   public DateFormatter() {}

	   public static Date format(String string) {
	      try {
	         date = new SimpleDateFormat("dd/MM/yyyy").parse(string);
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	      return date;
	   }

	   public static LocalDate formatLocally(String string) {
	      dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	      LocalDate ld = LocalDate.parse(string, dtf);
	      return ld;
	   }

	   public static LocalDate formatLocallyddMMyy(String string) {
	      dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
	      LocalDate ld = LocalDate.parse(string, dtf);
	      return ld;
	   }
	   
	   public static String formatLdToString(LocalDate localDate) {
		   return localDate.toString();
	   }
	   
	   public static long formatStringToEpoch(String string) {
		   return formatLocally(string).atStartOfDay(zoneId).toEpochSecond();
	   }
	   
	   public static String formatEpochToString(long date) {
		   Date d = new Date(date);
		   DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		   return df.format(d);
	   }
	   
}
