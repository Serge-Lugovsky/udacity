package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AllureEnvironmentWriter {

    public static void environmentWriter(){
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");
        String javaVersion = System.getProperty("java.version");


        Properties prop = new Properties();
        OutputStream output = null;

        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir")
                    + "/target/allure-results"));

            File outputEnvPropertiesFile = new File(System.getProperty("user.dir")
                    + "/target/allure-results/environment.properties");

            output = new FileOutputStream(outputEnvPropertiesFile);

            prop.setProperty("OS.version", osName + " " + osVersion + " " + osArch);
            prop.setProperty("Java.version", javaVersion);
            prop.setProperty("Maven.version", PropertyLoader.loadProperty("maven.version"));
            prop.setProperty("Allure.testng.version", PropertyLoader.loadProperty("allure.testng.version"));
            prop.setProperty("Allure.report.version", PropertyLoader.loadProperty("allure.report.version"));

            prop.store(output, null);

        } catch (IOException io) {
            System.out.println("error trace 1");
            io.printStackTrace();

        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("Trace 2 error");
                    e.printStackTrace();
                }
            }

        }

    }
}
