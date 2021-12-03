package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {

  public static int diagnostic(String filename) {
    File file = new File(filename);
    StringBuilder gsb = new StringBuilder();
    StringBuilder esb = new StringBuilder();
    try {
      Scanner scanner = new Scanner(file);
      List<String> nums = new ArrayList<>();

      while (scanner.hasNextLine()) {
        nums.add(scanner.nextLine());
      }

      for (int i = 0; i < nums.get(0).length(); i++) {
        int finalI = i;
        long numOnes = nums.stream().filter(s -> s.charAt(finalI) == '1').count();
        long numZeros = nums.size() - numOnes;
        char max = numOnes >= numZeros ? '1' : '0';
        char min = numOnes < numZeros ? '1' : '0';
        gsb.append(max);
        esb.append(min);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int gamma = Integer.parseInt(gsb.toString(), 2);
    int epsilon = Integer.parseInt(esb.toString(), 2);

    return gamma * epsilon;
  }

  public static void main(String[] args) {
    System.out.println(diagnostic("src/day3/input.txt"));
  }
}
