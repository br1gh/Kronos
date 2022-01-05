import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ConfPath {

    public static String spath = System.getenv("HOME")+"/.config/kronos";
    public static Path   ppath = Paths.get(spath);


    public static Boolean exists()
    {
        return Files.exists(ppath);
    }

    public static void ensure()
    {
        if ( exists() ) {
            System.out.println(String.format("Already exists: {0}", spath));
        }
        else {
            System.out.println(String.format("Creating {0}...", spath));
            try {
                Files.createDirectories(ppath);
            }
            catch (Exception e) {
                System.err.println(String.format("Failed to create {0}!", spath));
                System.err.println(e.getMessage());
            }
        }
    }

}
