package bookmanager;

import java.util.Scanner;

public class Console {

    private static Scanner scanner = new Scanner(System.in);

    public static void print() {
        System.out.println("");
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printf(String message, Object... args) {
        System.out.printf(message, args);
        System.out.println("");
    }

    public static void clear() {
        for(int i = 0; i < 30; i++) {
            print("");
        }
    }

    public static String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

}
