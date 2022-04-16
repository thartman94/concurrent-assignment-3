import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MultiThread extends Thread {

	List<Integer> primes = new ArrayList<Integer>();
	Map<Integer, List<Integer>> nonPrimes = new LinkedHashMap<Integer, List<Integer>>();
	int i;
	Factors fc = new Factors();

	public MultiThread(List<Integer> primes, Map<Integer, List<Integer>> nonPrimes, int i) {
		this.primes = primes;
		this.nonPrimes = nonPrimes;
		this.i = i;
	}

	public void run() {

		List<Integer> factors = fc.getFactors(i);
		if (factors.size() == 2) {
			primes.add(i);
			// System.out.println("prime");
		} else {
			nonPrimes.put(i, factors);
			// System.out.println("not prime");

		}
	}
}
