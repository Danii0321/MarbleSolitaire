package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This interface represents the operations offered by the marble solitaire
 * controller. One object of the controller represents one game of marble solitaire being played.
 */
public interface MarbleSolitaireController {

  /**
   * This method should play a new game of Marble Solitaire using the provided model.
   * It should throw an IllegalArgumentException if the provided model is null.
   * It should throw an IllegalStateException only if the controller is unable to successfully
   * receive input or transmit output.
   * The nature of input/output will be an implementation detail (see below).
   *
   * @param model The model of the game being played
   */
  void playGame(MarbleSolitaireModel model);
}
