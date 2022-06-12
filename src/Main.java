import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main {
    private static final String GAMES_DIR = "D://Java//Games";
    private static File saveGamesFile = new File(GAMES_DIR + "//savegames");
    private static File saveGamesDatFile1 = new File(saveGamesFile.getAbsolutePath() + "save1.dat");
    private static GameProgress save1 = new GameProgress(100, 100, 1, 0);
    private static GameProgress save2 = new GameProgress(50, 50, 10, 10_000.1);
    private static GameProgress save3 = new GameProgress(10, 20, 20, 21_500.5);


    public static void main(String[] args) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(saveGamesDatFile1);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(save1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
