import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
// Путь к основной директории (поменяйте под свой путь)
        String baseDirPath = "C:\\Users\\Keniq\\Games"; // Windows
        // String baseDirPath = "/Users/admin/Games"; // macOS или Linux

        File baseDir = new File(baseDirPath);
        if (!baseDir.exists()) {
            System.out.println("Папка Games не найдена по указанному пути.");
            return;
        }

        StringBuilder log = new StringBuilder();

        // Список для хранения всех создаваемых файлов и папок
        File[] directoriesToCreate = {
                new File(baseDir, "src"),
                new File(baseDir, "res"),
                new File(baseDir, "savegames"),
                new File(baseDir, "temp"),
                new File(baseDir, "src/main"),
                new File(baseDir, "src/test"),
                new File(baseDir, "res/drawables"),
                new File(baseDir, "res/vectors"),
                new File(baseDir, "res/icons")
        };

        File[] filesToCreate = {
                new File(baseDir, "src/main/Main.java"),
                new File(baseDir, "src/main/Utils.java")
        };

        // Создание директорий
        for (File dir : directoriesToCreate) {
            boolean result = dir.mkdirs();
            if (result) {
                log.append("Директория создана: ").append(dir.getAbsolutePath()).append("\n");
            } else {
                log.append("Не удалось создать директорию: ").append(dir.getAbsolutePath()).append("\n");
            }
        }

        // Создание файлов
        for (File file : filesToCreate) {
            try {
                boolean result = file.createNewFile();
                if (result) {
                    log.append("Файл создан: ").append(file.getAbsolutePath()).append("\n");
                } else {
                    log.append("Файл уже существует: ").append(file.getAbsolutePath()).append("\n");
                }
            } catch (IOException e) {
                log.append("Ошибка при создании файла: ").append(file.getAbsolutePath()).append("\n");
                e.printStackTrace();
            }
        }

        // Запись лога в temp/temp.txt
        File tempFile = new File(baseDir, "temp/temp.txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Не удалось записать лог в файл.");
            e.printStackTrace();
        }
    }
}