package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.jupiter.api.Assertions.*;

public class SeleniumHappySadPathTest {
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
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void setDown() {
        driver.quit();
        driver = null;
    }

    //------------ HappyPath
    @Test
    void shouldSuccess() {
        driver.findElement(By.cssSelector("[class='input__control'][type='text']")).sendKeys("Амиржан");
        driver.findElement(By.cssSelector("[class='input__control'][type='tel']")).sendKeys("+79000000000");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("[type='button'][class='button button_view_extra button_size_m button_theme_alfa-on-white']")).click();
        String text = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    //------------ Проверка поля Имя

    //неверный формат поля Имя
    @Test
    void shouldNameFail() {
        driver.findElement(By.cssSelector("[class='input__control'][type='text']")).sendKeys("Амиржан 4");
        driver.findElement(By.cssSelector("[class='input__control'][type='tel']")).sendKeys("+7911735630");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("[type='button'][class='button button_view_extra button_size_m button_theme_alfa-on-white']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    //незаполнение поля Имя
    @Test
    void shouldFailNameEmpty() {
        driver.findElement(By.cssSelector("[class='input__control'][type='text']")).sendKeys("");
        driver.findElement(By.cssSelector("[class='input__control'][type='tel']")).sendKeys("7911735630");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("[type='button'][class='button button_view_extra button_size_m button_theme_alfa-on-white']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

//------------ Проверка поля Телефон

    //неверный формат поля Телефон
    @Test
    void shouldPhoneFail() {
        driver.findElement(By.cssSelector("[class='input__control'][type='text']")).sendKeys("Амиржан");
        driver.findElement(By.cssSelector("[class='input__control'][type='tel']")).sendKeys("88911735630");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("[type='button'][class='button button_view_extra button_size_m button_theme_alfa-on-white']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    //незаполнение поля Телефон
    @Test
    void shouldPhoneFailEmpty() {
        driver.findElement(By.cssSelector("[class='input__control'][type='text']")).sendKeys("Амиржан");
        driver.findElement(By.cssSelector("[class='input__control'][type='tel']")).sendKeys("");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("[type='button'][class='button button_view_extra button_size_m button_theme_alfa-on-white']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }
}
