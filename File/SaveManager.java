import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SaveManager {

    // Путь к папке savegames (внутри Games из предыдущей задачи)
    private static final String SAVE_DIR = "C:\\Users\\Keniq\\Games\\savegames"; // Windows


    public static void main(String[] args) {
        // Создаем три экземпляра GameProgress
        GameProgress save1 = new GameProgress(100, 3, 5, 250.5);
        GameProgress save2 = new GameProgress(80, 2, 4, 190.75);
        GameProgress save3 = new GameProgress(120, 4, 6, 300.0);

        // Сохраняем каждый объект в отдельный файл
        saveGame(SAVE_DIR + "/save1.dat", save1);
        saveGame(SAVE_DIR + "/save2.dat", save2);
        saveGame(SAVE_DIR + "/save3.dat", save3);

        // Архивируем все .dat файлы
        List<String> filesToZip = List.of(
                SAVE_DIR + "/save1.dat",
                SAVE_DIR + "/save2.dat",
                SAVE_DIR + "/save3.dat"
        );

        zipFiles(SAVE_DIR + "/zip.zip", filesToZip);

        // Удаляем исходные файлы
        for (String file : filesToZip) {
            File f = new File(file);
            if (f.delete()) {
                System.out.println("Файл удален: " + file);
            } else {
                System.err.println("Не удалось удалить файл: " + file);
            }
        }
    }

    // Метод для сохранения объекта GameProgress в файл
    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(gameProgress);
            System.out.println("Сохранение создано: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + filePath);
            e.printStackTrace();
        }
    }

    // Метод для упаковки файлов в ZIP
    public static void zipFiles(String zipFilePath, List<String> filesToZip) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            byte[] buffer = new byte[1024];

            for (String file : filesToZip) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    File srcFile = new File(file);
                    String entryName = srcFile.getName();

                    zos.putNextEntry(new ZipEntry(entryName));
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                    System.out.println("Файл добавлен в архив: " + entryName);
                } catch (IOException e) {
                    System.err.println("Ошибка при добавлении файла в архив: " + file);
                    e.printStackTrace();
                }
            }
            System.out.println("Архив создан: " + zipFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка при создании ZIP-архива.");
            e.printStackTrace();
        }
    }
}