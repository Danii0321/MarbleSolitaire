import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.io.StringReader;

/**
 * Tests for {@link cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl}.
 * and
 * Tests for {@link cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl}.
 */
public class MarbleSolitaireTests {

  //ENGLISH SOLITAIRE TESTS
  //Tests both getGameState and fillBoard in all constructors
  //TEST CONSTRUCTOR 1
  @Test
  public void constructor1Test1() {
    String answer1 = new String("    O O O\n" + "    O O O\n" + "O O O O O O O\n"
        + "O O O _ O O O\n" + "O O O O O O O\n" + "    O O O\n" + "    O O O");
    MarbleSolitaireModelImpl m1 = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //tests that a null value is not passed

  //TEST CONSTRUCTOR 2
  //tests moving the empty space in the board to a valid space
  @Test
  public void constructor2Test1() {
    String answer2 = new String("    O O O\n" + "    O O O\n" + "O O _ O O O O\n"
        + "O O O O O O O\n" + "O O O O O O O\n" + "    O O O\n" + "    O O O");

    MarbleSolitaireModelImpl m2 =
        new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(2, 2);
    Assert.assertEquals(answer2, m2.getGameState());
  }

  //Tests moving the empty space to a valid edge of the board
  @Test
  public void constructor2Test2() {
    String answer3 = new String("    O O O\n" + "    O O O\n" + "O O O O O O O\n"
        + "O O O O O O O\n" + "O O O O O O O\n" + "    O O O\n" + "    O _ O");

    MarbleSolitaireModelImpl m3 =
        new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(6, 3);
    Assert.assertEquals(answer3, m3.getGameState());
  }


  //makes sure an error is thrown if the sRow is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void constructor2RowOutOfBounds() {
    try {
      MarbleSolitaireModelImpl m4 =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(10, 3);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (10,3)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");

  }

  //makes sure an error is thrown if the sCol is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void constructor2ColOutOfBounds() {
    try {
      MarbleSolitaireModelImpl m5 =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(2, 30);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (2,30)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //makes sure an error is thrown if the sRow and sCol are out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void constructor2RowAndColOutOfBounds() {
    try {
      MarbleSolitaireModelImpl m6 =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(20, 20);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (20,20)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //makes sure empty space cannot be placed in an INVALID space
  @Test(expected = IllegalArgumentException.class)
  public void constructor2InvalidEmpty() {
    try {
      MarbleSolitaireModelImpl m6 =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(0, 0);
    } catch (IllegalArgumentException i) {
      String message = "Cannot place empty value at invalid position";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //makes sure empty space cannot be placed in an IGNORED space
  @Test(expected = IllegalArgumentException.class)
  public void constructor2InvalidEmpty2() {
    try {
      MarbleSolitaireModelImpl m6 =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(6, 6);
    } catch (IllegalArgumentException i) {
      String message = "Cannot place empty value at invalid position";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //tests increasing the board size
  //TEST CONSTRUCTOR 3
  @Test
  public void constructor3Test1() {
    String answer7 = new String("        O O O O O\n" + "        O O O O O\n"
        + "        O O O O O\n" + "        O O O O O\n" + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n" + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n" + "        O O O O O\n" + "        O O O O O\n"
        + "        O O O O O");

    MarbleSolitaireModelImpl m7 =
        new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(5);
    Assert.assertEquals(answer7, m7.getGameState());
  }

  //given negative arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void constructor3Test2() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(-1);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //given arm thickness of zero
  @Test(expected = IllegalArgumentException.class)
  public void constructor3Test3() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(0);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //given an even arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void constructor3Test4() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(4);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }


  //TEST CONSTRUCTOR 4
  //tests increasing board size and moving empty space, both valid
  @Test
  public void constructor4Test1() {
    String answer = new String("        O O O O O\n" + "        O O O O O\n"
        + "        O O O O O\n" + "        O O O O O\n" + "O O O O _ O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n" + "        O O O O O\n" + "        O O O O O\n"
        + "        O O O O O");

    MarbleSolitaireModelImpl m =
        new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(5, 4,
            4);
    Assert.assertEquals(answer, m.getGameState());
  }

  //given arm thickness of zero
  @Test(expected = IllegalArgumentException.class)
  public void constructor4Test2() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(0, 4,
              4);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //given even arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void constructor4Test3() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(4, 4,
              4);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //given valid arm thickness with invalid row and valid col
  @Test(expected = IllegalArgumentException.class)
  public void constructor4Test4() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(3, 10,
              5);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (10,5)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //given valid arm thickness with valid row and invalid col
  @Test(expected = IllegalArgumentException.class)
  public void constructor4Test5() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(3, 4,
              50);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (4,50)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //given valid arm thickness with invalid row and col
  @Test(expected = IllegalArgumentException.class)
  public void constructor4Test6() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(3, 10,
              50);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (10,50)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //test armThickness of 3, with center moved to 0, 2 - edge
  @Test
  public void constructor4Test7() {
    String expected = new String("    _ O O\n" + "    O O O\n" + "O O O O O O O\n"
        + "O O O O O O O\n" + "O O O O O O O\n" + "    O O O\n" + "    O O O");

    MarbleSolitaireModelImpl m =
        new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(3, 0,
            2);

    Assert.assertEquals(expected, m.getGameState());
  }


  //TESTING MOVE
  //move up from center space
  @Test
  public void moveUp() {
    String afterMove = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O");
    MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
    m.move(5, 3, 3, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  //move down from center space
  @Test
  public void moveDown() {
    String afterMove = new String("    O O O\n"
        + "    O _ O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O");

    MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
    m.move(1, 3, 3, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  //move left from center space
  @Test
  public void moveLeft() {
    String afterMove = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O");
    MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
    m.move(3, 5, 3, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  //move right from center space
  @Test
  public void moveRight() {
    String afterMove = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O");
    MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
    m.move(3, 1, 3, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  //makes sure move still works on valid arm thicknesses
  @Test
  public void moveValidOnBiggerBoard() {
    /*
    String before = new String("        O O O O O\n"
                                     + "        O O O O O\n"
                                     + "        O O O O O\n"
                                     + "        O O O O O\n"
                                     + "O O O O O O O O O O O O O\n"
                                     + "O O O O O O O O O O O O O\n"
                                     + "O O O O O O _ O O O O O O\n"
                                     + "O O O O O O O O O O O O O\n"
                                     + "O O O O O O O O O O O O O\n"
                                     + "        O O O O O\n"
                                     + "        O O O O O\n"
                                     + "        O O O O O\n"
                                     + "        O O O O O");
   */

    String after = new String("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O");

    MarbleSolitaireModelImpl m =
        new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(5, 6,
            6);
    m.move(4, 6, 6, 6);
    Assert.assertEquals(after, m.getGameState());

  }

  //tests a move given: an empty space to move from - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void fromEmptySpace() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
      m.move(3, 3, 1, 3);
    } catch (IllegalArgumentException i) {
      String message = "From position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given: a filled space to move to - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void toFilledSpace() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
      m.move(3, 0, 3, 2);
    } catch (IllegalArgumentException i) {
      String message = "To position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given an invalid state space to move from - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void FromInvalidSpace() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
      m.move(5, 0, 3, 0);
    } catch (IllegalArgumentException i) {
      String message = "From position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given an invalid state space to move to - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void toInvalidSpace() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
      m.move(1, 2, 1, 0);
    } catch (IllegalArgumentException i) {
      String message = "To position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given a space too far away to move to - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void tooFarMove() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
      m.move(3, 0, 3, 3);
    } catch (IllegalArgumentException i) {
      String message = "Must move 2 spaces up, down, left, or right";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a diagonal move
  @Test(expected = IllegalArgumentException.class)
  public void noDiagonalMove() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
      //correct move to free up space
      m.move(1, 3, 3, 3);
      //correct move to free up space
      m.move(2, 1, 2, 3);
      //attempted diagonal move
      m.move(0, 4, 2, 2);
    } catch (IllegalArgumentException i) {
      String message = "Must move 2 spaces up, down, left, or right";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given an out of bounds space to move from - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void outOfBoundsFrom() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
      m.move(-1, -2, 1, 0);
    } catch (IllegalArgumentException i) {
      String message = "From or to position is out of bounds";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given an out of bounds space to move to - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void outOfBoundsTo() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
      m.move(3, 3, 30, 30);
    } catch (IllegalArgumentException i) {
      String message = "From or to position is out of bounds";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //trying to jump over an empty space
  @Test(expected = IllegalArgumentException.class)
  public void jumpOverEmpty() {
    try {
      MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();

      //moving right
      m.move(3, 1, 3, 3);
      /*
      "    O O O\n"
      "    O O O\n"
      "O O O O O O O\n"
      "O _ _ O O O O\n"
      "O O O O O O O\n"
      "    O O O\n"
      "    O O O");
      */

      //jumping over an empty space via a right move
      m.move(3, 0, 3, 2);
    } catch (IllegalArgumentException i) {
      String message = "Cannot jump over empty space";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
  }

  //makes sure move still fails when it should on any valid arm thickness
  //problem - asked to jump over empty space
  @Test(expected = IllegalArgumentException.class)
  public void jumpOverEmpty2() {
    try {
      MarbleSolitaireModelImpl m =
          new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(5, 6,
              6);
      m.move(4, 6, 6, 6);
      m.move(3, 6, 5, 6);
    } catch (IllegalArgumentException i) {
      String message = "Cannot jump over empty space";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
  }

  //TESTING GETSCORE
  //all invalid moves would be caught earlier
  //makes one valid move, score should decrease by 1
  @Test
  public void getScoreTest1() {
    MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
    m.move(1, 3, 3, 3);

    Assert.assertEquals(31, m.getScore());
  }

  //makes two valid moves, score should decrease by 2
  @Test
  public void getScoreTest2() {
    MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
    m.move(1, 3, 3, 3);
    m.move(2, 1, 2, 3);

    Assert.assertEquals(30, m.getScore());
  }

  //TESTING isGameOver
  //given a game that is not over
  @Test
  public void isGameOver1() {
    MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();

    Assert.assertEquals(false, m.isGameOver());
  }


  // give a game that is over
  // completed perfect game, no more moves exist
  @Test
  public void isGameOverTrue() {
    MarbleSolitaireModelImpl m = new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl();
    m.move(5, 3, 3, 3);
    m.move(4, 5, 4, 3);
    m.move(6, 4, 4, 4);
    m.move(6, 2, 6, 4);
    m.move(3, 4, 5, 4);
    m.move(6, 4, 4, 4);
    m.move(1, 4, 3, 4);
    m.move(2, 6, 2, 4);
    m.move(4, 6, 2, 6);
    m.move(2, 3, 2, 5);
    m.move(2, 6, 2, 4);
    m.move(2, 1, 2, 3);
    m.move(0, 2, 2, 2);
    m.move(0, 4, 0, 2);
    m.move(3, 2, 1, 2);
    m.move(0, 2, 2, 2);
    m.move(5, 2, 3, 2);
    m.move(4, 0, 4, 2);
    m.move(2, 0, 4, 0);
    m.move(4, 3, 4, 1);
    m.move(4, 0, 4, 2);
    m.move(2, 3, 2, 1);
    m.move(2, 1, 4, 1);
    m.move(4, 1, 4, 3);
    m.move(4, 3, 4, 5);
    m.move(4, 5, 2, 5);
    m.move(2, 5, 2, 3);
    m.move(3, 3, 3, 5);
    m.move(1, 3, 3, 3);
    m.move(3, 2, 3, 4);
    m.move(3, 5, 3, 3);
    Assert.assertEquals(true, m.isGameOver());
  }


  //////////////////////////////////////////////////////////////////////////////////////////////////

  //CONTROLLER TESTS

  //CONSTRUCTOR TESTS
  //Tests constructor by trying to pass null readable and appendable to constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullReadableAndAppendable() {
    Readable read = null;
    Appendable app = null;
    try {
      MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    } catch (IllegalArgumentException i) {
      String message = "One or more element is null";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid input message did not throw!");
  }

  //Tests constructor by trying to pass null readable and valid appendable to constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullReadable() {
    Readable read = null;
    Appendable app = new StringBuffer();
    try {
      MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    } catch (IllegalArgumentException i) {
      String message = "One or more element is null";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid input message did not throw!");
  }

  //Tests constructor by trying to pass a valid readable and null appendable to constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullAppendable() {
    Readable read = new StringReader("4 6 4 4 q");
    Appendable app = null;
    try {
      MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    } catch (IllegalArgumentException i) {
      String message = "One or more element is null";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid input message did not throw!");
  }

  //PLAYGAME TESTS
  //Tests playGame by trying a valid move with default constructor for model
  @Test
  public void testPlayGameValid1() {
    Readable read = new StringReader("4 6 4 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);
    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //Tests playGame by trying a valid move with default constructor, different direction
  @Test
  public void testPlayGameValid2() {
    Readable read = new StringReader("2 4 4 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);
    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //Tests playGame by trying a valid move in a larger board - different constructor
  @Test
  public void testPlayGameBiggerBoard() {
    Readable read = new StringReader("5 7 7 7 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mModel =
        new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(5, 6,
            6);
    m.playGame(mModel);
    String after = new String("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 104\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 103\n");

    Assert.assertEquals(after, app.toString());
  }

  //Tests playGame when passed a null model
  @Test(expected = IllegalArgumentException.class)
  public void testPlayGamePassedNullModel() {
    Readable read = new StringReader("4 6 4 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = null;

    try {
      m.playGame(mmodel);
    } catch (IllegalArgumentException i) {
      String message = "Error: null model";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid input message did not throw!");
  }

  //Tests playGame by trying a valid move without winning or quitting --> error
  @Test(expected = IllegalStateException.class)
  public void testPlayGameNoQuitOrGameOver() {
    Readable read = new StringReader("4 6 4 4");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    try {
      m.playGame(mmodel);
    } catch (IllegalStateException i) {
      String message = "Must quit or finish game\n";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid input message did not throw!");
  }

  //Tests playGame by trying multiple valid moves
  @Test
  public void testPlayGameMultipleMoves() {
    Readable read = new StringReader("2 4 4 4 3 2 3 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 30\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //TESTING WITH VALID AND INVALID MOVES
  //Tests playGame by trying one valid move and one invalid move before quitting
  @Test
  public void testPlayGameMultipleMovesWithInvalid1() {
    Readable read = new StringReader("2 4 4 4 6 1 5 2 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. From position is invalid\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //Tests playGame by trying invalid move between valid moves before quitting
  @Test
  public void testPlayGameMultipleMovesWithInvalid2() {
    Readable read = new StringReader("2 4 4 4 6 1 5 2 3 2 3 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. From position is invalid\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 30\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //Tests playGame by trying valid move between invalid moves
  @Test
  public void testPlayGameMultipleMovesWithInvalid3() {
    Readable read = new StringReader("1 1 3 1 6 4 4 4 2 3 2 1 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. From position is invalid\n"
            + "Invalid move. Play again. To position is invalid\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 31\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //TESTING BAD INPUTS
  //Tests playGame given bad input between proper moves
  @Test
  public void testBadInputAmongValid() {
    Readable read = new StringReader("2 4 4 4 l 3 2 3 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String output = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Please enter value again.\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 30\n");

    Assert.assertEquals(output, app.toString());

  }

  //Tests playGame given bad input - a string without numbers that is not a quit
  @Test
  public void testBadInputString() {
    Readable read = new StringReader("hi q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Please enter value again.\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n");

    Assert.assertEquals(output, app.toString());
  }

  //Tests playGame given negative numbers
  @Test
  public void testBadInputNegativeInt() {
    Readable read = new StringReader("-1 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Please enter value again.\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }


  //TESTING QUITTING BEHAVIOR IN PLAYGAME
  //Tests playGame by trying to quit with "q"
  @Test
  public void testQuitLowercase() {
    Readable read = new StringReader("q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //Tests playGame by trying to quit with "Q"
  @Test
  public void testQuitUppercase() {
    Readable read = new StringReader("Q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //Tests playGame by trying to quit multiple times
  @Test
  public void testQuitMultipleTimes() {
    Readable read = new StringReader("Q Q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //quits in fromRow
  @Test
  public void testQuitFromRow() {
    Readable read = new StringReader("q 2 4 4");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n");
    Assert.assertEquals(afterMove, app.toString());
  }

  //quits in fromCol
  @Test
  public void testQuitFromCol() {
    Readable read = new StringReader("4 q 4 4");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //quits in toRow
  @Test
  public void testQuitToRow() {
    Readable read = new StringReader("4 2 q 4");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //quits in toCol
  @Test
  public void testQuitToCol() {
    Readable read = new StringReader("4 2 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //Tests playGame by trying to quit among valid input
  @Test
  public void testQuitAmongValidInput() {
    Readable read = new StringReader("4 q 2 4 4");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //Tests playGame by trying to quit among invalid input
  @Test
  public void testQuitAmongInvalidInput() {
    Readable read = new StringReader("hello Q how are you");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    m.playGame(mmodel);

    String afterMove = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Please enter value again.\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n");

    Assert.assertEquals(afterMove, app.toString());
  }

  //TESTING WITH INVALID MOVES CAUGHT IN MODEL
  //test playGame with an invalid move - out of bounds
  @Test
  public void testInvalidMoveOutOfBounds() {
    Readable read = new StringReader("20 30 50 60 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Invalid move. Play again. From or to position is out of bounds\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n");

    Assert.assertEquals(output, app.toString());
  }

  //test playGame with an invalid move - from an invalid space
  @Test
  public void testInvalidMoveFromInvalid() {
    Readable read = new StringReader("1 1 1 3 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Invalid move. Play again. From position is invalid\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n");

    Assert.assertEquals(output, app.toString());
  }

  //test playGame with an invalid move - to an invalid space
  @Test
  public void testInvalidMoveToInvalid() {
    Readable read = new StringReader("2 3 2 1 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Invalid move. Play again. To position is invalid\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n");

    Assert.assertEquals(output, app.toString());
  }

  //test playGame with an invalid move - trying to jump over empty
  @Test
  public void testInvalidJumpOverEmpty() {
    Readable read = new StringReader("6 4 4 4 7 4 5 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Invalid move. Play again. Cannot jump over empty space\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n");

    Assert.assertEquals(output, app.toString());
  }


  //Tests playGame given an invalid move - given a move that it too far
  @Test
  public void testInvalidMoveTooFar() {
    Readable read = new StringReader("1 4 4 4 q");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Invalid move. Play again. Must move 2 spaces up, down, left, or right\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n");

    Assert.assertEquals(output, app.toString());
  }

  //TESTING GAMEOVER BEHAVIOR
  //Test playGame given a complete sequence of moves that wins the game
  @Test
  public void testGameOverWon() {
    Readable read = new StringReader("6 4 4 4 "
        + "5 6 5 4 "
        + "7 5 5 5 "
        + "7 3 7 5 "
        + "4 5 6 5 "
        + "7 5 5 5 "
        + "2 5 4 5 "
        + "3 7 3 5 "
        + "5 7 3 7 "
        + "3 4 3 6 "
        + "3 7 3 5 "
        + "3 2 3 4 "
        + "1 3 3 3 "
        + "1 5 1 3 "
        + "4 3 2 3 "
        + "1 3 3 3 "
        + "6 3 4 3 "
        + "5 1 5 3 "
        + "3 1 5 1 "
        + "5 4 5 2 "
        + "5 1 5 3 "
        + "3 4 3 2 "
        + "3 2 5 2 "
        + "5 2 5 4 "
        + "5 4 5 6 "
        + "5 6 3 6 "
        + "3 6 3 4 "
        + "4 4 4 6 "
        + "2 4 4 4 "
        + "4 3 4 5 "
        + "4 6 4 4 ");
    Appendable app = new StringBuffer();

    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game over!\n"
        + "    _ _ _\n"
        + "    _ _ _\n"
        + "_ _ _ _ _ _ _\n"
        + "_ _ _ O _ _ _\n"
        + "_ _ _ _ _ _ _\n"
        + "    _ _ _\n"
        + "    _ _ _\n"
        + "Score: 1\n");

    Assert.assertEquals(output, app.toString());
  }

  //Test playGame given a sequence of moves that ends with no more possible moves
  @Test
  public void testGameOverNoMoreMoves() {
    Readable read = new StringReader("6 4 4 4 "
        + "3 4 5 4 "
        + "4 2 4 4 "
        + "4 5 4 3 "
        + "6 5 4 5 "
        + "5 7 5 5 "
        + "1 4 3 4 "
        + "4 6 4 4 "
        + "2 5 4 5 "
        + "5 5 3 5 "
        + "4 4 4 2 "
        + "2 3 4 3 "
        + "3 5 3 3 "
        + "3 7 3 5 "
        + "4 3 2 3 "
        + "1 3 3 3 "
        + "6 3 4 3 "
        + "5 1 5 3 "
        + "3 1 5 1 "
        + "3 2 5 2 "
        + "5 3 5 5 "
        + "3 3 5 3 "
        + "5 2 5 4 "
        + "5 5 5 3");
    Appendable app = new StringBuffer();
    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game over!\n"
        + "    _ _ O\n"
        + "    _ _ _\n"
        + "_ _ _ _ O _ _\n"
        + "_ _ _ _ _ _ O\n"
        + "O _ O _ _ _ _\n"
        + "    _ _ _\n"
        + "    O O O\n"
        + "Score: 8\n");

    Assert.assertEquals(output, app.toString());
  }

  //Test playGame given a sequence of moves that ends with no more possible moves, with
  //a invalid character after
  @Test
  public void testGameOverNoMoreMoves2() {
    Readable read = new StringReader("6 4 4 4 "
        + "3 4 5 4 "
        + "4 2 4 4 "
        + "4 5 4 3 "
        + "6 5 4 5 "
        + "5 7 5 5 "
        + "1 4 3 4 "
        + "4 6 4 4 "
        + "2 5 4 5 "
        + "5 5 3 5 "
        + "4 4 4 2 "
        + "2 3 4 3 "
        + "3 5 3 3 "
        + "3 7 3 5 "
        + "4 3 2 3 "
        + "1 3 3 3 "
        + "6 3 4 3 "
        + "5 1 5 3 "
        + "3 1 5 1 "
        + "3 2 5 2 "
        + "5 3 5 5 "
        + "3 3 5 3 "
        + "5 2 5 4 "
        + "5 5 5 3 l");
    Appendable app = new StringBuffer();
    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game over!\n"
        + "    _ _ O\n"
        + "    _ _ _\n"
        + "_ _ _ _ O _ _\n"
        + "_ _ _ _ _ _ O\n"
        + "O _ O _ _ _ _\n"
        + "    _ _ _\n"
        + "    O O O\n"
        + "Score: 8\n");

    Assert.assertEquals(output, app.toString());
  }

  //Test playGame given a sequence of moves that ends with no more possible moves, with
  //another move after
  @Test
  public void testGameOverNoMoreMoves3() {
    Readable read = new StringReader("6 4 4 4 "
        + "3 4 5 4 "
        + "4 2 4 4 "
        + "4 5 4 3 "
        + "6 5 4 5 "
        + "5 7 5 5 "
        + "1 4 3 4 "
        + "4 6 4 4 "
        + "2 5 4 5 "
        + "5 5 3 5 "
        + "4 4 4 2 "
        + "2 3 4 3 "
        + "3 5 3 3 "
        + "3 7 3 5 "
        + "4 3 2 3 "
        + "1 3 3 3 "
        + "6 3 4 3 "
        + "5 1 5 3 "
        + "3 1 5 1 "
        + "3 2 5 2 "
        + "5 3 5 5 "
        + "3 3 5 3 "
        + "5 2 5 4 "
        + "5 5 5 3 "
        + "1 1 1 3");
    Appendable app = new StringBuffer();
    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();

    m.playGame(mmodel);

    String output = new String("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Game over!\n"
        + "    _ _ O\n"
        + "    _ _ _\n"
        + "_ _ _ _ O _ _\n"
        + "_ _ _ _ _ _ O\n"
        + "O _ O _ _ _ _\n"
        + "    _ _ _\n"
        + "    O O O\n"
        + "Score: 8\n");

    Assert.assertEquals(output, app.toString());
  }


  //TESTING IOEXCEPTION
  //Testing playGame given a bogus implementation of Appendable interface to throw an IOException
  @Test(expected = IOException.class)
  public void testTerribleAppendable1() throws IOException {

    //making a bogus implementation of Appendable interface for testing
    class TerribleAppendable implements Appendable {
      @Override
      public Appendable append(char c) {
        throw new IllegalArgumentException("IOException thrown");
      }

      @Override
      public Appendable append(CharSequence c) {
        throw new IllegalArgumentException("IOException thrown");
      }

      @Override
      public Appendable append(CharSequence c, int start, int end) {
        throw new IllegalArgumentException("IOException thrown");
      }
    }

    Readable read = new StringReader("1 4 4 4 q");
    Appendable app = new TerribleAppendable();
    MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
    MarbleSolitaireModelImpl mmodel = new MarbleSolitaireModelImpl();
    try {
      m.playGame(mmodel);
    } catch (IllegalArgumentException i) {
      throw new IOException();
    }
  }


  /////////////////////////////////////////////////////////////////////////////////////////////////
  //EUROPEAN MODEL TESTS

  //test constructor 1
  @Test
  public void euroConstructor1Test1() {
    String answer1 = new String("    O O O\n" + "  O O O O O\n" + "O O O O O O O\n"
        + "O O O _ O O O\n" + "O O O O O O O\n" + "  O O O O O\n" + "    O O O");
    EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl();

    Assert.assertEquals(answer1, m1.getGameState());

  }

  //test constructor 2 with valid sRow and sCol
  @Test
  public void euroConstructor2Test1() {
    String answer1 = new String("    O O O\n" + "  O O O O O\n" + "O O O O O O O\n"
        + "O O _ O O O O\n" + "O O O O O O O\n" + "  O O O O O\n" + "    O O O");
    EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(3, 2);

    Assert.assertEquals(answer1, m1.getGameState());
  }


  //makes sure an error is thrown if the sRow is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor2RowOutOfBounds() {
    try {
      EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(100, 2);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (100,2)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //makes sure an error is thrown if the sCol is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor2ColOutOfBounds() {
    try {
      EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(4, -1);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (4,-1)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //makes sure an error is thrown if the sRow and sCol are out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor2RowAndColOutOfBounds() {
    try {
      EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(20, 20);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (20,20)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //makes sure empty space cannot be placed in an INVALID space
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor2InvalidEmpty() {
    try {
      EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(0, 0);
    } catch (IllegalArgumentException i) {
      String message = "Cannot place empty value at invalid position";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //makes sure empty space cannot be placed in an IGNORED space
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor2InvalidEmpty2() {
    try {
      EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(1, 6);
    } catch (IllegalArgumentException i) {
      String message = "Cannot place empty value at invalid position";
      Assert.assertEquals(message, i.getMessage());
      throw i;

    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //make sure empty space can be placed on an edge
  @Test
  public void euroConstructor2EmptyOnEdge1() {
    String answer = new String("    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O _\n"
        + "    O O O");

    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl(5, 5);
    Assert.assertEquals(answer, m.getGameState());

  }

  //test constructor 3 with valid arm thickness
  @Test
  public void euroConstructor3Test1() {
    String answer1 = new String(
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O");

    EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(5);

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //try to construct given invalid armThickness - must be odd
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor3TestBadArmThickness1() {
    try {
      EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(4);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }


  //given negative arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor3TestBadArmThickness2() {
    try {
      EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(0);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //given arm thickness of zero
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor3TestBadArmThickness3() {
    try {
      EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(-2);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }


  //test constructor 4 with all valid arguments
  @Test
  public void euroConstructor4Test1() {
    String answer1 = new String(
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O _ O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O");

    EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(5, 4, 8);

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //test constructor 4 with all valid arguments and empty space on edge
  @Test
  public void euroConstructor4TestEdge() {
    String answer1 = new String(
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O _\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O");

    EuropeanSolitaireModelImpl m1 =
        new EuropeanSolitaireModelImpl(5, 2, 10);

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //test constructor 4 with invalid armThickness and valid row and col
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor4TestBadArmThickness1() {
    try {
      EuropeanSolitaireModelImpl m1 =
          new EuropeanSolitaireModelImpl(4, 4, 4);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //test constructor 4 with invalid row
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor4TestBadRow1() {
    try {
      EuropeanSolitaireModelImpl m1 =
          new EuropeanSolitaireModelImpl(5, -1, 4);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (-1,4)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //test constructor 4 with invalid col
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor4TestBadCol1() {
    try {
      EuropeanSolitaireModelImpl m1 =
          new EuropeanSolitaireModelImpl(5, 3, 15);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (3,15)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //test constructor 4 with invalid row and col
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor4TestBadRowAndCol1() {
    try {
      EuropeanSolitaireModelImpl m1 =
          new EuropeanSolitaireModelImpl(5, 80, 60);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (80,60)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //test constructor 4 with invalid armThickness, row, and col
  @Test(expected = IllegalArgumentException.class)
  public void euroConstructor4TestBadArmThicknessRowAndCol1() {
    try {
      EuropeanSolitaireModelImpl m1 =
          new EuropeanSolitaireModelImpl(6, 80, 60);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //TESTING MOVE

  //valid move up
  @Test
  public void euroMoveUp() {
    String afterMove = new String("    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "  O O _ O O\n"
        + "    O O O");

    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    m.move(5, 3, 3, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  //valid move down
  @Test
  public void euroMoveDown() {
    String afterMove = new String("    O O O\n"
        + "  O O _ O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O");

    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    m.move(1, 3, 3, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  //valid move left
  @Test
  public void euroMoveLeft() {
    String afterMove = new String("    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O");

    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    m.move(3, 5, 3, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  //valid move right
  @Test
  public void euroMoveRight() {
    String afterMove = new String("    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O");

    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    m.move(3, 1, 3, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  @Test
  public void euroTestMoveOnBiggerBoard() {
    String answer1 = new String(
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O");

    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl(5);
    m.move(8, 6, 6, 6);
    Assert.assertEquals(answer1, m.getGameState());
  }

  //tests a move given: a filled space to move to - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void euroToFilledSpace() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
      m.move(3, 0, 3, 2);
    } catch (IllegalArgumentException i) {
      String message = "To position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given an invalid state space to move from - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void euroToInvalidSpace() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
      m.move(1, 2, 1, 0);
    } catch (IllegalArgumentException i) {
      String message = "To position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given an invalid state space to move from - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void euroFromInvalidSpace() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
      m.move(5, 0, 3, 0);
    } catch (IllegalArgumentException i) {
      String message = "From position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move that is too far
  @Test(expected = IllegalArgumentException.class)
  public void euroTooFarMove() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
      m.move(3, 0, 3, 3);
    } catch (IllegalArgumentException i) {
      String message = "Must move 2 spaces up, down, left, or right";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a diagonal move
  @Test(expected = IllegalArgumentException.class)
  public void euroNoDiagonalMove() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
      //valid moves
      m.move(1, 3, 3, 3);
      m.move(2, 1, 2, 3);

      //diagonal move attempted
      m.move(0, 4, 2, 2);

    } catch (IllegalArgumentException i) {
      String message = "Must move 2 spaces up, down, left, or right";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given an out of bounds space to move from - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void euroOutOfBoundsFrom() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
      m.move(-1, -2, 1, 0);

    } catch (IllegalArgumentException i) {
      String message = "From or to position is out of bounds";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //tests a move given an out of bounds space to move to - ERROR
  @Test(expected = IllegalArgumentException.class)
  public void euroOutOfBoundsTo() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
      m.move(3, 5, 3, 7);

    } catch (IllegalArgumentException i) {
      String message = "From or to position is out of bounds";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //trying to jump over an empty space
  @Test(expected = IllegalArgumentException.class)
  public void euroJumpOverEmpty() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
      m.move(1, 3, 3, 3);
      m.move(0, 3, 2, 3);

    } catch (IllegalArgumentException i) {
      String message = "Cannot jump over empty space";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //trying to jump over an empty space in large
  @Test(expected = IllegalArgumentException.class)
  public void euroJumpOverEmpty2() {
    try {
      EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl(5, 6,
          6);
      //valid move
      m.move(4, 6, 6, 6);
      //test move
      m.move(3, 6, 5, 6);

    } catch (IllegalArgumentException i) {
      String message = "Cannot jump over empty space";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //makes sure moving from newly added spaces is valid
  @Test
  public void euroToNewSpace() {
    String afterMove = new String("    O O O\n"
        + "  _ _ O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O");

    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    m.move(1, 3, 3, 3);
    m.move(1, 1, 1, 3);
    Assert.assertEquals(afterMove, m.getGameState());
  }

  //makes sure moving to newly added spaces is valid
  @Test
  public void euroFromNewSpace() {
    String afterMove = new String("    O O O\n"
        + "  O O _ _ O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O");

    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl(1, 5);
    m.move(1, 3, 1, 5);

    Assert.assertEquals(afterMove, m.getGameState());
  }

  //TESTING GETSCORE
  //all invalid moves would be caught earlier
  //makes one valid move, score should decrease by 1
  @Test
  public void euroTestGetScore() {
    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    m.move(1, 3, 3, 3);
    Assert.assertEquals(35, m.getScore());
  }

  @Test
  public void euroTestGetScore2() {
    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    m.move(1, 3, 3, 3);
    m.move(2, 1, 2, 3);
    m.move(0, 2, 2, 2);

    Assert.assertEquals(33, m.getScore());
  }

  //TEST ISGAMEOVER
  //test with original board, no moves
  @Test
  public void euroTestIsGameOver1() {
    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    Assert.assertEquals(false, m.isGameOver());
  }

  //test with several valid moves, but not a completed game
  @Test
  public void euroTestIsGameOver2() {
    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl();
    m.move(1, 3, 3, 3);
    m.move(2, 1, 2, 3);
    m.move(0, 2, 2, 2);

    Assert.assertEquals(false, m.isGameOver());
  }

  //test with a game that is over
  @Test
  public void euroTestIsGameOver3() {
    EuropeanSolitaireModelImpl m = new EuropeanSolitaireModelImpl(2, 6);
    m.move(4, 6, 2, 6);
    m.move(3, 4, 3, 6);
    m.move(2, 6, 4, 6);
    m.move(1, 5, 3, 5);
    m.move(4, 5, 2, 5);
    m.move(5, 4, 3, 4);
    m.move(2, 4, 4, 4);
    m.move(0, 4, 2, 4);
    m.move(2, 4, 2, 6);
    m.move(0, 2, 0, 4);
    m.move(2, 3, 0, 3);
    m.move(2, 2, 0, 2);
    m.move(2, 0, 2, 2);
    m.move(3, 2, 3, 4);
    m.move(3, 4, 5, 4);
    m.move(3, 0, 3, 2);
    m.move(3, 2, 1, 2);
    m.move(0, 2, 2, 2);
    m.move(0, 4, 0, 2);
    m.move(5, 1, 3, 1);
    m.move(6, 4, 4, 4);
    m.move(6, 2, 6, 4);
    m.move(4, 2, 6, 2);
    m.move(4, 3, 6, 3);

    /*
      String afterMove = new String("    O _ _\n"
                                  + "  O _ _ _ _\n"
                                  + "_ _ O _ _ _ O\n"
                                  + "_ O _ _ _ _ _\n"
                                  + "O _ _ _ O _ O\n"
                                  + "  _ _ _ _ O\n"
                                  + "    O O O");

     */

    Assert.assertEquals(true, m.isGameOver());
  }


  /////////////////////////////////////////////////////////////////////////////////////////////////
  //Triangular Model tests

  //test constructor 1
  @Test
  public void TriangleConstructor1Test1() {
    String answer1 = new String(
        "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O");
    TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl();

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //constructor 2 tests row and col
  //valid position
  @Test
  public void TriangleConstructor2Test1() {
    String answer1 = new String("    O\n"
        + "   _ O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O");
    TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl(1, 0);

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //out of bounds row
  @Test(expected = IllegalArgumentException.class)
  public void TriangleOutOfBoundsRow() {
    try {
      TriangleSolitaireModelImpl m = new TriangleSolitaireModelImpl(5, 2);

    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (5,2)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //out of bounds col
  @Test(expected = IllegalArgumentException.class)
  public void TriangleOutOfBoundsCol() {
    try {
      TriangleSolitaireModelImpl m = new TriangleSolitaireModelImpl(2, 5);

    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (2,5)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //out of bounds row and col
  @Test(expected = IllegalArgumentException.class)
  public void TriangleOutOfBoundsRowAndCol() {
    try {
      TriangleSolitaireModelImpl m = new TriangleSolitaireModelImpl(50, 10);

    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (50,10)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //invalid space
  @Test(expected = IllegalArgumentException.class)
  public void TriangleInvalidSpace() {
    try {
      TriangleSolitaireModelImpl m = new TriangleSolitaireModelImpl(1, 2);

    } catch (IllegalArgumentException i) {
      String message = "Cannot place empty in invalid space";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Correct move error message did not throw!");
  }

  //test constructor 3
  //valid armThickness
  @Test
  public void TriangleConstructor3Test1() {
    String answer1 = new String("      _\n" + "     O O\n" + "    O O O\n"
        + "   O O O O\n" + "  O O O O O\n" + " O O O O O O\n" + "O O O O O O O");
    TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl(7);

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //test with valid arm thickness that is invalid for any other board type (even)
  @Test
  public void TriangleConstructor3Test2() {
    String answer1 = new String("   _\n" + "  O O\n" + " O O O\n"
        + "O O O O");
    TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl(4);

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //try to construct given invalid armThickness - must be nonzero
  @Test(expected = IllegalArgumentException.class)
  public void triConstructor3TestBadArmThickness1() {
    try {
      TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl(0);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //try to construct given invalid armThickness - must be positive
  @Test(expected = IllegalArgumentException.class)
  public void triConstructor3TestBadArmThickness2() {
    try {
      TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl(-3);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //testing constructor 4
  //all valid
  @Test
  public void TriangleConstructor4Test1() {
    String answer1 = new String("       O\n" + "      O O\n" + "     O O O\n"
        + "    O O O O\n" + "   O O _ O O\n" + "  O O O O O O\n" + " O O O O O O O\n"
        + "O O O O O O O O");
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(8, 4, 2);

    Assert.assertEquals(answer1, m1.getGameState());
  }

  //invalid armthickness
  @Test(expected = IllegalArgumentException.class)
  public void triConstructor4TestBadArmThickness() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(-3, 1, 0);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //invalid row
  @Test(expected = IllegalArgumentException.class)
  public void triConstructor4TestBadRow() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(4, 100, 0);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (100,0)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //invalid col
  @Test(expected = IllegalArgumentException.class)
  public void triConstructor4TestBadCol() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(4, 1, -5);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (1,-5)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //invalid row and col
  @Test(expected = IllegalArgumentException.class)
  public void triConstructor4TestBadRowAndCol() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(4, 100, 100);
    } catch (IllegalArgumentException i) {
      String message = "Invalid empty cell position (100,100)";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //invalid armthickness, row, and col
  @Test(expected = IllegalArgumentException.class)
  public void triConstructor4TestBadArmThicknessRowAndCol() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(0, 100, 100);
    } catch (IllegalArgumentException i) {
      String message = "Invalid arm thickness";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }


  //TESTING MOVE
  @Test
  //move top left
  public void TriangleValidMoveTest1() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(2, 0, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
    */
    String afterMove = new String("      O\n"
        + "     O O\n"
        + "    _ O O\n"
        + "   O _ O O\n"
        + "  O O O O O\n"
        + " O O O O O O\n"
        + "O O O O O O O");
    Assert.assertEquals(afterMove, m1.getGameState());
  }

  //move top right
  @Test
  public void TriangleValidMoveTest2() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(2, 2, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
    */
    String afterMove = new String("      O\n"
        + "     O O\n"
        + "    O O _\n"
        + "   O O _ O\n"
        + "  O O O O O\n"
        + " O O O O O O\n"
        + "O O O O O O O");
    Assert.assertEquals(afterMove, m1.getGameState());
  }

  //move middle left
  @Test
  public void TriangleValidMoveTest3() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(4, 0, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
    */
    String afterMove = new String("      O\n"
        + "     O O\n"
        + "    O O O\n"
        + "   O O O O\n"
        + "  _ _ O O O\n"
        + " O O O O O O\n"
        + "O O O O O O O");
    Assert.assertEquals(afterMove, m1.getGameState());
  }

  //move middle right
  @Test
  public void TriangleValidMoveTest4() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(4, 4, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
    */
    String afterMove = new String("      O\n"
        + "     O O\n"
        + "    O O O\n"
        + "   O O O O\n"
        + "  O O O _ _\n"
        + " O O O O O O\n"
        + "O O O O O O O");
    Assert.assertEquals(afterMove, m1.getGameState());
  }

  //move bottom left
  @Test
  public void TriangleValidMoveTest5() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(6, 2, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
    */
    String afterMove = new String("      O\n"
        + "     O O\n"
        + "    O O O\n"
        + "   O O O O\n"
        + "  O O O O O\n"
        + " O O _ O O O\n"
        + "O O _ O O O O");
    Assert.assertEquals(afterMove, m1.getGameState());
  }

  //move bottom right
  @Test
  public void TriangleValidMoveTest6() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(6, 4, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
    */
    String afterMove = new String("      O\n"
        + "     O O\n"
        + "    O O O\n"
        + "   O O O O\n"
        + "  O O O O O\n"
        + " O O O _ O O\n"
        + "O O O O _ O O");
    Assert.assertEquals(afterMove, m1.getGameState());
  }

  //move too far
  @Test(expected = IllegalArgumentException.class)
  public void TriangleValidMoveBad1() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(8, 4, 2);
      m1.move(7, 2, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
                                 + "O O O O O O O O");
    */
    } catch (IllegalArgumentException i) {
      String message = "Must move 2 spaces up, down, left, or right";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //move to filled space - invalid to
  @Test(expected = IllegalArgumentException.class)
  public void TriangleValidMoveBad2() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(8, 4, 2);
      m1.move(0, 0, 2, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
                                 + "O O O O O O O O");
    */
    } catch (IllegalArgumentException i) {
      String message = "To position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //from invalid
  @Test(expected = IllegalArgumentException.class)
  public void TriangleValidMoveBad5() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(8, 4, 2);
      m1.move(0, 1, 1, 1);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
                                 + "O O O O O O O O");
    */
    } catch (IllegalArgumentException i) {
      String message = "From position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //out of bounds from
  @Test(expected = IllegalArgumentException.class)
  public void TriangleValidMoveBad3() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(8, 4, 2);
      m1.move(-1, 0, 2, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
                                 + "O O O O O O O O");
    */
    } catch (IllegalArgumentException i) {
      String message = "From or to position is out of bounds";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //out of bounds to
  @Test(expected = IllegalArgumentException.class)
  public void TriangleValidMoveBad4() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(8, 4, 2);
      m1.move(0, 0, 20, 20);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
                                 + "O O O O O O O O");
    */
    } catch (IllegalArgumentException i) {
      String message = "From or to position is out of bounds";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }


  //to and from invalid
  @Test(expected = IllegalArgumentException.class)
  public void TriangleValidMoveBad6() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(8, 4, 2);
      m1.move(0, 2, 2, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
                                 + "O O O O O O O O");
    */
    } catch (IllegalArgumentException i) {
      String message = "From position is invalid";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //to and from out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void TriangleValidMoveBad7() {
    try {
      TriangleSolitaireModelImpl m1 =
          new TriangleSolitaireModelImpl(8, 4, 2);
      m1.move(-30, -2, -2, 70);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
                                 + "O O O O O O O O");
    */
    } catch (IllegalArgumentException i) {
      String message = "From or to position is out of bounds";
      Assert.assertEquals(message, i.getMessage());
      throw i;
    }
    Assert.fail("Invalid row or column message did not throw!");
  }

  //CHECK SCORE

  //no moves
  @Test
  public void TriangleCheckScore1() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    Assert.assertEquals(27, m1.getScore());
  }

  //one valid move
  @Test
  public void TriangleCheckScore2() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(6, 4, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
    */
    Assert.assertEquals(26, m1.getScore());
  }

  //multiple valid moves
  @Test
  public void TriangleCheckScore3() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(6, 4, 4, 2);
    m1.move(4, 4, 6, 4);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O O O _\n"
                                   + "O O O _ _ O\n"
                                  + "O O O O O O O");
    */
    Assert.assertEquals(25, m1.getScore());
  }


  //CHECK ISGAMEOVER
  //not over
  @Test
  public void TriangleCheckIsGameOver() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    Assert.assertEquals(false, m1.isGameOver());
  }

  //not over after move
  @Test
  public void TriangleCheckIsGameOver2() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(7, 4, 2);
    m1.move(6, 4, 4, 2);
    /*
           String beforeMove = new String("O\n"
                                       + "O O\n"
                                      + "O O O\n"
                                     + "O O O O\n"
                                    + "O O _ O O\n"
                                   + "O O O O O O\n"
                                  + "O O O O O O O");
    */
    Assert.assertEquals(false, m1.isGameOver());
  }

  //game that is over after no moves
  @Test
  public void TriangleCheckIsGameOver4() {
    TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl(1);
    Assert.assertEquals(true, m1.isGameOver());
  }

  //game that is over after one move
  @Test
  public void TriangleCheckIsGameOver5() {
    TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl(3);
    m1.move(2, 0, 0, 0);

    Assert.assertEquals(true, m1.isGameOver());
  }

  //game that is over after multiple moves
  @Test
  public void TriangleCheckIsGameOver6() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(5, 4, 2);
    m1.move(2, 0, 4, 2);
    m1.move(0, 0, 2, 0);
    m1.move(2, 2, 0, 0);
    m1.move(3, 2, 1, 0);
    m1.move(4, 4, 2, 2);
    m1.move(4, 2, 4, 4);
    m1.move(4, 0, 4, 2);
    m1.move(2, 0, 4, 0);
    m1.move(0, 0, 2, 0);

    Assert.assertEquals(true, m1.isGameOver());
  }

  //game that is not over after a move test on smaller board
  @Test
  public void TriangleCheckIsGameOver7() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(4, 3, 0);
    m1.move(1, 0, 3, 0);
    Assert.assertEquals(false, m1.isGameOver());
  }

  //game that is over on smaller board
  @Test
  public void TriangleCheckIsGameOver8() {
    TriangleSolitaireModelImpl m1 =
        new TriangleSolitaireModelImpl(4, 3, 0);
    m1.move(1, 0, 3, 0);
    m1.move(2, 2, 2, 0);
    Assert.assertEquals(false, m1.isGameOver());
  }
}