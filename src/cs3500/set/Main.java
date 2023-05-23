package cs3500.set;

import java.io.InputStreamReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.ASetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

/**
 * Main method for the SetThreeGameModel class.
 * Lets the user provide inputs for the controller to process
 * View lets the user see the progress of the game and receive prompts/updates from the controller
 */
public class Main {

  /**
   * Method that sets the game to the starting point.
   * @param args Inputs from the user.
   */
  public static void main(String[] args) {
    ASetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model);
    SetGameController controller = new SetGameControllerImpl(model, view,
            new InputStreamReader(System.in));
    controller.playGame();
  }
}
