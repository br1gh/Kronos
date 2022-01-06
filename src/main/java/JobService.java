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
                    "\"" + job.command + "\""                      + " , " +
                    ConversionHelpers.integerToString(job.month)   + " , " +
                    ConversionHelpers.integerToString(job.day)     + " , " +
                    ConversionHelpers.integerToString(job.hour)    + " , " +
                    ConversionHelpers.integerToString(job.minute)  + "   " +
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
