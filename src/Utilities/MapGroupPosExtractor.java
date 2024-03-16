package Utilities;

import GraphicInterfaces.Constants.Interfaces.UserPathConstants;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;



public class MapGroupPosExtractor implements UserPathConstants {


    private final List<String> HAS_LOOTSPAWNS_LIST = new ArrayList<>();
    private final Set<String> HAS_LOOTSPAWNS_SET = new HashSet<>();

    public MapGroupPosExtractor(String filePath, String sourceFile) throws FileNotFoundException {
        System.out.println("Extracting from: " + filePath);
        List<String> placedObjects;


        File file = new File(CUSTOM_RESOURCES_PATH);
        File[] customFiles = file.listFiles();

        readLinesFromResourceFile(LIVONIA);
        readLinesFromResourceFile(CHERNARUS);

        if (customFiles != null) {
            for (File customFile : customFiles) {
                BufferedReader customFilesReader = new BufferedReader(new FileReader(customFile.getPath()));
                String line;
                while (true) {
                    try {
                        if ((line = customFilesReader.readLine()) == null) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    HAS_LOOTSPAWNS_SET.add(line);
                }
            }
        }

        try {
            placedObjects = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        placedObjects.forEach(object -> HAS_LOOTSPAWNS_SET.forEach(protoObject -> {
            if (object.contains(protoObject)) {
                HAS_LOOTSPAWNS_LIST.add(object);
            }

        }));



        sourceFile = sourceFile.substring(0, sourceFile.length() - 4);
        String output = DAYZ_EDITOR_PATH + File.separator + sourceFile + ".txt";


        //Adding distinct, because for whatever reason I had ONLY_ONE occurrence of doubled item,
        //even though it wasn't duplicated in The Editor nor anywhere else
        //this seems to have fixed it though...

        List<String> lootspawns = new ArrayList<>(HAS_LOOTSPAWNS_LIST.stream().distinct().toList());

        try (PrintWriter writer = new PrintWriter(output)) {
            lootspawns.forEach(writer::println);
        } catch (IOException e) {
            System.out.println(output);
            if (new File(DAYZ_EDITOR_PATH).mkdirs()) {
                System.out.println("Created - [" + DAYZ_EDITOR_PATH + "].");
            }
            try (PrintWriter writer = new PrintWriter(output)) {
                lootspawns.forEach(writer::println);
            } catch (IOException e1) {
                throw new RuntimeException("Missing DayZ Editor directory");
            }
            try {
                Desktop.getDesktop().open(new File(output));
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        }
        try {
            Desktop.getDesktop().open(new File(output));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    private void readLinesFromResourceFile(String map) {
        try (InputStream inputStream = getClass().getResourceAsStream("/protoFiles/" + map)) {
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    HAS_LOOTSPAWNS_SET.add(line);
                }
            } else {
                throw new RuntimeException("Nie można odczytać zasobów protoFiles.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}