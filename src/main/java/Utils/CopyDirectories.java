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
//            e.printStackTrace();
            System.out.println("=====================|Failed copy|=========================");
        }
    }

    public static void makeAllureReport() {
        try {
            Runtime.getRuntime().exec("mvn allure:report");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=====|Report complete|=====");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyResultAndHistoryDirs(){

        String sourceResult = "/home/serhii/Udacity/target/allure-results/";
        String destinationResult = "/home/serhii/Udacity/src/main/resources/allure-results/";

        copyFiles(sourceResult, destinationResult);

        String sourceHistory = "/home/serhii/Udacity/target/allure-reports/history/";
        String destinationHistory = "/home/serhii/Udacity/src/main/resources/allure-results/history/";

        copyFiles(sourceHistory, destinationHistory);
    }
}
