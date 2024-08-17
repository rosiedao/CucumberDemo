package common;

public class InitApplication extends InitBrowser {
    public void gotoAdminLoginPage() {
        openPageUrl(getDriver(), GlobalConstant.ADMIN_URL);
    }
}