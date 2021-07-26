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
        this.server = "ethans24server";
        this.dbName = "HW3 Database";
        this.userId = "ethans24";
        this.password = "Test1234";
        
    }

    // move to sql_manager.py
    // instead of opening db.conn for password information use
    // environmental variables (google how to in java)
    // takes in string (sql query that students pass in)
    // have function run query (will call all prepared statements)

    public void openConnection() throws Exception {
    
        String jSQLDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String jSQLUrl = "jdbc:sqlserver://" + dbName;
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
  
      public void prepareStatements() throws Exception
      {

      }
  
      public void closeConnection() throws Exception {
        conn.close();
      }

      public void executeQuery(String query) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.executeUpdate();
            System.out.println(ps);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

      }
  
  
  
  
}