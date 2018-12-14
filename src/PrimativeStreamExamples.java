import java.util.DoubleSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/* 
 * Example program that shows a bunch of examples using primitive streams
 */

public class PrimativeStreamExamples {

	public static void main(String[] args) {
		showPrimativeStreams();

	}
	
	private static void showPrimativeStreams() {
		
		DoubleStream randomStream = DoubleStream.generate(Math::random);
		randomStream.limit(3).forEach(System.out::println);
		System.out.println();
		
		DoubleStream diminishingStream = DoubleStream.iterate(.5, d -> d/2);
		diminishingStream.limit(3).forEach(System.out::println);
		System.out.println();
		
		IntStream rangeStream = IntStream.rangeClosed(1,10);
		System.out.println("Sum: " + rangeStream.sum() + "\n");
		
		// Note that average returns an optional
		rangeStream = IntStream.rangeClosed(1, 10);
		OptionalDouble optAvg = rangeStream.average();
		System.out.println("Average: " + optAvg.orElse(Double.NaN));
		
		// Summary Statistics
		DoubleStream statStream = DoubleStream.iterate(0, d -> d+1.0);
		DoubleSummaryStatistics stats = statStream.limit(10).summaryStatistics();
		System.out.println("\nSummary Statistics:");
		System.out.println("Count: " + stats.getCount());
		System.out.println("Min: " + stats.getMin());
		System.out.println("Max: " + stats.getMax());
	}

}
