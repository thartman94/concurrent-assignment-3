import java.util.*;

public class UI {
	Scanner sc;

	public UI(Scanner sc) {
		this.sc = sc;
	}

	String[] options = {
			"Exit",
			"Single Threaded",
			"Multi Threaded",
			"Boudned Threadpool",
	};

	public int getOpperation() {
		int operation = 0;

		while (true) {
			System.out.println("\nSelect an execution method, or exit: ");
			for (int i = 0; i < options.length; i++) {
				System.out.println(i + ") " + options[i]);
			}

			operation = sc.nextInt();
			if (operation >= 0 && operation < options.length) {
				return operation;
			}
		}
	}

	public int getInput(String type) {
		System.out.println("Enter number for " + type + " calculation: ");
		return sc.nextInt();
	}

	public void printResults(String type, int input, double time, List<Integer> primes,
			Map<Integer, List<Integer>> nonPrimes) {

		// uncomment if you want to print data
		// System.out.println("\nPrimes: " + primes);
		// System.out.println("Non Primes: " + nonPrimes);

		System.out.println("");
		System.out.println(type + " opperation completed for " + input + " completed in: " + time + "ms");

		System.out.println("\nSanity checks:");
		System.out.println("Total Primes found: " + primes.size());
		System.out.println("Total Non Primes found: " + nonPrimes.size());
		System.out.println("primes.size() + nonPrimes.size(): " + (primes.size() + nonPrimes.size()));

		System.out.println("\n");
	}
}
