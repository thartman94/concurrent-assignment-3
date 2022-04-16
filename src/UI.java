import java.util.*;

public class UI {
  Scanner sc;

  public UI(Scanner sc) {
    this.sc = sc;
  }

  public int getOpperation() {
    int operation = 0;
    while (true) {
      System.out.println("1) Single thread execution");
      System.out.println("2) Multi thread execution");
      System.out.println("9) Exit");
      System.out.println("\nSelect an option: ");

      operation = sc.nextInt();

      if (operation == 1 || operation == 2 || operation == 9) {
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
