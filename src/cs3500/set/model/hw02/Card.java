package cs3500.set.model.hw02;

/**
 * Class to represent the cards needed to play the game.
 */
public class Card {
  public Count count;
  public Shape shape;
  public Filling fill;

  /**
   * Constructor for the cards in the Set game.
   * @param count the number of shapes on a card.
   * @param fill the shading type of the shapes on the card.
   * @param shape the shape of the image(s) on the card.
   */
  public Card(Count count, Filling fill, Shape shape) {

    this.count = count;
    this.shape = shape;
    this.fill = fill;
  }

  /**
   * method to get the shape of the card.
   * @return Shape enum value of the card.
   */
  public String getShape() {
    return this.shape.getShape();
  }

  /**
   * method to get the count of the card.
   * @return Count enum value of the card.
   */
  public String getCount() {
    return String.valueOf(this.count.getCount());
  }

  /**
   * method to get the fill of the card.
   * @return Fill enum value of the card.
   */
  public String getFill() {
    return this.fill.getFill();
  }

  /**
   * method to convert a card to string form.
   * @return a string representing the card.
   */
  @Override
  public String toString() {
    return (this.getCount() + this.getFill() + this.getShape());
  }

  /**
   * Method to determine if the given object is the same card as original card.
   * @param c a given object that may or not be a card.
   * @return a boolean value that determines if the given object is the same card as the original.
   */
  public boolean sameCard(Object c) {
    if (this == c) {
      return true;
    }
    if (c == null || getClass() != c.getClass()) {
      return false;
    }
    Card card = (Card) c;
    return count == card.count && shape == card.shape && fill == card.fill;
  }
}


