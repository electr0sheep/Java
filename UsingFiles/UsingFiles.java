package usingfiles;

import java.util.*;
import java.io.*;
import java.io.

public class UsingFiles {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:/Java/InputData.txt"));
        while (input.hasNext()){
            String text = input.next();
            System.out.println(text);
        }
        input.close();
        
        String [] TextLines = ("CS 2365 Section 001", "Object Oriented Programming", "Mario A Pita");
        //Example WRITING a file.
        PrintStream out = new PrintStream(new File("C:\Java\OutputFile.txt"));
        out.println("Text file created thru a Java program!");
        for (String aStr:TextLines){
            out.println(aStr);
        }
        out.close();
    }
}
