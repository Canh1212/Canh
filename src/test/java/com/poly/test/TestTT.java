package com.poly.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestTT {

    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\acer\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/login");
    }

    @Test(description = "Đăng nhập" , priority = 1)
    public void testLoginUser() throws InterruptedException {
        WebElement username = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));

        Thread.sleep(1000);
        username.sendKeys("admin@gmail.com");
        Thread.sleep(1000);
        password.sendKeys("123");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }
    
    @Test(description = "Truy cập trang admin" , priority = 2)
    public void testAccessAdmin() throws InterruptedException {        
        driver.get("http://localhost:8080/admin");
        Thread.sleep(2000);
    }

    @Test(description = "Truy cập trang danh mục" , priority = 3)
    public void testAccessCategories() throws InterruptedException {
        driver.get("http://localhost:8080/admin/categories");
        Thread.sleep(2000);
    }

    @Test(description = "Truy cập trang Add" , priority = 4)
    public void testCRUD() throws InterruptedException {
    	driver.get("http://localhost:8080/admin/categories/add");
        WebElement name = driver.findElement(By.id("name"));
        Thread.sleep(1000);
        name.sendKeys("Máy Tính");
        driver.findElement(By.id("them")).click();
        Thread.sleep(2000);
          
        List<WebElement> updateLinks = driver.findElements(By.cssSelector("a.btn.btn-warning"));

        int maxId = -1;
        for (WebElement link : updateLinks) {
            String href = link.getAttribute("href"); 
            String[] parts = href.split("/");
            int id = Integer.parseInt(parts[parts.length - 1]);
            if (id > maxId) {
                maxId = id;
            }
        }

        driver.get("http://localhost:8080/admin/categories/update/" + maxId);

        WebElement updateButton = driver.findElement(By.id("name"));
        Thread.sleep(1000);
        updateButton.clear();
        Thread.sleep(2000);
        updateButton.sendKeys("Máy Lạnh");
        Thread.sleep(2000);
        driver.findElement(By.id("them")).click();
        Thread.sleep(2000);
        
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("a.btn.btn-danger"));
        deleteButtons.get(deleteButtons.size() - 1).click(); // Click nút Xóa cuối cùng
        Thread.sleep(2000);
        
        driver.get("http://localhost:8080/");       
        Thread.sleep(2000);
        
        WebElement keywordInput = driver.findElement(By.name("keyword"));
        Thread.sleep(2000);
        keywordInput.sendKeys("MacBook");
        Thread.sleep(2000);

        WebElement searchButton = driver.findElement(By.cssSelector("button.btn-outline-success"));
        searchButton.click();
        Thread.sleep(5000);      
    }
	    @AfterTest
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}

