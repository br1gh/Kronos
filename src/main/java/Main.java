public class Main
{
    public static void main(String [] args)
    {
        System.out.println("Config path: " + ConfPath.kronos_path_string);
        ConfPath.ensureConfPath();
        DBConn.ensureDB();

        /*
        Job j = new Job(1, "echo test >> ${HOME}/.config/kronos/test",
                null, null, null, 30);
        JobService.insert(j);

        for ( Job j : JobService.getAll() ) {
            j.display();
        }

        for ( Execution e : ExecutionService.getAll() ) {
            e.display();
        }
        */

        // "tick" every minute
        // creates new executor and checks if any fob can run
        while ( true ) {
            Executor executor = new Executor();
            executor.doExec();

            System.out.println("Sleeping until next tick ...");
            try {
                Thread.sleep(60000);
            }
            catch ( Exception e ) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

    }
}
