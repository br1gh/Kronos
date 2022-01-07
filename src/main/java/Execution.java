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
}
