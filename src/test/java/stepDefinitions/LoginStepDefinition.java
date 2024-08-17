package stepDefinitions;

import common.GlobalConstant;
import common.InitApplication;
import common.InitBrowser;
import interfaces.pageUI.GuruDemoLoginPageUI;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefinition extends InitApplication{
    public LoginStepDefinition(){
        gotoAdminLoginPage();
    }
    @Given("user opens login page")
    public void userOpensLoginPage() {
        System.out.println("Step 1");
    }

    @When("user enters username and password")
    public void userEntersUsernameAndPassword() {
        inputDataToTextField(driver, GuruDemoLoginPageUI.EMAIL,GlobalConstant.ADMIN_USERNAME);
        inputDataToTextField(driver,GuruDemoLoginPageUI.PASSWORD,GlobalConstant.ADMIN_PASSWORD);
    }

    @And("clicks on login button")
    public void clicksOnLoginButton() {
        clickToElement(driver,GuruDemoLoginPageUI.BUTTON_LOGIN);
    }

    @Then("user is navigated to the homepage")
    public void userIsNavigatedToTheHomepage() {
        Assert.assertEquals("Successfully in...", getTextOfElement(driver,GuruDemoLoginPageUI.SUCCESS_MESSAGE)); //Check fail
    }
}
