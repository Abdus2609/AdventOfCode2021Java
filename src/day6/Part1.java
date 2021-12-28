package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

  public static int partOne(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    ArrayList<Integer> fishes = new ArrayList<>();

    String line;

    while ((line = br.readLine()) != null) {
      String[] words = line.split(",");
      Arrays.stream(words).forEach(w -> fishes.add(Integer.parseInt(w)));
    }

    for (int i = 0; i < 80; i++) {
      List<Integer> fishCopy = (List<Integer>) fishes.clone();
      for (int j = 0; j < fishCopy.size(); j++) {
        if (fishCopy.get(j) == 0) {
          fishes.add(8);
          fishes.set(j, 7);
        }
        fishes.set(j, fishes.get(j) - 1);
      }
    }

    return fishes.size();
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partOne("src/day6/input.txt"));
  }
}
