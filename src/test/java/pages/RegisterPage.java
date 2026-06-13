package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class RegisterPage {

    public void register() {

        $$("span")
                .findBy(Condition.text("Create an account"))
                .click();

        $("button.Button_isHollow")
                .click();

        $("#firstname").shouldBe(Condition.visible).setValue("ტესტერი");

        $("#lastname").setValue("ტესტერაშვილი");

        String email =
                "tester" +
                        System.currentTimeMillis() +
                        "@gmail.com";


        $("#email:not(.Subscription-Field)").setValue(email);


        $("#password")
                .setValue("Testeri123!");

        $("#confirm_password")
                .setValue("Testeri123!");

        $("button[type='submit']")
                .click();
    }

    public boolean isRegistered() {


        return $$("button")
                .findBy(Condition.text("Logout"))
                .shouldBe(Condition.visible)
                .exists();
    }
}
