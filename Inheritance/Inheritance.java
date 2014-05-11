package inheritance;

class Person{
    String name = "";
    String address = "";
    String phoneNumber = "";
    String emailAddress = "";
    
    Person(String n, String a, String p, String e){
        name = n;
        address = a;
        phoneNumber = p;
        emailAddress = e;
    }
    
    @Override
    public String toString(){
        return "Name: " + name + ", Address: " + address + ", Phone #: " + phoneNumber + ", Email: " + emailAddress;
    }
}

class Student extends Person{
    final String classStatus;
    
    Student(String n, String a, String p, String e, String status){
        super(n, a, p, e);
        classStatus = status;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Class: " + classStatus;
    }
}

class Employee extends Person{
    class MyDate{
        String year = "";
        String month = "";
        String day = "";
        
        MyDate(){
            year = "";
            month = "";
            day = "";
        }
    }
    String office = "";
    double salary = 0;
    MyDate dateHired = new MyDate();
    
    Employee(String n, String a, String p, String e, String m, String d, String y, double s, String o){
        super(n, a, p, e);
        dateHired.year = y;
        dateHired.month = m;
        dateHired.day = d;
        salary = s;
        office = o;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Hire date: " + dateHired.month + " " + dateHired.day + ", " + dateHired.year + ", Salary: " + salary + ", Office: " + office;
    }
}

class Faculty extends Employee{
    String officeHours = "";
    String rank = "";
    
    Faculty(String n, String a, String p, String e, String y, String m, String d, double s, String o, String h, String r){
        super(n, a, p, e, y, m, d, s, o);
        officeHours = h;
        rank = r;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Office Hours: " + officeHours + ", Rank: " + rank;
    }
}

class Staff extends Employee{
    String title = "";
    
    Staff(String n, String a, String p, String e, String y, String m, String d, double s, String o, String t){
        super(n, a, p, e, y, m, d, s, o);
        title = t;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Title: " + title;
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Person person = new Person("Bob", "2222 Bob Way", "(888)888-8888", "bob@email.com");
        Student student = new Student("Joe", "1111 Joe Way", "(111)111-1111", "joe@email.com", "Freshman");
        Employee employee = new Employee("Mary", "3333 Mary Way", "(333)333-3333", "mary@email.com", "January", "18", "2005", 22, "Mary's Office");
        Faculty faculty = new Faculty("Frank", "4444 Frank Way", "(444)444-4444", "frank@email.com", "March", "4", "2001", 50, "Frank's Office", "9 to 5", "Lieutenant General");
        Staff staff = new Staff("Linda", "5555 Linda Way", "(555)555-5555", "linda@email.com", "May", "25", "1992", 15, "Linda's Office", "Flotilla Command");
        System.out.println(person);
        System.out.println(student);
        System.out.println(faculty);
        System.out.println(employee);
        System.out.println(staff);
    }
    
}
