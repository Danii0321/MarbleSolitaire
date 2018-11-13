package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

import java.io.InputStreamReader;

/**
 * This class represents a game based on command line inputs.
 * Takes in arguments from command line and constructs a model, plays game via the controller
 */
public final class MarbleSolitaire {
  /**
   * Main method takes in arguments from the command line and parses for the board type, commands,
   * and values. Then, it constructs the board accordingly and allows the user to play the game;
   *
   * @param args The string[] of input from the command line
   */
  public static void main(String[] args) {
    //readable and appendable for the controller
    Readable read = new InputStreamReader(System.in);
    Appendable app = System.out;

    //assuming the user is entering valid input:
    //boardType is a string, size row and col are positive integers
    int argsLength = args.length;
    String boardType = "";
    String command1 = "";
    int size = -1;
    int row = -1;
    int col = -1;

    //parsing through args
    for (int i = 0; i < argsLength; i++) {
      if (i == 1) {
        boardType = args[i];
      }
      if (i == 2) {
        command1 = args[i];
        if (command1.equals("-size")) {
          String sizeString = args[3];
          size = Integer.parseInt(sizeString);
        } else if (command1.equals("-hole")) {
          String rowString = args[3];
          row = Integer.parseInt(rowString);

          String colString = args[4];
          col = Integer.parseInt(colString);
        }
      }
      if (i == 6) {
        if (command1.equals("-size")) {
          String rowString = args[5];
          row = Integer.parseInt(rowString);

          String colString = args[6];
          col = Integer.parseInt(colString);
        }
        if (command1.equals("-hole")) {
          String sizeString = args[6];
          size = Integer.parseInt(sizeString);
        }
      }
    }

    //building boards based on board types
    switch (boardType) {
      case "english": {
        //defaults
        if (size == -1) {
          size = 3;
        }
        if (row == -1 && col == -1) {
          row = 3;
          col = 3;
        }
        MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl(size, row, col);
        MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
        m.playGame(m1);
        break;
      }
      case "european": {
        //defaults
        if (size == -1) {
          size = 3;
        }
        if (row == -1 && col == -1) {
          row = 3;
          col = 3;
        }
        EuropeanSolitaireModelImpl m1 = new EuropeanSolitaireModelImpl(size, row, col);
        MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
        m.playGame(m1);
        break;
      }
      case "triangular": {
        //defaults
        if (size == -1) {
          size = 5;
        }
        if (row == -1 && col == -1) {
          row = 0;
          col = 0;
        }
        TriangleSolitaireModelImpl m1 = new TriangleSolitaireModelImpl(size, row, col);
        MarbleSolitaireControllerImpl m = new MarbleSolitaireControllerImpl(read, app);
        m.playGame(m1);
        break;
      }
      default:
        throw new IllegalArgumentException("Please pick either english, european, or triangular");
    }
  }
}
