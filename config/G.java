package config;

public class G {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void print(Object object) {
        System.out.println(object);
    }
}
