package Utilities;

import GraphicInterfaces.Constants.Interfaces.MapsConstants;
import GraphicInterfaces.Constants.Interfaces.UserInterfaceConstants;
import GraphicInterfaces.Constants.Interfaces.UserPathConstants;
import GraphicInterfaces.FileChooserFrame;
import Utilities.DataStructures.InGameObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DynamicEventAdapter implements UserPathConstants, MapsConstants {

    public DynamicEventAdapter(String filePath, FileChooserFrame fileChooserFrame) throws ParserConfigurationException, IOException, SAXException {

        String output = filePath;

        Document dynamicEventDocument = getDocument(filePath);

        NodeList itemList = dynamicEventDocument.getElementsByTagName("events");

        if (itemList.getLength() == 0) {
            fileChooserFrame.setTitle("This is not an event file!");
            try {
                Thread.sleep(1250);
                fileChooserFrame.setTitle(" Choose file from which you want to extract objects" + PropertiesReader.getVersion(true) + " " + PropertiesReader.getAuthor());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("Selected file doesn't follow a dynamic event structure that DayZ Editor exports.");
        }

        StringBuilder stringBuilder = parseFile(itemList);
        writeToFile(output, stringBuilder);
        openFile(output.substring(0, output.length() - 3) + "txt");

        System.exit(0);
    }

    private static void openFile(String output) {
        try {
            Desktop.getDesktop().open(new File(output));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFile(String output, StringBuilder stringBuilder) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.substring(0, output.length() - 3) + "txt"))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder parseFile(NodeList itemList) {
        StringBuilder stringBuilder = new StringBuilder();
        float x = 0, y = 0, z = 0;
        int entry = 0;

        List<InGameObject> inGameObjectsList = new ArrayList<>();
        InGameObject referenceObject = null;

        for (int i = 0; i < itemList.getLength(); i++) {
            Element element = (Element) itemList.item(i);
            NodeList events = element.getElementsByTagName("event");

            for (int j = 0; j < events.getLength(); j++) {
                Element event = (Element) events.item(j);
                String inGameObjectName = event.getAttribute("name");
                NodeList positions = event.getElementsByTagName("pos");

                for (int k = 0; k < positions.getLength(); k++) {
                    Element pos = (Element) positions.item(k);
                    InGameObject inGameObject = new InGameObject(inGameObjectName, pos.getAttribute("x"), pos.getAttribute("y"), pos.getAttribute("a"), pos.getAttribute("z"));

                    if (inGameObjectName.contains("__")) {
                        referenceObject = inGameObject;
                    }

                    inGameObjectsList.add(inGameObject);
                }
            }
        }

        if (referenceObject != null) {
            inGameObjectsList.remove(referenceObject);
            inGameObjectsList.add(0, referenceObject);
        }

        List<String> spawnCapableObjectsList = readProtoClassNames();

        for (InGameObject inGameObject : inGameObjectsList) {
            boolean canSpawnLoot = spawnCapableObjectsList.contains(inGameObject.getObjectClassName());

            if (entry++ == 0) {
                x = inGameObject.x;
                z = inGameObject.z;
                y = inGameObject.y;
                inGameObject.x = 0;
                inGameObject.z = 0;
                inGameObject.y = 0;
            } else {
                inGameObject.x = -(x - inGameObject.x);
                inGameObject.x = (float) (Math.round(inGameObject.x * 100.0) / 100.0);
                inGameObject.y = -(y - inGameObject.y);
                inGameObject.y = (float) (Math.round(inGameObject.y * 100.0) / 100.0);
                inGameObject.z = -(z - inGameObject.z);
                inGameObject.z = (float) (Math.round(inGameObject.z * 100.0) / 100.0);

                if (inGameObject.a < 0) {
                    inGameObject.a += 360;
                }
            }
            prepareEventFile(inGameObject, inGameObjectsList, canSpawnLoot, stringBuilder);
        }
        return stringBuilder;
    }

    private static void prepareEventFile(InGameObject inGameObject, List<InGameObject> inGameObjectsList, boolean canSpawnLoot, StringBuilder stringBuilder) {
        if (inGameObjectsList.get(0).equals(inGameObject) && canSpawnLoot) {
            stringBuilder.append("<child type=\"").append(inGameObject.getObjectClassName()).append("\" deloot=\"1\" lootmax=\"1\" lootmin=\"1").append("\" x=\"").append(inGameObject.x).append("\" z=\"").append(inGameObject.z).append("\" a=\"").append(inGameObject.a).append("\" y=\"").append(inGameObject.y).append("\"/>\n");
        } else if (inGameObjectsList.get(0).equals(inGameObject)) {
            stringBuilder.append("<child type=\"").append(inGameObject.getObjectClassName()).append("\" x=\"").append(inGameObject.x).append("\" z=\"").append(inGameObject.z).append("\" a=\"").append(inGameObject.a).append("\" y=\"").append(inGameObject.y).append("\"/>\n");
        } else if (canSpawnLoot) {
            stringBuilder.append("<child type=\"").append(inGameObject.getObjectClassName()).append("\" spawnsecondary=\"false\" deloot=\"1\" lootmax=\"1\" lootmin=\"1").append("\" x=\"").append(inGameObject.x).append("\" z=\"").append(inGameObject.z).append("\" a=\"").append(inGameObject.a).append("\" y=\"").append(inGameObject.y).append("\"/>\n");
        } else {
            stringBuilder.append("<child type=\"").append(inGameObject.getObjectClassName()).append("\" spawnsecondary=\"false\" x=\"").append(inGameObject.x).append("\" z=\"").append(inGameObject.z).append("\" a=\"").append(inGameObject.a).append("\" y=\"").append(inGameObject.y).append("\"/>\n");
        }
    }

    private static List<String> readProtoClassNames() {
        List<String> spawnCapableClassesList = new ArrayList<>();
        File file = new File(CUSTOM_RESOURCES_PATH);
        File[] customFiles = file.listFiles();

        readLinesFromAllResourceFiles(spawnCapableClassesList);

        if (customFiles != null) {
            for (File customFile : customFiles) {
                System.out.println("Processing custom files: " + customFile);
                readSpawnsCapableClassesFromMission(spawnCapableClassesList, customFile);
            }
        }

        return spawnCapableClassesList;
    }

    private static void readLinesFromAllResourceFiles(List<String> spawnCapableClassesList) {
        Field[] fields = MapsConstants.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                try {
                    String mapName = (String) field.get(null);
                    if (resourceFileExists(mapName)) {
                        readSpawnsCapableClassesFromMission(spawnCapableClassesList, mapName);
                    } else {
                        System.out.println("Warning: Resource file for map " + mapName + " not found.");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static boolean resourceFileExists(String map) {
        return DynamicEventAdapter.class.getResource("/protoFiles/" + map) != null;
    }

    private static void readSpawnsCapableClassesFromMission(List<String> spawnCapableClassesList, File customFile) {
        try (BufferedReader customFilesReader = new BufferedReader(new FileReader(customFile))) {
            String line;
            while ((line = customFilesReader.readLine()) != null) {
                spawnCapableClassesList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readSpawnsCapableClassesFromMission(List<String> spawnCapableClassesList, String map) {
        try (InputStream inputStream = DynamicEventAdapter.class.getResourceAsStream("/protoFiles/" + map)) {
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    spawnCapableClassesList.add(line);
                }
            } else {
                throw new RuntimeException("Nie można odczytać zasobów protoFiles: " + map);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Document getDocument(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(path));
    }
}
