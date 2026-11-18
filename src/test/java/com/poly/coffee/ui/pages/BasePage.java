package com.poly.coffee.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * =========================================================================
 * BASE PAGE OBJECT - LỚP CƠ SỞ CHO TOÀN BỘ CÁC TRANG (POM)
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        if (text != null) {
            element.sendKeys(text);
        }
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Chụp ảnh màn hình lưu vào thư mục screenshots/ phục vụ minh chứng kiểm thử.
     */
    public String takeScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            Path destDir = Paths.get("screenshots");
            if (!Files.exists(destDir)) {
                Files.createDirectories(destDir);
            }
            Path destination = destDir.resolve(testName + "_" + System.currentTimeMillis() + ".png");
            Files.copy(source.toPath(), destination);
            return destination.toAbsolutePath().toString();
        } catch (IOException e) {
            return "Không thể chụp màn hình: " + e.getMessage();
        }
    }
}
