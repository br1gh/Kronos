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

    public void display()
    {
        System.out.println("------------------------------");
        System.out.println("  Job properties");
        System.out.println("  id:       " + this.id);
        System.out.println("  command:  " + this.command);
        System.out.println("  month:    " + this.month);
        System.out.println("  day:      " + this.day);
        System.out.println("  hour:     " + this.hour);
        System.out.println("  minute:   " + this.minute);
        System.out.println("------------------------------");
    }

    // CONSIDER: Do we need this?
    public String [] toStringArray()
    {
        return new String[] {
            this.id.toString(),
            this.command,
            this.month.toString(),
            this.day.toString(),
            this.hour.toString(),
            this.minute.toString()
        };
    }

}
