package eg.edu.alexu.csd.datastructure.linkedList.cs76_cs35;


//import com.sun.org.apache.xpath.internal.SourceTree;

//import javax.xml.soap.Node;
import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.util.Scanner;

public class Main {
  private static Scanner scanner = new Scanner(System.in);
  public static Application newList = new Application();
  public static void main(String[] args) {
    int[][] array = new int[1][2];
    char poly;
    boolean end = false;
    boolean error;
    int input;
    char poly1;
    char poly2;
    while (!end) {
      printLayout();
      try {
        input = scanner.nextInt();
      } catch (Exception e) {
        System.out.println("Wrong Format Enter an integer please");
        continue;
      }
      if (input > 0 && input < 9) {
        switch (input) {
          case 1:
            System.out.println("Insert the variable name: A, B or C");
            try {
              poly = getPoly();
            } catch (Exception e) {
              System.out.println("Wrong Variable Name");
              continue;
            }
            System.out.println("Insert the polynomial terms in the form:\n(coeff1, exponent1), (coeff2, exponent2), ..");
            try {
              array = getTerms();
            } catch (Exception e) {
              System.out.println("Wrong Format... returning to Home");
              continue;
            }
            newList.setPolynomial(poly, array);
            System.out.println("Polynomial " + poly + " is set");
            break;
          case 2:
            System.out.println("Enter the polynomial you want To print");
            try {
              poly = getPolyForString();
            } catch (Exception e) {
              System.out.println("Wrong Format... returning to Home");
              continue;
            }
            System.out.print("Polynomial " + poly + " =" + " ");
            printPolynomial(poly);
            System.out.println(" ");
            break;
          case 3:
            System.out.println("Which polynomials you want to add ? ex A, B");
            try {
              poly1 = getPoly();
              poly2 = getPoly();
            } catch (Exception e) {
              System.out.println("Wrong Format... returning to Home");
              continue;
            }
            newList.add(poly1, poly2);

            break;
          case 4:
            System.out.println("Which polynomials you want to subtract ? ex A, B");
            try {
              poly1 = getPoly();
              poly2 = getPoly();
            } catch (Exception e) {
              System.out.println("Wrong Format... returning to Home");
              continue;
            }
            newList.subtract(poly1, poly2);
            break;
          case 5:
            System.out.println("Wrong Format... returning to Home");
            try {
              poly1 = getPoly();
              poly2 = getPoly();
            } catch (Exception e) {
              System.out.println("Wrong Format... returning to Home");
              continue;
            }
            int[][] array2 = newList.multiply(poly1, poly2);
            break;
          case 6:
            System.out.println("Enter the polynomial");
            try {
              poly1 = getPoly();
            } catch (Exception e) {
              System.out.println("Wrong Format... Restarting");
              continue;
            }
            System.out.println("Enter the value");
            float value;
            try {
              value = scanner.nextFloat();
            } catch (Exception e) {
              System.out.println("Wrong Format... returning to Home");
              continue;
            }
            System.out.println(newList.evaluatePolynomial(poly1, value));
            break;
          case 7:
            System.out.println("Enter the polynomial you want to clear");
            try {
              poly1 = getPolyForString();
            } catch (Exception e) {
              System.out.println("Wrong Format... returning to Home");
              continue;
            }
            newList.clearPolynomial(poly1);
            System.out.println("Polynomial " + poly1 + " cleared");
            break;
          case 8:
            System.out.println("Exit the program");
            end = true;
            break;
        }
      }
    }
  }

  public static void printLayout() {
    System.out.println("Please choose an action");
    System.out.println("------------------------");
    System.out.println("1- Set a polynomial variable");
    System.out.println("2- Print the value of a polynomial variable");
    System.out.println("3- Add two polynomials");
    System.out.println("4- Subtract two polynomials");
    System.out.println("5- Multiply two polynomials");
    System.out.println("6- Evaluate a polynomial at some point");
    System.out.println("7- Clear a polynomial variable");
    System.out.println("8- Exit");
  }

  public static char getPoly() {
    char poly = 0;
    boolean error = true;
    while (error) {
      poly = scanner.next().charAt(0);
      if (poly == 'A' || poly == 'B' || poly == 'C') {
        error = false;
      }
    }
    return poly;
  }

  public static char getPolyForString() {
    char poly = 0;
    boolean error = true;
    while (error) {
      poly = scanner.next().charAt(0);
      if (poly == 'A' || poly == 'B' || poly == 'C' || poly == 'R') {
        error = false;
      } else {
        System.out.println("Error : Choose A,B,C or R only");
        error = true;
      }
    }
    return poly;
  }

  public static int[][] getTerms() {
    System.out.println("Enter terms number");
    int termsNumber = scanner.nextInt();
    int[][] array = new int[termsNumber][2];
    int check;
    for (int i = 0;i < termsNumber;i++) {
      System.out.println("Enter term number " + (i + 1));
      array[i][0] = scanner.nextInt();
      array[i][1] = scanner.nextInt();
    }
    return array;
  }

  public static void printPolynomial(char poly) {
    String a = newList.print(poly);
    System.out.print(a);
  }
}
