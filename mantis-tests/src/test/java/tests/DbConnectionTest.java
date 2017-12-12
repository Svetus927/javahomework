package tests;

/**
 * Created on 13/11/2017. для создания пользователя Mantis для модификации напрямую в базе
 */
import org.testng.annotations.Test;

import java.sql.*;


public class DbConnectionTest {
    // Пример подключения к БД через JDBC



  //  @Test
    public void DbConnectionTest() {

        Connection conn = null;
        try {
            // app.getProperty("ftp.username")  "jdbc:mysql://localhost/addressbook?user=root&password=");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker", "root", "");
          //  conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username from mantis_user_table");
            //      ResultSet rsNew = st.execute("INSERT INTO group_list VALUES (12336, NULL, 'SelfNEW', 'Chelseyheader', 'Clelsey')" );
            System.out.println("fetch size " + rs.getFetchSize());
            while (rs.next() ) {
                // rs.getInt("group_id"))  rs.getString("group_name")));

            }
            // ** Освобождаем ресурсы: **
            rs.close();
            st.close();
            conn.close();


            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


}
