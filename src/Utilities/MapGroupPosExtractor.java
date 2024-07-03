package Utilities;

import GraphicInterfaces.Constants.Interfaces.MapsConstants;
import GraphicInterfaces.Constants.Interfaces.UserPathConstants;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class MapGroupPosExtractor implements UserPathConstants, MapsConstants {

    private final List<String> HAS_LOOTSPAWNS_LIST = new ArrayList<>();
    private final Set<String> HAS_LOOTSPAWNS_SET = new HashSet<>();

    public MapGroupPosExtractor(String filePath, String sourceFile) throws FileNotFoundException {
        System.out.println("Extracting from: " + filePath);
        List<String> placedObjects;

        File file = new File(CUSTOM_RESOURCES_PATH);
        File[] customFiles = file.listFiles();

        // Automatically read lines from all resource files defined in MapsConstants
        readLinesFromAllResourceFiles();

        if (customFiles != null) {
            for (File customFile : customFiles) {
                try (BufferedReader customFilesReader = new BufferedReader(new FileReader(customFile.getPath()))) {
                    String line;
                    while ((line = customFilesReader.readLine()) != null) {
                        HAS_LOOTSPAWNS_SET.add(line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        try {
            placedObjects = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        placedObjects.stream()
                .filter(object -> {
                    String name = object.replaceAll("(.*name=\"(\\w+)\".*)", "$2");
                    return HAS_LOOTSPAWNS_SET.contains(name);
                })
                .forEach(HAS_LOOTSPAWNS_LIST::add);

        sourceFile = sourceFile.substring(0, sourceFile.length() - 4);
        String output = DAYZ_EDITOR_PATH + File.separator + sourceFile + ".txt";

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

    private void readLinesFromAllResourceFiles() {
        Field[] fields = MapsConstants.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                try {
                    String mapName = (String) field.get(null);
                    if (resourceFileExists(mapName)) {
                        readLinesFromResourceFile(mapName);
                    } else {
                        System.out.println("Warning: Resource file for map " + mapName + " not found.");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private boolean resourceFileExists(String map) {
        return getClass().getResource("/protoFiles/" + map) != null;
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
                throw new RuntimeException("Nie można odczytać zasobów protoFiles: " + map);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
