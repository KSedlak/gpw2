package pl.spring.demo.mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateMapper {
	public static LocalDate map(Date d){

		Instant instant = convertFromSQLDateToJAVADate(d).toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();		
		return date;
		
		
	}
public static java.util.Date convertFromSQLDateToJAVADate(Date d) {
    java.util.Date javaDate = null;
    if (d != null) {
        javaDate = new Date(d.getTime());
    } 
    return javaDate;
} 
	public static Date map(LocalDate d){
		return Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
