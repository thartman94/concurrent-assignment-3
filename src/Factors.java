import java.util.List;
import java.util.ArrayList;

/** ... */
/**
 * Handles the factorization of an integer value
 */
public class Factors {

	public Factors() {
	}

	/**
	 * Factorizes an integer value
	 * 
	 * @param input The integer value to be factorized
	 * @return The list of factors
	 */
	public List<Integer> getFactors(int number) {
		List<Integer> factors = new ArrayList<Integer>();

		double sqrt = Math.sqrt(number); // store value to avoid repeated computation

		for (int i = 1; i <= sqrt; i++) {
			if (number % i == 0) {
				factors.add(factors.size() / 2, i);

				if (i != sqrt) {
					factors.add(1 + (factors.size() / 2), number / i); // avoid doubling the sqrt as a factor
				}
			}
		}

		return factors;
	}
}
