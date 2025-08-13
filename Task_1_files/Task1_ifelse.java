import java.util.*;
public class Task1_ifelse {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the number to check");
        int num = inp.nextInt();
        if(num%2==0){
            System.out.println("even");
        }
        else{
            System.out.println("odd");
        }
        
    }
}