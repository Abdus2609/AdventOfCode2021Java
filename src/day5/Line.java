package day5;

public class Line {

  Point start;
  Point end;

  public Line(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  public int[][] addToGrid(int[][] grid) {

    if (start.x == end.x || start.y == end.y) {
      for (int i = Math.min(start.y, end.y); i <= Math.max(start.y, end.y); i++) {
        for (int j = Math.min(start.x, end.x); j <= Math.max(start.x, end.x); j++) {
          grid[i][j]++;
        }
      }
    } else if (start.x < end.x && start.y < end.y) {
      int currX = start.x;
      for (int i = start.y; i <= end.y; i++) {
        grid[i][currX++]++;
      }
    } else if (start.x > end.x && start.y > end.y) {
      int currX = start.x;
      for (int i = start.y; i >= end.y; i--) {
        grid[i][currX--]++;
      }
    } else if (start.x < end.x) {
      int currX = start.x;
      for (int i = start.y; i >= end.y; i--) {
        grid[i][currX++]++;
      }
    } else {
      int currX = start.x;
      for (int i = start.y; i <= end.y; i++) {
        grid[i][currX--]++;
      }
    }

    return grid;
  }
}
