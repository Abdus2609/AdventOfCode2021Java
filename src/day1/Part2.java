package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

  public static int calculateIncreases(String filename) {
    int numIncreases = 0;
    File file = new File(filename);
    try {
      Scanner scanner = new Scanner(file);
      Triple prev = new Triple(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
      while (scanner.hasNextLine()) {
        Triple cur = new Triple(prev.second, prev.third, scanner.nextLine());
        if (cur.sum > prev.sum) {
          numIncreases++;
        }

        prev = cur;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return numIncreases;
  }

  public static void main(String[] args) {
    System.out.println(calculateIncreases("src/day1/input.txt"));
  }

  private static class Triple {

    private final String second;
    private final String third;
    private final int sum;

    private Triple(String first, String second, String third) {
      this.second = second;
      this.third = third;
      this.sum = Integer.parseInt(first) + Integer.parseInt(second) + Integer.parseInt(third);
    }
  }
}
