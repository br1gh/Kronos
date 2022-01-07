import java.util.List;


public class Executor
{
    private final List<Job> jobs = JobService.getAll();

    public static void jobExec(Job job)
    {
        System.out.println("Executing: " + job.command);
    }

    public void doExec()
    {
        for ( Job job : jobs ) {
            if ( job.doesDateSatisfy() ) {
                jobExec(job);
            }
        }
    }

}
