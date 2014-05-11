package quadraticequation;

import java.util.Scanner;

class QuadraticFunction{
    private double a, b, c;
    
    QuadraticFunction(double A, double B, double C){
        a = A;
        b = B;
        c = C;
    }
    
    double getA(){
        return a;
    }
    
    double getB(){
        return b;
    }
    
    double getC(){
        return c;
    }
    
    double getDiscriminant(){
        return b*b-(4*a*c);
    }
    
    double getRoot1(){
        if (getDiscriminant() >= 0){
            return (-b + Math.sqrt(getDiscriminant())/(2*a));
        }
        return 0;
    }
    
    double getRoot2(){
        if (getDiscriminant() >= 0){
            return (-b - Math.sqrt(getDiscriminant())/(2*a));
        }
        return 0;
    }
}

public class QuadraticEquation {
    public static void main(String[] args) {
        // variables
        Scanner in = new Scanner(System.in);
        double a, b, c;
        
        System.out.println("A quadratic equation is of the form:");
        System.out.println("ax^2+bx+c=0");
        System.out.println("Enter a number for a: ");
        a = in.nextDouble();
        System.out.println("Enter a number for b: ");
        b = in.nextDouble();
        System.out.println("Enter a number for c: ");
        c = in.nextDouble();
        
        // QuadraticFunction initialization
        QuadraticFunction function = new QuadraticFunction(a, b, c);
        
        // display the proper response based on the input
        if (function.getDiscriminant() < 0){
            System.out.println("The equation has no roots.");
        } else if (function.getDiscriminant() == 0){
            System.out.println("The root is: " + function.getRoot1());
        } else{
            System.out.println("Root 1 is: " + function.getRoot1());
            System.out.println("Root 2 is: " + function.getRoot2());
        }
    }
}
