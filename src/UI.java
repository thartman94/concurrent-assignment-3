import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** ... */

/**
 * Handles the user interface for the program.
 */

public class UI {
	Scanner sc;

	/**
	 * Constructor for the UI class.
	 */
	public UI(Scanner sc) {
		this.sc = sc;
	}

	String[] operations = {
			"Exit",
			"Single Threaded",
			"Multi Threaded",
			"Bounded Threadpool",
			"Stream",
	};

	/**
	 * Prints the menu to the console.
	 * 
	 * @return the user's choice
	 */
	public int getOperation() {
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

	/**
	 * Asks the user for the number they want to factor.
	 * Used for all calculations operations.
	 * 
	 * @return the number that will be factored
	 */
	public int getInput() {
		System.out.println("Enter number for calculation: ");
		return sc.nextInt();
	}

	/**
	 * Gets the number of threads to use for the calculation.
	 * Only Used for the bounded threadpool method.
	 * 
	 * If the user enters a zero, the default value of 1 + the number of
	 * logical cores will be used.
	 * 
	 * @return the number of threads to be used
	 */

	public int getThreads() {
		System.out.println("Enter number of threads (Enter 0 for default): ");
		return sc.nextInt();
	}

	/**
	 * Prints the results of the calculation.
	 * 
	 * Features commented code for debugging and error checking.
	 * Will not print the list of primes and non primes unless these
	 * lines are un-commmented.
	 * 
	 * @param type      the type of calculation the user selected
	 * @param int       the number that was provided by the user to be factored
	 * @param time      the time it took to complete the calculation
	 * @param primes    the list of primes that were found
	 * @param nonPrimes the list of non-primes that were found
	 */

	public void printResults(String type, int input, double time, List<Integer> primes,
			Map<Integer, List<Integer>> nonPrimes) {

		// uncomment if you want to print data
		// System.out.println("\nPrimes: " + primes);
		// System.out.println("Non Primes: " + nonPrimes);

		System.out.println("");
		System.out.println(type + " operation completed for " + input + " completed in: " + time + "ms");

		System.out.println("- Sanity checks:");
		System.out.println("- Total Primes found: " + primes.size());
		System.out.println("- Total Non Primes found: " + nonPrimes.size());
		System.out.println("- primes.size() + nonPrimes.size(): " + (primes.size() + nonPrimes.size()));

		System.out.println("\n");
	}
}
