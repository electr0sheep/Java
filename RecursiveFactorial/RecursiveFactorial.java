// package file
package recursivefactorial;
// imports
import java.util.Scanner;
//start point
public class RecursiveFactorial {
    // main
    public static void main(String[] args) {
        // variable declarations
        Scanner input = new Scanner(System.in);
        int number = 0;
        
        // show output and get input
        System.out.println("Enter a number: ");
        number = input.nextInt();
        
        // process result
        System.out.println("The factorial is: " + factorial(number));
    }
    
    // recursive method
    public static int factorial(int num){
        if (num == 1)
            return 1;
        return num * factorial(num - 1);
    }
}