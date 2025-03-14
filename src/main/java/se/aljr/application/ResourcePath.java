package se.aljr.application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Scanner;

public class ResourcePath {
    public static String getResourcePath(String resourceName) {
        try {
            InputStream inputStream;
            if(isRunningFromJar()){
                // Hämta resursens URL
                inputStream = ResourcePath.class.getClassLoader().getResourceAsStream("resources/"+resourceName);
                if (inputStream == null) {
                    throw new RuntimeException("Filen hittades inte: " + resourceName);
                }
            }else{
                inputStream = Objects.requireNonNull(ResourcePath.class.getClassLoader().getResource(resourceName)).openStream();
            }


            // Skapa en temporär fil
            Path tempFile = Files.createTempFile(null, ".json");

            // Kopiera innehållet från resursen till den temporära filen
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            // Stäng strömmen
            inputStream.close();

            // Returnera den temporära filens väg
            return tempFile.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException("Fel vid läsning eller extrahering av resurs: " + resourceName, e);
        }
    }

    public static boolean isRunningFromJar() {
        try {
            URL location = ResourcePath.class.getProtectionDomain().getCodeSource().getLocation();

            String path = location.getPath();

            return path.endsWith(".jar");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
