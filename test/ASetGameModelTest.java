import org.junit.Before;

import java.util.ArrayList;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.Shape;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;

/**
 * A class to test methods that are able to abstracted.
 * Methods that are shared between SetThreeGame and GeneralSetGame models.
 */
public abstract class ASetGameModelTest {
  protected ArrayList<Card> deck;
  protected Card c1;
  protected Card c2;
  protected Card c3;
  protected Card c4;
  protected Card c5 = new Card(Count.THREE, Filling.EMPTY, Shape.SQUIGGLY);
  protected Card c6 = new Card(Count.TWO, Filling.STRIPED, Shape.OVAL);
  protected Card c7 = new Card(Count.ONE, Filling.STRIPED, Shape.OVAL);
  protected Card c8 = new Card(Count.ONE, Filling.FULL, Shape.DIAMOND);
  protected Card c9 = new Card(Count.THREE, Filling.FULL, Shape.SQUIGGLY);
  protected Card c10 = new Card(Count.THREE, Filling.STRIPED, Shape.DIAMOND);
  protected Card c11 = new Card(Count.ONE, Filling.STRIPED, Shape.DIAMOND);
  protected Card c12 = new Card(Count.ONE, Filling.FULL, Shape.SQUIGGLY);
  protected Card c13 = new Card(Count.TWO, Filling.STRIPED, Shape.DIAMOND);
  protected Card c14 = new Card(Count.TWO, Filling.STRIPED, Shape.SQUIGGLY);
  protected Card c15 = new Card(Count.THREE, Filling.STRIPED, Shape.SQUIGGLY);
  protected Card c16 = new Card(Count.TWO, Filling.FULL, Shape.OVAL);
  protected Card c17 = new Card(Count.TWO, Filling.FULL, Shape.DIAMOND);
  protected Card c18 = new Card(Count.TWO, Filling.FULL, Shape.SQUIGGLY);
  protected Card c19 = new Card(Count.THREE, Filling.FULL, Shape.OVAL);
  protected Card c20 = new Card(Count.THREE, Filling.FULL, Shape.DIAMOND);
  protected Card c21 = new Card(Count.ONE, Filling.EMPTY, Shape.OVAL);
  protected Card c22 = new Card(Count.ONE, Filling.EMPTY, Shape.SQUIGGLY);
  protected Card c23 = new Card(Count.TWO, Filling.EMPTY, Shape.OVAL);
  protected Card c24 = new Card(Count.TWO, Filling.EMPTY, Shape.DIAMOND);
  protected Card c25 = new Card(Count.TWO, Filling.EMPTY, Shape.SQUIGGLY);
  protected Card c26 = new Card(Count.THREE, Filling.EMPTY, Shape.OVAL);
  protected Card c27 = new Card(Count.THREE, Filling.EMPTY, Shape.DIAMOND);

  @Before
  public void init() {
    SetGameModel<Card> game = new GeneralSetGameModel();
    SetGameTextView view = new SetGameTextView(game);
    ArrayList<Card> deck = new ArrayList<Card>();
    Card c1 = new Card(Count.ONE, Filling.EMPTY, Shape.DIAMOND);
    Card c2 = new Card(Count.ONE, Filling.FULL, Shape.OVAL);
    Card c3 = new Card(Count.ONE, Filling.STRIPED, Shape.SQUIGGLY);
    Card c4 = new Card(Count.THREE, Filling.STRIPED, Shape.OVAL);
    Card c5 = new Card(Count.THREE, Filling.EMPTY, Shape.SQUIGGLY);
    Card c6 = new Card(Count.TWO, Filling.STRIPED, Shape.OVAL);
    Card c7 = new Card(Count.ONE, Filling.STRIPED, Shape.OVAL);
    Card c8 = new Card(Count.ONE, Filling.FULL, Shape.DIAMOND);
    Card c9 = new Card(Count.THREE, Filling.FULL, Shape.SQUIGGLY);
    Card c10 = new Card(Count.THREE, Filling.STRIPED, Shape.DIAMOND);
    Card c11 = new Card(Count.ONE, Filling.STRIPED, Shape.DIAMOND);
    Card c12 = new Card(Count.ONE, Filling.FULL, Shape.SQUIGGLY);
    Card c13 = new Card(Count.TWO, Filling.STRIPED, Shape.DIAMOND);
    Card c14 = new Card(Count.TWO, Filling.STRIPED, Shape.SQUIGGLY);
    Card c15 = new Card(Count.THREE, Filling.STRIPED, Shape.SQUIGGLY);
    Card c16 = new Card(Count.TWO, Filling.FULL, Shape.OVAL);
    Card c17 = new Card(Count.TWO, Filling.FULL, Shape.DIAMOND);
    Card c18 = new Card(Count.TWO, Filling.FULL, Shape.SQUIGGLY);
    Card c19 = new Card(Count.THREE, Filling.FULL, Shape.OVAL);
    Card c20 = new Card(Count.THREE, Filling.FULL, Shape.DIAMOND);
    Card c21 = new Card(Count.ONE, Filling.EMPTY, Shape.OVAL);
    Card c22 = new Card(Count.ONE, Filling.EMPTY, Shape.SQUIGGLY);
    Card c23 = new Card(Count.TWO, Filling.EMPTY, Shape.OVAL);
    Card c24 = new Card(Count.TWO, Filling.EMPTY, Shape.DIAMOND);
    Card c25 = new Card(Count.TWO, Filling.EMPTY, Shape.SQUIGGLY);
    Card c26 = new Card(Count.THREE, Filling.EMPTY, Shape.OVAL);
    Card c27 = new Card(Count.THREE, Filling.EMPTY, Shape.DIAMOND);
  }
}