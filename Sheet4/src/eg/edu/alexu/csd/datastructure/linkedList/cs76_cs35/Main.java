  package eg.edu.alexu.csd.datastructure.linkedList.cs76_cs35;


  //import com.sun.org.apache.xpath.internal.SourceTree;

  //import javax.xml.soap.Node;
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
          String input;
          while (!end) {
              error = true;
              printLayout();
              while (error) {
                  error = false;
                  input = scanner.next();
                  if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8")) {
                      switch (input) {
                          case "1":
                              System.out.println("Insert the variable name: A, B or C");
                              poly = getPoly();
                              System.out.println("Insert the polynomial terms in the form:\n(coeff1, exponent1), (coeff2, exponent2), ..");
                              array = getTerms();
                              newList.setPolynomial(poly,array);
                              System.out.println("Polynomial " + poly + " is set");
                              break;
                          case "2":
                              System.out.println("Enter the polynomial you want To print");
                              poly = getPolyForString();
                              System.out.print("Polynomial " + poly + " =" + " ");
                              printPolynomial(poly);
                              System.out.println(" ");
                              break;
                          case "3":
                              System.out.println("Which polynomials you want to add ? ex A, B");
                              char poly1 = getPoly();
                              char poly2 = getPoly();
                              if(newList.isEmpty())
                              newList.add(poly1,poly2);

                              break;
                          case "4":
                              System.out.println("Which polynomials you want to subtract ? ex A, B");
                              char poly3 = getPoly();
                              char poly4 = getPoly();
                              newList.subtract(poly3,poly4);
                              break;
                          case "5":
                              System.out.println("Which polynomials you want to multiply ? ex A, B");
                              char poly5 = getPoly();
                              char poly6 = getPoly();
                              int[][] array2 = newList.multiply(poly5,poly6);
                              break;
                          case "6":
                              boolean error2=true;
                              System.out.println("Enter the polynomial");
                              char poly7 = getPoly();
                              while(error2) {
                                  error=false;
                                  System.out.println("Enter the value");
                                  String value = scanner.next();
                                  if (isNumeric(value)) {
                                      float d = Float.parseFloat(value);
                                      float result = newList.evaluatePolynomial(poly7, d);
                                      System.out.println("Result is " + result);
                                  } else {
                                      error=true;
                                      System.out.println("Error value must be numerical only");
                                  }
                              }
                              break;
                          case "7":
                              System.out.println("Enter the polynomial you want to clear");
                              char poly8 = getPolyForString();
                              newList.clearPolynomial(poly8);
                              System.out.println("Polynomial " + poly8 + " cleared");
                              break;
                          case "8":
                              System.out.println("Exit the program");
                              end = true;
                              break;
                      }

                  } else {
                      System.out.println("Error >> choose from 1 to 8");
                      error = true;
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
          String poly="Z";
          boolean error = true;
          while (error) {
              poly = scanner.next();
              if (poly.equals("A") || poly.equals("B") || poly.equals("C") ) {
                  error = false;
              } else {
                  System.out.println("Error : Choose A,B or C only");
                  error = true;
              }
          }

          return poly.charAt(0);
      }

      public static char getPolyForString() {
          String poly = "Z";
          boolean error = true;
          while (error) {
              poly = scanner.next();
              if (poly.equals("A") || poly.equals("B") || poly.equals("C") || poly.equals("R")) {
                  error = false;
              } else {
                  System.out.println("Error : Choose A,B,C or R only");
                  error = true;
              }
          }
          return poly.charAt(0);
      }

      public static int[][] getTerms() {
          boolean error=true;
          while(error) {
              System.out.println("Enter terms number");
              String termsNumber = scanner.next();
              if(isNumeric(termsNumber)) {
                  int[][] array = new int[Integer.parseInt(termsNumber)][2];
                  int check;
                  for (int i = 0;i < Integer.parseInt(termsNumber);i++) {
                      System.out.println("Enter term number " + (i + 1));
                      array[i][0] = scanner.nextInt();
                      array[i][1] = scanner.nextInt();
                  }
                  error=false;
                  return array;
              } else {
                  System.out.println("Error Choose Only Numbers !");
                  error=true;
              }

          }

          return null;
      }
      public static boolean isNumeric(String strNum) {
          try {
              double d = Double.parseDouble(strNum); // return the string in form of integer
          } catch (NumberFormatException | NullPointerException nfe) {
              return false;
          }
          return true;
      }
      public static void printPolynomial(char poly) {
          String a = newList.print(poly);
          System.out.print(a);
      }
  }