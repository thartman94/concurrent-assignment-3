import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Map;

/**
 * 
 * Handles Stream Execution
 *
 */

public class MyStream {
	List<Integer> synPrimes;
	Map<Integer, List<Integer>> synNonPrimes;
	int input;

	/**
	 * Constructor for the MyStream class.
	 * 
	 * @param synPrimes    The list of primes.
	 * @param synNonPrimes The map of non-primes.
	 * @param input        The the value the program will find all primes less *
	 *                     than or equal to.
	 */

	public MyStream(List<Integer> synPrimes, Map<Integer, List<Integer>> synNonPrimes, int input) {
		this.synPrimes = synPrimes;
		this.synNonPrimes = synNonPrimes;
		this.input = input;

		List<Integer> listIntegers = IntStream.rangeClosed(1, input).boxed().collect(Collectors.toList());
		printStream(listIntegers.parallelStream(), synPrimes, synNonPrimes);
	}

	/**
	 * Function to call the streams
	 * 
	 * @param synPrimes    The list of primes.
	 * @param synNonPrimes The map of non-primes.
	 * @param list         The list of integers between 0 and the input.
	 */

	static void printStream(Stream<Integer> list, List<Integer> synPrimes, Map<Integer, List<Integer>> synNonPrimes) {
		Factors fc = new Factors();

		list.forEach(value -> {
			List<Integer> factors = fc.getFactors(value);
			if (factors.size() == 2) {
				synPrimes.add(value);
			} else {
				synNonPrimes.put(value, factors);
			}
		});
	}
}
