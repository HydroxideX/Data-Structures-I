package Project;

import java.rmi.server.ExportException;
import java.util.Scanner;
import java.lang.*;
import java.math.MathContext;

public class Evaluator implements IExpressionEvaluator {

    /**
     * Takes a symbolic/numeric infix expression as input and classifys it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression
     * <p>
     * infix expression
     * @return postfix expression
     */
    private static final char[] rank1 = {'+', '-'};
    private static final char[] rank2 = {'*', '/'};
    private static final char[] rank3 = {'^'};
    private static final char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[] openParenthesis = {'(', '[', '{'};
    private static final char[] closeParenthesis = {')', ']', '}'};
    boolean lastIsNumerical;
    int openParenthesisNo;
    int lastNumber;
    int i = 0;
    boolean nextParenthesisIsNegative;

    public static void main(String[] args) {
        Evaluator ExpressionValue = new Evaluator();
        Scanner input = new Scanner(System.in);
        boolean stillWorking = true;
        while (stillWorking) {
            char choice = '0';
            while (choice != '1' && choice != '2') {
                System.out.println("Enter 1 to use classify an expression from infix to postfix or 2 to evaluate a postfix expression:");
                choice = input.nextLine().charAt(0);
            }
            if (choice == '1') {
                try {
                    String z = null;
                    while (z == null) {
                        System.out.println("Enter The infix expression to be classifyed:");
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
            } else if (choice == '2') {
                System.out.println("Enter The postfix expression to be evaluated:");
                String expression = input.nextLine();
                try {
                    ExpressionValue.evaluate(expression);
                } catch (Exception e) {
                    System.out.println("Wrong Expression");
                }
            }
            System.out.println("Do you want to do another operation ?(Y/N)");
            char x = input.nextLine().charAt(0);
            if (x == 'N' || x == 'n') {
                stillWorking = false;
            }
        }
        input.close();
    }

    public String infixToPostfix(String expression) throws Exception {
        i = 0;
        openParenthesisNo = 0;
        lastIsNumerical = false;
        nextParenthesisIsNegative = false;
        Stack operators = new Stack();
        Stack result = new Stack();
        i = 0;
        while (i < expression.length()) {
            if (expression.charAt(i) == ' ') {
                i++;
                continue;
            } else if (In(openParenthesis, expression.charAt(i))) {
                if (nextParenthesisIsNegative) result.push("-1");
                parenthesisHelper(expression, result, operators);
                handleNegativeParenthesis(nextParenthesisIsNegative, result, operators);
                lastIsNumerical = true;
                nextParenthesisIsNegative = false;
            } else if (In(closeParenthesis, expression.charAt(i))) {
                throw new Exception("Wrong Expression");
            } else {
                readNextValue(expression, lastIsNumerical, operators, result);
                lastIsNumerical = !lastIsNumerical;
            }
        }
        if (!lastIsNumerical) throw new Exception("Wrong Expression");
        while (!operators.isEmpty()) {
            result.push(operators.pop());
        }
        StringBuilder finalExpression = new StringBuilder();
        Stack operandsCheck = new Stack();
        Stack operatorsCheck = new Stack();
        while (result.peek() != null) {
            if (Character.isDigit(result.peek().toString().charAt(0)) || (result.peek().toString().length() > 1 && result.peek().toString().charAt(0) == '-')) {
                operandsCheck.push(result.peek());
            } else {
                operatorsCheck.push(result.peek());
            }
            finalExpression.append(result.pop());
            finalExpression.append(" ");
        }
        if (operandsCheck.size() - operatorsCheck.size() != 1) {
            return null;
        }
        String x = finalExpression.toString();
        String postfixExpression = "";
        int j;
        for (j = x.length() - 2; j >= 0; ) {
            int first = j;
            if (Character.isDigit(x.charAt(j))) {
                while (j > 0 && x.charAt(j - 1) != ' ') {
                    j--;
                }
                postfixExpression = postfixExpression + x.substring(j, first + 1);
                j--;
            } else {
                postfixExpression += x.charAt(j--);
            }
        }
        return postfixExpression;
    }

    private void parenthesisHelper(String expression, Stack result, Stack operators) throws Exception {
        openParenthesisNo++;
        Stack ParenthesizedStack = parenthesisComputation(expression);
        Stack tempStk = new Stack();
        while (!ParenthesizedStack.isEmpty()) {
            tempStk.push(ParenthesizedStack.pop());
        }
        while (!tempStk.isEmpty()) {
            result.push(tempStk.pop());
        }
        if (lastIsNumerical) {
            while (operators.size() > 0) {
                String topOperator = (String) operators.peek();
                if (In(rank2, topOperator.charAt(0)) || In(rank3, topOperator.charAt(0))) {
                    result.push(topOperator);
                    operators.pop();
                } else {
                    break;
                }
            }
        }
        openParenthesisNo--;
    }

    private Stack parenthesisComputation(String expression) throws Exception {
        i++;
        Stack operators = new Stack();
        Stack result = new Stack();
        boolean lastIsNumerical2 = false;
        boolean nextParenthesisIsNegative2 = false;
        while (i < expression.length()) {
            if (In(closeParenthesis, expression.charAt(i))) {
                while (operators.size() > 0) {
                    result.push(operators.pop());
                }
                i++;
                return result;
            } else if (expression.charAt(i) == ' ') {
                continue;
            } else if (In(openParenthesis, expression.charAt(i))) {
                if (nextParenthesisIsNegative2) result.push(-1);
                parenthesisHelper(expression, result, operators);
                handleNegativeParenthesis(nextParenthesisIsNegative2, result, operators);
                lastIsNumerical = true;
                nextParenthesisIsNegative2 = false;
            } else {
                nextParenthesisIsNegative2 = readNextValue(expression, lastIsNumerical2, operators, result);
                lastIsNumerical2 = !lastIsNumerical2;
            }
        }
        return result;
    }

    private void handleNegativeParenthesis(boolean nextParenthesisIsNegative, Stack result, Stack operators) {
        if (nextParenthesisIsNegative) {
            while (operators.size() > 0) {
                String topOperator = (String) operators.peek();
                if (In(rank2, topOperator.charAt(0)) || In(rank3, topOperator.charAt(0))) {
                    result.push(topOperator);
                    operators.pop();
                } else {
                    break;
                }
            }
            operators.push("*");
        }
    }

    private boolean readNextValue(String expression, boolean lastIsNumerical, Stack operators, Stack result) throws Exception {
        if (lastIsNumerical) {
            String ch = readOperator(expression);
            if (ch.length() == 1) {
                while (operators.size() > 0) {
                    String topOperator = (String) operators.peek();
                    if (In(rank3, topOperator.charAt(0)) && In(rank3, ch.charAt(0))) {
                        result.push(topOperator);
                        operators.pop();
                    } else if ((In(rank2, topOperator.charAt(0)) || In(rank3, topOperator.charAt(0))) && In(rank2, ch.charAt(0))) {
                        result.push(topOperator);
                        operators.pop();
                    } else if ((In(rank1, topOperator.charAt(0)) || In(rank2, topOperator.charAt(0))
                            || In(rank3, topOperator.charAt(0))) && In(rank1, ch.charAt(0))) {
                        result.push(topOperator);
                        operators.pop();
                    } else {
                        break;
                    }
                }
                operators.push(ch);
            }
        } else {
            String no = readNo(expression);
            return handleNo(expression, no, result);
        }
        return false;
    }

    private String readOperator(String expression) throws Exception {
        while (expression.charAt(i) == ' ') {
            i++;
        }
        if (In(number, expression.charAt(i))) {
            throw new Exception("Wrong Expression");
        } else if (In(openParenthesis, expression.charAt(i))) {
            return "*";
        } else if (In(closeParenthesis, expression.charAt(i))) {
            if (openParenthesisNo > 0) {
                return "";
            } else {
                throw new Exception("Wrong Expression");
            }
        } else {
            String x = "";
            x = x + expression.charAt(i);
            i++;
            return x;
        }
    }

    private String readNo(String expression) throws Exception {
        String no = "";
        Boolean negative = false;
        while (expression.charAt(i) == ' ') {
            i++;
        }
        if (In(openParenthesis, expression.charAt(i))) {
            return no;
        } else if (In(closeParenthesis, expression.charAt(i))) {
            throw new Exception("Wrong Expression");
        }
        while (i < expression.length()) {
            if (In(rank2, expression.charAt(i)) || In(rank3, expression.charAt(i))) {
                throw new Exception("Wrong Expression");
            } else if (In(rank1, expression.charAt(i))) {
                if (expression.charAt(i) == '-') {
                    negative = !negative;
                }
            } else {
                break;
            }
            i++;
        }
        if (i < expression.length()) {
            if (In(openParenthesis, expression.charAt(i))) {
                return "-" + no;
            } else if (In(closeParenthesis, expression.charAt(i))) {
                throw new Exception("Wrong Expression");
            }
        }
        while (i < expression.length()) {
            if (In(number, expression.charAt(i))) {
                no = no + expression.charAt(i);
            } else {
                break;
            }
            i++;
        }
        if (negative) no = "-" + no;
        return no;
    }

    private boolean handleNo(String expression, String no, Stack result) throws Exception {
        if (no.length() == 0) {
            return false;
        } else if (no.length() == 1 && no.charAt(0) == '-') {
            nextParenthesisIsNegative = true;
            return true;
        }
        result.push(no);
        return false;
    }

    public boolean In(char[] ar, char c) {
        for (int j = 0; j < ar.length; j++) {
            if (ar[j] == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Evaluate a postfix numeric expression, with a single space separator.
     *
     * @param expression postfix expression
     * @return the expression evaluated value
     */
    public int evaluate(String expression) {
        Stack operands = new Stack();
        i = 0;
        int firstIndex;
        while (i < expression.length()) {
            while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == ' ')) {
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
            if (i < expression.length() - 1 && expression.charAt(i) == '-' && Character.isDigit(expression.charAt(i + 1))) {
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
                        calculate(operands, '+', expression);
                        break;
                    }
                    case '-': {
                        calculate(operands, '-', expression);
                        break;
                    }
                    case '*': {
                        calculate(operands, '*', expression);
                        break;
                    }
                    case '/': {
                        calculate(operands, '/', expression);
                        break;
                    }
                    case '^': {
                        calculate(operands, '^', expression);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
        if (operands.size() > 1) {
            System.out.println("Error wrong Input exit code 1");
            return 1;
        } else {
            System.out.println("The Value of the expression is " + (int) Double.parseDouble(operands.peek().toString()));
            return (int) Double.parseDouble(operands.pop().toString());
        }
    }

    private void calculate(Stack operands, char ch, String expression) {
        double y = Double.parseDouble(operands.pop().toString());
        double x = Double.parseDouble(operands.pop().toString());
        Double z = (double) 0;
        switch (ch) {
            case '+': {
                z = x + y;
                break;
            }
            case '-': {
                if (i < expression.length() - 1 && Character.isDigit(expression.charAt(i + 1))) {
                    break;
                }
                z = x - y;
                break;
            }
            case '*': {
                z = x * y;
                break;
            }
            case '/': {
                if (y - 0 < 0.00000001) {
                    System.out.println("Wrong Input Can't Divide by zero");
                    return;
                }
                z = x / y;
                break;
            }
            case '^': {
                z = java.lang.Math.pow(x, y);
                break;
            }
            default:
                break;
        }
        operands.push(z);
        i++;
    }
}