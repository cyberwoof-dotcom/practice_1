package Interface;

import java.util.List;

public interface JsonInterface {
    Object jsonRead(Object object);
    <T> List<T> jsonReadAll(Class<T> clazz);
}
