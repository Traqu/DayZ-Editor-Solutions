package Logic;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class Script {
    public static final String USER_PATH = System.getProperty("user.home");
    private final List<String> HAS_LOOTSPAWNS_LIST = new ArrayList<>();
    private final Set<String> HAS_LOOTSPAWNS_SET = new HashSet<>();

    public Script(String filePath, String sourceFile) throws FileNotFoundException {
        System.out.println("File location: " + filePath);
        List<String> placedObjects;

        File sourceDir = new File("resources/protoFiles");
        File[] files = sourceDir.listFiles();
        assert files != null;
        for (File file : files) {
            if (!file.getName().equals("rawProtoFiles")) {
                try {
                    List<String> lines = Files.readAllLines(Path.of(file.getPath()));
                    HAS_LOOTSPAWNS_SET.addAll(lines);
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

        placedObjects.forEach(object -> HAS_LOOTSPAWNS_SET.forEach(protoObject -> {
            if (object.contains(protoObject)) {
                HAS_LOOTSPAWNS_LIST.add(object);
            }

        }));

        String path = USER_PATH + File.separator + "Documents" + File.separator + "DayZ" + File.separator + "Editor";

        sourceFile = sourceFile.substring(0,sourceFile.length()-4);
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
}