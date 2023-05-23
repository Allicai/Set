package cs3500.set.model.hw02;

/**
 * an enumeration representing the filling of the images on the cards.
 */

public enum Filling {
  EMPTY("E"),
  STRIPED("S"),
  FULL("F");

  private final String fill;

  /**
   * method to get the actual String value stored in the enumerator.
   */
  public String getFill() {
    return fill;
  }

  Filling(String fill) {
    this.fill = fill;
  }

}

