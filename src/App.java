import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Collections;

public class App {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		UI ui = new UI(sc);
		int operation = 0;
		boolean running = true;

		while (running) {
			operation = ui.getOpperation();

			List<Integer> primes = new ArrayList<Integer>();
			Map<Integer, List<Integer>> nonPrimes = new LinkedHashMap<Integer, List<Integer>>();

			// Single-thread execution
			if (operation == 1) {
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

			// Multi-thread execution
			else if (operation == 2) {
				List<Integer> synPrimes = Collections.synchronizedList(primes);
				Map<Integer, List<Integer>> synNonPrimes = Collections.synchronizedMap(nonPrimes);
				int input = ui.getInput();
				double startTime = System.currentTimeMillis();

				for (int i = 1; i <= input; i++) {
					MultiThread mt = new MultiThread(synPrimes, synNonPrimes, i);
					mt.start();
				}

				// wait for all threads to be done, I'm positive there's a better way to do this
				while (synPrimes.size() + synNonPrimes.size() != input) {
				}

				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;

				ui.printResults("Multi-threaded", input, time, synPrimes, synNonPrimes);
			}

			else if (operation == 3) {
				List<Integer> synPrimes = Collections.synchronizedList(primes);
				Map<Integer, List<Integer>> synNonPrimes = Collections.synchronizedMap(nonPrimes);

				int threads = ui.getThreads();
				int input = ui.getInput();

				double startTime = System.currentTimeMillis();
				Bounded bd = new Bounded(synPrimes, synNonPrimes, input, threads);
				double endTime = System.currentTimeMillis();
				double time = endTime - startTime;
				ui.printResults("Bounded", input, time, synPrimes, synNonPrimes);
			}

			// Exit
			else {
				System.out.println("Exiting...");
				sc.close();
				running = false;
			}
		}
	}
}
