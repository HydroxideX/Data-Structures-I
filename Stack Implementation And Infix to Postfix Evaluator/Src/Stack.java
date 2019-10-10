package Project;

public class Stack implements IStack {
  private LinkedList list = new LinkedList();

  private class LinkedList {
    private class Node {
      private Object data;
      private Node next;

      private Node(Object newValue) {
        data = newValue;
      }

      private void setValue(Object newValue) {
        data = newValue;
      }

      private void setNext(Node next) {
        next = next;
      }

      private Object getValue() {
        return data;
      }

      private Node getNext() {
        return next;
      }
    }

    private Node head;
    private int size;

    private void insertNode(Object value) {
      Node node = new Node(value);
      node.next = head;
      head = node;
      size++;
    }

    private int getSize() {
      return size;
    }

    private Object removeHead() {
      Object headData = head.data;
      head = head.getNext();
      size--;
      return headData;
    }

    private boolean isEmpty() {
      return size == 0;
    }
  }

  /**
   * Removes the element at the top of stack and returns that element.
   *
   * @return top of stack element, or through exception if empty
   */

  public Object pop() {
    if (list.isEmpty()) {
      return null;
    } else {
      return list.removeHead();
    }
  }

  /**
   * Get the element at the top of stack without removing it from stack.
   *
   * @return top of stack element, or through exception if empty
   */

  public Object peek() {
    if (list.isEmpty()) {
      return null;
    } else {
      return list.head.data;
    }
  }
  /**
   * Pushes an item onto the top of this stack.
   *
   * @param element
   *
  to insert
   */

  public void push(Object element) {
    list.insertNode(element);
  }
  /**
   * Tests if this stack is empty.
   *
   * @return true if stack empty
   */

  public boolean isEmpty() {
    return list.isEmpty();
  }

  /**
   * Returns the number of elements in the stack.
   *
   * @return number of elements in the stack
   */

  public int size() {
    return list.getSize();
  }
  public void clear() { list.head = null;}
}
