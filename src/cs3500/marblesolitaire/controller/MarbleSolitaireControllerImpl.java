package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class represents the operations offered by the marble solitaire
 * controller. One object of the controller represents one game of marble solitaire being played.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  //fields
  private Readable rd;
  private Appendable ap;

  /**
   * Constructor for MarbleSolitaireControllerImpl: takes in a readable and appendable.
   *
   * @param rd Readable object containing the user's input.
   * @param ap Appendable object containing the output.
   * @throws IllegalArgumentException if either is null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("One or more element is null");
    }

    //else, set up fields
    this.rd = rd;
    this.ap = ap;
  }

  /*
   * This method should play a new game of Marble Solitaire using the provided model.
   * It should throw an IllegalArgumentException if the provided model is null.
   * It should throw an IllegalStateException only if the controller is unable to successfully
   * receive input or transmit output.
   * The nature of input/output will be an implementation detail (see below).
   *
   * Uses a Scanner to read the user's input, and checks every value individually
   * to see if it is:
   * -a quit ("q" or 'Q") --> modifies appendable accordingly
   * -a valid input (positive integer)
   *
   * Checks for valid input by calling a helper (IsValidInput)
   * -If it is valid, adds it to an ArrayList of valid inputs
   * -Checks
   *
   * @param model The model of the game being played
   */
  @Override
  public void playGame(MarbleSolitaireModel model) {
    //ArrayList containing valid inputs
    ArrayList<Integer> validInputs = new ArrayList<>();
    //tracks if the game has been quit or is over
    boolean hasBeenQuitOrOver = false;

    try {
      //Make sure model is not null
      if (model == null) {
        throw new IllegalArgumentException("Error: null model");
      } else {
        //transmit game state to appendable
        this.ap.append(model.getGameState() + "\n");
        //transmit score to appendable
        this.ap.append("Score: " + model.getScore() + "\n");

        //While there are still values in readable, find next user input
        Scanner input = new Scanner(this.rd);
        while (input.hasNext() && !hasBeenQuitOrOver) {
          String s = input.next();
          //quitting
          if (s.equalsIgnoreCase("q")) {
            this.ap.append("Game quit!" + "\n");
            this.ap.append("State of game when quit:" + "\n");
            this.ap.append(model.getGameState() + "\n");
            this.ap.append("Score: " + model.getScore() + "\n");
            hasBeenQuitOrOver = true;
            break;
          }
          //checks if s is a valid input (positive integer)
          //if so, adds to an arrayList containing valid inputs
          else if (isValidInput(s)) {
            int num = Integer.parseInt(s);
            validInputs.add(num);

            //looks for sets of four values, one set corresponds to one move
            if (validInputs.size() % 4 == 0) {
              int first = validInputs.get(0);
              validInputs.remove(0);
              int second = validInputs.get(0);
              validInputs.remove(0);
              int third = validInputs.get(0);
              validInputs.remove(0);
              int fourth = validInputs.get(0);
              validInputs.remove(0);

              //tries the move
              try {
                model.move(first - 1, second - 1, third - 1, fourth - 1);
              } catch (IllegalArgumentException i) {
                String message = i.getMessage();
                this.ap.append("Invalid move. Play again. " + message + "\n");
              }

              //checks if the game is over
              if (model.isGameOver()) {
                this.ap.append("Game over!\n");
                this.ap.append(model.getGameState() + "\n");
                this.ap.append("Score: " + model.getScore() + "\n");
                hasBeenQuitOrOver = true;
              }
            }
          }
        }
      }
      //make sure game has been quit or ended
      if (!hasBeenQuitOrOver) {
        throw new IllegalStateException("Must quit or finish game\n");
      }
    } catch (IOException i) {
      throw new IllegalStateException("There was an IOException, uh oh\n");
    }

  }

  //checks if input is a positive integer
  private boolean isValidInput(String s) {
    int num;

    try {
      //check if input can be converted to an int
      try {
        num = Integer.parseInt(s);

        //if it is positive int, it is good
        if (num > 0) {
          return true;
        } else {
          this.ap.append("Please enter value again.\n");
        }
      } catch (NumberFormatException n) {
        //num = -1;
        this.ap.append("Please enter value again.\n");
      }

    } catch (IOException i) {
      throw new IllegalStateException("There was an IOException, uh oh\n");
    }
    return false;
  }
}