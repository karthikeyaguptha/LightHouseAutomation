package lightHouse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class testLHA {



    public static void main(String[] args) throws IOException {

        String CustomSpecificPort = "9226";

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver","D:\\Selenium Workout\\LightHouseAutomation\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--remote-allow-origins=*");
        opt.setExperimentalOption("debuggerAddress","localhost:"+CustomSpecificPort); // using same chrome instance in custom specific port given
        WebDriver driver = new ChromeDriver(opt);

        String URL = "https://www.youtube.com/";
        driver.get(URL);

        lighthouseReport(URL, "Page", CustomSpecificPort); // lighthouse execution will be done here

    }


    private static void lighthouseReport(String URL, String ReportName, String CustomSpecificPort) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "lighthouse", URL, "--port="+CustomSpecificPort,
                "--preset=desktop", "--output=html", "--output-path=target/" + ReportName + ".html");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }



}
