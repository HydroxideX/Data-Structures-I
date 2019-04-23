package eg.edu.alexu.csd.datastructure.stack.cs76;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Evaluator ExpressionValue = new Evaluator();
    Scanner input = new Scanner(System.in);
    boolean stillWorking = true;
    while (stillWorking ) {
      System.out.println("Enter The infix expression to be evaluated:");
      String expression = input.nextLine();
      try {
        int z = ExpressionValue.evaluate(expression);
      } catch(Exception e) {
        System.out.println("Wrong Expression");
      }
      System.out.println("Do you want to evaluate another expression?(Y/N)");
      char x = input.nextLine().charAt(0);
      if (x == 'N') {
        stillWorking = false;
      }
    }
  }
}
