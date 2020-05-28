
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.LinkedList;
import java.util.List;

public class Sorter {

    private static final String JAR_EXTENSION = "jar";

    static List<String> miejscaDev = new LinkedList<>();
    static List<String> miejscaTest = new LinkedList<>();

    static int devCounter = 0;
    static int testCounter = 0;

    public static void sort(String path) throws IOException {

        Path path1 = Paths.get(DirectoryInitializer.SYS_PATH + DirectoryInitializer.HOME + path);

        FileWriter fileWriter;



        String extension = "none";

        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i + 1);
        }

        try {
            if (extension.equals(JAR_EXTENSION)) {
                BasicFileAttributes attr = Files.readAttributes(path1, BasicFileAttributes.class);
                FileTime fileTime = attr.creationTime();
                long time = fileTime.toMillis();


                if (!parzysta(time)) {

                        Files.move(Paths.get(DirectoryInitializer.SYS_PATH + DirectoryInitializer.HOME + path),
                                Paths.get(DirectoryInitializer.SYS_PATH + DirectoryInitializer.TEST + path),
                                StandardCopyOption.REPLACE_EXISTING);

                        System.out.println("Przenoszę plik  " +path);
                        System.out.println("Plik został przeniesiony do katalogu /TEST");

                        testCounter++;
                        miejscaTest.add(path);
                        fileWriter = new FileWriter(DirectoryInitializer.SYS_PATH + DirectoryInitializer.HOME + "/count.txt");
                        fileWriter.write("Pliki przeniesione do:"
                                + DirectoryInitializer.SYS_PATH + DirectoryInitializer.DEV
                                + "   (Ilość="
                                + devCounter
                                + "):   "
                                + miejscaDev
                                + System.getProperty("line.separator")
                                + "Pliki przeniesione do:"
                                + DirectoryInitializer.SYS_PATH + DirectoryInitializer.TEST
                                + "   (Ilość="
                                + testCounter
                                + "):   "
                                + miejscaTest);
                        fileWriter.close();
                }else {
                    Files.move(Paths.get(DirectoryInitializer.SYS_PATH + DirectoryInitializer.HOME + path),
                            Paths.get(DirectoryInitializer.SYS_PATH + DirectoryInitializer.DEV + path),
                            StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Przenoszę plik  " +path);
                    System.out.println("Plik został przeniesiony do katalogu /DEV");

                    devCounter++;
                    miejscaDev.add(path);
                    fileWriter = new FileWriter(DirectoryInitializer.SYS_PATH + DirectoryInitializer.HOME + "/count.txt");
                    fileWriter.write("Pliki przeniesione do:"
                            + DirectoryInitializer.SYS_PATH + DirectoryInitializer.DEV
                            + "   (Ilość="
                            + devCounter
                            + "):   "
                            + miejscaDev
                            + System.getProperty("line.separator")
                            + "Pliki przeniesione do:"
                            + DirectoryInitializer.SYS_PATH + DirectoryInitializer.TEST
                            + "(Ilość="
                            + testCounter
                            + "):"
                            + miejscaTest);
                    fileWriter.close();
                }
            } else {

                Files.move(Paths.get(DirectoryInitializer.SYS_PATH + DirectoryInitializer.HOME + path),
                        Paths.get(DirectoryInitializer.SYS_PATH + DirectoryInitializer.DEV + path),
                        StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Przenoszę plik  " +path);
                System.out.println("Plik został przeniesiony do katalogu /DEV");

                devCounter++;
                miejscaDev.add(path);
                fileWriter = new FileWriter(DirectoryInitializer.SYS_PATH + DirectoryInitializer.HOME + "/count.txt");
                fileWriter.write("Pliki przeniesione do:"
                        + DirectoryInitializer.SYS_PATH + DirectoryInitializer.DEV
                        + "   (Ilość="
                        + devCounter
                        + "):   "
                        + miejscaDev
                        + System.getProperty("line.separator")
                        + "Pliki przeniesione do:"
                        + DirectoryInitializer.SYS_PATH + DirectoryInitializer.TEST
                        + "(Ilość="
                        + testCounter
                        + "):"
                        + miejscaTest);
                fileWriter.close();


            }
        } catch (NoSuchFileException e) {
        }


    }

    private static boolean parzysta(long time) {
        return time % 2 == 0;
    }
}
