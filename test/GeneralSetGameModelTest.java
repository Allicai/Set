import org.junit.Test;

import java.util.ArrayList;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.Shape;
import cs3500.set.model.hw03.ASetGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the GeneralSetGameModelTest.
 */
public class GeneralSetGameModelTest {


  ASetGameModel game = new GeneralSetGameModel();
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


  /**
   * method to generate a deck that is useful for testing.
   * first three are a set!!
   */
  private void genDeck1() {
    deck.add(c2);
    deck.add(c16);
    deck.add(c19);
    deck.add(c5);
    deck.add(c7);
    deck.add(c8);
    deck.add(c12);
    deck.add(c16);
    deck.add(c24);
    deck.add(c13);
    deck.add(c15);
    deck.add(c23);
  }

  @Test
  public void testCompleteDeck() {
    assertEquals(27, game.completeDeck().size());
  }

  @Test
  public void testMakeGrid() { // testing random combinations of board renderings
    game.startGameWithDeck(game.getCompleteDeck(), 2, 7);
    assertEquals("3SD 3FD 3ED 3SO 3FO 3EO 3SQ\n3FQ 3EQ 2SD 2FD 2ED 2SO 2FO",
            view.toString());
    assertEquals(2, game.getHeight());
    assertEquals(7, game.getWidth());

    game.startGameWithDeck(game.getCompleteDeck(), 3, 3);
    assertEquals("3SD 3FD 3ED\n3SO 3FO 3EO\n3SQ 3FQ 3EQ", view.toString());
    assertEquals(3, game.getHeight());
    assertEquals(3, game.getWidth());

    game.startGameWithDeck(game.getCompleteDeck(), 1, 3);
    assertEquals("3SD 3FD 3ED", view.toString());
    assertEquals(1, game.getHeight());
    assertEquals(3, game.getWidth());
  }

  @Test
  public void testAddRowWhenNoSets() { // test for when the model should add a row instead of ending
    deck.add(c1);
    deck.add(c25);
    deck.add(c27);
    deck.add(c24);
    deck.add(c2);
    deck.add(c15);
    game.startGameWithDeck(deck, 1, 3);
    assertEquals("1ED 2EQ 3ED\n2ED 1FO 3SQ", view.toString());
    assertEquals(2, game.getHeight()); // make sure the height is actually 2
  }

  @Test
  public void testGeneralEnd() { // test where the game should end instead of adding a row
    deck = new ArrayList<>();
    deck.add(c1);
    deck.add(c25);
    deck.add(c27);
    game.startGameWithDeck(deck, 1, 3);
    assertTrue(game.isGameOver());
  }

  @Test
  public void testDontAddRowWhenSet() { // makes sure a row is not being added when there is a set
    genDeck1();
    game.startGameWithDeck(deck, 1, 3);
    assertEquals(1, game.getHeight());
    assertEquals("1FO 2FO 3FO", view.toString());
  }

  @Test
  public void testClaimValidSet() { // shows that claim set works and the score increases properly
    genDeck1();
    game.startGameWithDeck(deck, 3, 3);
    game.claimSet(new Coord(0, 0), new Coord(0, 1), new Coord(0, 2));
    assertEquals(1, game.getScore());
  }

  @Test
  public void testInvalidSetClaim() { // test that no points given for incorrect set
    genDeck1();
    game.startGameWithDeck(deck, 3, 3);
    try {
      game.claimSet(new Coord(0, 0), new Coord(0, 1), new Coord(5, 2));
      fail("Out of bounds for the board.");
    } catch (IllegalArgumentException e) {
      // ignore
    }
    assertEquals(0, game.getScore());
  }

  @Test
  public void testIncorrectSetClaim() { // exception is thrown and no points added
    genDeck1();
    game.startGameWithDeck(deck, 3, 3);
    try {
      game.claimSet(new Coord(1, 0), new Coord(0, 1), new Coord(0, 2));
      fail("Not a valid set.");
    } catch (IllegalArgumentException e) {
      // ignore
    }
    assertEquals(0, game.getScore());
  }

  @Test
  public void setClaimBeforeStart() { // checks for exception
    genDeck1();
    try {
      game.claimSet(new Coord(1, 0), new Coord(0, 1), new Coord(0, 2));
      fail("Game is not active.");
    } catch (IllegalStateException e) {
      // ignore
    }
  }

  @Test
  public void testGameOver() {
    // checks that the game ends after a set is claimed and no cards are left in deck
    this.deck.add(c19);
    this.deck.add(c12);
    this.deck.add(c1);
    this.deck.add(c18);
    this.deck.add(c24);
    this.deck.add(c7);
    this.deck.add(c25);
    this.deck.add(c21);
    this.deck.add(c10);
    game.startGameWithDeck(this.deck, 3, 3);
    game.claimSet(new Coord(1, 0), new Coord(2, 1), new Coord(2, 2));
    assertTrue(game.isGameOver());
  }

