package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class Part2 {

  public static long partTwo(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    Map<Character, Integer> scoreMap = Map.of('(', 1, '[', 2, '{', 3, '<', 4);
    List<Long> scores = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
      Deque<Character> stack = new ArrayDeque<>();

      if (!isCorrupted(line.toCharArray(), stack)) {
        long score = 0;
        while (!stack.isEmpty()) {
          char c = stack.pop();
          score += (4 * score) + scoreMap.get(c);
        }

        scores.add(score);
      }
    }

    scores.sort(Long::compareTo);
    System.out.println(scores);

    return scores.get(scores.size() / 2);
  }

  private static boolean isCorrupted(char[] array, Deque<Character> stack) {
    for (char c : array) {
      if (stack.isEmpty()) {
        stack.push(c);
        continue;
      }
      switch (c) {
        case '(', '[', '{', '<' -> stack.push(c);
        case ')' -> {
          if (stack.peek() != '(') {
            return true;
          }
          stack.pop();
        }
        case ']' -> {
          if (stack.peek() != '[') {
            return true;
          }
          stack.pop();
        }
        case '}' -> {
          if (stack.peek() != '{') {
            return true;
          }
          stack.pop();
        }
        case '>' -> {
          if (stack.peek() != '<') {
            return true;
          }
          stack.pop();
        }
      }
    }

    return false;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partTwo("src/day10/input.txt"));
  }
}
