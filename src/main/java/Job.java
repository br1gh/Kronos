import java.time.LocalDateTime;


public class Job
{
    public Integer id;
    public String  command;
    public Integer month;
    public Integer wday;  // week day
    public Integer hour;
    public Integer minute;

    Job (Integer id, String command, Integer month,
         Integer wday, Integer hour, Integer minute)
    {
        this.id      = id;
        this.command = command;
        this.month   = month;
        this.wday    = wday;
        this.hour    = hour;
        this.minute  = minute;
    }

    // TODO: Take LocalDateTime as param
    public Boolean doesDateSatisfy()
    {
        LocalDateTime date_now = LocalDateTime.now();
        return ( (this.month    == null || date_now.getMonthValue() == this.month)
                && (this.wday   == null || date_now.getDayOfWeek().getValue() == this.wday)
                && (this.hour   == null || date_now.getHour() == this.hour)
                && (this.minute == null || date_now.getMinute() == this.minute) );
    }

    public void display()
    {
        System.out.println("------------------------------");
        System.out.println("  Job properties");
        System.out.println("  id:        " + this.id);
        System.out.println("  command:   " + this.command);
        System.out.println("  month:     " + this.month);
        System.out.println("  week day:  " + this.wday);
        System.out.println("  hour:      " + this.hour);
        System.out.println("  minute:    " + this.minute);
        System.out.println("------------------------------");
    }

}
