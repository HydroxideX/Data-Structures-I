package eg.edu.alexu.csd.datastructure.linkedList.cs76_cs35;

public class Main {

  public static void main(String[] args) {
    int [][] x = {{1,-5},{3,-5}};
    int [][] y = {{5,-5},{7,-5}};
    Application z = new Application();
    z.setPolynomial('A',x);
    z.setPolynomial('B',y);
    z.setPolynomial('A',y);
    System.out.println(z.evaluatePolynomial('B',1));
    System.out.println(z.print('B'));
    z.clearPolynomial('B');
    System.out.println(z.print('R'));
  }
}
