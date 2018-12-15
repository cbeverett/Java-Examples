import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/* 
 * Example program showing various localization techniques
 */
public class LocalizationExample {

	public static void main(String[] args) {
		showLocaleExamples();
		showResourceBundleExamples();
		showFormatterExamples();
		showDateTimeExamples();
	}

	private static void showLocaleExamples() {
		
		// Get the default locale
		Locale defaultLocale = Locale.getDefault();
		System.out.println(defaultLocale);
		
		// Other ways to create Locales
		Locale germany = Locale.GERMANY;
		Locale the_french = Locale.FRENCH;  // Language as opposed to Country
		System.out.println(germany);
		
		Locale hindi = new Locale("hi");
		Locale india = new Locale("hi", "IN");
		System.out.println(india);
		
		Locale frenchCanadian = new Locale.Builder().setLanguage("fr").setRegion("Ca").build();
		System.out.println(frenchCanadian);
		
	}
	
	private static void showResourceBundleExamples() {
		Locale us = new Locale("en", "US");
		Locale fr = new Locale("fr", "FR");
		
		ResourceBundle rb_en = ResourceBundle.getBundle("example");  // Use the default locale
		
		System.out.println("English:");
		System.out.println(rb_en.getString("hello"));
		
		System.out.println("French:");
		
		ResourceBundle rb_fr = ResourceBundle.getBundle("example", fr);
		
		Set<String> keys = rb_fr.keySet();
		keys.stream().map(k -> k + " " + rb_fr.getString(k))
        			.forEach(System.out::println);
		
		// Class based resource bundle
		Locale de = new Locale("de", "DE");
		ResourceBundle rb_de = ResourceBundle.getBundle("example", de);
		System.out.println("German:");
		System.out.println(rb_de.getString("goodbye"));		
		
	}
	
	private static void showFormatterExamples() {
		NumberFormat deFormatter = NumberFormat.getInstance(Locale.GERMANY);
		NumberFormat deCurrencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		
		System.out.println(deFormatter.format(1000000));
		System.out.println(deCurrencyFormatter.format(100.25));
		
		try {
			Number euros = deCurrencyFormatter.parse("99,99 €");
			System.out.println(euros);
			
		}
		catch(ParseException e) {
			System.err.println("Can't parse currency string!");
		}
	}
	
	private static void showDateTimeExamples() {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		// ISO Format
		System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
		
		// Localized Short Date format
		System.out.println(localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
		
		DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter medF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		
		System.out.println(localDateTime.format(shortF));
		System.out.println(localDateTime.format(medF));
		
		// Custom
		DateTimeFormatter customF = DateTimeFormatter.ofPattern("MMMM dd. yy - hh:mm");
		System.out.println(localDateTime.format(customF));
		
		// Parsing
		LocalDate parsedDate = LocalDate.parse("4/01/01", DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		System.out.println(parsedDate);
		
	}



}
