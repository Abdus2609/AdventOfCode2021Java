package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {

  public static int lifeSupport(String filename) {
    File file = new File(filename);
    int oxygen = 0;
    int co2 = 0;
    try {
      Scanner scanner = new Scanner(file);

      List<String> oxygenStrings = new ArrayList<>();
      List<String> co2Strings = new ArrayList<>();

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        oxygenStrings.add(line);
        co2Strings.add(line);
      }

      int i = 0;
      while (oxygenStrings.size() > 1) {
        int finalI = i;

        long numOnes = oxygenStrings.stream().filter(s -> s.charAt(finalI) == '1').count();
        long numZeros = oxygenStrings.size() - numOnes;

        char max = numOnes >= numZeros ? '1' : '0';
        oxygenStrings.removeIf(s -> s.charAt(finalI) != max);

        i++;
      }

      i = 0;
      while (co2Strings.size() > 1) {
        int numOnes = 0;
        int finalI = i;

        numOnes += co2Strings.stream().filter(s -> s.charAt(finalI) == '1').count();
        int numZeros = co2Strings.size() - numOnes;

        char min = numOnes < numZeros ? '1' : '0';
        co2Strings.removeIf(s -> s.charAt(finalI) != min);

        i++;
      }

      oxygen = Integer.parseInt(oxygenStrings.get(0), 2);
      co2 = Integer.parseInt(co2Strings.get(0), 2);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return co2 * oxygen;
  }

  public static void main(String[] args) {
    System.out.println(lifeSupport("src/day3/input.txt"));
  }
}
