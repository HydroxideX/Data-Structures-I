package eg.edu.alexu.csd.datastructure.linkedList.cs76_cs35;

import java.awt.Point;
import java.util.*;

public class DoublyLinkedList implements ILinkedList {
  public class Node {
    public Object data;
    public Node next;
    public Node previous;

    public Node(Object newdata) {
      data = newdata;
    }
  }

  public Node head;
  public Node tail;
  private int size = 0;

  public void add(int index, Object element) {
    if (index > size + 1 || index<0) {
      return;
    }

    Node temp = new Node(element);
    if (index == 0) {
      if (size == 0) {
        head = temp;
        tail = temp;
        size++;
        return;
      }
      temp.next = head;
      head = temp;
      size++;
      return;
    }
    if (index == size+1) {
      if (size == 0) {
        return;
      }
      tail.next = temp;
      temp.previous = tail;
      tail = temp;
      size++;
      return;
    }
    Node current = getIndex(index);
    if (current.next == null) {
      current.next = temp;
      temp.previous = current;
    } else {
      temp.next = current.next;
      current.next = temp;
      temp.next.previous = temp;
      temp.previous = current;
    }
    size++;
  }

  public void add(Object element) {
    if (size == 0) {
      head = new Node(element);
      tail = head;
    } else {
      Node current = head;
      for (; current.next != null;) {
        current = current.next;
      }
      Node temp = new Node(element);
      temp.previous = current;
      current.next = temp;
    }
    size++;
  }

  private Node getIndex(int index) {
    if (index >= size || index<0) {
      return null;
    }
    Node current = head;
    for (int j = 1; j < index; j++) {
      current = current.next;
    }
    return current;
  }



  public Object get(int index) {
    if (index >= size  || index<0) {
      return null;
    }
    Node iterator = head;
    int j = 0;
    while (j++ != index) {
      iterator = iterator.next;
    }
    return iterator.data;
  }

  public void set(int index, Object element) {
    if (index > size-1 || index<0) {
      return;
    }
    Node temp = new Node(element);
    if (index == 0) {
      if (size == 0) {
        return;
      }
      head.data = element;
      return;
    }
    Node current = getIndex(index);
    current.next.data = element;
  }


  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void remove(int index) {
    if (index >= size  || index < 0) {
      return;
    }
    if (index == size - 1) {
      tail = tail.previous;
    }
    if (index == 0) {
      head = head.next;
      head.previous = null;
      size--;
      return;
    }
    Node current = getIndex(index);
    current.next = current.next.next;
    if (current.next != null) {
      current.next.previous = current;
    }
    size--;
  }

  public int size() {
    return size;
  }

  public DoublyLinkedList sublist(int fromIndex, int toIndex) {
    DoublyLinkedList newList = new DoublyLinkedList();
    if (fromIndex <0 || fromIndex >= size || toIndex <0 || toIndex >= size) {
      return newList;
    }
    Node current = getIndex(fromIndex);
    int j = fromIndex;
    while (current != null && j++ <= toIndex) {
      newList.add(current.data);
      current = current.next;
    }
    return newList;
  }

  public boolean contains(Object o) {
    Node iterator = head;
    while (iterator != null && iterator.data != o) {
      iterator = iterator.next;
    }
    return iterator != null;
  }

  public void print() {
    Node current = head;
    while (current != null) {
      System.out.println(current.data);
      current = current.next;
    }
  }
}
