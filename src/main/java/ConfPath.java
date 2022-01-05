import java.text.MessageFormat;


public class ConfPath
{
    public static String kronos_path_string =
            MessageFormat.format("{0}/.config/kronos", System.getenv("HOME"));


    public static String prefix(String string_path)
    {
        return (kronos_path_string + "/" + string_path);
    }

    public static void ensureConfPath()
    {
        PathHelpers.ensure(kronos_path_string);
    }
}
