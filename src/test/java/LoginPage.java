import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class LoginPage {

    static void login (String login, String password) {
        $("[data-statlog='notifications.mail.logout.enter']").shouldBe(visible).click();
        if ($("input[type=tel]").exists()) {
            $(".Button2-Text", 0).parent().click();
        }
        $("[id='passp-field-login']").setValue(login);
        $("[id='passp:sign-in']").click();
        $("[id='passp-field-passwd']").setValue(password).pressEnter();
        $(".desk-notif-card__mail-title").shouldBe(visible).click();
        switchTo().window(1);
    }
}
