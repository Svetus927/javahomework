package tests;

/**
 * Create on 21/09/2017. Проба создания подключения к БД через JDBC
 */
import model.GroupData;
import model.Groups;
import  org.testng.annotations.Test;

import java.sql.*;


public class DbConnection {
// Пример подключения к БД через JDBC
    @Test
 public void DbConnectionTest() {

        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id, group_name from group_list");
      //      ResultSet rsNew = st.execute("INSERT INTO group_list VALUES (12336, NULL, 'SelfNEW', 'Chelseyheader', 'Clelsey')" );
            Groups groups = new Groups();
            while (rs.next() ) {
                groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name")));
            }
            // ** Освобождаем ресурсы: **
            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);

            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
