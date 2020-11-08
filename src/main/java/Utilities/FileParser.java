package Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileParser {

    private final String FILE_NAME = "src/test/resources/fileForTestRail.txt";

    public Map<String, List<String>> fileToMapConversionForTestRail() {
        Map<String, List<String>> map = new LinkedHashMap<>();
        List<String> verifyFilesTest = new LinkedList<>();
        List<Integer> lineNumbers = new LinkedList<>();
        int counter = 0;
        String value = "";
        String testCaseName = "";
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {
            List<String> list = Files.readAllLines(Paths.get(FILE_NAME.toString()));
            verifyFilesTest = stream.filter(line -> line.contains("verifyFilesTest"))
                    .map(String::toUpperCase).collect(Collectors.toList());

            lineNumbers = IntStream.range(0, list.size()).filter(i -> list.get(i).contains("verifyFilesTest"))
                    .mapToObj(i -> i + 1).collect(Collectors.toList());

            Iterator<String> iterator = verifyFilesTest.iterator();

            Long fromLine = 0L;
            while (iterator.hasNext()) {
                value = iterator.next();
                String suite[] = value.split(":");
                testCaseName = suite[1];

                List<String> allScenarios = new LinkedList<>();

                for (int i = 0; i < lineNumbers.size(); i++) {

                    try {
                        if (i == counter && i < lineNumbers.size() - 1) {
                            Stream<String> linesOfEveryScenario = Files.lines(Paths.get(FILE_NAME)).skip(lineNumbers.get(i)).limit(lineNumbers.get(i + 1) - lineNumbers.get(i) - 2);
                            allScenarios = linesOfEveryScenario.collect(Collectors.toList());
                            map.put(testCaseName, allScenarios);
                            fromLine = Long.valueOf(lineNumbers.get(i));
                            counter++;
                            break;

                        } else if (counter == lineNumbers.size() - 1) {
                            Stream<String> linesOfEveryScenario = Files.lines(Paths.get(FILE_NAME)).skip(lineNumbers.get(counter)).limit(list.size() - lineNumbers.get(counter));
                            allScenarios = linesOfEveryScenario.collect(Collectors.toList());
                            map.put(testCaseName, allScenarios);
                            break;
                        }
                    } catch (Exception e) {

                    }
                }


            }

         System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        FileParser fileParser=new FileParser();
        fileParser.fileToMapConversionForTestRail();
    }
}
