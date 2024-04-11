package SupportingProgrammes.ProtoExtractor;

import java.io.*;

public class ProtoExtractor {

    public static void main(String[] args) {
        final String SOURCE_FOLDER = "C:\\Users\\piotr\\IdeaProjects\\DayZ-Editor-Code_PostCleaner\\resources\\rawProtoFiles";
        final String DESTINATION_FOLDER = "C:\\Users\\piotr\\IdeaProjects\\DayZ-Editor-Code_PostCleaner\\resources\\protoFiles";

        try {
            File sourceDir = new File(SOURCE_FOLDER);
            File[] files = sourceDir.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        processFile(file, DESTINATION_FOLDER);
                        file.delete();
                    }
                }
            } else {
                System.out.println("No files found in the source folder.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        File sourceDir = new File(DESTINATION_FOLDER);
        File[] files = sourceDir.listFiles();
        assert files != null;
        for (File file : files) {
            if (!file.getName().equals("rawProtoFiles")) {
                try {
                    removeEmptyLines(file.getAbsolutePath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private static void processFile(File sourceFile, String destinationFolder) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String line;
            String sourceFileName = sourceFile.getName();
            String mapName = getMapNameFromFileName(sourceFileName);
            String outputFileName = destinationFolder + "\\" + mapName;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                boolean isFirstLine = true;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("<group name=")) {
                        int startIndex = line.indexOf("name=\"") + 6;
                        int endIndex = line.indexOf("\"", startIndex);

                        if (endIndex != -1) {
                            String groupName = line.substring(startIndex, endIndex);
                            if (!isFirstLine) {
                                writer.newLine();
                            }
                            writer.write(groupName);
                            isFirstLine = false;
                        }
                    }
                }
            }
        }
    }

    private static String getMapNameFromFileName(String fileName) {
        int startIndex = fileName.indexOf("_-_") + 3;
        int endIndex = fileName.lastIndexOf(".xml");

        if (endIndex != -1) {
            return fileName.substring(startIndex, endIndex);
        }

        return fileName;
    }

    public static void removeEmptyLines(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder content = new StringBuilder();
        boolean isFirstLine = true;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                if (!isFirstLine) {
                    content.append("\n");
                }
                content.append(line);
                isFirstLine = false;
            }
        }

        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content.toString());
        writer.close();
    }
}