import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class ExecutionService
{
    public static void insert(Execution execution)
    {
        Connection conn = DBConn.establishConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into executions" +
                    "( job_id, date, exit_code, exit_output )" +
                    "values (" +
                    execution.job_id              + "   , " +
                    "\"" + execution.date         + "\" , " +
                    execution.exit_code           + "   , " +
                    "\"" + execution.exit_output  + "\"   " +
                    ");");
        }
        catch ( Exception e ) {
             System.err.println(e.getMessage());
        }
        finally {
            DBConn.closeConn(conn);
        }
    }

    public static List<Execution> getAll()
    {
        List<Execution> result = new LinkedList<>();
        Connection conn = DBConn.establishConn();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM executions");

            while ( rs.next() ) {
                Integer id           = rs.getInt("id");
                Integer job_id       = rs.getInt("job_id");
                String  date         = rs.getString("date");
                Integer exit_code    = rs.getInt("exit_code");
                String  exit_output  = rs.getString("exit_output");
                result.add(new Execution(id, job_id, date, exit_code, exit_output));
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
