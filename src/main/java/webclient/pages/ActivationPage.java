package webclient.pages;

import api2.service.DataBase;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class ActivationPage {

    public ActivationPage() throws SQLException {
        open(getEmailActivationLink());
    }

    private String getEmailActivationLink() throws SQLException {
        String page;
        String link;
        page = new DataBase()
                .getResponseX("SELECT MessageContent FROM wemailmessages ORDER BY MessageDate DESC LIMIT 1").toString();

        link = page.split("href=\"")[1].split("</a>")[0].split("\"")[0];
        return link;
    }
}
