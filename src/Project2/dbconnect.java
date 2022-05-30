
package Project2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbconnect {
    public static Connection connectdb(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Project_two_db", "pdc","pdc");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
