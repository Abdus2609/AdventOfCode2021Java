package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {

  public static int partTwo(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    List<Integer> basins = new ArrayList<>();
    List<List<Character>> rows = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
      List<Character> row = new ArrayList<>();
      for (char c : line.toCharArray()) {
        row.add(c);
      }

      rows.add(row);
    }

    int[][] grid = new int[rows.size()][rows.get(0).size()];
    populate(grid, rows);

    boolean[][] visited = new boolean[rows.size()][rows.get(0).size()];

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (isLowPoint(i, j, grid)) {
          visited[i][j] = true;
          basins.add(getBasinSize(i, j, grid, visited));
        }
      }
    }

    basins.sort(Collections.reverseOrder());

    System.out.println(basins);

    return basins.get(0) * basins.get(1) * basins.get(2);
  }

  private static Integer getBasinSize(int row, int col, int[][] grid, boolean[][] visited) {
    int sum = grid[row][col];
    visited[row][col] = true;
    if (sum == 9) return 0;

    sum = 1;

    if (row > 0 && !visited[row - 1][col]) {
      sum += getBasinSize(row - 1, col, grid, visited);
    }

    if (row < grid.length - 1 && !visited[row + 1][col]) {
      sum += getBasinSize(row + 1, col, grid, visited);
    }

    if (col > 0 && !visited[row][col - 1]) {
      sum += getBasinSize(row, col - 1, grid, visited);
    }

    if (col < grid[0].length - 1 && !visited[row][col + 1]) {
      sum += getBasinSize(row, col + 1, grid, visited);
    }

    return sum;
  }

  private static boolean isLowPoint(int row, int col, int[][] grid) {
    List<Integer> surrounding = new ArrayList<>();

    if (row > 0) {
      surrounding.add(grid[row - 1][col]);
    }

    if (row < grid.length - 1) {
      surrounding.add(grid[row + 1][col]);
    }

    if (col > 0) {
      surrounding.add(grid[row][col - 1]);
    }

    if (col < grid[0].length - 1) {
      surrounding.add(grid[row][col + 1]);
    }

    int num = grid[row][col];

    for (int n : surrounding) {
      if (num >= n) {
        return false;
      }
    }

    return true;
  }

  private static void populate(int[][] grid, List<List<Character>> rows) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        grid[i][j] = Character.getNumericValue(rows.get(i).get(j));
      }
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partTwo("src/day9/input.txt"));
  }
}
