package acceptance.frontend.tests;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionTest {
  @Test (enabled = false)
  public void testDbConnection(){
    Connection conn = null;
    try {
      conn =
              DriverManager.getConnection("jdbc:mysql://localhost:5432/shell_market?" +
                      "user=shell-b2b&password=vtqVSgjrm9XKxpPStMpJS2kwjj64WSQP");
/*      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select * from users_list");
      Users users = new Users();
      while (rs.next()){
        users.add(new UserData().getCreateEmail(rs.getString("user_email")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(users);*/

      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
