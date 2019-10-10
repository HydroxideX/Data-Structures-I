package eg.edu.alexu.csd.datastructure.queue.cs76_cs67;

import static org.junit.Assert.*;

public class QueueLinkedListTest {

  @org.junit.Test
  public void enqueueAndDeque() {
   QueueLinkedList x = new QueueLinkedList();
    x.enqueue(5);
    x.enqueue("hi");
    x.enqueue(7);
    assertEquals(5,x.dequeue());
    assertEquals("hi",x.dequeue());
    assertEquals(7,x.dequeue());
  }

  @org.junit.Test
  public void isEmpty() {
   QueueLinkedList x = new QueueLinkedList();
    assertEquals(true,x.isEmpty());
    x.enqueue(5);
    x.enqueue("hi");
    x.enqueue(7);
    assertEquals(false,x.isEmpty());
    x.dequeue();
    assertEquals(false,x.isEmpty());
    x.dequeue();
    assertEquals(false,x.isEmpty());
    x.dequeue();
    assertEquals(true,x.isEmpty());
    x.enqueue(5);
    x.enqueue("hi");
    x.enqueue(7);
    assertEquals(false,x.isEmpty());
    x.dequeue();
    assertEquals(false,x.isEmpty());
    x.dequeue();
    assertEquals(false,x.isEmpty());
    x.dequeue();
    assertEquals(true,x.isEmpty());
  }

  @org.junit.Test
  public void size() {
    QueueLinkedList x = new QueueLinkedList();


    x.enqueue(5);
    x.enqueue("hi");
    x.enqueue(7);
    x.enqueue(5);
    x.enqueue(5);
    assertEquals(5,x.size());
    x.dequeue();
    assertEquals(4,x.size());
    x.dequeue();
    assertEquals(3,x.size());
    x.dequeue();
    assertEquals(2,x.size());
    x.enqueue(5);
    assertEquals(3,x.size());
    x.enqueue("hi");
    assertEquals(4,x.size());
    x.enqueue(7);
    assertEquals(5,x.size());
    x.dequeue();
    assertEquals(4,x.size());
    x.dequeue();
    assertEquals(3,x.size());
    x.dequeue();
    assertEquals(2,x.size());
    x.dequeue();
    assertEquals(1,x.size());
    x.dequeue();
    assertEquals(0,x.size());
    x.dequeue();
    assertEquals(0,x.size());
  }
}