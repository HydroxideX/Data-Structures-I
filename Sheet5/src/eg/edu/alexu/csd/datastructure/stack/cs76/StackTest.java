package eg.edu.alexu.csd.datastructure.stack.cs76;

import static org.junit.Assert.*;
import static org.junit.Test.*;

public class StackTest extends Evaluator {
  Stack x = new Stack();
  @org.junit.Test
  public void pop() {
    x.push(5);
    assertEquals(5,x.pop());
    assertEquals(null,x.pop());
  }

  @org.junit.Test
  public void peek() {
    x.push(5);
    assertEquals(5,x.peek());
  }

  @org.junit.Test
  public void isEmpty() {
    x.push(5);
    assertEquals(x.isEmpty(),false);
    x.pop();
    assertEquals(x.isEmpty(),true);
  }

  @org.junit.Test
  public void size() {
    assertEquals(x.size(),0);
    x.push(3);
    assertEquals(x.size(),1);
    x.push(77);
    assertEquals(x.size(),2);
    x.pop();
    assertEquals(x.size(),1);
    x.pop();
    assertEquals(x.size(),0);
  }
}