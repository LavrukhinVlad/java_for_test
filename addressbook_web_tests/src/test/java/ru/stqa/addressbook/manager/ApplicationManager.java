package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contact;
    private JdbcHelper jdbc;
    private HibernateHelper hbm;

    private Properties properties;

    public void init(String browser, Properties properties) throws MalformedURLException {
        this.properties = properties;
        if (driver == null) {
            var seleniumServer = properties.getProperty("seleniumServer");
            if ("chrome".equals(browser)) {
                if (seleniumServer != null){
                    driver = new RemoteWebDriver(new URL(seleniumServer), new ChromeOptions());
                }
                else {
                    driver = new ChromeDriver();
                }
            } else if ("edge".equals(browser)) {
                if (seleniumServer != null){
                    driver = new RemoteWebDriver(new URL(seleniumServer), new EdgeOptions());
                }
                else {
                    driver = new EdgeDriver();
                }
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"), this);
        }
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public ContactHelper contact() {
        if (contact == null) {
            contact = new ContactHelper(this);
        }
        return contact;
    }

    public JdbcHelper jdbc() {
        if (jdbc == null) {
            jdbc = new JdbcHelper(this);
        }
        return jdbc;
    }

    public HibernateHelper

    hbm() {
        if (hbm == null) {
            hbm = new HibernateHelper(this);
        }
        return hbm;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
