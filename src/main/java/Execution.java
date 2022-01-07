public class Execution
{
    public Integer id;
    public Integer job_id;
    public String  date;
    public Integer exit_code;
    public String  exit_output;

    Execution (Integer id, Integer job_id, String date,
         Integer exit_code, String exit_output)
    {
        this.id           = id;
        this.job_id       = job_id;
        this.date         = date;
        this.exit_code    = exit_code;
        this.exit_output  = exit_output;
    }

    public void display()
    {
        System.out.println("------------------------------");
        System.out.println("  Execution properties");
        System.out.println("  id:       " + this.id);
        System.out.println("  command:  " + this.job_id);
        System.out.println("  month:    " + this.date);
        System.out.println("  day:      " + this.exit_code);
        System.out.println("  hour:     " + this.exit_output);
        System.out.println("------------------------------");
    }

}
