package webclient.pages;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class RegisterConfirmPage {

    public RegisterConfirmPage() {
    }

    public RegisterConfirmPage enterCode(String code) {
        $("#ActivateRegistrationRequestForm_Code").setValue(code);
        return this;
    }

    public RegisterConfirmPage submit() {
        $(byName("yt0")).click();
        return this;
    }
}
