import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;


public class Executor
{
    public static void jobExec(Job job)
    {
        System.out.println("Executing: " + job.command);
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(job.command);
            p.waitFor();

            BufferedReader buf =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String p_line = "";
            StringBuilder p_exit_output = new StringBuilder();

            while ( p_line != null ) {
                p_exit_output.append(p_line);
                p_line = buf.readLine();
            }

            String date = LocalDateTime.now().toString();
            Execution execution = new Execution(0, job.id, date,
                    p.exitValue(), p_exit_output.toString());
            ExecutionService.insert(execution);
        }
        catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
    }

    public static void tryExecAll(List<Job> jobs)
    {
        for ( Job job : jobs ) {
            if ( job.doesDateSatisfy(LocalDateTime.now()) ) {
                jobExec(job);
            }
        }
    }

}
