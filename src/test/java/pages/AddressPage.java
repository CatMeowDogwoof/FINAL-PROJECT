package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class AddressPage {

    public void openAddressBook() {

        $$("button")
                .findBy(Condition.text("Address book"))
                .click();

        $$("button")
                .findBy(Condition.text("Add new address"))
                .shouldBe(Condition.visible);
    }

    public int getAddressCount() {


        return $$("button").filterBy(Condition.text("Edit address")).size();
    }

    public void addAddress() {

        int before = getAddressCount();

        $$("button")
                .findBy(Condition.text("Add new address"))
                .click();

        $("label[for='default_billing']").click();

        $("#firstname").setValue("testerovna");
        $("#lastname").setValue("satesteroshvili");
        $("#telephone").setValue("555555555");
        $("#city").setValue("ტესტერის 1");


        $("#country_id").selectOptionByValue("AF");

        $("#region_string")
                .setValue("კულინარიული დუელი");

        $("#postcode").setValue("1500");

        $("#street")
                .setValue("სამრეცხაოს გამზირი 34, ბინა 8");

        $$("button")
                .findBy(Condition.text("Save address"))
                .click();


        $$("button")
                .filterBy(Condition.text("Edit address"))
                .shouldHave(CollectionCondition.size(before + 1));
    }
}
