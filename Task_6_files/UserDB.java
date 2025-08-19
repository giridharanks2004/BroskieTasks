package Task_6_files;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserDB {
    private static String url="jdbc:mysql://localhost:3306/student";
    private static String username="root";
    private static String password="root123";

    public static Connection getConnection() throws Exception{
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

}
