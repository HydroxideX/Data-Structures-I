package eg.edu.alexu.csd.datastructure.iceHockey;
import javax.print.DocFlavor;
import java.awt.Point;
import java.util.*;
class Main extends IPlayersFind {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rowNo = input.nextInt();
        if(rowNo==0) return;
        String[] stringArray = new String[rowNo];

        for (int i = 0; i < rowNo; i++) {
            stringArray[i] = input.next();
        }
        char[][] array = new char[rowNo][stringArray[0].length()];
        for(int i = 0; i <rowNo;i++){
            for(int j = 0;j<stringArray[i].length();j++){
               array[i][j] = stringArray[i].charAt(j);
            }
        }
        for(int i=0;i<rowNo;i++){
            for(int j = 0;j<stringArray[0].length();j++)
            {
                System.out.println(array[i][j]);
            }
        }
    }
}