import org.junit.Test;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.Shape;

import static org.junit.Assert.assertEquals;

/**
 * tests for the Card class.
 */
public class CardTest {

  private final Card ODE = new Card(Count.ONE, Filling.EMPTY, Shape.DIAMOND);
  private final Card TOS = new Card(Count.TWO, Filling.STRIPED, Shape.OVAL);
  private final Card TQF = new Card(Count.THREE, Filling.FULL, Shape.SQUIGGLY);


  @Test
  public void testGetShapeO() {
    assertEquals("O", TOS.getShape());
  }

  @Test
  public void testGetShapeD() {
    assertEquals("D", ODE.getShape());
  }

  @Test
  public void testGetShapeQ() {
    assertEquals("Q", TQF.getShape());
  }


  @Test
  public void testCardToString1() {
    assertEquals("1ED", ODE.toString());
  }

  @Test
  public void testCardToString2() {
    assertEquals("2SO", TOS.toString());
  }

  @Test
  public void testCardToString3() {

    assertEquals("3FQ", TQF.toString());
  }

  @Test
  public void testSameCardT() {
    assertEquals(true, ODE.sameCard(ODE));
  }

  @Test
  public void testSameCardF() {
    assertEquals(false, ODE.sameCard(TOS));
  }

  @Test
  public void testSameCardF2() {
    assertEquals(false, ODE.sameCard("Hello"));
  }
}