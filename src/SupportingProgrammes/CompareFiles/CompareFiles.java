package SupportingProgrammes.CompareFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CompareFiles {
    public static void main(String[] args) {
        String file1Path = "C:\\Users\\piotr\\IdeaProjects\\DayZ-Editor-Purifier\\resources\\protoFiles\\chernarusTest";
        String file2Path = "C:\\Users\\piotr\\IdeaProjects\\DayZ-Editor-Purifier\\resources\\protoFiles\\livoniaTest";

        try {
            Map<String, Set<String>> linesFromFile1 = new HashMap<>();
            Map<String, Set<String>> linesFromFile2 = new HashMap<>();

            // Odczytaj wszystkie wiersze z pliku 1
            BufferedReader reader1 = new BufferedReader(new FileReader(file1Path));
            String line;
            int lineNumber = 1;
            while ((line = reader1.readLine()) != null) {
                linesFromFile1.computeIfAbsent(line, k -> new HashSet<>()).add("File 1, line " + lineNumber);
                lineNumber++;
            }
            reader1.close();

            // Odczytaj wszystkie wiersze z pliku 2
            BufferedReader reader2 = new BufferedReader(new FileReader(file2Path));
            lineNumber = 1;
            while ((line = reader2.readLine()) != null) {
                linesFromFile2.computeIfAbsent(line, k -> new HashSet<>()).add("File 2, line " + lineNumber);
                lineNumber++;
            }
            reader2.close();

            // Znajdź wiersze, które są w pliku 1, ale nie ma ich w pliku 2
            for (Map.Entry<String, Set<String>> entry : linesFromFile1.entrySet()) {
                String lineText = entry.getKey();
                Set<String> locations = entry.getValue();
                if (!linesFromFile2.containsKey(lineText)) {
                    System.out.println("CHERNARUS: " + lineText + " (znaleziono w: " + locations + ")");
                }
            }

            System.out.println();

            // Znajdź wiersze, które są w pliku 2, ale nie ma ich w pliku 1
            for (Map.Entry<String, Set<String>> entry : linesFromFile2.entrySet()) {
                String lineText = entry.getKey();
                Set<String> locations = entry.getValue();
                if (!linesFromFile1.containsKey(lineText)) {
                    System.out.println("LIVONIA: " + lineText + " (znaleziono w: " + locations + ")");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
