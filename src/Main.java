import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

public class Main {
    private static final String GAMES_DIR = "D://Java//Games";
    private static final String SAVE_DIR_PATH = GAMES_DIR + "//savegames";
    private static final String ZIP_PATH = SAVE_DIR_PATH + "//saves.zip";
    private static final String SAVE1_PATH = SAVE_DIR_PATH + "//save1.dat";
    private static final String SAVE2_PATH = SAVE_DIR_PATH + "//save2.dat";
    private static final String SAVE3_PATH = SAVE_DIR_PATH + "//save3.dat";
    private static List saveFilesList = new ArrayList<String>();
    private static GameProgress save1 = new GameProgress(100, 100, 1, 0);
    private static GameProgress save2 = new GameProgress(50, 50, 10, 10_000.1);
    private static GameProgress save3 = new GameProgress(10, 20, 20, 21_500.5);

    public static void fillFilePathList() {
        saveFilesList.add(SAVE1_PATH);
        saveFilesList.add(SAVE2_PATH);
        saveFilesList.add(SAVE3_PATH);
    }

    public static void saveGame(String path, GameProgress progress) {
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(progress);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String zipFile, List<String> filesPathList) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (String filePath : filesPathList) {
                File file = new File(filePath);
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

    public static void deleteFiles(List<String> listFilesPath) {
        for (String filePath : listFilesPath) {
            File file = new File(filePath);
            if (file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }

    public static void main(String[] args) {
        saveGame(SAVE1_PATH, save1);
        saveGame(SAVE2_PATH, save2);
        saveGame(SAVE3_PATH, save3);
        fillFilePathList();
        zipFiles(ZIP_PATH, saveFilesList);
        deleteFiles(saveFilesList);
    }
}
