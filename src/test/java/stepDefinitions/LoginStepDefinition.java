package stepDefinitions;

import common.GlobalConstant;
import common.InitApplication;
import interfaces.pageUI.GuruDemoLoginPageUI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class LoginStepDefinition extends InitApplication{
    //Tao ra constructor cho tung step def, luc nao cung goi dau tien
    public LoginStepDefinition(){
        gotoAdminLoginPage();
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
        Assert.assertEquals("Successfully Logged in...", getTextOfElement(driver,GuruDemoLoginPageUI.SUCCESS_MESSAGE)); //Check fail
    }

    @When("user enters username with value {string} and password with value {string}")
    public void userEntersUsernameWithValueAndPasswordWithValue(String email, String password) {
        inputDataToTextField(driver, GuruDemoLoginPageUI.EMAIL, email);
        inputDataToTextField(driver,GuruDemoLoginPageUI.PASSWORD, password);
    }

    @When("user enters username and password as below data")
    public void userEntersUsernameAndPasswordAsBelowData(DataTable dataTable) {
        //List<Map<String, String>> accounts = dataTable.asMaps(String.class, String.class);
        for(Map<String, String> loginInfo : dataTable.asMaps(String.class, String.class)) {
            inputDataToTextField(driver, GuruDemoLoginPageUI.EMAIL, loginInfo.get("Username"));
            inputDataToTextField(driver, GuruDemoLoginPageUI.PASSWORD, loginInfo.get("Password"));
        }
    }
}
