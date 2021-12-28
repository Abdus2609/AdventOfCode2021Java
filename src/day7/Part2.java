package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

  public static int partTwo(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    List<Integer> positions = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
      String[] pos = line.split(",");
      Arrays.stream(pos).forEach(p -> positions.add(Integer.parseInt(p)));
    }

    int minFuel = Integer.MAX_VALUE;
    int maxPos = positions.stream().max(Integer::compareTo).get();

    for (int i = 0; i < maxPos; i++) {
      int sum = 0;
      for (int p : positions) {
        int diff = Math.abs(p - i);
        sum += (diff * (diff + 1)) / 2;
      }

      if (sum < minFuel) {
        minFuel = sum;
      }
    }

    return minFuel;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partTwo("src/day7/input.txt"));
  }
}
