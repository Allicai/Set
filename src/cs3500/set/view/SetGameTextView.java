package cs3500.set.view;

import java.io.IOException;

import cs3500.set.model.hw02.SetGameModelState;

/**
 * class to represent the game in a text format for the observers.
 */

public class SetGameTextView implements SetGameView {
  SetGameModelState state;
  Appendable destination;

  public SetGameTextView(SetGameModelState state) {
    this.state = state;
    this.destination = System.out;
  }

  /**
   * Constructor for the SetGameText view.
   * @param state The state of the game model.
   * @param destination The appendable where the text goes.
   */
  public SetGameTextView(SetGameModelState state, Appendable destination) {

    if (state == null || destination == null) {
      throw new IllegalArgumentException("Null state or destination.");
    }

    this.state = state;
    this.destination = destination;
  }

  /**
   * Method to render the grid of the game as a string.
   * @return returns a string representing the board.
   */
  public String toString() {
    String string = "";
    for (int a = 0; a < this.state.getHeight(); a++) {
      for (int b = 0; b < this.state.getWidth(); b++) {

        if (a == this.state.getHeight() - 1 && b == this.state.getWidth() - 1) {
          string += state.getCardAtCoord(a, b).toString();
        } else if (a != this.state.getHeight() - 1 && b == this.state.getWidth() - 1) {
          string += state.getCardAtCoord(a, b).toString() + "\n";
        } else {
          string += state.getCardAtCoord(a, b).toString() + " ";
        }
      }
    }
    return string;
  }

  @Override
  public void renderGrid() throws IOException {

    try {
      this.destination.append(this.state.toString() + System.lineSeparator());
    } catch (IOException e) {
      System.out.println("Something went wrong");
    }

  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      destination.append(message);

    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }


}
