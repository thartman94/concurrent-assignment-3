import java.util.*;

public class Factors {

  public Factors() {
  }

  // Returns a list of factors of the given number.
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
