package Task_6_files;
import java.util.*;
public class User {
    int Id;
    String Name;
    String email;

    public User(){
        Scanner inp = new Scanner(System.in);
        System.out.println("enter user id");
        int id = inp.nextInt();
        this.Id = id;
        inp.nextLine();
        System.out.println("Enter user name:");
        String name = inp.nextLine();
        this.Name = name;
        System.out.println("Enter user email");
        String mail = inp.nextLine(); 
        this.email = mail;
    }
}
