package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

  public static int partTwo(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    int sum = 0;

    String line;
    while ((line = br.readLine()) != null) {
      Map<Integer, String> map = new HashMap<>();
      String[] sections = line.split(" \\| ");
      String output = sections[1];
      String[] words = output.split(" ");

      List<String> list = new ArrayList<>(List.of(sections[0].split(" ")));
      list.addAll(List.of(words));

      for (String word : list) {
        decodeUnique(word, map);
      }

      List<String> sixes = list.stream().filter(w -> w.length() == 6).toList();
      List<String> fives = list.stream().filter(w -> w.length() == 5).toList();

      for (String word : sixes) {
        decodeSix(word, map);
      }

      for (String word : fives) {
        decodeFive(word, map);
      }

      StringBuilder sb = new StringBuilder();

      for (String word : words) {
        sb.append(reverseLookup(word, map));
      }

      sum += Integer.parseInt(sb.toString());
    }

    return sum;
  }

  private static int reverseLookup(String value, Map<Integer, String> map) {
    for (int key : map.keySet()) {
      boolean containsNum = true;
      String word = map.get(key);

      if (value.length() != word.length()) {
        continue;
      }

      for (char c : word.toCharArray()) {
        if (!value.contains(Character.toString(c))) {
          containsNum = false;
          break;
        }
      }

      if (containsNum) {
        return key;
      }
    }

    return -1;
  }

  private static void decodeUnique(String word, Map<Integer, String> map) {
    switch (word.length()) {
      case 2 -> map.put(1, word);
      case 3 -> map.put(7, word);
      case 4 -> map.put(4, word);
      case 7 -> map.put(8, word);
    }
  }

  private static void decodeSix(String word, Map<Integer, String> map) {
    for (char c : map.get(1).toCharArray()) {
      if (!word.contains(Character.toString(c))) {
        map.put(6, word);
        return;
      }
    }

    for (char c : map.get(4).toCharArray()) {
      if (!word.contains(Character.toString(c))) {
        map.put(0, word);
        return;
      }
    }

    map.put(9, word);
  }

  private static void decodeFive(String word, Map<Integer, String> map) {
    String nine = map.get(9);
    for (char c : word.toCharArray()) {
      if (!nine.contains(Character.toString(c))) {
        map.put(2, word);
        return;
      }
    }

    for (char c : map.get(1).toCharArray()) {
      if (!word.contains(Character.toString(c))) {
        map.put(5, word);
        return;
      }
    }

    map.put(3, word);
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partTwo("src/day8/input.txt"));
  }
}
