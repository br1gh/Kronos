import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.MessageFormat;


public class DBConn
{
    public static String db_path =
        ConfPath.prefix("db.sqlite");

    public static String url =
        MessageFormat.format("jdbc:sqlite:{0}", db_path);


    public static Connection establishConn()
    {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        }
        catch ( Exception e ) {
            System.out.println("Error during establishing connection to: " + db_path);
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void closeConn(Connection conn)
    {
        try {
            if ( conn != null ) {
                conn.close();
            }
        }
        catch ( Exception e ) {
            System.out.println("Error during closing connection!");
            System.out.println(e.getMessage());
        }
    }

    public static void createDB()
    {
         System.out.println(MessageFormat.format("Creating {0} database...", db_path));
         Connection conn = establishConn();

         try {
             Statement stmt = conn.createStatement();
             stmt.executeUpdate("create table jobs " +
                     "( " +
                     "id           integer primary key , " +
                     "command      text not null       , " +
                     "month        integer             , " +
                     "day          integer             , " +
                     "hour         integer             , " +
                     "minute       integer               " +
                     ");");
             stmt.executeUpdate("create table executions " +
                     "( " +
                     "id           integer primary key , " +
                     "job_id       integer not null    , " +
                     "date         text                , " +
                     "exit_code    text                , " +
                     "exit_output  text                , " +
                     "foreign key (job_id) references jobs (id)" +
                     ");");
             System.out.println("... created successfully.");
         }
         catch ( Exception e ) {
             System.err.println(e.getMessage());
         }
         finally {
             closeConn(conn);
         }
    }

    public static void ensureDB()
    {
        if ( ! PathHelpers.exists(db_path) ) {
            createDB();
        }
        else {
            System.out.println("Database already exists.");
        }
    }


}
