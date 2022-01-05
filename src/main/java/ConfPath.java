import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;


public class ConfPath {

    public static String spath =
            MessageFormat.format("{0}/.config/kronos", System.getenv("HOME"));
    public static Path ppath =
            Paths.get(spath);


    public static Boolean exists()
    {
        return Files.exists(ppath);
    }

    public static void ensure()
    {
        if ( exists() ) {
            System.out.println(MessageFormat.format("Already exists: {0}", spath));
        }
        else {
            System.out.println(MessageFormat.format("Creating {0}...", spath));
            try {
                Files.createDirectories(ppath);
            }
            catch (Exception e) {
                System.err.println(MessageFormat.format("Failed to create {0}!", spath));
                System.err.println(e.getMessage());
            }
        }
    }

}
