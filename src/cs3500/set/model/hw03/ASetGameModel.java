package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.Shape;

/**
 * An abstraction of a SetGameModel.
 * Meant to give a base representation of what the game should be run like, and can be adapted
 * for further implementation
 */
public abstract class ASetGameModel implements SetGameModel<Card> {

  protected int width;
  protected int height;
  protected int score;
  protected List<ArrayList<Card>> grid;
  protected List<Card> deck;
  protected boolean started;
  protected boolean ended;

  /**
   * takes in a width, height, score, deck, and boolean values representing the status of the game.
   */
  public ASetGameModel() {
    this.width = 0;
    this.height = 0;
    this.score = 0;
    this.started = false;
    this.ended = false;
  }

  /**
   * method to produce a complete deck of the 27 possible cards.
   *
   * @return a list of cards.
   */
  public List<Card> completeDeck() {
    ArrayList<Card> deck = new ArrayList<>(27);
    ArrayList<Filling> fillings = new ArrayList<>(Arrays.asList(Filling.EMPTY, Filling.FULL,
            Filling.STRIPED));
    ArrayList<Shape> shapes = new ArrayList<>(Arrays.asList(Shape.SQUIGGLY, Shape.OVAL,
            Shape.DIAMOND));
    ArrayList<Count> counts = new ArrayList<>(Arrays.asList(Count.ONE, Count.TWO, Count.THREE));
    for (int k = 2; k >= 0; k--) {
      for (int j = 2; j >= 0; j--) {
        for (int h = 2; h >= 0; h--) {
          deck.add(new Card(counts.get(k), fillings.get(h), shapes.get(j)));
        }
      }
    }
    return deck;
  }

  /**
   * A method to replace the card at the given location (coordinate).
   *
   * @param c The coordinate of the card you want to replace
   */
  protected void replaceCard(Coord c) {
    if (this.deck.size() == 0) {
      grid.get(c.row).set(c.col, null);
    } else {
      grid.get(c.row).set(c.col, deck.get(0));
      deck.remove(0);
    }
  }

