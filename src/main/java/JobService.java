import java.sql.Connection;
import java.sql.Statement;


public class JobService
{
    public static void insert(Job job)
    {
        Connection conn = DBConn.establishConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into jobs" +
                    "( command, month, day, hour, minute )" +
                    "values (" +
                    "\"" + job.command + "\"" + " , " +
                    job.month   + " , " +
                    job.day     + " , " +
                    job.hour    + " , " +
                    job.minute  + "   " +
                    ");");
        }
        catch ( Exception e ) {
             System.err.println(e.getMessage());
        }
        finally {
            DBConn.closeConn(conn);
        }
    }
}
