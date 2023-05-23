import org.junit.Test;

import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.Shape;

import static org.junit.Assert.assertEquals;

/**
 * class to test methods for the enums.
 */
public class TestEnums {

  @Test
  public void testGetShape1() {
    assertEquals("D", Shape.DIAMOND.getShape());
  }

  @Test
  public void testGetShape2() {
    assertEquals("O", Shape.OVAL.getShape());
  }

  @Test
  public void testGetShape3() {
    assertEquals("Q", Shape.SQUIGGLY.getShape());
  }

  @Test
  public void testGetCount1() {
    assertEquals(1, Count.ONE.getCount());
  }

  @Test
  public void testGetCount2() {
    assertEquals(2, Count.TWO.getCount());
  }

  @Test
  public void testGetCount3() {
    assertEquals(3, Count.THREE.getCount());
  }

  @Test
  public void testGetFill1() {
    assertEquals("F", Filling.FULL.getFill());
  }

  @Test
  public void testGetFill2() {
    assertEquals("E", Filling.EMPTY.getFill());
  }

  @Test
  public void testGetFill3() {
    assertEquals("S", Filling.STRIPED.getFill());
  }
}
