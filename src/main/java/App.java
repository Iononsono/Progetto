import java.util.Locale;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Gamesystem gs = new Gamesystem();
        gs.start();
    }
}
