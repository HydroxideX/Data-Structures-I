package eg.edu.alexu.csd.datastructure.queue.cs76_cs67;

public class QueueLinkedList implements IQueue , ILinkedBased{
  /**
   * Inserts an item at the queue front.
   */
  public class DoublyLinkedList {

    public class Node {
      private Object data;
      private Node next;
      private Node previous;

      private Node(Object newdata) {
        data = newdata;
      }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    /**
     * adds Object to front.
     * @param element to insert.
     */

    public void add(Object element) {
      Node temp = new Node(element);
      if (size == 0) {
        head = temp;
        tail = head;
      } else if (size == 1) {
        head.previous = temp;
        temp.next = head;
        head = temp;
        tail = head.next;
      } else {
        head.previous = temp;
        temp.next = head;
        head = temp;
      }
      size++;
    }
    /**
     * removes tail.
     *.
     */

    public Object removeTail() {
      if (size == 0) {
        return null;
      }
      Object data = tail.data;
      size--;
      tail.data = null;
      if (tail.previous == null) {
        return data;
      }
      tail = tail.previous;
      return data;
    }
    /**
     * Checks if LinkedList is empty.
     * @return true if size is 0.
     */

    public boolean isEmpty() {
      return size == 0;
    }

    /**
     * Checks size of LinkedList.
     * @return size.
     */
    public int size() {
      return size;
    }
  }

  private DoublyLinkedList queue = new DoublyLinkedList();

  public void enqueue(Object item) {
    queue.add(item);
  }
  /**
   * Removes the object at the queue rear and returns it.
   */

  public Object dequeue() {
    return queue.removeTail();
  }
  /**
   * Tests if this queue is empty.
   */

  public boolean isEmpty() {
    return queue.isEmpty();
  }
  /**
   * Returns the number of elements in the queue.
   */

  public int size() {
    return queue.size();
  }
}
