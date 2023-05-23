package cs3500.set.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameView;

/**
 * implementation of the set game controller.
 * lets the user provide inputs to set up and then play the game.
 */
public class SetGameControllerImpl implements SetGameController {

  SetGameModel model;
  SetGameView view;
  Readable input;

  /**
   * constructor for the controller used to play the Set game.
   *
   * @param model the model of the game being shown to the player
   * @param view  what the player sees i.e. the representation of the game
   * @param input the player's inputs
   */
  public SetGameControllerImpl(SetGameModel model, SetGameView view, Readable input) {
    this.model = model;
    this.view = view;
    this.input = input;

    if (this.model == null || this.view == null || this.input == null) {
      throw new IllegalArgumentException("Null inputs for the controller.");
    }
  }

  /**
   * method to start a new game of Set.
   *
   * @throws IllegalStateException controller is unable to read input/ transmit output
   */

  // when you are asking for numbers for the coordinates, keep asking for a valid number till you
  // get 6, dont throw an error.
  // try getting one number at a time to build your coords, run a loop that adds ints to an
  // array or arraylist
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(input);
    int width = 0;
    int height = 0;
    boolean gameActive = false;

    boolean hAndW = false;

    while (!hAndW) {
      String w = sc.next();

      if (isNumber(w)) {
        height = Integer.parseInt(w);
      } else if (isQuit(w)) {
        handleQuit(gameActive);
        break;
      } else {
        writeMessage("\nInvalid height/width. Try again.\n");
      }

      String z = sc.next();
      if (isNumber(z)) {
        width = Integer.parseInt(z);
      } else if (isQuit(z)) {
        handleQuit(gameActive);
        break;
      } else {
        writeMessage("\nInvalid height/width. Try again.\n");
      }

      try {
        model.startGameWithDeck(model.getCompleteDeck(), height, width);
        gameActive = true;
        hAndW = true;
      } catch (IllegalArgumentException e) {
        // ignore
      }
    }

    int a = 0;
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    int x3 = 0;
    int y3 = 0;

    while (gameActive) {
      writeMessage(view.toString() + System.lineSeparator());
      writeMessage("Score: " + model.getScore() + System.lineSeparator());

      a = getNextValidInput(sc);
      if (a == -1) {
        handleQuit(true);
        break;
      } else if (a > 0) {
        x1 = a;
      }

      a = getNextValidInput(sc);
      if (a == -1) {
        handleQuit(true);
        break;
      } else if (a > 0) {
        y1 = a;
      }

      a = getNextValidInput(sc);
      if (a == -1) {
        handleQuit(true);
        break;
      } else if (a > 0) {
        x2 = a;
      }

      a = getNextValidInput(sc);
      if (a == -1) {
        handleQuit(true);
        break;
      } else if (a > 0) {
        y2 = a;
      }

      a = getNextValidInput(sc);
      if (a == -1) {
        handleQuit(true);
        break;
      } else if (a > 0) {
        x3 = a;
      }

      a = getNextValidInput(sc);

      if (a == -1) {
        handleQuit(true);
        break;
      } else if (a > 0) {
        y3 = a;
      }

      Coord c1 = new Coord(x1 - 1, y1 - 1);
      Coord c2 = new Coord(x2 - 1, y2 - 1);
      Coord c3 = new Coord(x3 - 1, y3 - 1);

      boolean result = false;

      try {
        model.isValidSet(c1, c2, c3);
        result = model.isValidSet(c1, c2, c3);
      } catch (IllegalArgumentException e) {
        // ignore
        writeMessage("Please try again: \n");
      }

      if (result) {
        try {
          model.claimSet(c1, c2, c3);
          if (model.isGameOver()) {
            writeMessage("Game over!\n");
            writeMessage("Score: " + model.getScore() + "\n");
            gameActive = false;
          }
        } catch (IllegalArgumentException e) {
          // ignore
          writeMessage("Invalid set. Try again" + System.lineSeparator());
        }
      }
    }
  }

  @Override
  public Readable getReadable() {
    return this.input;
  }

  private int getNextValidInput(Scanner scanner) {
    while (true) {
      if (scanner.hasNext()) {
        String s = scanner.next();
        if (isQuit(s)) {
          return -1;
        } else if (isNumber(s)) {
          return Integer.parseInt(s);
        }
      }
    }
  }

  private void writeMessage(String s) {
    try {
      view.renderMessage(s);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean isQuit(String s) {
    return s.equalsIgnoreCase("q");
  }


  private void handleQuit(boolean hasGameStarted) {
    writeMessage("Game quit!" + System.lineSeparator());
    if (hasGameStarted) {
      writeMessage("State of game when quit:" + System.lineSeparator());
      writeMessage(view.toString() + System.lineSeparator());
      writeMessage("Score: " + model.getScore() + System.lineSeparator());
    } else {
      writeMessage("Score: 0" + System.lineSeparator());
    }
    return;
  }


  static boolean isNumber(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      // ignore e
      return false;
    }
  }

  public SetGameModel getModel() {
    return this.model;
  }
}
