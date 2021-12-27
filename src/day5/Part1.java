package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

  public static int partOne(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    List<Line> lines = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
      String[] points = line.split(" -> ");
      String[] p1Coords = points[0].split(",");
      String[] p2Coords = points[1].split(",");

      Point p1 = new Point(p1Coords[0], p1Coords[1]);
      Point p2 = new Point(p2Coords[0], p2Coords[1]);

      if (p1.x == p2.x || p1.y == p2.y) {
        lines.add(new Line(p1, p2));
      }
    }

    int[][] grid = new int[1000][1000];

    for (Line l : lines) {
      grid = l.addToGrid(grid);
    }

    int count = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        count += (grid[i][j] > 1) ? 1 : 0;
      }
    }

    return count;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partOne("src/day5/input.txt"));
  }
}
