package day4;

import java.util.ArrayList;
import java.util.List;

public class BingoBoard {

  int[][] board;
  List<Integer> allNums = new ArrayList<>();

  public BingoBoard(List<String> strings) {
    this.board = new int[5][5];
    populate(strings);
  }

  private void populate(List<String> strings) {
    for (int i = 0; i < 5; i++) {
      String[] nums = strings.get(i).split(" ");
      for (int j = 0; j < 5; j++) {
        int num = Integer.parseInt(nums[j]);
        allNums.add(num);
        board[i][j] = num;
      }
    }
  }

  public List<List<Integer>> getRowsAndCols() {
    List<List<Integer>> rowsAndCols = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      List<Integer> row = new ArrayList<>();
      for (int n : board[i]) {
        row.add(n);
      }

      rowsAndCols.add(row);

      List<Integer> col = new ArrayList<>();
      for (int j = 0; j < 5; j++) {
        col.add(board[j][i]);
      }

      rowsAndCols.add(col);
    }

    return rowsAndCols;
  }

  public List<Integer> checkWinner(List<Integer> chosenNumbers) {
    List<List<Integer>> rowsAndCols = getRowsAndCols();

    for (List<Integer> arr : rowsAndCols) {
      if (chosenNumbers.containsAll(arr)) {
        return arr;
      }
    }

    return null;
  }
}
