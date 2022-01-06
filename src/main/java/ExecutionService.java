import java.sql.Connection;
import java.sql.Statement;


public class ExecutionService
{
    public void insert(Execution execution)
    {
        Connection conn = DBConn.establishConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into executions" +
                    "( job_id, date, exit_code, exit_output )" +
                    "values (" +
                    execution.job_id       + " , " +
                    execution.date         + " , " +
                    execution.exit_code    + " , " +
                    execution.exit_output  + " , " +
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
