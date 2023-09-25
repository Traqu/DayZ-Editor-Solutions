package Logic;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class Script {
    public static final String USER_PATH = System.getProperty("user.home");
    private static final String CUSTOM_RESOURCES_SUB_PATH = "\\AppData\\Local\\DayZ EditorPurifier\\custom";
    private static final String CUSTOM_RESOURCES_PATH = USER_PATH + CUSTOM_RESOURCES_SUB_PATH;
    public static final String LIVONIA = "livonia";
    public static final String CHERNARUS = "chernarus";


    private final List<String> HAS_LOOTSPAWNS_LIST = new ArrayList<>();
    private final Set<String> HAS_LOOTSPAWNS_SET = new HashSet<>();

    public Script(String filePath, String sourceFile) throws FileNotFoundException {
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

        String path = USER_PATH + "\\Documents\\DayZ\\Editor";

        sourceFile = sourceFile.substring(0, sourceFile.length() - 4);
        String output = path + File.separator + sourceFile + ".txt";

        try (PrintWriter writer = new PrintWriter(output)) {
            for (String lootSpawn : HAS_LOOTSPAWNS_LIST) {
                writer.println(lootSpawn);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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