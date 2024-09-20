package stepDefinitions;

import common.InitApplication;
import interfaces.pageUI.CommonPageUI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GuruInsuranceRegisterStepDefinition extends InitApplication {
    public String email;
    public GuruInsuranceRegisterStepDefinition() {
        gotoGuruInsuranceLoginPage();
        email = "dung" + generateRandomStringNumber(3) + "@test.com";
    }
    @Given("User click button link field {string}")
    public void userClickButtonLinkField(String label) {
        clickToElement(driver, CommonPageUI.BUTTON_LINK_FIELD, label);
    }
    @When("User select dropdown list field {string} with value {string}")
    public void userSelectDropdownListFieldWithValue(String fieldName, String value) {
        selectValueOfDropdownList(driver, value, CommonPageUI.DROPDOWN_LIST_FIELD, fieldName);
    }
    @When("User fill textbox field {string} with value {string}")
    public void userFillTextboxFieldWithValue(String fieldName, String value) {
        if(fieldName.equals("email")){
            value = email;
        }
        inputDataToTextField(driver, value,CommonPageUI.TEXTBOX_FIELD, fieldName);
    }

    @And("User select radio button with value {string}")
    public void userSelectRadioButtonWithValue(String value) {
        clickToElement(driver, CommonPageUI.RADIO_BUTTON_FIELD, value);
    }

    @And("User click button input field {string}")
    public void userClickButtonInputField(String label) {
        clickToElement(driver, CommonPageUI.BUTTON_INPUT_FIELD, label);
    }

    @Then("User verify login sucessfully")
    public void userVerifyLoginSucessfully() {
        assertTrue(verifyElementVisible(driver, CommonPageUI.BUTTON_INPUT_FIELD,"Log out"));
        assertEquals(email, getTextOfElement(driver, CommonPageUI.EMAIL_LOGIN_SUCCESS));
    }
}
