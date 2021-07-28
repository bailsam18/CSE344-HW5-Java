
import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;


public class SqlConnectionManager {


    public Connection conn;

    String server;
    String dbName;
    String userId;
    String password;

    public SqlConnectionManager(String server, String dbName, String userId, String password) {
        this.server = server;
        this.dbName = dbName;
        this.userId = userId;
        this.password = password;

    }

    public void openConnection() throws Exception {

        String jSQLDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String jSQLUrl = "jdbc:sqlserver://" + server + ".database.windows.net:1433;database=" + dbName;
        String jSQLUser = userId;
        String jSQLPassword = password;

        /* load jdbc drivers */
        Class.forName(jSQLDriver).newInstance();

        /* open connections to the flights database */
        conn = DriverManager.getConnection(jSQLUrl, // database
                jSQLUser, // user
                jSQLPassword); // password

        conn.setAutoCommit(true);
    }

    public void clearTables() {
        try {
            String deleteTables = "Truncate Table CareGiverSchedule;\n" +
                    "DBCC CHECKIDENT ('CareGiverSchedule', RESEED, 0);\n"
                    + "Delete From Caregivers;" + "DBCC CHECKIDENT ('Caregivers', RESEED, 0);";
            PreparedStatement ps = conn.prepareStatement(deleteTables);
            ps.clearParameters();
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void closeConnection() throws Exception {
        conn.close();
    }

    public ResultSet executeQuery(String query) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet test = ps.executeQuery();
            while (test.next()) {
                System.out.println(test.getString(1));
            }
            ps.close();
            return test;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}