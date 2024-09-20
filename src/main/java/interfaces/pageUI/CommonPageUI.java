package interfaces.pageUI;

public class CommonPageUI {
    public static final String TEXTBOX_FIELD = "xpath = //input[@name='%s']";
    public static final String BUTTON_LINK_FIELD = "xpath = //a[contains(@class,'btn') and text()='%s']";
    public static final String BUTTON_INPUT_FIELD = "xpath = //input[contains(@class,'btn') and @value='%s']";
    public static final String DROPDOWN_LIST_FIELD = "xpath = //select[@name='%s']";
    public static final String RADIO_BUTTON_FIELD = "xpath = //label[text()='%s']";
    public static final String EMAIL_LOGIN_SUCCESS = "css = div.content>h4";
}
