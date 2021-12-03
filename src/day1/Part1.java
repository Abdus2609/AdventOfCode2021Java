package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

  public static int calculateIncreases(String filename) {
    int numIncreases = 0;
    File file = new File(filename);
    try {
      Scanner scanner = new Scanner(file);
      int prev = Integer.parseInt(scanner.nextLine());
      while (scanner.hasNextLine()) {
        int cur = Integer.parseInt(scanner.nextLine());
        if (cur > prev) {
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
}
