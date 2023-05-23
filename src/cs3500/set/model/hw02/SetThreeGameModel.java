package cs3500.set.model.hw02;

import java.util.List;

import cs3500.set.model.hw03.ASetGameModel;

/**
 * class that represents the implementation of Sets formed by 3 cards.
 * The class that has the "brains" behind the game.
 */
public class SetThreeGameModel extends ASetGameModel {

  /**
   * takes in a width, height, score, deck, and boolean values representing the status of the game.
   * in this case, the fields are stored in the super (ASetGameModel)
   */
  public SetThreeGameModel() {
    super();
  }

  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException {

    // height and width must be 3 otherwise throw an exception
    if (height != 3 || width != 3) {
      throw new IllegalArgumentException("Inappropriate board size.");
    }
    // generalized startGameWithDeck
    super.startGameWithDeck(deck, height, width);
  }

  @Override
  public boolean isGameOver() {
    // calls the generalized isGameOver
    if (super.isGameOver()) {
      return true;
    } else {
      // checks if there are any sets on the board at the given time
      return !this.anySetsPresent();
    }
  }
}