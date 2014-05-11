/*
 * CS 2365 001 - Object Oriented Programming
 * Instructor: Mario A. Pitalua
 * Spring 2014
 */

package carexample;

// @author Mario A. Pitalua

// This code illustrade the use of the keyword this in class Vehicle
// Keyword '" this "'
// Within an instance method or a constructor, this is a reference to the 
// current object — the object whose method or constructor is being called. 
// You can refer to any member of the current object from within an instance 
// method or a constructor by using this.

class Vehicle {
  public int passengers; // number of passengers
  public int fuelCap; // fuel capacity in gallons
  public int mpg; // fuel consumption in miles per gallon
  
  //Constructor
  Vehicle(){
     //fuelCap = 12;
     //mpg = 25;
     //passengers = 5;
      this(5,12,25);   // Using keyword this to call the initializer 
    }                  //construction with the default values as parameters.

  // Contructor Initializer
  Vehicle(int p, int f, int m){
     this.fuelCap = f;     // To refer to the current object data members
     this.mpg = m;         // Data Members a.k.a. Attributes a.k.a. 
     this.passengers = p;
  }
  
  // Since NOW the data member are private,
  // There is a NEED to provide methods to change their values.
  public void setFuelCap ( int f) {
    this.fuelCap = f;
    }
  public void setMPG ( int m) {
    this.mpg = m;
    }
  public void setPssngrs (int p) {
    this.passengers = p;
  }
  // There is a NEED to provide methods to obtain their values.
  public int getFuelCap ()
    { return this.fuelCap; }
  public  int getMPG()
   { return this.mpg; }
  public int getPssngrs()
    { return this.passengers;}
  // Display the range.
  public void printRange() {
    int rng = this.range();  // Using Keyword this to call another object's method 
                             // from inside the object
    System.out.println("Range is " + rng);
    }
  // Return the range.
  public int range() {
    return this.mpg * this.fuelCap;
    }
  // Compute fuel needed for a given distance.
  public double fuelNeeded(int miles) {
    return (double) miles / this.mpg;
  }
} //End Class Vehicle

// This class declares an object of type class Vehicle.
public class CarExample {
  public static void main(String[] args) {
    Vehicle minivan = new Vehicle();
    int range;

    // assign values to fields in minivan
    // No possible because now they are private members
    //minivan.passengers = 7;
    //minivan.fuelCap = 16;
    //minivan.mpg = 21;
    
    //So we need to use the SET methods for this now.
    minivan.setPssngrs(7);
    minivan.setFuelCap(16);
    minivan.setMPG(21);

    // compute the range assuming a full tank of gas
    // NOW we need to use GET methods
    range = minivan.getFuelCap() * minivan.getMPG();
    System.out.println("Minivan can carry " + minivan.getPssngrs() +
                       " with a range of " + range);
    minivan.printRange();

    
    //Using TWO Vehicles, lets at another Object from Vehicle class.
    Vehicle sportscar = new Vehicle();

    int range1, range2;

    // assign values to fields in minivan
    minivan.passengers = 7;
    minivan.fuelCap = 16;
    minivan.mpg = 21;
    
    // assign values to fields in sportscar
    sportscar.passengers = 2;
    sportscar.fuelCap = 14;
    sportscar.mpg = 12;

    // compute the ranges assuming a full tank of gas
    range1 = minivan.fuelCap * minivan.mpg;
    range2 = sportscar.fuelCap * sportscar.mpg;

    System.out.println("Minivan can carry " + minivan.passengers +
                       " with a range of " + range1);
    minivan.printRange();
    
    int vehicleRange = minivan.range();
    System.out.println("Minivan range:"+ vehicleRange);
    
    System.out.println("Sportscar can carry " + sportscar.passengers +
                       " with a range of " + range2);
    sportscar.printRange();
    
    
    double gallons;
    int dist = 252;
    gallons = minivan.fuelNeeded(dist);

    System.out.println("To go " + dist + " miles minivan needs " + gallons + " gallons of fuel.");

    gallons = sportscar.fuelNeeded(dist);

    System.out.println("To go " + dist + " miles sportscar needs " + gallons + " gallons of fuel.");
    // Using Class Constructors
    Vehicle car = new Vehicle (5,12,25);
    Vehicle van = new Vehicle (7,24,21);
    Vehicle auto = new Vehicle();
    
    car.printRange();
    van.printRange();
    auto.printRange();
  }
}
