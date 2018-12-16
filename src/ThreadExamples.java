import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
 * This class demonstrates concurrency in Java
 */

class RunnableThread implements Runnable {

	public void run() {
		System.out.println("Runnable Thread is running.");
	}
}

class ExtendsThread extends Thread {

	@Override
	public void run() {
		super.run();
		
		System.out.println("ExtendedThread is running.");
		for(int i = 0; i < 5; i++) {
			System.out.println(String.format("Thunderbird %d is go.", i));
		}
	}
	
}

public class ThreadExamples {

	public static void main(String[] args) {
		
		int numProcessors = Runtime.getRuntime().availableProcessors();
		
		System.out.println(String.format("This machine has %d processors.", numProcessors));

		runSimpleThreads();
		singleThreadExecutorExample();
		runCallableExample();
		runScheduledTaskExample();
	}

	private static void runSimpleThreads() {
		Thread thread1 = new Thread(new RunnableThread());
		Thread thread2 = new ExtendsThread();
		
		thread1.start();
		thread2.start();
		
	}
	
	private static void singleThreadExecutorExample() {
		ExecutorService service = null;
		
		try {
			service = Executors.newSingleThreadExecutor();
			
			System.out.println("Starting Threads:");
			service.execute(() -> System.out.println("First thread is running."));
			service.execute(() -> { for(int i = 0; i < 5; i++) {
				System.out.println(String.format("Thunderbird %d is go.", i));
			}
			return;});
			service.execute(() -> System.out.println("Third thread is running."));
			
			// Example with submit that checks the Future
			
			Future<?> result = service.submit(() -> {
				try {
					for(int i = 0; i < 10; i++) {
						// This is intentionally going to trigger a time out.
						Thread.sleep(1000);
					}
				}
				catch(InterruptedException ie) {
					System.out.println("Who woke me up!?");
				}
			}
			);
			result.get(5,TimeUnit.SECONDS);
		}
		catch(TimeoutException te) {
			System.out.println("Thread timed out!");
		}
		catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(service != null) service.shutdown();
		}	
	}

	private static void runCallableExample() {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			Future<Integer> result = service.submit(()->30+12);
			System.out.println(result.get());			
		}
		catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
		}		
		finally {
			if(service != null) service.shutdown();
		}	
	}

	private static void runScheduledTaskExample() {
		ScheduledExecutorService service = null;
		
		try {
			service = Executors.newSingleThreadScheduledExecutor();
			
			Runnable task1 = () -> System.out.println("Performing task 1");
			Callable<String> task2 = () -> "Task 2";
			Future<?> result1 = service.schedule(task1, 5, TimeUnit.SECONDS);
			Future<?> result2 = service.schedule(task2, 4, TimeUnit.SECONDS);
			
			System.out.println("I'm going to take a short break.");
			String result2Return = (String)result2.get();
			System.out.println(result2Return);
		}
		catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(service != null) service.shutdown();
		}

		
	}

}
