import java.util.*;
import java.sql.*;


public class VaccineCaregiver {

    public SqlConnectionManager sqlClient;
    public String sqltext;
    public int caregiverId;


    public VaccineCaregiver(String name) {
        this.sqlClient = new SqlConnectionManager(
                System.getenv("Server"),
                System.getenv("DBName"),
                System.getenv("UserID"),
                System.getenv("Password")
        );

        try {
            sqlClient.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> hoursToSchedule =new ArrayList<Integer>();
        hoursToSchedule.add(10);
        hoursToSchedule.add(11);
        int appointmentDuration = 15;
        sqltext = "INSERT INTO CareGivers (CaregiverName) VALUES ('" + name + "')";
        caregiverId = 0;
        try {
            sqlClient.executeQuery(sqltext);
            ResultSet rs = sqlClient.executeQuery("SELECT @@IDENTITY AS 'Identity'; ");
            //caregiverId = (rs.getString(1));
        } catch (Exception e) {

        }





    }

}