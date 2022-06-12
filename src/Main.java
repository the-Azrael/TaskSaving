import java.io.*;
import java.util.zip.*;

public class Main {
    private static final String GAMES_DIR = "D://Java//Games";
    private static File saveFile = new File(GAMES_DIR + "//savegames");
    private static File zipFile = new File(saveFile.getAbsolutePath() + "//saves.zip");
    private static File saveDatFile1 = new File(saveFile.getAbsolutePath() + "//save1.dat");
    private static File saveDatFile2 = new File(saveFile.getAbsolutePath() + "//save2.dat");
    private static File saveDatFile3 = new File(saveFile.getAbsolutePath() + "//save3.dat");
    private static GameProgress save1 = new GameProgress(100, 100, 1, 0);
    private static GameProgress save2 = new GameProgress(50, 50, 10, 10_000.1);
    private static GameProgress save3 = new GameProgress(10, 20, 20, 21_500.5);

    public static void saveGame(String path, GameProgress progress) {
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(progress);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(File zipFile, File[] saveFile) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (File file : saveFile) {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);
                byte[] bytes = new byte[fis.available()];
                fis.read(bytes);
                zos.write(bytes);
                zos.closeEntry();
                fis.close();
            }
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteFiles(File[] files) {
        for (File file : files) {
            if (file.exists() && file.canWrite()) {
                System.out.println(file.getAbsolutePath());
            }

            if (file.delete()) {
                System.out.println(file.getName() + " deleted!");
            } else {
                System.out.println(file.getAbsolutePath() + " not deleted!");
            };
        }
    }

    public static void main(String[] args) {
        saveGame(saveDatFile1.getAbsolutePath(), save1);
        saveGame(saveDatFile2.getAbsolutePath(), save2);
        saveGame(saveDatFile3.getAbsolutePath(), save3);
        File[] files = new File[] {saveDatFile1, saveDatFile2, saveDatFile3};
        zipFiles(zipFile, files);
        deleteFiles(files);
    }
}
