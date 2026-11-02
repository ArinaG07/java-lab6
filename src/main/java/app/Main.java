package app;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Задание № 1. Рефлексия и аннотации ---");
        Invoker.invokeAnnotatedMethods();

        System.out.println("\n--- Задание № 2. Работа с файловой системой ---");
        FileSystemTask.runFileSystemTask();
    }
}
