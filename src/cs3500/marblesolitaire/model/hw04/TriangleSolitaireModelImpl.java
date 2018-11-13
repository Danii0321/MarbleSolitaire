package cs3500.marblesolitaire.model.hw04;


import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class represents the operations offered by the Triangular marble solitaire
 * model. One object of the model represents one game of Triangular marble solitaire.
 */
public class TriangleSolitaireModelImpl extends AbstractSolitaireModelImpl {
  /**
   * Constructor 1: No parameters passed in.
   * Default board has arm thickness of 3, empty space in center.
   */
  public TriangleSolitaireModelImpl() {
    //(row, col)
    this(5, 0, 0);
  }

  /**
   * Constructor 2: Takes in sRow and sCol parameters
   * which specify the starting position of the empty slot.
   *
   * @param sRow Row containing the starting empty space.
   * @param sCol Column containing the starting empty space.
   * @throws IllegalArgumentException Thrown if sCol or sRow are out of bounds.
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * Constructor 3: Takes in arm thickness as a parameter and sets up the board with the default
   * empty position in the center.
   *
   * @param armThickness The number of marbles in the top row.
   * @throws IllegalArgumentException if armThickness is not a a non-negative odd number.
   */
  public TriangleSolitaireModelImpl(int armThickness) {

    this(armThickness, 0, 0);
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
  public TriangleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    //does not need to have an odd armthickness, dimention is same as armthickness
    if (armThickness <= 0) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }

    //represents the total size of the board side
    this.score = 0;
    this.board = new State[armThickness][armThickness];

    //checks that the empty position is within the board bounds
    if (sRow >= board.length || sCol >= board.length || sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      //fills the board according to the octagonal shape
      fillBoard(sRow, sCol, armThickness);
    }
  }

  @Override
  public String getGameState() {
    ArrayList<String> rows = new ArrayList<>();
    //ArrayList<String> pieces = new ArrayList<String>;

    //getting a String representing each row
    for (int i = 0; i < board.length; i++) {
      String thisRow = this.rowToString(board[i]);
      for (int j = 0; j < (board.length - i - 1); j++) {
        thisRow = " " + thisRow;
      }
      rows.add(thisRow);
    }

    //adding all strings together with \n
    String gameState = rows.stream().collect(Collectors.joining("\n"));
    return gameState;

    //System.out.println(rows);
    //return "";
  }


  /*
    Checks if the move is one of six possibilities:
    -two diagonally up to the left
    -two diagonally up to the right
    -two left
    -two right
    -two diagonally down to the left
    -two diagonally down to the right

    returns a boolean representing if the move is in a valid direction or not
   */
  @Override
  protected boolean isValidDirection(int fromRow, int fromCol, int toRow, int toCol) {
    //checks that move is uo down left or right by 2 places
    //same for euro model
    return (Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 2
        || Math.abs(toRow - fromRow) == 2 && toCol == fromCol
        || fromRow == toRow && Math.abs(toCol - fromCol) == 2);
  }

  /*
     Triangle can have 6 possible valid moves rather than just 4:
     -upper left
     -upper right
     -left
     -right
     -lower left
     -lower right

     @param row the row of the space being checked.
     @param col the column of the space being checked.
     @return boolean representing if move is valid or not
   */
  @Override
  protected boolean existsValidMove(int row, int col) {
    //upper left
    try {
      if (isValidMove(row, col, row - 2, col - 2)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }

    //upper right
    try {
      if (isValidMove(row, col, row - 2, col + 2)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }

    //right
    try {
      if (isValidMove(row, col, row, col + 2)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }

    //left
    try {
      if (isValidMove(row, col, row, col - 2)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }

    //lower left
    try {
      if (isValidMove(row, col, row + 2, col - 2)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }

    //lower right
    try {
      if (isValidMove(row, col, row + 2, col + 2)) {
        return true;
      }
    } catch (IllegalArgumentException i) {
      //catch exception so boolean value must be returned
    }

    return false;
  }

  /*
     Fills board in triangular shape
     NumFilled represents the maximum filled space of the row
     incremented each row until there are n rows (where n is the armThickness)

     Throws IllegalArgumentException if the empty space is placed in an invalid position
     Increments score when a marble is added
   */
  @Override
  protected void fillBoard(int sRow, int sCol, int armThickness) {
    int numFilled = 0;

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board.length; col++) {
        if (col > numFilled) {
          if (row == sRow && col == sCol) {
            throw new IllegalArgumentException("Cannot place empty in invalid space");
          } else {
            board[row][col] = State.IGNORED;
          }
        } else if (row == sRow && col == sCol) {
          if (col <= numFilled) {
            board[row][col] = State.EMPTY;
          } else {
            throw new IllegalArgumentException("Cannot place empty in invalid space");
          }
        } else {
          board[row][col] = State.FILLED;
          this.score++;
        }
      }
      numFilled++;
    }
  }
}
