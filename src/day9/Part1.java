package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

  public static int partOne(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

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

    List<Integer> lowPoints = new ArrayList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (isLowPoint(i, j, grid)) {
          lowPoints.add(grid[i][j]);
        }
      }
    }

    return lowPoints.stream().reduce(Integer::sum).get() + lowPoints.size();
  }

  private static boolean isLowPoint(int row, int col, int[][] grid) {
    List<Integer> surrounding = new ArrayList<>();

    if (row != 0) {
      surrounding.add(grid[row - 1][col]);
    }

    if (row != grid.length - 1) {
      surrounding.add(grid[row + 1][col]);
    }

    if (col != 0) {
      surrounding.add(grid[row][col - 1]);
    }

    if (col != grid[0].length - 1) {
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
    System.out.println(partOne("src/day9/input.txt"));
  }
}
