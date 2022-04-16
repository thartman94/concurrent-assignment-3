import java.util.*;

public class SingleThread {

  List<Integer> primes = new ArrayList<Integer>();
  Map<Integer, List<Integer>> nonPrimes = new LinkedHashMap<Integer, List<Integer>>();
  int i;
  Factors fc = new Factors();

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
