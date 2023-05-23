import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw02.Shape;
import cs3500.set.view.SetGameTextView;

/**
 * class to test SetGameTextView methods.
 */

public class SetGameTextViewTest extends TestCase {

  String newGrid;

  @Test
  public void testConstruction() {
    SetGameModel setGameModel = new SetThreeGameModel();
    SetGameTextView setGameTextView = new SetGameTextView(setGameModel);

    assertEquals(setGameTextView, setGameModel);
  }

  @Test
  public void testGridToString() {
    List<Card> deck = new ArrayList<Card>();
    SetGameModel setGameModel = new SetThreeGameModel();

    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.DIAMOND));

    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.DIAMOND));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.DIAMOND));

    deck.add(new Card(Count.TWO, Filling.FULL, Shape.OVAL));
    deck.add(new Card(Count.TWO, Filling.FULL, Shape.SQUIGGLY));
    deck.add(new Card(Count.TWO, Filling.FULL, Shape.DIAMOND));

    String grid = "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD";
    newGrid = grid;

    setGameModel.startGameWithDeck(deck, 3, 3);

    SetGameTextView setGameTextView = new SetGameTextView(setGameModel);
    assertEquals(grid, setGameTextView.toString());

  }

  @Test
  public void testRenderGrid() throws IOException {

    List<Card> deck = new ArrayList<Card>();
    SetGameModel setGameModel = new SetThreeGameModel();

    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.DIAMOND));

    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.DIAMOND));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.DIAMOND));

    deck.add(new Card(Count.TWO, Filling.FULL, Shape.OVAL));
    deck.add(new Card(Count.TWO, Filling.FULL, Shape.SQUIGGLY));
    deck.add(new Card(Count.TWO, Filling.FULL, Shape.DIAMOND));

    String grid = "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n";

    setGameModel.startGameWithDeck(deck, 3, 3);

    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    SetGameTextView writer = new SetGameTextView(setGameModel, ap);
    writer.renderGrid();
    assertEquals(grid, builder.toString());
  }

  @Test(expected = IOException.class)
  public void testInvalidGrid() throws IOException {
    List<Card> deck = new ArrayList<Card>();
    SetGameModel setGameModel = new SetThreeGameModel();

    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.EMPTY, Shape.DIAMOND));

    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.STRIPED, Shape.DIAMOND));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.OVAL));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.SQUIGGLY));
    deck.add(new Card(Count.ONE, Filling.FULL, Shape.DIAMOND));

    deck.add(new Card(Count.TWO, Filling.FULL, Shape.OVAL));
    deck.add(new Card(Count.TWO, Filling.FULL, Shape.SQUIGGLY));
    deck.add(new Card(Count.TWO, Filling.FULL, Shape.DIAMOND));

    String grid = "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n";

    setGameModel.startGameWithDeck(deck, 3, 3);

    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    SetGameTextView writer = new SetGameTextView(setGameModel, ap);
    writer.renderGrid();
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testRenderMessage() throws IOException {
    SetGameModel model = new SetThreeGameModel();
    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    String message = "Hello, World!";
    SetGameTextView writer = new SetGameTextView(model, ap);
    writer.renderMessage(message);
    assertEquals(message, builder.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderInvalid() throws IOException {
    SetGameModel model = new SetThreeGameModel();
    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    String message = "Hello, World!";
    SetGameTextView writer = new SetGameTextView(model, ap);
    writer.renderMessage(message);
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testToString() {

    SetThreeGameModel s = new SetThreeGameModel();
    SetGameTextView view = new SetGameTextView(s);
    s = new SetThreeGameModel();
    view = new SetGameTextView(s);
    s.startGameWithDeck(s.completeDeck(), 3, 3);
    assertEquals("3SD 3FD 3ED\n"
            + "3SO 3FO 3EO\n"
            + "3SQ 3FQ 3EQ", view.toString());
  }

}