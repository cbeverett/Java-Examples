/* 
 * Example program that shows a bunch of examples using stream terminals
 */

import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.lang.StringBuilder;

public class StreamTerminalExamples {

	public static void main(String[] args) {
		showCommonTerminalOperations();
	}
	
	private static void showCommonTerminalOperations() {
		// Simple stream of strings
		List<String> sourceList = Arrays.asList("Hello", "World", "as", "a", "stream");
		Stream<String> stream = sourceList.stream();
		
		// Print the stream using the forEach() terminal
		System.out.println("Stream Elements:");
		stream.forEach(System.out::println);
		System.out.println("----------");
		
		// count() terminal
		stream = sourceList.stream();
		long elementCount = stream.count();
		System.out.println("Number of elements in stream: " + elementCount);	
		
		// min()/max() terminals
		Comparator<String> c = (s1,s2)->s1.length() - s2.length();
		
		stream = sourceList.stream();
		Optional<String> minTerm = stream.min(c);
		System.out.print("Shortest string: ");
		minTerm.ifPresent(System.out::println);
		
		stream = sourceList.stream();
		Optional<String> maxTerm = stream.max(c);
		System.out.print("Longest string: ");
		maxTerm.ifPresent(System.out::println);
		
		// Grab an element from an infinite stream using findAny()
		Stream<String> turtlesAllTheWayDown = Stream.generate(()->"Turtles");
		turtlesAllTheWayDown.findAny().ifPresent(System.out::println);
		
		// Look for matches with allMatch(), anyMatch(), and noneMatch()
		Predicate<String> beginsWithA = x->x.toUpperCase().startsWith("A");
		System.out.println("All begin with A: " + sourceList.stream().allMatch(beginsWithA));
		System.out.println("Any begin with A: " + sourceList.stream().anyMatch(beginsWithA));	
		System.out.println("None begin with A: " + sourceList.stream().noneMatch(beginsWithA));
		
		// Reductions using reduce()
		stream = sourceList.stream();
		String concat = stream.reduce("All together now: ", (s,t) -> s + t);
		System.out.println(concat);
		
		// Without the identity
		stream = sourceList.stream();
		Optional<String> concat2 = stream.reduce((s,t) -> s + t);
		concat2.ifPresent(System.out::println);
		
		// Gathering up the stream using collect()
		stream = sourceList.stream();
		BiConsumer<StringBuilder,String> bi_op = (t,u) -> t.append(u).append(" ");
		StringBuilder sb = stream.collect(StringBuilder::new, bi_op, StringBuilder::append);
		System.out.println(sb.toString());		
	}

}
