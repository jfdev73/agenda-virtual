package com.miranda.agendavirtual.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertDate {
	
	private ConvertDate() {
		
	}
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static LocalDate  toLocalDate(String date) {
		
		return LocalDate.parse(date, FORMATTER); 
		
	}
	
	public static String toString(LocalDate date) {
		
		return date.format(FORMATTER);
		
	}

}
