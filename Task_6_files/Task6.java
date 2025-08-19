package Task_6_files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Task6 {
    public static void addUser() throws Exception{
        User new_user = new User();
        Connection con = UserDB.getConnection();
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement("insert into usersinfo values(?,?,?)");
        st.setInt(1, new_user.Id);
        st.setString(2, new_user.Name);
        st.setString(3, new_user.email);
        int rows = st.executeUpdate();
        if(rows>0){
            System.out.println("added user data sucessfully to db");
            System.out.println("rows affected: "+rows);
            con.commit();
        }
        con.close();

    }
    public static void Display() throws Exception{
        Connection con = UserDB.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from usersinfo");
        while (rs.next()) {
            System.out.println("user id : "+rs.getInt(1));
            System.out.println("user name: "+rs.getString(2));
            System.out.println("user name: "+rs.getString(3));
        }
        con.close();
    }
    public static void main(String[] args){
        Scanner inp = new Scanner(System.in);
        try{
            String ch = "y";
            while(ch.equals("y") || ch.equals("1") || ch.equals("2")){
                System.out.println(" ");
                System.out.println("select the below options:");
                System.out.println("1. Display all users from DB");
                System.out.println("2. add users to DB");
                System.out.println("3.exit");
                ch = inp.nextLine();
                if(ch.equals("1")){
                    Display();
                }else if(ch.equals("2")){
                    addUser();
                }else{
                    break;
                }
                System.out.println(" ");
            }
            
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally{
            inp.close();
        }
    }
}
