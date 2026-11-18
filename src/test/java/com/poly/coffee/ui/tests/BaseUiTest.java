package com.poly.coffee.ui.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * =========================================================================
 * BASE UI TEST - KHỞI TẠO VÀ DỌN DẸP CHROME WEBDRIVER
 * Hỗ trợ chế độ Headless cho CI/CD GitHub Actions
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public abstract class BaseUiTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Cấu hình chạy không đầu (Headless) tương thích máy chủ Linux CI/CD
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        this.driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Lấy đường dẫn file HTML tĩnh trong resources/static
     */
    protected String getPageUrl(String pageName) {
        Path path = Paths.get("src/main/resources/static/" + pageName);
        return path.toUri().toString();
    }
}
