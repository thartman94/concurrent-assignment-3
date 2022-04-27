import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/** ... */
/**
 * Handles the single threaded execution of the program.
 * 
 */
public class SingleThread {

	List<Integer> primes = new ArrayList<Integer>();
	Map<Integer, List<Integer>> nonPrimes = new LinkedHashMap<Integer, List<Integer>>();
	int i;
	Factors fc = new Factors();

	/**
	 * Constructor for the SingleThread class.
	 * 
	 * @param primes    The list of primes.
	 * @param nonPrimes The map of non-primes.
	 * @param i         The the value to be factored, provided by user
	 */
	public SingleThread(List<Integer> primes, Map<Integer, List<Integer>> nonPrimes, int i) {
		this.primes = primes;
		this.nonPrimes = nonPrimes;
		this.i = i;
	}

	public void run() {
		List<Integer> factors = fc.getFactors(i);
		if (factors.size() == 2) {
			primes.add(i);
		} else {
			nonPrimes.put(i, factors);
		}
	}

}
