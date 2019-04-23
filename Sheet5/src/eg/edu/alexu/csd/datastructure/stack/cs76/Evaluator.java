package eg.edu.alexu.csd.datastructure.stack.cs76;

public class Evaluator implements IExpressionEvaluator {
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
    while (i != expression.length()) {
      if (Character.isDigit(expression.charAt(i))) {
        StringBuilder digits = new StringBuilder();
        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
          digits.append(expression.charAt(i));
          i++;
        }
        int no = Integer.parseInt(digits.toString());
        res.push(no);
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
              && (expression.charAt(i) == '+' || expression.charAt(i) == '-')
              && ((char)operators.peek() == '+' || (char)operators.peek() == '-')) {
            res.push(operators.pop());
          }
          operators.push(expression.charAt(i));
        } else if  (expression.charAt(i) == '*' || expression.charAt(i) == '/') {
          operators.push(expression.charAt(i));
        }
        i++;
      }
    }
    while (!operators.isEmpty()) {
      res.push(operators.pop());
    }
    StringBuilder finalExpression = new StringBuilder();
    while (res.peek() != null) {
      finalExpression.append(res.pop());
      finalExpression.append(" ");
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
    expression = infixToPostfix(expression);
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
          operands.push(Double.parseDouble(expression.substring(firstIndex, i)));
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
