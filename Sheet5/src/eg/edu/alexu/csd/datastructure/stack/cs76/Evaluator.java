package eg.edu.alexu.csd.datastructure.stack.cs76;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Evaluator implements IExpressionEvaluator {

  public static void main(String[] args) {
    Evaluator ExpressionValue = new Evaluator();
    Scanner input = new Scanner(System.in);
    boolean stillWorking = true;
    while (stillWorking ) {
      char choice = 0;
      while (choice != '1' && choice != '2') {
        System.out.println("Enter 1 to use convert an expression from infix to postfix or 2 to evaluate a postfix expression:");
        choice = input.nextLine().charAt(0);
      }
      if (choice == '1') {
        try {
          String z = null;
          while(z == null) {
            System.out.println("Enter The infix expression to be converted:");
            String expression = input.nextLine();
            z = ExpressionValue.infixToPostfix(expression);
            if (z != null)
              System.out.println(z);
            else {
              System.out.println("Wrong Expression Enter Again");
            }
          }
        } catch (Exception e) {
          System.out.println("Wrong Expression");
        }
      }
      else if (choice == '2') {
        System.out.println("Enter The postfix expression to be evaluated:");
        String expression = input.nextLine();
        try {
          int z = ExpressionValue.evaluate(expression);
        } catch (Exception e) {
          System.out.println("Wrong Expression");
        }
      }
      System.out.println("Do you want to do another operation ?(Y/N)");
      char x = input.nextLine().charAt(0);
      if (x == 'N') {
        stillWorking = false;
      }
    }
  }
  /**
   * Takes a symbolic/numeric infix expression as input and converts it to
   * postfix notation. There is no assumption on spaces between terms or the
   * length of the term (e.g., two digits symbolic or numeric term)
   *
   * @param expression
   *
  infix expression
   * @return postfix expression
   */
  public  String infixToPostfix (String expression) {
    int i = 0;
    Stack operators = new Stack();
    Stack res = new Stack();
    Stack temp = new Stack();
    char lastChar = '1';
    boolean visited = false;
    while (i != expression.length()) {
      if(i<expression.length() && expression.charAt(0) == '-' && !visited) {
        visited = true;
        StringBuilder digits = new StringBuilder();
        i++;
        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
          digits.append(expression.charAt(i));
          i++;
        }
        int no = Integer.parseInt(digits.toString());
        no = -1 * no;
        res.push(no);
        lastChar = '1';
      }
      if(i<expression.length() && expression.charAt(i) == '-' && (lastChar == '+' || lastChar == '*')) {
        i++;
        while (expression.charAt(i) == ' ') {
          i++;
        }
        if (Character.isDigit(expression.charAt(i))) {
          StringBuilder digits = new StringBuilder();
          while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
            digits.append(expression.charAt(i));
            i++;
          }
          int no = Integer.parseInt(digits.toString());
          no = -1 * no;
          res.push(no);
        }
        lastChar = '1';
      }
      if (i<expression.length() && Character.isDigit(expression.charAt(i))) {
        StringBuilder digits = new StringBuilder();
        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
          digits.append(expression.charAt(i));
          i++;
        }
        int no = Integer.parseInt(digits.toString());
        res.push(no);
        lastChar = '1';
      }

      if (i < expression.length()) {
        if (expression.charAt(i) == '(' || expression.charAt(i) == '{'
            || expression.charAt(i) == '[') {
          operators.push('(');
        } else if (expression.charAt(i) == ')' || expression.charAt(i) == '}'
                  || expression.charAt(i) == ']') {
          while ((char)operators.peek() != '(') {
            res.push(operators.pop());
          }
          operators.pop();
        } else if  (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
          while (!operators.isEmpty() && ((char)operators.peek() == '*'
            || (char)operators.peek() == '/')) {
            res.push(operators.pop());
          }
          if (i < expression.length() && operators.size() > 0
              && ((char)operators.peek() == '+' || (char)operators.peek() == '-')) {
            res.push(operators.pop());
          }
          operators.push(expression.charAt(i));
          lastChar = '+';
        } else if  (expression.charAt(i) == '*' || expression.charAt(i) == '/') {
          operators.push(expression.charAt(i));
          lastChar = '*';
        }
        i++;
      }
    }
    while (!operators.isEmpty()) {
      res.push(operators.pop());
    }
    StringBuilder finalExpression = new StringBuilder();
    Stack operandsCheck = new Stack();
    Stack operatorsCheck = new Stack();
    while (res.peek() != null) {
      if (Character.isDigit(res.peek().toString().charAt(0)) || (res.peek().toString().length()>1 && res.peek().toString().charAt(0)=='-')) {
        operandsCheck.push(res.peek());
      } else {
        operatorsCheck.push(res.peek());
      }
      finalExpression.append(res.pop());
      finalExpression.append(" ");
    }
    if (operandsCheck.size()-operatorsCheck.size() != 1) {
      return null;
    }
    String x = finalExpression.toString();
    String y = "";
    for (i = x.length() - 2; i >= 0; ) {
      int first = i;
      if (Character.isDigit(x.charAt(i))) {

        while (i > 0 && x.charAt(i - 1) != ' ') {
          i--;
        }
        y = y + x.substring(i, first + 1);
        i--;
      } else {
        y += x.charAt(i--);
      }
    }
    return y;
  }

  /**
   * Evaluate a postfix numeric expression, with a single space separator.
   *
   * @param expression
   *
  postfix expression
   * @return the expression evaluated value
   */
  public int evaluate(String expression) {
    Stack operands = new Stack();
    int i = 0;
    int firstIndex;
    while (i < expression.length()) {
      while (i<expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == ' ')) {
        if (Character.isDigit(expression.charAt(i))) {
          firstIndex = i;
          while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
            i++;
          }
          double v = Double.parseDouble(expression.substring(firstIndex, i));
          operands.push(v);
        } else {
          i++;
        }
      }
      if (i<expression.length()-1 && expression.charAt(i) == '-' && Character.isDigit(expression.charAt(i+1))) {
        i++;
        if (Character.isDigit(expression.charAt(i))) {
          firstIndex = i;
          while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
            i++;
          }
          double v = Double.parseDouble(expression.substring(firstIndex, i));
          v = -1 * v;
          operands.push(v);
        } else {
          i++;
        }
      }
      if (i < expression.length()) {
        switch (expression.charAt(i)) {
          case '+': {
            double x = Double.parseDouble(operands.pop().toString());
            double y = Double.parseDouble(operands.pop().toString());
            Double z = x + y;
            operands.push(z);
            i++;
            break;
          }
          case '-': {
            if (i <expression.length()-1 && Character.isDigit(expression.charAt(i+1))) {
              continue;
            }
            double y = Double.parseDouble(operands.pop().toString());
            double x = Double.parseDouble(operands.pop().toString());
            Double z = x - y;
            operands.push(z);
            i++;
            break;
          }
          case '*': {
            double x = Double.parseDouble(operands.pop().toString());
            double y = Double.parseDouble(operands.pop().toString());
            Double z = x * y;
            operands.push(z);
            i++;
            break;
          }
          case '/': {
            double y = Double.parseDouble(operands.pop().toString());
            double x = Double.parseDouble(operands.pop().toString());
            if (y-0<0.000001) {
              System.out.println("Wrong Input Can't Divide by zero");
              return 0;
            }
            Double z = x / y;
            operands.push(z);
            i++;
            break;
          }
          default:
            break;
        }
      }
    }
    if (operands.size() > 1) {
      System.out.println("Error wrong Input exit code 1" );
      return 1;
    } else {
      System.out.println("The Value of the expression is " + (int) Double.parseDouble(operands.peek().toString()));
      return (int) Double.parseDouble(operands.pop().toString());
    }
  }
}
