package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    public void editName(String name) {

        $$("button")
                .findBy(Condition.text("Edit details"))
                .click();

        $("#firstname").shouldBe(Condition.visible).clear();

        $("#firstname")
                .setValue(name);

        $$("button")
                .findBy(Condition.text("Save customer"))
                .click();


        $$("button")
                .findBy(Condition.text("Edit details"))
                .shouldBe(Condition.visible);
    }

    public String getName() {


        $$("button")
                .findBy(Condition.text("Edit details"))
                .click();

        return $("#firstname").shouldBe(Condition.visible).getValue();
    }
}
