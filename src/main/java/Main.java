import java.io.IOException;
import java.nio.file.*;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Path homePath;


        // Create Directories if not exist:
        DirectoryInitializer.initialize();


        // Add Watcher:

        WatchService watchService = FileSystems.getDefault().newWatchService();

        homePath = Paths.get(DirectoryInitializer.SYS_PATH + DirectoryInitializer.HOME);


        homePath.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);


        WatchKey key;

        System.out.println("Program uruchomiony. Dodaj pliki do katalogu /HOME");

        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                String test = event.context().toString();

                if (!test.equals("count.txt")) {
                    String change = "/" + event.context();
                    Sorter.sort(change);
                }
            }
            key.reset();
        }

    }
}
