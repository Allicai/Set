package cs3500.set.model.hw02;

/**
 * enumerator to represent the type of shape on a card.
 */
public enum Shape {
  SQUIGGLY("Q"),
  DIAMOND("D"),
  OVAL("O");

  private final String shape;

  /**
   * method to get the actual String value stored in the enumerator.
   */
  public String getShape() {
    return shape;
  }

  Shape(String shape) {
    this.shape = shape;
  }

}
