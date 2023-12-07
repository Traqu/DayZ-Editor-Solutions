package Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class VersionReader {
    public static String getVersion(){
        String version = "";
        try (InputStream inputStream = VersionReader.class.getResourceAsStream("/version");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            version = bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  version;
    }
}