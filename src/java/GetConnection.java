import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetConnection {

    public static Connection c = null;

    private GetConnection() {

    }

    public static Connection getConnection() {
        if (c == null) {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GetConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return c;
        } else {
            return c;
        }
    }

}
