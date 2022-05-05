import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Collections;

/** ... */
/**
 * 
 * Main class for the program.
 * 
 * @param args
 * @throws Exception
 */

public class App {
	public static void main(String[] args) throws Exception {

		final int SEQUENTIAL = 1;
		final int UNBOUNDED = 2;
		final int BOUNDED = 3;
		final int STREAM = 4;
		Scanner sc = new Scanner(System.in);
		UI ui = new UI(sc);
		int operation = 0;
		boolean running = true;

		while (running) {
			operation = ui.getOperation();

			List<Integer> primes = new ArrayList<Integer>();
			Map<Integer, List<Integer>> nonPrimes = new LinkedHashMap<Integer, List<Integer>>();

			/**
			 * Single Threaded Execution
			 */
			if (operation == SEQUENTIAL) {
				int input = ui.getInput();
				double startTime = System.currentTimeMillis();

				for (int i = 1; i <= input; i++) {
					SingleThread st = new SingleThread(primes, nonPrimes, i);
					st.run();
				}
				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;

				ui.printResults("Single-threaded", input, time, primes, nonPrimes);
			}

			/**
			 * Unbounded Multi-threaded Execution
			 */
			else if (operation == UNBOUNDED) {
				List<Integer> synPrimes = Collections.synchronizedList(primes);
				Map<Integer, List<Integer>> synNonPrimes = Collections.synchronizedMap(nonPrimes);
				int input = ui.getInput();
				double startTime = System.currentTimeMillis();
				for (int i = 1; i <= input; i++) {
					MultiThread mt = new MultiThread(synPrimes, synNonPrimes, i);
					mt.start();
				}
				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;
				ui.printResults("Multi-threaded", input, time, synPrimes, synNonPrimes);
			}

			else if (operation == BOUNDED) {
				List<Integer> synPrimes = Collections.synchronizedList(primes);
				Map<Integer, List<Integer>> synNonPrimes = Collections.synchronizedMap(nonPrimes);
				int threads = ui.getThreads();
				int input = ui.getInput();
				double startTime = System.currentTimeMillis();
				new Bounded(synPrimes, synNonPrimes, input, threads);
				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;
				ui.printResults("Bounded", input, time, synPrimes, synNonPrimes);
			}

			else if (operation == STREAM) {
				List<Integer> synPrimes = Collections.synchronizedList(primes);
				Map<Integer, List<Integer>> synNonPrimes = Collections.synchronizedMap(nonPrimes);
				int input = ui.getInput();
				double startTime = System.currentTimeMillis();
				new MyStream(synPrimes, synNonPrimes, input);
				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;
				ui.printResults("Stream", input, time, synPrimes, synNonPrimes);
			}

			else {
				System.out.println("Exiting...");
				sc.close();
				running = false;
			}
		}
	}
}
