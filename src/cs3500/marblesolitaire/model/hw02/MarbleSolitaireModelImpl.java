package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModelImpl;

/**
 * This class represents the operations offered by the marble solitaire
 * model. One object of the model represents one game of marble solitaire.
 */
public class MarbleSolitaireModelImpl extends AbstractSolitaireModelImpl {

  /**
   * Constructor 1: No parameters passed in.
   * Default board has arm thickness of 3, empty space in center.
   */
  public MarbleSolitaireModelImpl() {
    //(row, col)
    this(3, 3, 3);
  }

  /**
   * Constructor 2: Takes in sRow and sCol parameters
   * which specify the starting position of the empty slot.
   *
   * @param sRow Row containing the starting empty space.
   * @param sCol Column containing the starting empty space.
   * @throws IllegalArgumentException Thrown if sCol or sRow are out of bounds.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor 3: Takes in arm thickness as a parameter and sets up the board with the default
   * empty position in the center.
   *
   * @param armThickness The number of marbles in the top row.
   * @throws IllegalArgumentException if armThickness is not a a non-negative odd number.
   */
  public MarbleSolitaireModelImpl(int armThickness) {

    this(armThickness, ((armThickness + (2 * armThickness - 1)) - 1) / 2,
        ((armThickness + (2 * armThickness - 1)) - 1) / 2);
  }

  /**
   * Constructor 4: Takes in arm thickness, and row and column of the empty slot to
   * construct the board.
   *
   * @param armThickness The number of marbles in the top row.
   * @param sRow         Row containing the starting empty space.
   * @param sCol         Col containing the starting empty space.
   * @throws IllegalArgumentException if any parameters are invalid.
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if (armThickness % 2 == 0 || armThickness < 1) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }

    int dimension = (armThickness + 2 * (armThickness - 1));
    this.board = new State[dimension][dimension];

    if (sRow >= board.length || sCol >= board.length || sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      //represents the total size of the board side
      this.score = 0;
      fillBoard(sRow, sCol, armThickness);
    }
  }

  /**
   * Fills the board with the States corresponding to the correct game pieces for each space.
   * -A space is "fillable" if it can contain an EMPTY or FILLED State.
   * -Fillable spaces are from index armThickness - 1 to (armThickness + (armThickness - 1) -1).
   * -All but one "fillable" space are FILLED upon construction of a game, one space is EMPTY and
   * is the original empty position specified upon construction (or the default).
   * -The spaces at a row or column position that are not "fillable" are either:
   * INVALID: top or bottom left corners where no moves can be made to or from.
   * IGNORED: the spaces left over after there are no more "fillable" positions, the top or bottom
   * right corners where no moves can be made to or from.
   * -When a space is filled with a marble, this marble is added to the score, which starts as the
   * number of marbles in the game.VARIES FOR EACH BOARD
   *
   * @param sRow         Row containing the starting empty space.
   * @param sCol         Column containing the starting empty space.
   * @param armThickness The number of marbles in the top row.
   */
  @Override
  protected void fillBoard(int sRow, int sCol, int armThickness) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board.length; col++) {

        //the maximum possible position of a marble in a row with ignored spaces
        int maxFill = (armThickness + (armThickness - 1) - 1);

        //the minimum possible position of a marble in a row with invalid spaces
        int minFill = armThickness - 1;

        //filling empty space with empty State
        if (row == sRow && col == sCol) {
          //row >= minFill && row < maxFill && col >= minFill && col < maxFill
          if (row >= minFill && row <= maxFill
              || row < minFill && col >= minFill && col <= maxFill
              || row > maxFill && col >= minFill && col <= maxFill) {
            board[row][col] = State.EMPTY;
          } else {
            throw new IllegalArgumentException("Cannot place empty value at invalid position");
          }
        }

        //invalid top left corner spaces, filled with INVALID State
        else if (row < minFill && col < minFill) {
          board[row][col] = State.INVALID;
        }

        //invalid bottom left corner spaces, filled with INVALID State
        else if (row > maxFill && col < minFill) {
          board[row][col] = State.INVALID;
        }

        //invalid top right corner spaces, filled with IGNORED State
        else if (row < minFill && col > maxFill) {
          board[row][col] = State.IGNORED;
        }

        //invalid bottom right corner spaces, filled with IGNORED State
        else if (row > maxFill && col > maxFill) {
          board[row][col] = State.IGNORED;
        }

        //filling all other spaces with marbles
        else {
          board[row][col] = State.FILLED;
          this.score++;
        }
      }
    }
  }
}
