import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/* 
 * Example program that shows a bunch of examples using stream intermediate operations
 */

public class StreamIntermediateOpExamples {

	public static void main(String[] args) {
		showCommonIntermediateOperations();
	}
	
	private static void showCommonIntermediateOperations() {
		
		// Filter things out of a stream using .filter()
		List<String> strList = Arrays.asList("This", "is", "a", "list", "of", "words");
		
		// Filter out short words
		strList.stream().filter(x -> { return x.length() > 2; }).forEach(System.out::println);
		
		// Remove duplicates with .distinct()		
		"Java Professional".chars().mapToObj(i -> (char)i).distinct().forEach(System.out::print);
		System.out.println();
		
		// Use .limit() and .skip() to get a handle on infinite streams
		BiConsumer<StringBuilder,Integer> bi_op = (t,u) -> t.append(u).append(" ");
		Stream<Integer> allNumbers = Stream.iterate(1, n -> n + 1);  // All the numbers
		StringBuilder sb = allNumbers.skip(10).limit(5).collect(StringBuilder::new, bi_op, StringBuilder::append);
		System.out.println(sb.toString());
		
		// Use .map() to transform elements in the stream...in this case transform strings to Integers for summation
		List<String> strNumbers = Arrays.asList("1", "2", "3", "4", "5");
		int sum = strNumbers.stream().map(x->Integer.valueOf(x)).reduce((x,y)->x+y).get();
		System.out.println("Sum: " + sum);
		
		// .flatmap() aggregates a series of streams into a single stream
		List<String> emptyList = new ArrayList<>();
		List<String> someWords = Arrays.asList("Algorithms", "are");
		List<String> someMoreWords = Arrays.asList("a", "lot", "of", "fun");
		
		Stream<List<String>> streamOfLists = Stream.of(emptyList, someWords, someMoreWords);
		
		streamOfLists.flatMap(l -> l.stream()).forEach(System.out::println);
		
		// .sorted() behaves as expected...also takes an optional Comparator
		System.out.println("Sorted List:");
		strList.stream().map(str -> str.toLowerCase()).sorted().forEach(System.out::println);
		
		System.out.println("Sorted List (in reverse):");
		strList.stream().map(str -> str.toLowerCase()).sorted(Comparator.reverseOrder()).forEach(System.out::println);		
		
		// .peak is useful for debugging....it doesn't alter the stream, but provides insight
		System.out.println("Peeking in on our stream");
		strList.stream().peek(x -> System.out.println("Peek: " + x)).forEach(System.out::println);
	}

}
