package helloworldapp;
import java.util.Scanner;

/*
 The HelloWorldApp class implements an application that
 simply prints "Hello World!" to standard output.
*/

public class HelloWorldApp {
    public static void main(String[] args) {
        int width, height, area;
        int whatIsThis = 0;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("This is a simple java program used to compute area of a rectangle");
        System.out.println("Enter width");
        width = in.nextInt();
        System.out.println("Enter height");
        height = in.nextInt();
        area = computeArea(height, width);
        System.out.println("Area of the rectangle was: " + area);
        
        // For loop example
        for (int count = 0; count < 5; count++)
            System.out.println(count);
        
        // System.in is also a valid input format. It returns the ascii equivalent
        // i.e. "0" equals 48 etc.
        try{
        System.out.println("Alternate method of gathering data");
        whatIsThis = System.in.read();
        }catch(java.io.IOException e){
            System.out.println("ERROR: " + e);
        }
        System.out.print((char)whatIsThis);
    }
    
    public static int computeArea(int width, int height){
        int area;
        area = width * height;
        return area;
    }
}