import java.util.*;
public class Task1 {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the number to check");
        int num = inp.nextInt();
        String Result =(num%2==0)? "Even" : "Odd";
        System.out.println(Result);
        inp.close();
    }
}