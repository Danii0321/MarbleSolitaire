package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class represents the operations offered by the abstract marble solitaire
 * model. One object of the model represents one game of marble solitaire.
 */
public abstract class AbstractSolitaireModelImpl implements MarbleSolitaireModel {
  //represents the current score of the game
  protected int score;

  //represents the game board
  protected State[][] board;

  /**
   * This documents "State", the possible states for each game space.
   * The States are:
   * -Invalid represents the top left and bottom left corners where no moves can be made to or from.
   * -Filled represents a marble.
   * -Empty represents an empty space.
   * -Ignored represents the extra spaces on the top and bottom right corners where no moves can be
   * made to or from.
   */
  protected enum State {
    INVALID(" "),
    FILLED("O"),
    EMPTY("_"),
    IGNORED("i");

    private final String text;

    State(final String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return text;
    }
  }


  /*
    Checks that the move can be made according to the specifications of the type of board
    -english and european: toRow must be two above or below and toCol is the same OR
     toCol is two to the left or right and toRow is the same
    ***same for english and european, overridden in triangular
  */
  protected boolean isValidDirection(int fromRow, int fromCol, int toRow, int toCol) {
    //checks that move is uo down left or right by 2 places
    //same for euro model
    return (Math.abs(toRow - fromRow) == 2 && toCol == fromCol
        || Math.abs(toCol - fromCol) == 2 && toRow == fromRow);
  }

  /*
    Abstracted checks
    In order to a move to be valid:
    -fromRow, fromCol, toRow, and toCol are all in bounds of the board
    -marble in from space and empty in to space
    -marble in space being jumped over

    returns boolean representing if the move is valid
    throws IllegalArgumentException with informative error message if any of the above conditions
    are untrue

   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    //checking all parameters are within bounds of the board
    if (fromRow >= 0 && fromRow < board.length
        && fromCol >= 0 && fromCol < board.length
        && toRow >= 0 && toRow < board.length
        && toCol >= 0 && toCol < board.length) {

      //checking there is a marble in the from space and an empty in the to space
      if (board[fromRow][fromCol].toString().equals("O")) {
        if (board[toRow][toCol].toString().equals("_")) {

          //checking that the nature of the move itself is valid for the given model
          if (isValidDirection(fromRow, fromCol, toRow, toCol)) {

            //making sure the middle space has a marble
            if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2].toString().equals("O")) {

              return true;

            } else if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2].toString().equals("_")) {
              throw new IllegalArgumentException("Cannot jump over empty space");
            }
          } else {
            throw new IllegalArgumentException("Must move 2 spaces up, down, left, or right");
          }
        } else {
          throw new IllegalArgumentException("To position is invalid");
        }
      } else {
        throw new IllegalArgumentException("From position is invalid");
      }
    } else {
      throw new IllegalArgumentException("From or to position is out of bounds");
    }
    return false;
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    try {
      if (isValidMove(fromRow, fromCol, toRow, toCol)) {
        //do if valid
        board[fromRow][fromCol] = State.EMPTY;
        board[toRow][toCol] = State.FILLED;
        collectPiece((fromRow + toRow) / 2, (fromCol + toCol) / 2);
      }
    } catch (IllegalArgumentException i) {
      throw i;
    }
  }

  /**
   * "Collects" piece jumped over if a piece was there (State is FILLED)
   * by changing the State from FILLED to EMPTY, increments score.
   *
   * @param overRow Represents the row that had been jumped over.
   * @param overCol Represents the column that had been jumped over.
   */
  private void collectPiece(int overRow, int overCol) {
    if (board[overRow][overCol].toString().equals("O")) {
      board[overRow][overCol] = State.EMPTY;
      score--;
    }
  }

  /*
   * Check all spaces of the board to see if a valid move
   * could be made by any piece.
   *
   * If a move can be made, returns false (the game is not over)
   * If no moves can be made, returns true (the game is over)
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (score > 1) {
          if (existsValidMove(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Could a valid move be made from this space or not?
   * Requirements:
   * -This space (at board[row][col] must contain a marble (FILLED State).
   * -There must be an empty space(EMPTY State) within bounds.
   * -AND Two spaces away.
   * -AND to the top, bottom, left, or right of this space only.
   * **DIFFERENT FOR TRIANGULAR
   *
   * @param row the row of the space being checked.
   * @param col the column of the space being checked.
   * @return boolean representing if move is valid or not
   */
  protected boolean existsValidMove(int row, int col) {
    try {
      if (isValidMove(row, col, row + 2, col)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }
    try {
      if (isValidMove(row, col, row - 2, col)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }
    try {
      if (isValidMove(row, col, row, col + 2)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }
    try {
      if (isValidMove(row, col, row + 2, col - 2)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }
    return false;
  }

  /*
     Returns the game board as a String by:
     1. Turning each row into a String (calling rowToString helper method)
     2. Joining all these Strings together to become the entire board, with "\n"
        in between to create a new line after each row.
   */
  @Override
  public String getGameState() {
    ArrayList<String> rows = new ArrayList<>();
    //ArrayList<String> pieces = new ArrayList<String>;

    //getting a String representing each row
    for (int i = 0; i < board.length; i++) {
      String thisRow = rowToString(board[i]);
      rows.add(thisRow);
    }

    //adding all strings together with \n
    String gameState = rows.stream().collect(Collectors.joining("\n"));
    return gameState;

    //System.out.println(rows);
    //return "";
  }

  /**
   * Converts the given State[] representing a single row in the board into a String
   * by iterating through all the States within that row and converting them to Strings.
   *
   * @param row Represents a single row in the board.
   * @return String representing the row.
   */
  protected String rowToString(State[] row) {
    ArrayList<String> pieces = new ArrayList<>();

    for (int i = 0; i < row.length; i++) {
      if (row[i].toString().equals("i")) {
        i = row.length;
      } else {
        pieces.add(row[i].toString());
      }
    }
    return pieces.stream().collect(Collectors.joining(" "));
  }


  @Override
  public int getScore() {
    return score;
  }

  /**
   * Fills the board with the States corresponding to the correct game pieces for each space.
   * -A space is "fillable" if it can contain an EMPTY or FILLED State.
   * -Fillable spaces are from minFill to maxFill
   * -All but one "fillable" space are FILLED upon construction of a game, one space is EMPTY and
   * is the original empty position specified upon construction (or the default).
   * -The spaces at a row or column position that are not "fillable" are either:
   * INVALID: top or bottom left corners where no moves can be made to or from.
   * IGNORED: the spaces left over after there are no more "fillable" positions
   * -When a space is filled with a marble, this marble is added to the score, which starts as the
   * number of marbles in the game.
   * SETUP VARIES FOR EACH BOARD
   *
   * @param sRow         Row containing the starting empty space.
   * @param sCol         Column containing the starting empty space.
   * @param armThickness The number of marbles in the top row.
   */
  protected abstract void fillBoard(int sRow, int sCol, int armThickness);

}
