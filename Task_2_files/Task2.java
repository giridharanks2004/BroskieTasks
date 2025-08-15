package Task_2_files;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String Name;
    private int id;
    private char grade;
    ArrayList<Student> ls;
    Student(){
        ls = new ArrayList<>();
    }
    Student(String sname,int sid,char grade){
        this.Name = sname;
        this.id = sid;
        this.grade = grade;
    }
    public void displayStudents(){
        System.out.println(" ");
        for(Student s : ls){
            System.out.println("Student Name: "+s.Name);
            System.out.println("Student id: "+s.id);
            System.out.println("Student grade: "+s.grade);
        }
        System.out.println(" ");
    }
    public void addStudent(Student s){
        ls.add(s);
        System.out.println("added");

    }
    public void removeStudentbyId(int id){
        for(Student s : ls){
            if(id == s.id){
                ls.remove(s);
                break;
            }
        }
        System.out.println("deleted");
    }

}
public class Task2 {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int ch = -1;
        Student control = new Student();
        while (ch != 0) {
            System.out.println("enter from the below options:");
            System.out.println("1. add student");
            System.out.println("2. remove student");
            System.out.println("3. display student");
            System.out.println("0. Exit");
            System.out.println("enter here:");
            ch = inp.nextInt();
            inp.nextLine();
            if(ch == 1){
                System.out.println("enter the student name:");
                String name = inp.nextLine();

                System.out.println("enter the student id:");
                int id = inp.nextInt();
                inp.nextLine();
                System.out.println("enter the student grade");
                char grade = inp.nextLine().charAt(0);
                control.addStudent(new Student(name,id,grade));
            }
            else if (ch == 2) {
                System.out.println("enter Student id to delete:");
                int sid = inp.nextInt();
                control.removeStudentbyId(sid);
            }
            else if(ch == 3){
                control.displayStudents();
            }
        }
    }
}
