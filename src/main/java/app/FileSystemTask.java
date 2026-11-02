package app;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileSystemTask {

    public static void runFileSystemTask() {

        String surname = "Гуляева";
        String name = "Арина";

        Path rootDir = Paths.get(surname);
        Path mainFile = rootDir.resolve(name + ".txt");
        Path nestedDirs = rootDir.resolve("dir1/dir2/dir3");
        Path copiedFile = nestedDirs.resolve(name + ".txt");

        try {
            // 1. Создать директорию <surname>
            Files.createDirectories(rootDir);

            // 2. Внутри создать файл <name>
            Files.createFile(mainFile);

            // 3. Создать вложенные директории и скопировать файл
            Files.createDirectories(nestedDirs);
            Files.copy(mainFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);

            // 4. Создать file1 в dir1
            Files.createFile(rootDir.resolve("dir1/file1.txt"));

            // 5. Создать file2 в dir2
            Files.createFile(rootDir.resolve("dir1/dir2/file2.txt"));

            // 6. Рекурсивный обход с метками D: и F:
            System.out.println("\n--- Обход директории: " + surname + " ---");
            try (Stream<Path> paths = Files.walk(rootDir)) {
                paths.forEach(path -> {
                    String prefix = Files.isDirectory(path) ? "D: " : "F: ";
                    System.out.println(prefix + path.getFileName());
                });
            }

            // 7. Удалить dir1 со всем содержимым
            Path dir1 = rootDir.resolve("dir1");
            if (Files.exists(dir1)) {

                try (Stream<Path> pathsToDelete = Files.walk(dir1)) {
                    pathsToDelete.sorted(Comparator.reverseOrder())
                            .forEach(path -> {
                                try {
                                    Files.delete(path);
                                } catch (IOException e) {
                                    System.err.println("Ошибка удаления: " + path);
                                }
                            });
                }
                System.out.println("\n Директория dir1 удалена.");
            }

        } catch (IOException e) {
            System.err.println("Ошибка файловой системы: " + e.getMessage());
        }
    }
}
