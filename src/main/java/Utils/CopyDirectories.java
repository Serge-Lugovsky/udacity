package Utils;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class CopyDirectories {

    private static void copyFiles(String source, String destination) {

        File srcDir = new File(source);

        File destDir = new File(destination);

        try {
            FileUtils.waitFor(srcDir, 5);
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            System.out.println("=====================|Failed copy|=========================");
            e.printStackTrace();
        }
    }

    public static void makeAllureReport() {
        try {
            Runtime.getRuntime().exec("mvn allure:report").waitFor();
            System.out.println("=====|Report complete|=====");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void copyResultsDir(){
        String sourceResult = new File("target/allure-results/").getAbsolutePath();
        String destinationResult = new File("src/main/resources/allure-results/").getAbsolutePath();

        copyFiles(sourceResult, destinationResult);
    }

    public static void copyHistoryDir(){
        String sourceHistory = new File("target/allure-reports/history").getAbsolutePath();
        String destinationHistory = new File("src/main/resources/allure-results/history/").getAbsolutePath();

        copyFiles(sourceHistory, destinationHistory);
    }
}
