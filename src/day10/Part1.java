package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Part1 {

  public static int partOne(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    Map<Character, Integer> scores = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137);

    Deque<Character> stack = new ArrayDeque<>();
    int score = 0;

    String line;
    while ((line = br.readLine()) != null) {
      boolean foundCorrupted = false;

      for (char c : line.toCharArray()) {
        if (stack.isEmpty()) {
          stack.push(c);
          continue;
        }

        switch (c) {
          case '(', '[', '{', '<' -> stack.push(c);
          case ')' -> {
            if (stack.peek() == '(') {
              stack.pop();
            } else {
              foundCorrupted = true;
            }
          }
          case ']' -> {
            if (stack.peek() == '[') {
              stack.pop();
            } else {
              foundCorrupted = true;
            }
          }
          case '}' -> {
            if (stack.peek() == '{') {
              stack.pop();
            } else {
              foundCorrupted = true;
            }
          }
          case '>' -> {
            if (stack.peek() == '<') {
              stack.pop();
            } else {
              foundCorrupted = true;
            }
          }
        }

        if (foundCorrupted) {
          score += scores.get(c);
          break;
        }
      }
    }

    return score;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partOne("src/day10/input.txt"));
  }
}
