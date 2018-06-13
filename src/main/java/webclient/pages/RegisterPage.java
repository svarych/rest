package webclient.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import webclient.Ownership;
import webclient.Server;
import webclient.UserType;
import org.joda.time.DateTime;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegisterPage {

    public RegisterPage() {
    }

    public RegisterPage(Server server, UserType userType) {
        if (server == Server.TEST) {
            open("http://webclient.sb.np.ua/registration");
        }
        if (server == Server.LIVE) {
            open("https://my.novaposhta.ua/registration");
        }
        if (server == Server.MY2) {
            open("https://my2.novaposhta.ua/registration");
        }
        userType(userType);
    }

    private RegisterPage userType(UserType userType) {
        if (userType == UserType.CORPORATE) {
            $(byAttribute("data-type", "business")).click();
        }
        if (userType == UserType.LOYALTY) {
            $(byAttribute("data-type", "person")).click();
        }
        return this;
    }

    public RegisterPage city(String city) {
        $("#NovaposhtaRegistrationRequestForm_CityRefSelectBoxItContainer").click();
        $(byXpath("//ul/li/a[.='" + city + "']")).scrollTo().click();
        return this;
    }

    public RegisterPage companyName(String name) {
        $("#NovaposhtaRegistrationRequestForm_CompanyName").setValue(name);
        return this;
    }

    public RegisterPage ownership(Ownership ownership) {
        $("#NovaposhtaRegistrationRequestForm_OwnerShipFormSelectBoxItText").click();
        $(byXpath("//ul/li/a[.='" + ownership + "']")).click();
        return this;
    }

    public RegisterPage edrpou(String code) {
        $("#NovaposhtaRegistrationRequestForm_EDRPOU").setValue(code);
        return this;
    }

    public RegisterPage lastName(String lastName) {
        $("#NovaposhtaRegistrationRequestForm_LastName").setValue(lastName);
        return this;
    }

    public RegisterPage firstName(String firstName) {
        $("#NovaposhtaRegistrationRequestForm_FirstName").setValue(firstName);
        return this;
    }

    public RegisterPage middleName(String middleName) {
        $("#NovaposhtaRegistrationRequestForm_MiddleName").setValue(middleName);
        return this;
    }

    public RegisterPage phone(String phone) {
        $("#NovaposhtaRegistrationRequestForm_Phone").setValue(phone);
        return this;
    }

    public RegisterPage email(String email) {
        $("#NovaposhtaRegistrationRequestForm_Email").setValue(email);
        return this;
    }

    public RegisterPage birthDay(String dateTime) throws ParseException {
        $("#NovaposhtaRegistrationRequestForm_BirthDate").scrollTo().click();
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateTime);
        DateTime dt = new DateTime(date);

        String year = dt.year().getAsString();
        String month = dt.monthOfYear().getAsString();
        String day = dt.dayOfMonth().getAsString();

        $(By.xpath("//span[contains(@class,'year')]")).shouldBe(Condition.visible);

        SelenideElement targetYear = $(By.xpath("//span[contains(@class,'year')][.='" + year + "']"));
        while (true) {
            if ($(targetYear).exists()) {
                $(targetYear).click();
                break;
            } else {
                $(By.xpath(".//div[@class='datetimepicker-years']//i[@class='icon-arrow-left']")).click();
            }
        }
        SelenideElement months = $(By.xpath("(.//div[@class='datetimepicker-months']//tbody//td/span)[" + month + "]"));
        SelenideElement days = $(By.xpath("//div[@class='datetimepicker-days']//td[@class='day'][.='" + day + "']"));
        months.shouldBe(Condition.appears).click();
        days.shouldBe(Condition.appears).click();

        return this;
    }

    public RegisterPage confirmPersonalDataUsage() {
        $("#NovaposhtaRegistrationRequestForm_Confirm").click();
        return this;
    }

    public RegisterPage acceptRules() {
        $("#NovaposhtaRegistrationRequestForm_Rules").click();
        return this;
    }

    public RegisterPage submit() {
        $(byName("yt0")).click();
        return this;
    }

    public String currentOwnership() {
        return $("#NovaposhtaRegistrationRequestForm_OwnerShipFormSelectBoxIt").getText();
    }

    public String getErrorMessage() {
        return $(byXpath("(//div[@class='errorSummary']/ul/li)[1]")).getText();
    }

    public List<String> getErrorMessages() {
        List<String> siteMessages = $$(byXpath("//div[@class='errorSummary']/ul/li")).texts();
        Collections.sort(siteMessages);
        return siteMessages;
    }

    public List<String> getMessagesFromFile(String file) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));

        List<Object> data = new ArrayList<>(properties.values());
        List<String> output = new ArrayList<>();
        for (Object o : data) {
            output.add(o.toString());
        }

        Collections.sort(output);
        return output;
    }

    public String getMessageFromFileFor(String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream("./src/main/resources/register_warnings.properties"), Charset.forName("UTF-8")));
        return properties.getProperty(key);
    }

    public String getMessageFromFileFor(String key, String file) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
        return properties.getProperty(key);
    }

    public List<String> getAllMessagesFromFile(String filename) throws IOException {
        String file = "./src/main/resources/" + filename;
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
        List<String> messages = new ArrayList<>();
        for (Object o : properties.values()) {
            messages.add(o.toString());
        }
        return messages;
    }
}
