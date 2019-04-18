package eg.edu.alexu.csd.datastructure.linkedList.cs76_cs35;

public class SinglyLinkedList implements ILinkedList {
    MyListNode head;

    public class MyListNode{
        Object data;
        private MyListNode next;

        public MyListNode(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    // Main Methods
        // Displays the list
    public void display(MyListNode head) {
        if(head == null)
            return;
        MyListNode current = head;
        while(current!=null) {
            System.out.print(" "+current.data);
            current=current.next;
        }
    }
        // return the length of head node
    public int getLength(MyListNode head) {
        if(head==null) {
            return 0;
        } else
            return 1 + getLength(head.next);

    }

    //Inserts a specified element at the specified position in the list
    public void add(int index, Object element) {
        if(index > getLength(head)-1) {
            System.out.println("Error : Index Exceeding Limits");
            return;
        }
        MyListNode newNode = new MyListNode(element);
        MyListNode current = head;
        if(index==0) {
            newNode.next=head;
            head=newNode;
        } else {
            int position = 0;

            while(position < index-1) {
                current= current.next;
                position++;
            }
            newNode.next=current.next;
            current.next=newNode;
        }
    }

    // Insert at the end
    // Changed name from add to addToEnd
    public void addToEnd(Object element){
        MyListNode newNode =  new MyListNode(element);
        if(head == null) {
            head=newNode;
            return;
        }
        MyListNode current = head;
        while(current.next!=null) {
            current=current.next;
        }
        current.next=newNode;
        newNode.next=null;
    }

    // Return the element at the specified position in this list
    public Object get(int index){
        if(index>getLength(head)-1) {
            System.out.println("Error");
            return head;
        }
        MyListNode current = head;
        if(index==0) {
            current = head;
        } else {
            MyListNode previous = head;
            int count=0;
            while(count < index) {
                previous = previous.next;
                count++;
            }
            current = previous;
        }
        return current.data;
    }

    //Replaces the element at the specified position in this list with the specified element.
    public void set(int index, Object element) {
        if(index > getLength(head)-1) {
            System.out.println("Error : Index Exceeding Limits");
            return;
        }
        MyListNode newNode = new MyListNode(element);
        MyListNode current = head;
        MyListNode previous= head;
        if(index==0) {
            newNode.next=head.next;
            current.next=null;
            head=newNode;
        } else {
            int position1 = 0;
            int position2 = 0;
            // Get previous node
            while(position2 < index-1) {
                previous= previous.next;
                position2++;
            }
            // Get current node which will be removed
            while(position1 < index) {
                current= current.next;
                position1++;
            }
            newNode.next=current.next;
            current.next=null;
            previous.next=newNode;
        }
    }

    //Removes all of the elements from this list
    public void clear() {
        head.next=null;
        head=null;
        System.out.println("All Items Are Cleared");
    }

    //Returns true if this list contains no elements
    public boolean isEmpty() {
        if(head==null) {
            System.out.println("The List Is Empty !");
            return true;
        }
        System.out.println("The List Is not Empty !");
        return false;
    }

    // Removes a node by getting its index
    public void remove(int index) {
        int size = getLength(head);
        if(index>size || index<0) {
            System.out.println("Error : Index Exceeding Limits");
        }
        if(index==0) {
            MyListNode temp = head;
            head = head.next;
            temp.next=null;
        } else {
            MyListNode previous=head;
            int count=0;
            while(count<index) {
                previous=previous.next;
                count++;
            }
            MyListNode current = previous.next;
            previous.next=current.next;
            current.next=null;
            System.out.println("Node At Index "+index+ " Was Removed Successfully !");
        }
    }

    // Returns the number of elements in this list
    public int size() {
        if(head==null) {
            return 0;
        } else
            return 1 + getLength(head.next);
    }

    // Returns a view of the portion of this list between the specified
    // fromIndex and toIndex, inclusively.
    public ILinkedList sublist(int fromIndex, int toIndex) {
        return null;
    }

    // Searches if the element exists or not if yes return true if no return false
    // It only catches the first represent of the element o
    public boolean contains(Object o) {
        MyListNode current=head;
        int position=0;
        while(current!=null) {
            if(current.data==o) {
                System.out.println("Element Was Found At Position "+ position);
                return true;
            } else {
                current=current.next;
                position++;
            }
        }
        System.out.println("Element Was Not Found !");
        return false;
    }


}
