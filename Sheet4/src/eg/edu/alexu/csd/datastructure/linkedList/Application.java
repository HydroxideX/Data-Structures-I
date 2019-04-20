package eg.edu.alexu.csd.datastructure.linkedList.cs76_cs35;
import java.awt.Point;
import java.util.*;
import java.util.zip.DeflaterOutputStream;

public class Application extends DoublyLinkedList implements IPolynomialSolver {
    private DoublyLinkedList A = new DoublyLinkedList();
    private DoublyLinkedList B = new DoublyLinkedList();
    private DoublyLinkedList C = new DoublyLinkedList();
    private DoublyLinkedList R = new DoublyLinkedList();

    public void setPolynomial(char poly, int[][] terms) {
        if(terms.length == 0 || terms[0] == null)
            return;
        if (poly == 'A') {
            A.clear();
            addToList(A,terms);
            sort(A);
            simplify(A);
        } else if (poly == 'B') {
            B.clear();
            addToList(B,terms);
            sort(B);
            simplify(B);
        } else if (poly == 'C') {
            C.clear();
            addToList(C,terms);
            sort(C);
            simplify(C);
        }
    }

    private void addToList(DoublyLinkedList list,int [][] array) {
        for (int i = 0; i < array.length; i++) {
            Point temp = new Point(array[i][0],array[i][1]);
            list.add(temp);
        }
    }
    //Done
    private void sort(DoublyLinkedList list) {
        for (Node current = list.head; current != null; current = current.next) {
            for (Node current2 = list.head; current2.next != null; current2 = current2.next) {
                Point point1 = (Point)current2.data;
                Point point2 = (Point)current2.next.data;
                if (point1.y > point2.y) {
                    Object temp = current2.next.data;
                    current2.next.data = current2.data;
                    current2.data = temp;
                }
            }
        }
    }

    public String print(char poly) {
        String str = "";
        if (poly == 'A') {
            str = getString(A);
        } else if (poly == 'B') {
            str = getString(B);
        } else if (poly == 'C') {
            str = getString(C);
        } else if (poly == 'R') {
            str = getString(R);
        }
        return str;
    }

    private String getString(DoublyLinkedList list) {
        String str = "";
        for (Node current = list.head; current != null; current = current.next) {
            Point point = (Point)current.data;
            int x = point.x;
            int y = point.y;
            if (y == 0) {
                if (x < 0) {
                    str= str + Integer.toString(x);
                } else if (x == 0){
                }
                else str = str + "+" + Integer.toString(x);
            } else {
                if (x < 0) {
                    str = str + Integer.toString(x) + "x^" + Integer.toString(y);
                } else if (x == 0) {
                } else {
                    str= str + ("+" + Integer.toString(x) + "x^" + Integer.toString(y));
                }
            }
        }
        return str;
    }

    public void clearPolynomial(char poly) {
        if (poly == 'A') {
            A.clear();
        } else if (poly == 'B') {
            B.clear();
        } else if (poly == 'C') {
            C.clear();
        } else if (poly == 'R') {
            R.clear();
        }
    }

    public float evaluatePolynomial(char poly, float value) {
        float result;
        if (poly == 'A') {
            result = substituteInPoly(A,value);
        } else if (poly == 'B') {
            result = substituteInPoly(B,value);
        } else if (poly == 'C') {
            result = substituteInPoly(C,value);
        } else if (poly == 'R') {
            result = substituteInPoly(R,value);
        } else {
            System.out.println("Wrong Variable or Variable not set");
            return 0;
        }
        return result;
    }

    private float substituteInPoly(DoublyLinkedList list,float value) {
        double result = 0;
        for (Node current = list.head; current != null; current = current.next) {
            Point temp = (Point)current.data;
            double x = temp.x;
            double y = temp.y;
            result += x * java.lang.Math.pow(value,y);
        }
        if (head == null || list.size()==0) {
            System.out.print("Variable is not set return error: ");
        }
        return (float)result;
    }

    public int[][] add(char poly1,char poly2) {
        R.clear();
        return operation(poly1,poly2,'+');
    }

    private int[][] operation(char poly1, char poly2,char sign) {
        DoublyLinkedList first = getList(poly1);
        DoublyLinkedList second = getList(poly2);
        boolean[] visited = new boolean[second.size()];
        Point temp1 = new Point();
        Point temp2 = new Point();
        int j = 0;
        R.clear();
        for (Node current = first.head; current != null; current = current.next) {
            temp1 = (Point) current.data;
            j = 0;
            Point temp3 = (Point) current.data;
            for (Node current2 = second.head; current2 != null; current2 = current2.next) {
                temp2 = (Point)current2.data;
                if (temp2.y == temp1.y) {
                    if (sign == '+') {
                        temp3.x += temp2.x;
                    }
                    if (sign == '-') {
                        temp3.x -= temp2.x;
                    }
                    visited[j] = true;
                }
                j++;
            }
            R.add(temp3);
        }
        j = 0;
        for (Node current = second.head; current != null; current = current.next) {
            if (!visited[j++]) {
                Point temp = (Point)current.data;
                R.add(temp);
            }
        }
        sort(R);
        return turnToArray(R);
    }

    private int[][] turnToArray(DoublyLinkedList list) {
        int [][] expression = new int[list.size()][2];
        int j = 0;
        for (Node current = list.head; current != null;current = current.next) {
            Point temp = (Point)current.data;
            expression[j][0] = temp.x;
            expression[j][1] = temp.y;
            j++;
        }
        return expression;
    }

    private DoublyLinkedList findList(char ch) {
        DoublyLinkedList list = null;
        if (ch == 'A') {
            list = A;
        } else if (ch == 'B') {
            list = B;
        } else if (ch == 'C') {
            list = C;
        }
        return list;
    }

    public int[][] subtract(char poly1, char poly2) {
        R.clear();
        return operation(poly1,poly2,'-');
    }

    public int[][] multiply(char poly1, char poly2) {
        R.clear();
        DoublyLinkedList first = getList(poly1);
        DoublyLinkedList second = getList(poly2);
        Node current = first.head;
        for (;current != null; current = current.next) {
            Point temp1 = (Point) current.data;
            for (Node current2 = second.head;current2 != null; current2 = current2.next) {
                Point temp2 = (Point) current2.data;
                Point temp3 = new Point();
                temp3.x = temp1.x * temp2.x;
                temp3.y = temp1.y + temp2.y;
                R.add(temp3);
            }
        }
        simplify(R);
        sort(R);
        return turnToArray(R);
    }

    private DoublyLinkedList getList(char poly1) {
        DoublyLinkedList newList = null;
        while (newList == null) {
            System.out.println("Insert the first operand variable name: A, B or C");
            newList = findList(poly1);
            if (newList == null) {
                System.out.println("Variable not set");
            }
        }
        return newList;
    }

    private void simplify(DoublyLinkedList list) {
        int j = 0;
        for (Node current = list.head; current != null; current = current.next) {
            Point temp1 = (Point)current.data;
            j = 0;
            for (Node current2 = list.head; current2 != null; current2 = current2.next) {
                if (current2 == current) {
                    j++;
                    continue;
                }
                Point temp2 = (Point)current2.data;
                if (temp2.y == temp1.y) {
                    temp1.x += temp2.x;
                    current.data = temp1;
                    current2 = current2.previous;
                    list.remove(j);
                    continue;
                }
                j++;
            }
        }
    }
}
