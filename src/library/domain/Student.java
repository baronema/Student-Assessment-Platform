/*
 * Primrose Assessment Application
 * Author: Mark Barone
 * Version: v1
 */
package library.domain;

public class Student {
    
    final private String fName;
    final private String lName;
    final private String id;
    
    public Student(String fName, String lName, String id){
        this.fName = fName;
        this.lName = lName;
        this.id = id;
    }
    public Student(Student stu){
        this.fName = stu.fName;
        this.lName = stu.lName;
        this.id = stu.id;
    }
    public String getfName(){return fName;}
    public String getlName(){return lName;}
    public String getID(){return id;}
    
    @Override
    public String toString(){return fName + " " + lName;}
}
