import java.sql.*;



public class SqlConnectionManager {


    public Connection conn;

    private String server;
    private String dbName;
    private String userId;
    private String password;

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




    public ResultSet executeQuery(String query) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeUpdate(String query) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}