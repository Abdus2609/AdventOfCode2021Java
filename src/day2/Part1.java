package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

  public static int move(String filename) {
    File file = new File(filename);
    int horizontal = 0;
    int depth = 0;
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String[] elems = scanner.nextLine().split(" ");
        String direction = elems[0];
        int value = Integer.parseInt(elems[1]);
        switch (direction) {
          case "forward" -> horizontal += value;
          case "down" -> depth += value;
          case "up" -> depth -= value;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return horizontal * depth;
  }

  public static void main(String[] args) {
    System.out.println(move("src/day2/input.txt"));
  }
}
