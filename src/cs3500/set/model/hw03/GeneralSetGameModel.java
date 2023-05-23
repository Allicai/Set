package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;

/**
 * a model that represents a GeneralSetGame.
 * works with any size grid, particularly the normal 4 by 3 usually used to play Set.
 * Adds a row of cards (if there are enough in the deck) rather than ending the game when
 * no sets are present on the board.
 */

public class GeneralSetGameModel extends ASetGameModel {

  /**
   * constructor for a GeneralSetGameModel.
   * The fields are stored in the super (ASetGameModel)
   */
  public GeneralSetGameModel() {
    super();
  }

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) throws IllegalStateException {

    super.claimSet(coord1, coord2, coord3);

    if (super.ended && this.deck.size() >= this.width) {
      this.ended = false;
      this.addRow();
    }

  }

  /**
   * method to add a row to a game when there are no longer any sets present.
   */
  public void addRow() {
    // only applies while there are no sets present and the deck is big enough to add a row
    while (!anySetsPresent()) {
      // defining a new row with cards from the deck
      if (deck.size() >= this.getWidth()) {
        ArrayList<Card> row = new ArrayList<>();
        for (int i = 0; i < this.getWidth(); i++) {
          row.add(this.deck.get(0));
          this.deck.remove(0);
        }
        // adding the row to the existing grid and increasing the height by 1
        this.height += 1;
        this.grid.add(row);
      } else {
        this.ended = true;
        break;
      }
    }
  }

  /**
   * a method to initialize the game.
   *
   * @param deck   the list of cards in the order they will be played.
   * @param height the height of the board for this game.
   * @param width  the width of the board for this game.
   * @throws IllegalArgumentException make sure the deck is not null.
   */
  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException {

    // grid must be able to hold at least 3 cards
    if ((height * width) < 3) {
      throw new IllegalArgumentException("Grid is too small.");
    }

    // calling the generalized startGameWithDeck method
    super.startGameWithDeck(deck, height, width);

    // adding a row in case the beginning board doesn't have a set on it
    this.addRow();
  }

  /**
   * method to determine if the game is over.
   *
   * @return boolean value that represents if the game has ended
   */
  @Override
  public boolean isGameOver() {
    // call the generalized isGameOver method
    if (super.isGameOver()) {
      return true;
    } else {
      return (!this.anySetsPresent() && this.deck.size() < this.width);
    }
  }
}