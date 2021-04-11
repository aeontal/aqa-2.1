package ru.netology.web;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideHappySadPathTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    //------------ HappyPath

    @Test
    void shouldSuccess(){
        $("[type='text']").sendKeys("Амиржан");
        $("[type='tel']").sendKeys("+79200000000");
        $("[class='checkbox__box']").click();
        $(byText("Продолжить")).click();
        $(Selectors.withText("Ваша заявка успешно отправлена!")).shouldBe(visible, Duration.ofSeconds(5));
    }


    //------------ Проверка поля Имя

    //незаполнение поля Имя
    @Test
    void shouldFailNameEmpty() {
        $("[type='text']").sendKeys("");
        $("[type='tel']").sendKeys("+79770000000");
        $("[class='checkbox__box']").click();
        $(byText("Продолжить")).click();
        $(Selectors.withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    //неверный формат поля Имя
    @Test
    void shouldFailName() {
        $("[type='text']").sendKeys("Амиржан 4");
        $("[type='tel']").sendKeys("+79200000010");
        $(byText("Продолжить")).click();
        $(Selectors.withText("Имя и Фамилия указаные неверно.")).shouldBe(visible);

    }

    //------------ Проверка поля Телефон

    //незаполнение поле Телефон
    @Test
    void shouldFailPhoneEmpty() {
        $("[type='text']").sendKeys("Вячеслав Никифоров");
        $("[type='tel']").sendKeys("");
        $("[class='checkbox__box']").click();
        $(byText("Продолжить")).click();
        $(Selectors.withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    //неверный формат поля Телефон
    @Test
    void shouldFailPhone() {
        $("[type='text']").sendKeys("Вячеслав Никифоров");
        $("[type='tel']").sendKeys("911");
        $(byText("Продолжить")).click();
        $(Selectors.withText("Телефон указан неверно.")).shouldBe(visible);
    }

    //------------ Проверка поля Согласия на обработку персональных данных
    //Невыставление пункта о согласии на обработку персональных данных

    @Test
    void shouldFieldValidBeOnCheckBox() {
        $("[type='text']").sendKeys("Дима");
        $("[type='tel']").sendKeys("+79200000000");
        $(byText("Продолжить")).click();
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white input_invalid']").shouldBe(visible);

    }
}




