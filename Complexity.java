import javax.naming.SizeLimitExceededException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Complexity {

    /***
     * This method prints table of n row-wise.
     * The time complexity of method is n^2.
     * @param n number of rows and columns.
     */
    public static void method1(int n) {
      try{
          if (n < 1 || n > Integer.MAX_VALUE) {
             throw new NumberFormatException();
          }
           System.out.println("\nThe table from 1 to  " + n);
               for(int i = 1; i <= n; i++){
                   for(int j = 1; j <= n; j++){
                       System.out.print(" " + i*j);
                   }
                   System.out.println();
               }
           } catch (NumberFormatException e){
                System.out.println( n + " is a invalid Input .Please enter positive integer number and greater than zero.");
            }
       }

    /***
     * This method prints cube of number from 1 till given input.
     * The time complexity of method is n^3.
     * @param n defines value of input.
     */
    public static void method2(int n) {
        try{
            if (n < 1 || n > Integer.MAX_VALUE) {
                throw new NumberFormatException();
            }
            System.out.println("\nThe cube of number from 1 to  " + n);

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (k == i && j == i) {
                            System.out.print(k + "^3 = " + k * i * j);
                        }
                    }
                }
                System.out.println();
            }
        } catch (NumberFormatException e){
        System.out.println( n + " is a invalid Input .Please enter positive integer number and greater than zero.");
       }
    }


    /***
     * This method prints power of 2 less than input n.
     * The time complexity of method is logN.
     * @param n value of input n.
     */
    public static void method3(int n) {
        int i = 1;
        int j = 0 ;

        try{
            if (n < 1 || n > Integer.MAX_VALUE) {
                throw new NumberFormatException();
            }
            System.out.println("\nThe power of 2 from 1 to  " + n);
            while (i <= n) {
                System.out.println("2^" + j++ + " = " + i);
                i = i * 2;
            }
        } catch (NumberFormatException e){
            System.out.println( n + " is a invalid Input .Please enter positive integer number and greater than zero.");
        }
    }

    /***
     * This method prints table of number from 1 to n those are power of 2.
     * The time complexity of method is NlogN.
     * @param n value of input n.
     */
    public static void method4(int n){
        try{
            if (n < 1 || n > Integer.MAX_VALUE) {
                throw new NumberFormatException();
            }
            System.out.println("\nThe table of number from 1 to  " + n + " those are power of 2.");
            for (int i = 1 ; i <= n ; i++) {
                for(int  j = 1 ; j <= n ; j = j * 2) {
                    System.out.print(" " + i * j);
                }
                System.out.println();
            }
        } catch (NumberFormatException e){
            System.out.println( n + " is a invalid Input .Please enter positive integer number and greater than zero.");
        }
    }

    /***
     * This method prints square of number which are power of 2.
     * The time complexity of method is log(logN).
     * @param n value of input n.
     */
        public static void method5(int n){
            try{
                if (n < 1 || n > Integer.MAX_VALUE) {
                    throw new NumberFormatException();
                }
               int counter = 1;
               System.out.println("\nThe value of counter.");
               for (int j = n; j >= 2; j = (int) Math.sqrt(j)) {
                   System.out.println("Counter = " + counter++);
               }
           } catch (NumberFormatException e){
                System.out.println( n + " is a invalid Input .Please enter positive integer number and greater than zero.");
            }
        }

    /**
     * This method calls fibonacci series in recursive form.
     * The time complexity is 2^N.
     * @param n whose fibonacci series need to find.
     */
    public static void method6(int n) {
        try {
            if (n < 1 || n > Integer.MAX_VALUE) {
                throw new NumberFormatException();
            }
            System.out.println("\nThe fibonacci number of " + n + " is = " + fibonaci(n));
        } catch (NumberFormatException e){
            System.out.println( n + " is a invalid Input .Please enter positive integer number and greater than zero.");
        }
    }

    /***
     * This method calculates fibonacci series in recursive form.
     * If input reaches to zero or 1, it starts returning value.
     * @param n whose fibonacci series need to find.
     * @return sum of fibonacci number.
     */
    public static int fibonaci(int n) {
        if( n <= 0 ) return  0;
        if( n == 1 ) return 1;
        return fibonaci(n - 1) +  fibonaci( n - 2 );
    }

}
