package Utilities;

import Annotation.CSVProviderAnnotation;
import Interface.CSVInterface;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.apache.poi.ss.formula.functions.T;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtility implements CSVInterface {

    public Object csvRead(Object object) {
        String[] array = object.toString().split(" ");
        Object t = null;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(array[1]);
            t = clazz.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Method method : t.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(CSVProviderAnnotation.class)) {
                method.setAccessible(true);
                try {
                    Map<String, String> mapping = new HashMap<String, String>();
                    for (Field field : clazz.getDeclaredFields()) {
                        mapping.put(field.getName(), field.getName());
                    }
                    String[] value = clazz.getSimpleName().split("\\.");
                    System.out.println(value[0]);
                    HeaderColumnNameTranslateMappingStrategy<T> strategy = new HeaderColumnNameTranslateMappingStrategy<T>();
                    strategy.setType((Class<? extends T>) t.getClass());
                    strategy.setColumnMapping(mapping);
                    Path resourceDirectory = Paths.get("src", "test", "data", "csv", value[0] + ".csv");
                    String path = Paths.get(String.valueOf(resourceDirectory)).toAbsolutePath().toString();
                    CSVReader csvReader = new CSVReader((new FileReader(path)), ',');
                    CsvToBean<T> csvToBean = new CsvToBean<>();
                    t = csvToBean.parse(strategy, csvReader).get(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return t;
    }

    public <T> List<T> csvReadAll(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CSVProviderAnnotation.class)) {
                method.setAccessible(true);
                try {
                    Map<String, String> mapping = new HashMap<String, String>();
                    for (Field field : clazz.getDeclaredFields()) {
                        mapping.put(field.getName(), field.getName());
                    }
                    String[] value = clazz.getSimpleName().split("\\.");
                    System.out.println(value[0]);
                    HeaderColumnNameTranslateMappingStrategy<T> strategy = new HeaderColumnNameTranslateMappingStrategy<T>();
                    strategy.setType(clazz);
                    strategy.setColumnMapping(mapping);
                    Path resourceDirectory = Paths.get("src", "test","data", "csv", value[0] + ".csv");
                    String path = Paths.get(String.valueOf(resourceDirectory)).toAbsolutePath().toString();
                    CSVReader csvReader = new CSVReader((new FileReader(path)), ',');
                    CsvToBean<T> csvToBean = new CsvToBean<>();
                    list = csvToBean.parse(strategy, csvReader);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
