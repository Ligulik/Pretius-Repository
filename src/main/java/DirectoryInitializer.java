import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryInitializer {

    public static final String SYS_PATH = System.getProperty("user.dir");
    public static final String HOME = "/HOME";
    public static final String DEV = "/DEV";
    public static final String TEST = "/TEST";

    public static void initialize() throws IOException {


        if (Files.notExists(Paths.get(SYS_PATH + HOME)) &&
                Files.notExists(Paths.get(SYS_PATH + DEV)) &&
                Files.notExists(Paths.get(SYS_PATH + TEST))) {

            Files.createDirectory(Paths.get(SYS_PATH + HOME));
            Files.createDirectory(Paths.get(SYS_PATH + DEV));
            Files.createDirectory(Paths.get(SYS_PATH + TEST));


        }

        if (Files.notExists(Paths.get(SYS_PATH + HOME + "/count.txt"))) {
            Files.createFile(Paths.get(SYS_PATH + HOME + "/count.txt"));
        }


    }


}
