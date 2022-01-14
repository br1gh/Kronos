import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;


public class PathHelpers
{
    /**
     * Check if given path exists.
     * @param  string_path  path as a {@link String}
     * @return  true if string_path exists
     */
    public static Boolean exists(String string_path)
    {
        return Files.exists(Paths.get(string_path));
    }

    /**
     * Create directory if it does not exist.
     * @param  string_path  path of directory
     */
    public static void ensure(String string_path)
    {
        Path path = Paths.get(string_path);

        if ( Files.exists(path) ) {
            System.out.println(MessageFormat.format("Already exists: {0}", string_path));
        }
        else {
            System.out.println(MessageFormat.format("Creating {0}...", string_path));

            try {
                Files.createDirectories(path);
            }
            catch ( Exception e ) {
                System.err.println(MessageFormat.format("Failed to create {0}!", string_path));
                System.err.println(e.getMessage());
            }
        }
    }
}
