public class Job
{
    public Integer id;
    public String  command;
    public Integer month;
    public Integer day;
    public Integer hour;
    public Integer minute;

    Job (Integer id, String command, Integer month,
         Integer day, Integer hour, Integer minute)
    {
        this.id      = id;
        this.command = command;
        this.month   = month;
        this.day     = day;
        this.hour    = hour;
        this.minute  = minute;
    }

    public String [] toStringArray()
    {
        String [] arr = {
            this.id.toString(),
            this.command,
            this.month.toString(),
            this.day.toString(),
            this.hour.toString(),
            this.minute.toString()
        };
        return arr;
    }

}
