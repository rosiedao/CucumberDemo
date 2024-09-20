package common;

public class InitApplication extends Hooks {
    public void gotoAdminLoginPage() {
        openPageUrl(getDriver(), GlobalConstant.ADMIN_URL);
    }
    public void gotoGuruInsuranceLoginPage() {
        openPageUrl(getDriver(), GlobalConstant.GURU_INSURANCE_URL);
    }
}