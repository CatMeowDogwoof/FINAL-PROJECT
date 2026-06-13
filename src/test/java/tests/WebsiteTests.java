package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AddressPage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.RegisterPage;

import static com.codeborne.selenide.Selenide.*;

public class WebsiteTests {

    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();
    AddressPage addressPage = new AddressPage();
    RegisterPage registerPage = new RegisterPage();

    String email = "xzichzijiciau@gmail.com";
    String password = "123QWEqwe@";

    @BeforeMethod
    public void setup() {

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        open("https://qatest-dev.indvp.com/");
    }

    @AfterMethod
    public void tearDown() {


        closeWebDriver();
    }

    @Test(priority = 1)
    @Description("TC01 Login with existing user")
    public void tc01_Login() {

        loginPage.login(email, password);

        Assert.assertTrue(
                webdriver().driver().url().contains("account"),
                "User should be redirected to the account area after login"
        );
    }

    @Test(priority = 2)
    @Description("TC02 Edit profile details")
    public void tc02_EditProfile() {

        loginPage.login(email, password);

        profilePage.editName("KANFETEBI");

        refresh();

        Assert.assertEquals(
                profilePage.getName(),
                "KANFETEBI",
                "The new first name should be persisted after a page refresh"
        );
    }

    @Test(priority = 3)
    @Description("TC03 Logout from the system")
    public void tc03_Logout() {

        loginPage.login(email, password);

        $$("button")
                .findBy(Condition.text("Logout"))
                .click();

        $$("span")
                .findBy(Condition.exactText("Sign in"))
                .shouldBe(Condition.visible);

        Assert.assertFalse(
                webdriver().driver().url().contains("account"),
                "User should be returned to the start page after logout"
        );
    }

    @Test(priority = 4)
    @Description("TC04 Register a new user")
    public void tc04_Register() {

        registerPage.register();

        Assert.assertTrue(
                registerPage.isRegistered(),
                "A new account should be created and logged in after registration"
        );
    }

    @Test(priority = 5)
    @Description("TC05 Add a new address")
    public void tc05_AddAddress() {

        loginPage.login(email, password);

        addressPage.openAddressBook();

        int before = addressPage.getAddressCount();

        addressPage.addAddress();

        Assert.assertEquals(
                addressPage.getAddressCount(),
                before + 1,
                "The new address should be added to the address list"
        );
    }
}
