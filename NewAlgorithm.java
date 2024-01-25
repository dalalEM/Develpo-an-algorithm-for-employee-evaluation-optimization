// NewAlgorithm.java
package newalgorithm;

import java.io.IOException;
import java.util.Scanner;

public class NewAlgorithm {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        solution obj = new solution();
        obj.readfile("file.txt");

        System.out.println("*************************************************************************");

        System.out.println("choose the method you want:");
        System.out.println("a.  Dynamic Programming");
        System.out.println("b.  Brute Force");
        System.out.println("c.  exit");
        char ch = input.next().charAt(0);

        while (ch != 'c') {
            System.out.println("*************************************************************************");

            switch (ch) {
                case 'a' -> {
                    obj.DP();
                    break;
                }
                case 'b' -> {
                    obj.BF();
                    break;
                }
            }//switch

            System.out.println("choose the method you want:");
            ch = input.next().charAt(0);
        }
    }
}
