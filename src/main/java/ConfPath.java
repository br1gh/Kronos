import java.text.MessageFormat;


public class ConfPath
{
    public static String kronos_path_string =
            MessageFormat.format("{0}/.config/kronos", System.getProperty("os.name").startsWith("Windows") ?
                    System.getenv("APPDATA") : System.getenv("HOME"));


    /**
     * Creates a strign prefixed with kronos_path_string.
     * @param  string_path  file inside kronos_path_string
     * @return  string prefixed with kronos_path_string
     */
    public static String prefix(String string_path)
    {
        return (kronos_path_string + "/" + string_path);
    }

    /**
     * Create kronos_path_string directory if it does not exist.
     */
    public static void ensureConfPath()
    {
        PathHelpers.ensure(kronos_path_string);
    }
}