  @Test
  public void testInvalidHeightAndWidth() { // makes sure valid parameters are given
    try { // grid cant hold 3 cards
      game.startGameWithDeck(game.getCompleteDeck(), 1, 2);
      fail("Grid is too small.");
    } catch (IllegalArgumentException e) {
      // ignore
    }

    try { // grid cant hold 3 cards
      game.startGameWithDeck(game.getCompleteDeck(), 2, 1);
      fail("Grid is too small.");
    } catch (IllegalArgumentException e) {
      //ignore
    }

    try { // negative height
      game.startGameWithDeck(game.getCompleteDeck(), -1, 3);
      fail("Inappropriate board size.");
    } catch (IllegalArgumentException e) {
      //ignore
    }

    try { // negative width
      game.startGameWithDeck(game.getCompleteDeck(), 3, -1);
      fail("Inappropriate board size.");
    } catch (IllegalArgumentException e) {
      //ignore
    }
  }

  @Test
  public void testClaimOnLargeBoard() { // checks that claim set works properly on a larger board
    game.startGameWithDeck(game.getCompleteDeck(), 5, 5);
    try {
      game.claimSet(new Coord(0, 0), new Coord(0, 4), new Coord(1, 2));
      fail("Not a valid set.");
    } catch (IllegalArgumentException e) {
      // ignore
    }
    try {
      game.claimSet(new Coord(6, 2), new Coord(2, 1), new Coord(6, 9));
      fail("Out of bounds for the board.");
    } catch (IllegalArgumentException e) {
      // ignore
    }
    game.claimSet(new Coord(0, 0), new Coord(0, 1), new Coord(0, 2));
    assertEquals(1, game.getScore());
    assertTrue(game.isGameOver());
  }

  @Test
  public void testAnySetsPresent() { // checks when there is/is not a set on the board
    genDeck1();
    game.startGameWithDeck(deck, 1, 3);
    assertTrue(game.anySetsPresent());
  }

  @Test
  public void testPlayEntireGame() { // check to play a game all the way through
    Coord c1 = new Coord(0, 0);
    Coord c2 = new Coord(0, 1);
    Coord c3 = new Coord(0, 2);
    game.startGameWithDeck(game.getCompleteDeck(), 1, 3);
    assertEquals(0, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(1, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(2, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(3, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(4, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(5, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(6, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(7, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(8, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(9, game.getScore());
    assertEquals(1, game.getHeight());
    assertTrue(game.isGameOver());
  }

  @Test
  public void testInvalidDeck() { // exception for when the deck is smaller than the area
    genDeck1();
    try {
      game.startGameWithDeck(deck, 5, 5);
      fail("Deck is too small.");
    } catch (IllegalArgumentException e) {
      // ignore
    }
  }

  @Test
  public void testValidAndInvalidGetCardAtCoord() { // tests both get card methods
    // tests both methods of getting a card at a coordinate
    game.startGameWithDeck(game.getCompleteDeck(), 1, 3);
    assertEquals(new Card(Count.THREE, Filling.STRIPED, Shape.DIAMOND).toString(),
            game.getCardAtCoord(new Coord(0, 0)).toString());
    assertEquals(new Card(Count.THREE, Filling.STRIPED, Shape.DIAMOND).toString(),
            game.getCardAtCoord(0, 0).toString());
    try { // first method for when coordinates outside the dimensions are given
      game.getCardAtCoord(5, 3);
      fail("Coordinate is outside of the board dimensions.");
    } catch (IllegalArgumentException e) {
      // ignore
    }

    try {
      game.getCardAtCoord(1, 6);
      fail("Coordinate is outside of the board dimensions.");
    } catch (IllegalArgumentException e) {
      // ignore
    }

    try { // first method for when negative coordinates are given
      game.getCardAtCoord(-1, 2);
      fail("Coordinate is invalid.");
    } catch (IllegalArgumentException e) {
      // ignore
    }

    try {
      game.getCardAtCoord(1, -1);
      fail("Coordinate is invalid.");
    } catch (IllegalArgumentException e) {
      // ignore
    }

    try { // second method for when the coordinate is outside the dimensions given
      game.getCardAtCoord(new Coord(5, 2));
      fail("Coordinate is outside of board dimensions.");
    } catch (IllegalArgumentException e) {
      // ignore
    }

    try {
      game.getCardAtCoord(new Coord(1, 6));
      fail("Coordinate is outside of board dimensions.");
    } catch (IllegalArgumentException e) {
      // ignore
    }

    try { // second method for when coordinate with a negative field is given
      game.getCardAtCoord(new Coord(-1, 2));
      fail("Coordinate is invalid.");
    } catch (IllegalArgumentException e) {
      // ignore
    }

    try {
      game.getCardAtCoord(new Coord(1, -1));
      fail("Coordinate is invalid.");
    } catch (IllegalArgumentException e) {
      // ignore
    }
  }

  @Test
  public void testInvalidHeightWidthCalls() {
    try {
      game.getWidth();
      fail("Game has not begun.");
    } catch (IllegalStateException e) {
      // ignore for test
    }
    try {
      game.getHeight();
      fail("Game has not begun.");
    } catch (IllegalStateException e) {
      // ignore for test
    }
  }


}