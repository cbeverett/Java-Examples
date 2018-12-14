import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 
 * Example program that shows a bunch of examples using stream collectors
 */

public class StreamCollectorExamples {

	public static void main(String[] args) {
		showStreamCollectors();
	}
	
	private static void showStreamCollectors() {
		
		String[] strings = {"These", "are", "some", "strings."};
		
		Stream<String> strStream = Arrays.stream(strings);
		
		// .joining(CharSequence delimiter) creates a string using the optionally supplied delimiter
		System.out.println(strStream.collect(Collectors.joining(" ")));
		
		// Average length of the words in strings
		strStream = Arrays.stream(strings);
		Double result = strStream.collect(Collectors.averagingInt(String::length));
		System.out.println("Average word length: " + result);
		
		// Collect our stream into a TreeSet
		strStream = Arrays.stream(strings);
		TreeSet<String> setFromStream = strStream.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(setFromStream);

		// Creating a Map from a stream
		strStream = Arrays.stream(strings);	
		Map<String,Integer> strMap = strStream.collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(strMap);
		
		// Grouping Example
		String[] groups = {"We", "want", "some", "words", "of", "same", "length"};
		Stream<String> strStream2 = Arrays.stream(groups);
		Map<Integer,List<String>> groupMap = strStream2.collect(Collectors.groupingBy(String::length));
		System.out.println(groupMap);
		
		// Partitioning Example
		strStream2 = Arrays.stream(groups);
		Map<Boolean,List<String>> partitioned = 
				strStream2.collect(Collectors.partitioningBy(s -> s.toLowerCase().startsWith("w")));
		System.out.println(partitioned);
		
		
	}

}
