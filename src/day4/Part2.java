package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

  public static int partTwo(String filename) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));
    List<Integer> bingoNumbers =
        Arrays.stream(br.readLine().split(",")).map(Integer::parseInt).toList();

    String line;

    List<BingoBoard> boards = new ArrayList<>();

    int index = 0;
    List<String> strings = new ArrayList<>();

    while ((line = br.readLine()) != null) {
      if (line.isEmpty()) continue;

      if (index == 0) {
        strings = new ArrayList<>();
      }

      strings.add(index++, line);

      if (index == 5) {
        boards.add(new BingoBoard(strings));
        index = 0;
      }
    }

    int sumUnmarked;
    int lastWinningScore = 0;

    List<Integer> chosenNumbers = new ArrayList<>();
    for (int num : bingoNumbers) {
      chosenNumbers.add(num);
      for (int i = 0; i < boards.size(); i++) {

        BingoBoard b = boards.get(i);

        if (b.checkWinner(chosenNumbers) != null) {
          sumUnmarked = 0;
          List<Integer> unmarked =
              b.allNums.stream().filter(n -> !chosenNumbers.contains(n)).toList();

          for (int n : unmarked) {
            sumUnmarked += n;
          }

          lastWinningScore = sumUnmarked * num;
          boards.remove(b);
        }
      }
    }

    return lastWinningScore;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(partTwo("src/day4/input.txt"));
  }
}