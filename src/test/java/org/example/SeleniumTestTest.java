package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class SeleniumTestTest {

//    private WebDriver webDriver;
//    @BeforeEach
//    void setUp() {
//        webDriver = new ChromeDriver();
//
//
//    }

//    @AfterEach
//    void tearDown(){
//        if (webDriver!=null){
//            webDriver.quit();
//        }
//    }
//    @Test
//    @DisplayName("打开百度，搜索 Selenium，断言标题包含 Selenium")
//    void searchBaidu() throws InterruptedException {
//        webDriver.get("https://the-internet.herokuapp.com/logi");
//
//        WebElement searchBox = webDriver.findElement(By.id("kw"));
//        searchBox.sendKeys("Selenium");
//
//        WebElement searchButton = webDriver.findElement(By.id("su"));
//        searchButton.click();
//
//        Thread.sleep(2000);  // 等页面加载，正式代码不用 sleep
//
//        assertTrue(webDriver.getTitle().contains("Selenium"));
//    }
}