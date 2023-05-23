package cs3500.set.controller;

/**
 * A class to represent a controller for the Set card game.
 */
public interface SetGameController {

  /**
   * method to start a new game of Set.
   * @throws IllegalStateException controller is unable to read input/ transmit output.
   */
  void playGame() throws IllegalStateException;

  Readable getReadable();
}
