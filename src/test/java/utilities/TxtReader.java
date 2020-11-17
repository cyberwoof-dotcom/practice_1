package utilities;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class TxtReader {
    private final String FILE_NAME = "src/test/resources/fileForTestRail.txt";

    public MultiValuedMap<String, String> fileToMapConversionForTestRail(){
        String delimiter = "#";
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();

        try(Stream<String> lines = Files.lines(Paths.get(FILE_NAME))){
            lines.filter(line -> line.contains(delimiter)).forEach(
                    line -> map.put(line.split(delimiter)[0], line.split(delimiter)[1])
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

     //   System.out.println(map);
        return map;
    }

    public static void main(String[] args) {
        TxtReader txtReader =new TxtReader();
        MultiValuedMap<String,String>map=txtReader.fileToMapConversionForTestRail();
        for(Map.Entry<String,String>entry:map.entries()){
            System.out.println(entry.getKey());
        }
    }
}