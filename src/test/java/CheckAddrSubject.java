import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckAddrSubject {
    String toWriteButton = "[href='#compose']";
    String newLetterButton = ".ComposePopup-Content";
    String sendEmailButton = "button.Button2.Button2_pin_circle-circle.Button2_view_default";
    String popupNotSentWindow = ".modal__content .ComposeConfirmPopup-Content";
    String backToLetterButton = ".ComposeConfirmPopup-Button";
    String addressInputField = ".MultipleAddressesDesktop-Field.ComposeYabblesField";
    String email = "n.surnametest2@yandex.ru";
    String topicInputField = "[name='subject']";
    String popupHasBeSentWindow = ".ComposeDoneScreen-Wrapper";

    @BeforeAll
    static void openBrowserLogin() {
        Selenide.open("https://yandex.ru");
        LoginPage.login("n.surnametest", "!N.surnametest!");
    }

    @Test
    void mainTest() {
        $(toWriteButton).click();
        $(newLetterButton).shouldHave(text("Новое письмо"));
        $(sendEmailButton).click();
        $(popupNotSentWindow).shouldHave(text("Письмо не отправлено"));
        $(popupNotSentWindow).shouldHave(text("Пожалуйста, укажите адрес получателя"));
        $(backToLetterButton).click();
        $$(addressInputField).first().lastChild().sendKeys(email);
        $(sendEmailButton).click();
        Selenide.sleep(300);
        if ($(popupNotSentWindow).$(byText("Письмо не отправлено")).exists()) {
            $(backToLetterButton).click();
            $(topicInputField).setValue("Тестовое письмо");
            $(sendEmailButton).click();
        }
        $(popupHasBeSentWindow).shouldHave(text("Письмо отправлено"));

    }
}
