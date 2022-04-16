import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UI {
	Scanner sc;

	public UI(Scanner sc) {
		this.sc = sc;
	}

	String[] operations = {
			"Exit",
			"Single Threaded",
			"Multi Threaded",
			"Bounded Threadpool",
	};

	public int getOpperation() {
		int operation = 0;

		while (true) {
			System.out.println("\nSelect an execution method, or exit: ");
			for (int i = 0; i < operations.length; i++) {
				System.out.println(i + ") " + operations[i]);
			}

			operation = sc.nextInt();
			if (operation >= 0 && operation < operations.length) {
				return operation;
			}
		}
	}

	public int getInput() {
		System.out.println("Enter number for calculation: ");
		return sc.nextInt();
	}

	public int getThreads() {
		System.out.println("Enter number of threads (Enter 0 for default): ");
		return sc.nextInt();
	}

	public void printResults(String type, int input, double time, List<Integer> primes,
			Map<Integer, List<Integer>> nonPrimes) {

		// uncomment if you want to print data
		// System.out.println("\nPrimes: " + primes);
		// System.out.println("Non Primes: " + nonPrimes);

		System.out.println("");
		System.out.println(type + " opperation completed for " + input + " completed in: " + time + "ms");

		System.out.println("- Sanity checks:");
		System.out.println("- Total Primes found: " + primes.size());
		System.out.println("- Total Non Primes found: " + nonPrimes.size());
		System.out.println("- primes.size() + nonPrimes.size(): " + (primes.size() + nonPrimes.size()));

		System.out.println("\n");
	}
}
