package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.jupiter.api.Assertions.*;

public class SimpleFormTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();


    }

    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSuccess() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[class='input__control'][type='text']")).sendKeys("Петя");
        driver.findElement(By.cssSelector("[class='input__control'][type='tel']")).sendKeys("+79000000000");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("[type='button'][class='button button_view_extra button_size_m button_theme_alfa-on-white']")).click();
        String text = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldNameFail() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[class='input__control'][type='text']")).sendKeys("Амиржан 4");
        driver.findElement(By.cssSelector("[class='input__control'][type='tel']")).sendKeys("+7911735630");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("[type='button'][class='button button_view_extra button_size_m button_theme_alfa-on-white']")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
    @Test
    void shouldPhoneFail() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[class='input__control'][type='text']")).sendKeys("Амиржан");
        driver.findElement(By.cssSelector("[class='input__control'][type='tel']")).sendKeys("+791173563000");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //String text = driver.findElement(By.xpath("//div[@class='form-field form-field_size_m form-field_theme_alfa-on-white']/span[@class='input input_type_tel input_view_default input_size_m input_width_available input_has-label input_has-value input_invalid input_theme_alfa-on-white']")).getText();
        //String text = driver.findElement(By.xpath("//div[@class='form-field form-field_size_m form-field_theme_alfa-on-white']")).getText();
        //String text = driver.findElement(By.className("form-field form-field_size_m form-field_theme_alfa-on-white")).findElement(By.className("input input_type_tel input_view_default input_size_m input_width_available input_has-label input_has-value input_invalid input_theme_alfa-on-white")).getText();
        String text=driver.findElement(By.xpath("//div[contains(@class,'input input_type_tel input_view_default input_size_m input_width_available input_has-label input_has-value input_invalid input_theme_alfa-on-white')] [contains(text(),'Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.')]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }
}
