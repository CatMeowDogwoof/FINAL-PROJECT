package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public void login(String email, String password) {

        $$("span")
                .findBy(Condition.exactText("Sign in"))
                .click();


        $("#email:not(.Subscription-Field)").setValue(email);

        $("#password").setValue(password);

        $$("button.Button")
                .findBy(Condition.text("Sign in"))
                .click();


        $$("button")
                .findBy(Condition.text("Logout"))
                .shouldBe(Condition.visible);
    }
}
