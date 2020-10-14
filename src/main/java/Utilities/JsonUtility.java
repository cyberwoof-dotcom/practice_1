package Utilities;

import Annotation.JsonProviderAnnotation;
import Interface.JsonInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtility implements JsonInterface {

    @Override
    public Object jsonRead(Object object) {
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
            if (method.isAnnotationPresent(JsonProviderAnnotation.class)) {
                method.setAccessible(true);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    String[] value = t.toString().split("\\{");
                    Path resourceDirectory = Paths.get("src", "test","data", "json", value[0] + ".json");
                    object = mapper.readValue(resourceDirectory.toFile(), t.getClass());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return object;
    }

    @Override
    public <T> List<T> jsonReadAll(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        ObjectMapper mapper=new ObjectMapper();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(JsonProviderAnnotation.class)) {
                method.setAccessible(true);
                try {
                    String[] value = clazz.getSimpleName().toString().split("\\{");
                    Path resourceDirectory = Paths.get("src", "test", "data", "json", value[0] + ".json");
                    CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class,clazz);
                    list=mapper.readValue(resourceDirectory.toFile(),javaType);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return list;
    }
}
