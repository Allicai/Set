package cs3500.set.model.hw02;

/**
 * an enumeration that represents the count; the number of images on a card.
 */
public enum Count {
  ONE(1),
  TWO(2),
  THREE(3);

  private final int count;

  /**
   * method to get the actual int value stored in the enumerator.
   */
  public int getCount() {
    return count;
  }

  Count(int count) {
    this.count = count;
  }

}
