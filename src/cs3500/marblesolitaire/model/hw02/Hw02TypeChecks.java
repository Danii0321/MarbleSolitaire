package cs3500.marblesolitaire.model.hw02;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw02TypeChecks {

  public static void main(String[] args) {
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl());
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl
        (2, 2));
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(5));
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(3, 0
        , 4));
  }

  private static void helper
      (cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model) {
    model.move(1, 3, 3, 3);
  }

}
