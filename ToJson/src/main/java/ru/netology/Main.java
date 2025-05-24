package ru.netology;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;


public class Main {

    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        writeString(json, "data.json");

        String xmlFile = "data.xml";
        List<Employee> xmlList = parseXML(xmlFile);
        String jsonFromXML = listToJson(xmlList);
        writeString(jsonFromXML, "data2.json");
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();

            return csvToBean.parse();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения CSV файла: " + e.getMessage());
        }
    }
    private static List<Employee> parseXML(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException("Файл не найден: " + fileName);
        }

        XStream xStream = new XStream();
        xStream.alias("employee", Employee.class);
        xStream.alias("employees", List.class);

        return (List<Employee>) xStream.fromXML(file);
    }

    private static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.toJson(list, listType);
    }

    private static void writeString(String json, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
            System.out.println("JSON файл успешно записан: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи JSON файла: " + e.getMessage());
        }
    }
}