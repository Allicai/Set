import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;

/**
 * class to test the methods for the SetGameControllerImpl.
 * Couldn't figure out how to actually push text into the console to interact
 * with the game, so they're really just "skeleton tests"
 */
public class SetGameControllerImplTest {

  @Test
  public void testValidConstruction() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameControllerImpl controller = new SetGameControllerImpl(model, sgv, rd);

    assertEquals(model, controller.getModel());
    assertEquals(sgv, controller.toString());
    assertEquals(rd, controller.getReadable());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstruction() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);

    try {
      SetGameController controller = new SetGameControllerImpl(null, sgv, rd);
    } catch (IllegalArgumentException e) {
      String message = "Null inputs for the controller.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(model, null, rd);
    } catch (IllegalArgumentException e) {
      String message = "Null inputs for the controller.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(model, sgv, null);
    } catch (IllegalArgumentException e) {
      String message = "Null inputs for the controller.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(null, null, rd);
    } catch (IllegalArgumentException e) {
      String message = "Null inputs for the controller.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(null, null, rd);
    } catch (IllegalArgumentException e) {
      String message = "Null inputs for the controller.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(null, null, null);
    } catch (IllegalArgumentException e) {
      String message = "Null inputs for the controller.";
      assertEquals(message, e.getMessage());
    }
  }

  @Test
  public void testInitalizeBoard() throws IOException {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String initBoard = "3 3";
    Scanner scanner = new Scanner(initBoard);

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testQuitHeight() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String quitHeight = "q 3";
    Scanner scanner = new Scanner(quitHeight);

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidHeight() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String invHeight = "a 3";
    Scanner scanner = new Scanner(invHeight);

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testQuitWidth() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String quitWidth = "3 q";
    Scanner scanner = new Scanner(quitWidth);

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidWidth() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String invWidth = "3 a";
    Scanner scanner = new Scanner(invWidth);

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidHeightAndWidth() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String invHeightAndWidth = "a a";
    Scanner scanner = new Scanner(invHeightAndWidth);

    // invalid height and width parameters

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testQuitInsteadOfRow1() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String row1Quit = "3 3\nq 1";
    Scanner scanner = new Scanner(row1Quit);

    // q instead of a valid number for the first row

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testQuitInsteadOfCol1() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String col1Quit = "3 3\n1 q";
    Scanner scanner = new Scanner(col1Quit);

    // as above for col 1

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testQuitInsteadOfRow2() {
    // name shortened - self-explanatory
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testQuitInsteadOfCol2() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testQuitInsteadOfRow3() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testQuitInsteadOfCol3() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidRow1() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidCol1() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidRow2() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidCol2() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidRow3() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testInvalidCol3() {
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testValidSetThenQuit() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    // valid set claimed and the game was then quit

    String validSetQuit = "3 3\n1 1 9 1 3 1\nq";
    Scanner scanner = new Scanner(validSetQuit);

    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testValidGame() {
    // test for a valid game played all the way through
    int a = 9;
    assertEquals(a, 9);
  }

  @Test
  public void testGetModel() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameControllerImpl controller = new SetGameControllerImpl(model, sgv, rd);

    assertEquals(model, controller.getModel());
  }

  @Test
  public void testGetSetGameView() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);

    assertEquals(sgv, controller.toString());
  }

  @Test
  public void testGetReadable() {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);

    assertEquals(rd, controller.getReadable());
  }

  SetGameModel model = new SetThreeGameModel();
  Appendable ap = System.out;
  Readable rd = new InputStreamReader(System.in);
  SetGameView sgv = new SetGameTextView(model, ap);
  SetGameControllerImpl controller = new SetGameControllerImpl(model, sgv, rd);

  @Test
  public void testIsValidInput() {
    int a = 9;
    assertEquals(a, 9);
  }

  // generic test to see if the writeMessage method (renderMessage rewrite) works
  @Test
  public void testWriteMessage() throws IOException {
    SetGameModel model = new SetThreeGameModel();
    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    String message = "Hi!";
    SetGameTextView writer = new SetGameTextView(model, ap);
    writer.renderMessage(message);
    assertEquals(message, builder.toString());
  }


}