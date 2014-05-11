package linearequationprogram;
import java.util.Scanner;

class LinearEquation{
    private int a, b, c, d, e, f;
    
    LinearEquation(int A, int B, int C, int D, int E, int F){
        a = A;
        b = B;
        c = C;
        d = D;
        e = E;
        f = F;
    }
    
    int getA(){
        return a;
    }
    
    int getB(){
        return b;
    }
    
    int getC(){
        return c;
    }
    
    int getD(){
        return d;
    }
    
    int getE(){
        return e;
    }
    
    int getF(){
        return f;
    }
    
    boolean isSolvable(){
        return (a*d)-(b*c) != 0;
    }
    
    double getX(){
        return ((e*d)-(b*f))/((a*d)-(b*c));
    }
    
    double getY(){
        return ((a*f)-(e*c))/((a*d)-(b*c));
    }
}

public class LinearEquationProgram {
    public static void main(String[] args) {
        // variables
        Scanner in = new Scanner(System.in);
        int a, b, c, d, e, f;
        LinearEquation equation;
        
        
        System.out.println("This program solves a system of linear equations of the form:");
        System.out.println("ax+by=e");
        System.out.println("cx+dy=f");
        System.out.println("Enter a value for a: ");
        a = in.nextInt();
        System.out.println("Enter a value for b: ");
        b = in.nextInt();
        System.out.println("Enter a value for c: ");
        c = in.nextInt();
        System.out.println("Enter a value for d: ");
        d = in.nextInt();
        System.out.println("Enter a value for e: ");
        e = in.nextInt();
        System.out.println("Enter a value for f: ");
        f = in.nextInt();
        
        // LinearEquation initilaztion
        equation = new LinearEquation(a, b, c, d, e, f);
        
        // display proper response based on input
        if (equation.isSolvable()){
            System.out.println("x = " + equation.getX());
            System.out.println("y = " + equation.getY());
        } else {
            System.out.println("The linear equations you entered have no solution");
        }
    }
}
