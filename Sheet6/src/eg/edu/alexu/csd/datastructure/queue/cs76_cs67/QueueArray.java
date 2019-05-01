package eg.edu.alexu.csd.datastructure.queue.cs76_cs67;

public class QueueArray implements IQueue ,IArrayBased {
  private int first = 0;
  private int last = 0;
  private int size = 0;
  private Object [] array;

  public QueueArray(int n) {
    array = new Object[n];
  }

  /**
   * Inserts an item at the queue front.
   */
  public void enqueue(Object item) {
    if (size == array.length) {
      System.out.println("Queue is Full");
    } else if (first == array.length - 1) {
      array[array.length - 1] = item;
      first = 0;
      size++;
    } else {
      array[first++] = item;
      size++;
    }
  }
  /**
   * Removes the object at the queue rear and returns it.
   */

  public Object dequeue() {
    Object data = null;
    if (size == 0) {
      System.out.println("Queue is Empty");
      return null;
    } else if (last < first || (last > first && last != array.length - 1)
              || (last == first && last != array.length - 1 && size == array.length)) {
      data = array[last];
      array[last] = null;
      last++;
    } else if ((last > first && last == array.length - 1)
              || (last == first && last == array.length - 1 && size == array.length)) {
      data = array[last];
      array[last] = null;
      last = 0;
    } else if (last == first && last == array.length - 1 && size == 1) {
      data = array[last];
      array[last] = null;
      last = 0;
      first = 0;
    } else if (last == first && last != array.length - 1 && size == 1) {
      data = array[last];
      array[last] = null;
    }
    size--;
    return data;
  }
  /**
   * Tests if this queue is empty.
   */

  public boolean isEmpty() {
    return size == 0;
  }
  /**
   * Returns the number of elements in the queue.
   */

  public int size() {
    return size;
  }
}
