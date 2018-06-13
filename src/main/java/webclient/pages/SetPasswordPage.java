package webclient.pages;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class SetPasswordPage {

    public SetPasswordPage(String password) {
        $("#PasswordResetForm_password").setValue(password);
        $("#PasswordResetForm_passwordConfirm").setValue(password);
    }

    public SetPasswordPage submit() {
        $(byName("yt0")).click();
        return this;
    }
}
