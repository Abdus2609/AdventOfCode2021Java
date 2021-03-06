package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Part2 {

  public static long partTwo(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    Map<Integer, Long> fishes =
        new HashMap<>(Map.of(0, 0L, 1, 0L, 2, 0L, 3, 0L, 4, 0L, 5, 0L, 6, 0L, 7, 0L, 8, 0L));

    String line;

    while ((line = br.readLine()) != null) {
      String[] words = line.split(",");
      Arrays.stream(words)
          .forEach(
              w -> {
                int num = Integer.parseInt(w);
                fishes.put(num, fishes.get(num) + 1);
              });
    }

    for (int i = 0; i < 256; i++) {

      long numZeros = fishes.get(0);

      for (int j = 1; j <= 8; j++) {
        fishes.put(j - 1, fishes.get(j));
        fishes.put(j, 0L);
      }

      fishes.put(6, fishes.get(6) + numZeros);
      fishes.put(8, numZeros);
    }

    long sum = 0;
    for (int i = 0; i < 9; i++) {
      sum += fishes.get(i);
    }

    return sum;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partTwo("src/day6/input.txt"));
  }
}
