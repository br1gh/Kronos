import java.util.List;


public class Main
{
    public static void main(String [] args)
    {
        System.out.println("Config path: " + ConfPath.kronos_path_string);
        ConfPath.ensureConfPath();
        DBConn.ensureDB();

        // Job j = new Job(1, "echo test >> ${HOME}/.config/kronos/test",
        //         null, null, null, 30);
        // JobService.insert(j);

        Job j = JobService.getAll().get(0);
        System.out.println(j.command + j.month + j.day);

    }
}
