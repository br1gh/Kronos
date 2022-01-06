public class Main
{
    public static void main(String [] args)
    {
        System.out.println("Config path: " + ConfPath.kronos_path_string);
        ConfPath.ensureConfPath();
        DBConn.ensureDB();
    }
}
