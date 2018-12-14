/* 
 * Example program with numerous Date and Time examples
 */

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;


public class DateAndTimeExamples {

	public static void main(String[] args) {
		showDateAndTimeExamples();
	}

	private static void showDateAndTimeExamples() {
		
		// All time/date classes have a static now() method
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());
		System.out.println(ZonedDateTime.now());
		
		// Use the static .of() to create a specific date/time
		LocalDate date1 = LocalDate.of(2018, 12, 13);
		LocalDate date2 = LocalDate.of(2018, Month.DECEMBER, 25);
		
		LocalTime time1 = LocalTime.of(13, 20); // 1:20 pm
		LocalTime time2 = LocalTime.of(14, 30, 12, 200); // Specific to the nanosecond
		
		LocalDateTime dateTime1 = LocalDateTime.of(2018,  12, 13, 23, 25);
		LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
		
		System.out.println(dateTime1);
		System.out.println(dateTime2);
		
		// Note that ZonedDateTime.of requires a zone
		ZoneId zone = ZoneId.of("US/Central");
		ZonedDateTime zoned1 = ZonedDateTime.of(date1, time1, zone);
		System.out.println(zoned1);
		
		// Curious about your timezone?
		System.out.println("You're in the " + ZoneId.systemDefault() + " time zone.");
		
		// Want to know all the US timezones?
		ZoneId.getAvailableZoneIds().stream()
									.filter(z -> z.contains("US") || z.contains("America"))
									.sorted()
									.forEach(System.out::println);
		
		// Manipulating Dates
		LocalDate updatedDate = date1.minusYears(10).plusMonths(3).plusDays(2);
		System.out.println(updatedDate);
		
		// Various periods
		Period year = Period.ofYears(1);
		Period quarter = Period.ofMonths(3);
		Period week = Period.ofDays(7);
		Period odd = Period.of(0, 3, 2); // 3 months and 2 days
		System.out.println(odd);  //P3M2D
		
		date1 = date1.plus(quarter);
		System.out.println("Added a quarter: " + date1);
		
		// Various Durations
		Duration halfDay = Duration.ofHours(12);
		Duration halfHour = Duration.ofMinutes(30);
		Duration primeTimeShow = Duration.ofMinutes(21).plus(Duration.ofSeconds(30));
		System.out.println(primeTimeShow);
		Duration halfDay2 = Duration.of(2, ChronoUnit.HALF_DAYS); 
		System.out.println(halfDay2);
		
		// ChronoUnit can be used for differences
		LocalDate today = LocalDate.now();
		LocalDate weekAgo = LocalDate.now().minusWeeks(1);
		long difference = ChronoUnit.DAYS.between(today, weekAgo);
		System.out.println(difference);
		
		// But so can duration
		Instant now = Instant.now();
		Instant later = Instant.now();
		Duration diff = Duration.between(now,later);
		System.out.println(diff.toNanos() + " nanoseconds!");
	}

}
