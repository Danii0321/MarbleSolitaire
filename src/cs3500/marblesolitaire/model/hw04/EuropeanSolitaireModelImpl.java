package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents the operations offered by the European marble solitaire
 * model. One object of the model represents one game of European marble solitaire.
 */
public class EuropeanSolitaireModelImpl extends AbstractSolitaireModelImpl {
  /**
   * Constructor 1: No parameters passed in.
   * Default board has arm thickness of 3, empty space in center.
   */
  public EuropeanSolitaireModelImpl() {
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
  public EuropeanSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor 3: Takes in arm thickness as a parameter and sets up the board with the default
   * empty position in the center.
   *
   * @param armThickness The number of marbles in the top row.
   * @throws IllegalArgumentException if armThickness is not a a non-negative odd number.
   */
  public EuropeanSolitaireModelImpl(int armThickness) {

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
  public EuropeanSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    //must have odd armthickness
    if (armThickness % 2 == 0 || armThickness < 1) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }

    //dimension remains the same as original model solitaire
    int dimension = (armThickness + 2 * (armThickness - 1));
    this.board = new State[dimension][dimension];

    //checks that the empty position is within the board bounds
    if (sRow >= board.length || sCol >= board.length || sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      //represents the total size of the board side
      this.score = 0;

      //fills the board according to the octagonal shape
      fillBoard(sRow, sCol, armThickness);

    }
  }

  /*
     Fills board according to European shape
     -Until the row number reaches the first fully filled row
     which is while the row < armThickness - 1, the valid fillable columns are from
     armThickness - row to armThickness + row
     -once the row = armThickness - 1, it is fully filled until
     (armThickness + (armThickness - 1) - 1)
     -if it is greater than this, valid fillable area is opposite of the first part.

     Throws IllegalArgumentException if the empty space is placed in an invalid position
     Increments score when a marble is added
   */
  @Override
  protected void fillBoard(int sRow, int sCol, int armThickness) {

    for (int row = 0; row < board.length; row++) {
      //represent the first and last valid, fillable spaces in a row
      int minFill = 0;
      int maxFill = armThickness + 2 * (armThickness - 1) - 1;
      for (int col = 0; col < board.length; col++) {

        //top section invalid or ignored spaces
        if (row < armThickness - 1) {
          minFill = (armThickness - 1) - row;
          maxFill = (armThickness + (armThickness - 1) - 1) + row;
        }

        //bottom section of invalid or ignored spaces
        else if (row > armThickness + (armThickness - 1) - 1) {
          //difference from last wide row
          //last wide row is at armThickness + (armThickness - 1) - 1
          minFill = row - ((armThickness + (armThickness - 1)) - 1);
          maxFill = (armThickness + 2 * (armThickness - 1) - 1) - minFill;
        }

        ///////////////////////////////////////////////////////////////////////
        //checking if empty can be placed, if so, places

        if (row == sRow && col == sCol) {
          if (col >= minFill && col <= maxFill) {
            board[row][col] = State.EMPTY;
          } else {
            throw new IllegalArgumentException("Cannot place empty value at invalid position");
          }
        }

        //area before valid spaces
        else if (col < minFill) {
          board[row][col] = State.INVALID;
        }
        //area after valid spaces
        else if (col > maxFill) {
          board[row][col] = State.IGNORED;
        }

        /*
        else if (row == sRow && col == sCol) {
          if ((board[row][col] != State.IGNORED && board[row][col] != State.INVALID)) {
            board[row][col] = State.EMPTY;
          } else {
            throw new IllegalArgumentException("Cannot place empty value at invalid position");
          }
        }
        */

        //all the rest are filled with marbles
        else {
          board[row][col] = State.FILLED;
          this.score++;
        }
      }
    }
  }
}
