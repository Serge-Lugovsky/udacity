package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogsWriter {

    public static void writeBrowserLog(WebDriver driver) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        final String LOGS_FILE = System.getProperty("user.dir")+"/browser.log";
        Logs logs = driver.manage().logs();
        LogEntries logEntries = logs.get(LogType.PERFORMANCE);

        System.out.println("==================| WRITE BROWSER LOGS |=======================");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOGS_FILE))) {
            for (LogEntry entry : logEntries) {
                bw.write(dateFormat.format(date) +" "+  entry.getTimestamp() + " " +
                        entry.getLevel() + " " + entry.getMessage() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
