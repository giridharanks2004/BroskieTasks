package Task_3_files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Employee{
    String Name;
    int Age;
    int Salary;
    Employee(String e_name,int e_age,int e_salary){
        this.Name = e_name;
        this.Age = e_age;
        this.Salary = e_salary;
    }
}
public class Task3 {
    public static String toString(Employee e){
        return "name: "+e.Name+" age: "+e.Age+" Salary: "+e.Salary;
    }
    public static void display(ArrayList<Employee> lst){
        for(Employee e : lst){
            System.out.println(toString(e));
        }
        System.out.println(" ");
    }
    public static void main(String[] args) {
        ArrayList<Employee> lst = new ArrayList<>();
        lst.add(new Employee("giri", 20, 80000));
        lst.add(new Employee("lalith", 20, 750000));
        lst.add(new Employee("kishore", 20, 920000));
        lst.add(new Employee("john", 20, 355500));
        System.out.println("without sorting:");
        display(lst);
        Collections.sort(lst,new Comparator<Employee>() {
          public int compare(Employee e1, Employee e2){
                if(e1.Salary<e2.Salary)
                    return 1;
                else
                    return -1;
                
          }  
        });
        System.out.println("sorting by salary desc:");
        display(lst);
        Collections.sort(lst,new Comparator<Employee>() {
          public int compare(Employee e1, Employee e2){
                if(e1.Name.charAt(0)>e2.Name.charAt(0))
                    return 1;
                else
                    return -1;
                
          }  
        });
        System.out.println("sorting by name asc:");
        display(lst);
        
    }
    
}