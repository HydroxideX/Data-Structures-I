package eg.edu.alexu.csd.datastructure.stack.cs76;

import java.net.StandardSocketOptions;

import static org.junit.Assert.*;

public class EvaluatorTest extends Evaluator {
  @org.junit.Test
  public void infixToPostfix1() {
    Evaluator x = new Evaluator();
    String z = x.infixToPostfix("-1+5*(7-2/3+1*5/-2)/-2+7*-3");
    assertEquals("-1 5 7 2 3 / - 1 5 -2 / * + -2 / * + 7 -3 * +",z);
    z = x.infixToPostfix("1+55/(7+521/5+23-75)*-3+25");
    assertEquals("1 55 7 521 5 / + 23 + 75 - -3 * / + 25 +",z);
  }

  @org.junit.Test
  public void evaluate1() {
    Evaluator x = new Evaluator();
    int z = x.evaluate("-1 2 * 3 +");
    assertEquals(1,z);
    z = x.evaluate("-1 5 * 12 + 7 3 / + -3 * 5 / 6 * -7 +");
    assertEquals(-40,z);
  }
}