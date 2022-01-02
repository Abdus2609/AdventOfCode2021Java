package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

  public static int partOne(String filename) throws IOException {

    BufferedReader br = new BufferedReader(new FileReader(filename));

    int numFlashes = 0;

    List<String> rows = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
      rows.add(line);
    }

    int[][] grid = new int[10][10];
    populate(grid, rows);

    for (int step = 0; step < 100; step++) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
          grid[i][j]++;
        }
      }

      boolean[][] visited = new boolean[10][10];

      while (!noFlashesNeeded(grid)) {
        numFlashes += flash(grid, visited);
      }
    }

    return numFlashes;
  }

  private static int flash(int[][] grid, boolean[][] visited) {
    int count = 0;

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (grid[i][j] > 9 && !visited[i][j]) {
          grid[i][j] = 0;
          visited[i][j] = true;
          count++;
          flashAdjacent(grid, visited, i, j);
        }
      }
    }

    return count;
  }

  private static void flashAdjacent(int[][] grid, boolean[][] visited, int i, int j) {

    if (i > 0) {
      if (!visited[i - 1][j]) {
        grid[i - 1][j]++;
      }

      if (j > 0 && !visited[i - 1][j - 1]) {
        grid[i - 1][j - 1]++;
      }

      if (j < 9 && !visited[i - 1][j + 1]) {
        grid[i - 1][j + 1]++;
      }
    }

    if (j > 0) {
      if (!visited[i][j - 1]) {
        grid[i][j - 1]++;
      }

      if (i < 9 && !visited[i + 1][j - 1]) {
        grid[i + 1][j - 1]++;
      }
    }

    if (j < 9) {
      if (!visited[i][j + 1]) {
        grid[i][j + 1]++;
      }

      if (i < 9 && !visited[i + 1][j + 1]) {
        grid[i + 1][j + 1]++;
      }
    }

    if (i < 9 && !visited[i + 1][j]) {
      grid[i + 1][j]++;
    }
  }

  private static boolean noFlashesNeeded(int[][] grid) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (grid[i][j] > 9) {
          return false;
        }
      }
    }

    return true;
  }

  private static void populate(int[][] grid, List<String> rows) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        grid[i][j] = Character.getNumericValue(rows.get(i).charAt(j));
      }
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partOne("src/day11/input.txt"));
  }
}
