package Interface;

import java.util.List;

public interface CSVInterface {
    Object csvRead(Object object);
    <T> List<T> csvReadAll(Class<T> clazz);
}
