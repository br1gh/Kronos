public class Main
{
    public static void main(String [] args)
    {
        System.out.println("Config path: " + ConfPath.kronos_path_string);
        ConfPath.ensureConfPath();
        DBConn.ensureDB();

        Thread executor_thread = new Thread(() -> {
            while ( true ) {
                Executor.tryExecAll(JobService.getAll());

                System.out.println("Sleeping until next tick ...");
                try {
                    Thread.sleep(60000);
                }
                catch ( Exception e ) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            }
        });
        executor_thread.start();

        Thread gui_thread = new Thread(MainFrame::show);
        gui_thread.start();

    }
}
