package application;

import java.sql.*;

/**
 * Created on 13/11/2017.
 * сюда будут вынесены все методы по работе с БД напрямую через JDBC
 */
public class DbJdbcHelper {

    public DbJdbcHelper() {

    }

    public int createUserWithoutUIandGetId(String username, String email) {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
          //  ResultSet rs  = st.executeQuery("select id, username from mantis_user_table");
            String q = "INSERT INTO mantis_user_table(username, realname, email, password, cookie_string) " +
                    String.format("VALUES ('%s', 'Vasik1', '%s', 'toor', '%s')", username,email, username);
            Boolean insert =  st.execute(q);
            ResultSet rs  = st.executeQuery(String.format("select id, username from mantis_user_table where username = '%s'", username));
            rs.next();
            int id = rs.getInt("id");
            System.out.println( id + ", username = " + rs.getString("username"));

            // ** Освобождаем ресурсы: **
            rs.close();
            st.close();
            conn.close();
            return id;

            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return -1;
        }
    }
}
