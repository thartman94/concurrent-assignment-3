import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class Bounded {

	List<Integer> primes = new ArrayList<Integer>();
	Map<Integer, List<Integer>> nonPrimes = new LinkedHashMap<Integer, List<Integer>>();
	int input;
	int threads;

	public Bounded(List<Integer> primes, Map<Integer, List<Integer>> nonPrimes, int input, int threads) {
		this.primes = primes;
		this.nonPrimes = nonPrimes;
		this.input = input;
		this.threads = threads;

		if (threads == 0) {
			threads = 1 + Runtime.getRuntime().availableProcessors();
		}

		ExecutorService service = Executors.newFixedThreadPool(10);

		List<Future<List<Integer>>> allFutures = new ArrayList<>();
		for (int j = 0; j < input; j++) {
			Future<List<Integer>> future = service.submit(new Task(j));
			allFutures.add(future);
		}

		for (int j = 0; j < input; j++) {
			Future<List<Integer>> future = allFutures.get(j);
			try {
				List<Integer> factors = future.get();
				if (factors.size() == 2) {
					primes.add(factors.get(0));
				} else {
					nonPrimes.put(factors.get(0), factors);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	static class Task implements Callable<List<Integer>> {
		private int number;

		public Task(int number) {
			this.number = number;
		}

		@Override
		public List<Integer> call() throws Exception {
			Factors fc = new Factors();
			List<Integer> factors = fc.getFactors(number);
			return factors;
		}
	}
}
