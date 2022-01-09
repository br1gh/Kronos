import java.time.LocalDateTime;


public class Job
{
    public Integer id;
    public String  command;
    public Integer month;
    public Integer m_day;  // month day
    public Integer w_day;  // week day
    public Integer hour;
    public Integer minute;

    Job (Integer id, String command, Integer month,
         Integer m_day, Integer w_day, Integer hour, Integer minute)
    {
        this.id      = id;
        this.command = command;
        this.month   = month;
        this.m_day   = m_day;
        this.w_day   = w_day;
        this.hour    = hour;
        this.minute  = minute;
    }

    public Boolean doesDateSatisfy(LocalDateTime ldt)
    {
        return ( (this.month    == null || ldt.getMonthValue() == this.month)
                && (this.m_day  == null || ldt.getDayOfMonth() == this.m_day)
                && (this.w_day  == null || ldt.getDayOfWeek().getValue() == this.w_day)
                && (this.hour   == null || ldt.getHour() == this.hour)
                && (this.minute == null || ldt.getMinute() == this.minute) );
    }

    public void display()
    {
        System.out.println("------------------------------");
        System.out.println("  Job properties");
        System.out.println("  id:         " + this.id);
        System.out.println("  command:    " + this.command);
        System.out.println("  month:      " + this.month);
        System.out.println("  month day:  " + this.m_day);
        System.out.println("  week day:   " + this.w_day);
        System.out.println("  hour:       " + this.hour);
        System.out.println("  minute:     " + this.minute);
        System.out.println("------------------------------");
    }

}
