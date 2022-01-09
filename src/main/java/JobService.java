import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class JobService
{
    public static void insert(Job job)
    {
        Connection conn = DBConn.establishConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into jobs" +
                    "( command, month, m_day, w_day, hour, minute )" +
                    "values (" +
                    "'" + job.command + "' , " +
                    job.month         + "  , " +
                    job.m_day         + "  , " +
                    job.w_day         + "  , " +
                    job.hour          + "  , " +
                    job.minute        + "    " +
                    ");");
        }
        catch ( Exception e ) {
             System.err.println(e.getMessage());
        }
        finally {
            DBConn.closeConn(conn);
        }
    }

    // hack to get nullable integer from RS
    private static Integer resultGetNInt(ResultSet rs, String what)
    {
        Integer result = null;
        try {
            result = rs.getObject(what) == null ? null : rs.getInt(what);
        }
        catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static List<Job> getAll()
    {
        List<Job> result = new LinkedList<>();
        Connection conn = DBConn.establishConn();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jobs");

            while ( rs.next() ) {
                Integer id       = rs.getInt("id");
                String  command  = rs.getString("command");
                Integer month    = resultGetNInt(rs, "month");
                Integer m_day    = resultGetNInt(rs, "m_day");
                Integer w_day    = resultGetNInt(rs, "w_day");
                Integer hour     = resultGetNInt(rs, "hour");
                Integer minute   = resultGetNInt(rs, "minute");
                result.add(new Job(id, command, month, m_day, w_day, hour, minute));
            }
        }
        catch ( Exception e ) {
             System.err.println(e.getMessage());
        }
        finally {
            DBConn.closeConn(conn);
        }

        return result;
    }

}