  /**
   * a method used to claim a set of 3 cards if they form a valid set.
   *
   * @param coord1 the coordinates of the first card.
   * @param coord2 the coordinates of the second card.
   * @param coord3 the coordinates of the third card.
   * @throws IllegalStateException make sure the game has begun.
   */
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) throws IllegalStateException {

    // generalized method that just checks for the potential exceptions
    if (!this.ended && !this.started) {
      throw new IllegalStateException("Game is not active.");
    }

    if (this.isInvalidCoord(coord1) || this.isInvalidCoord(coord2)
            || this.isInvalidCoord(coord3)) {
      throw new IllegalArgumentException("Out of bounds of the board.");
    } else if (this.getCardAtCoord(coord1) == null || this.getCardAtCoord(coord2) == null
            || this.getCardAtCoord(coord3) == null) {
      this.ended = true;
    }
    if (!this.isValidSet(coord1, coord2, coord3)) {
      throw new IllegalArgumentException("Not a valid set.");
    }

    if (isValidSet(coord1, coord2, coord3)) {
      // not enough cards to replace the set that was claimed
      if (this.deck.size() < 3 || !this.anySetsPresent()) {
        // adds 1 to the score but also ends the game
        this.score++;
        this.ended = true;
      } else { // replaces the cards and adds 1 to the score
        replaceCard(coord1);
        replaceCard(coord2);
        replaceCard(coord3);
        this.score++;
      }
    } else {
      // throws an error if the set is invalid
      throw new IllegalArgumentException("Not a valid set.");
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

    // if the deck value is null
    if (deck == null) {
      throw new IllegalArgumentException("Deck is null.");
    }

    // if the deck size is less than the area of the grid
    // aka not enough cards to fill the grid
    if (deck.size() < width * height) {
      this.ended = true;
      throw new IllegalArgumentException("Deck size too small.");
    }

    // negative height/width
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("Invalid grid parameters");
    }

    // begin the game
    this.started = true;

    // initialize the grid and the relevant fields of the model
    this.grid = new ArrayList<ArrayList<Card>>();

    this.height = height;
    this.width = width;
    this.score = 0;

    for (int i = 0; i < this.height; i++) {
      ArrayList<Card> row = new ArrayList<Card>();
      for (int f = 0; f < this.width; f++) {
        row.add(deck.get(0));
        deck.remove(0);
      }
      this.grid.add(row);
    }

    ArrayList<Card> cards = new ArrayList<Card>();
    for (int t = 0; t < deck.size(); t++) {
      cards.add(deck.get(t));
    }
    this.deck = cards;

  }


  /**
   * method that returns the width of the game (how many columns of cards there are).
   *
   * @return int representing the width of the game board.
   * @throws IllegalStateException Make sure the game has begun.
   */

  @Override
  public int getWidth() throws IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not begun.");
    }
    return this.width;
  }

  /**
   * method that returns the height of the game (how many rows of cards there are).
   *
   * @return an int representing the height of the game board.
   * @throws IllegalStateException Make sure the game has started.
   */

  @Override
  public int getHeight() throws IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not begun.");
    }
    return this.height;
  }

  /**
   * method that returns the score of the game.
   *
   * @return an int representing the score of the game.
   * @throws IllegalStateException Make sure the game has begun.
   */
  @Override
  public int getScore() throws IllegalStateException {
    if (this.height == 0 || this.width == 0) {
      throw new IllegalStateException("Game has not begun.");
    }
    return this.score;
  }

  /**
   * method that determines if there are any sets present on the board.
   *
   * @return a boolean value that represents whether there are any sets on the board.
   * @throws IllegalStateException Make sure the game has begun.
   */
  @Override
  public boolean anySetsPresent() throws IllegalStateException {

    if (!started) {
      throw new IllegalStateException("The game is not active.");
    }
    List<Coord> possibleCoords = new ArrayList<>();
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        possibleCoords.add(new Coord(i, j));
      }

    }

    for (int i = 0; i < possibleCoords.size(); i++) {
      for (int j = i + 1; j < possibleCoords.size(); j++) {
        for (int k = j + 1; k < possibleCoords.size(); k++) {
          if (isValidSet(possibleCoords.get(i), possibleCoords.get(j), possibleCoords.get(k))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * A helper method to ensure a coordinate is not outside the bounds of a grid.
   *
   * @param c the coordinate to be analyzed
   * @return a boolean value that confirms whether the coordinate is outside the grid
   */
  protected boolean isInvalidCoord(Coord c) {
    return (0 > c.col || c.col >= this.getWidth() || 0 > c.row || c.row >= this.getHeight());
  }

  /**
   * method to determine if the three cards at the given coords form a valid set.
   *
   * @param coord1 the coordinates of the first card.
   * @param coord2 the coordinates of the second card.
   * @param coord3 the coordinates of the third card.
   * @return boolean value that determines if the set selected is valid
   * @throws IllegalArgumentException Make sure the coordinates are valid.
   */
  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3) throws
          IllegalArgumentException {
    if (isInvalidCoord(coord1) || isInvalidCoord(coord2) || isInvalidCoord(coord3)) {
      throw new IllegalArgumentException("Not valid coordinates.");
    }

    if (getCardAtCoord(coord1) == null || getCardAtCoord(coord2) == null
            || getCardAtCoord(coord3) == null) {
      return false;
    }

    if (coord1.col == coord2.col && coord1.row == coord2.row
            || coord1.col == coord3.col && coord1.row == coord3.row
            || coord2.col == coord3.col && coord2.row == coord3.row) {
      return false;
    }

    boolean validity = false;
    Card c1 = this.getCardAtCoord(coord1);
    Card c2 = this.getCardAtCoord(coord2);
    Card c3 = this.getCardAtCoord(coord3);

    if ((c1.fill == c2.fill && c1.fill == c3.fill) || (c1.fill != c2.fill && c1.fill != c3.fill
            && c2.fill != c3.fill)) {
      if ((c1.shape == c2.shape && c2.shape == c3.shape) || (c1.shape != c2.shape && c2.shape
              != c3.shape && c1.shape != c3.shape)) {
        if ((c1.count == c2.count && c2.count == c3.count) || (c1.count != c2.count && c2.count
                != c3.count && c1.count != c3.count)) {
          validity = true;
        }
      }
    }
    return validity;
  }

  /**
   * method to get the card at a given int and row.
   *
   * @param row the row of the desired card
   * @param col the column of the desired card
   * @return returns the Card at the given location
   */
  @Override
  public Card getCardAtCoord(int row, int col) {
    if (!isInvalidCoord(new Coord(row, col))) {
      return grid.get(row).get(col);
    } else {
      throw new IllegalArgumentException("Not a valid coord.");
    }
  }

  /**
   * method to get the card at the given coord on the grid.
   *
   * @param coord the coordinates of the desired card
   * @return the card at the given location
   */
  @Override
  public Card getCardAtCoord(Coord coord) {
    if (!isInvalidCoord(coord)) {
      return grid.get(coord.row).get(coord.col);
    } else {
      throw new IllegalArgumentException("Not a valid coord.");
    }
  }

  /**
   * method to determine if the game is over.
   *
   * @return boolean value that represents if the game has ended
   */
  @Override
  public boolean isGameOver() {
    if (this.ended) {
      return true;
    } else {
      return (!this.anySetsPresent() && this.grid.isEmpty());
    }
  }

  /**
   * method to generate all 27 cards possible.
   *
   * @return a list of all 27 possible cards
   */
  public List<Card> getCompleteDeck() {
    return this.completeDeck();
  }

}