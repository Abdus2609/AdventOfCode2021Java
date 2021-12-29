package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

  public static int partOne(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    int numDigits = 0;

    String line;
    while ((line = br.readLine()) != null) {
      String output = line.split(" \\| ")[1];
      String[] words = output.split(" ");

      for (String word : words) {
        numDigits += checkUniqueLength(word) ? 1 : 0;
      }
    }

    return numDigits;
  }

  private static boolean checkUniqueLength(String word) {
    return word.length() == 2 || word.length() == 3 || word.length() == 4 || word.length() == 7;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partOne("src/day8/input.txt"));
  }
}
