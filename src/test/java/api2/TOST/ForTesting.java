package api2.TOST;

import api2.models.internetDocument.CreateEW;
import api2.service.Helper;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ForTesting {

    private Model model;

    @Test
    void t0() throws IOException {
        model = new CreateEW()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c") // test_tech01

                .replaceProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .replaceProperty("CitySender", "8d5a980d-391c-11dd-90d9-001a92567626")
                .replaceProperty("SenderAddress", "35725e52-54df-11e8-ad11-005056886752")
                .replaceProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")

                .replaceProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .replaceProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .replaceProperty("RecipientAddress", "1ec09d88-e1c2-11e3-8c4a-0050568002cf")
                .replaceProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()


        ;
    }

    @Test
    void t1() throws IOException {
        model = new CreateEW()
                .apiKey(new Helper().getApiKeyCorporateTest())

                .replaceProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .replaceProperty("CitySender", "8d5a980d-391c-11dd-90d9-001a92567626")
                .replaceProperty("SenderAddress", "35725e52-54df-11e8-ad11-005056886752")
                .replaceProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")

                .replaceProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .replaceProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .replaceProperty("RecipientAddress", "1ec09d88-e1c2-11e3-8c4a-0050568002cf")
                .replaceProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")

                .build().printPrettyRequest()
                .run(Server.TEST)
                .printPrettyResponse()

        ;
    }

    @Test
    @DisplayName("POST - RPZ-54468 -- Реализация нового шаблона маркировки ЕН (пдф, 8 на А4)")
    void t4() throws IOException {
        List<String> refs = new ArrayList<>();
        refs.add("9e95acd6-84da-11e8-ad11-005056886752");
        refs.add("ae3fe54b-84da-11e8-ad11-005056886752");
        refs.add("d180cfd3-85a9-11e8-ad11-005056886752");
        refs.add("df3d6468-85a9-11e8-ad11-005056886752");

        refs.add("9e95acd6-84da-11e8-ad11-005056886752");
        refs.add("ae3fe54b-84da-11e8-ad11-005056886752");
//        refs.add("d180cfd3-85a9-11e8-ad11-005056886752");
//        refs.add("df3d6468-85a9-11e8-ad11-005056886752");

        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("printMarkings")

                .addProperty("DocumentRefs", refs)
                .addProperty("Type", "html4")

                .build()
                .printPrettyRequest()

//                .run(Server.TEST).printPrettyResponse()
        ;


    }

    @Test
    @DisplayName("GET - RPZ-54468 -- Реализация нового шаблона маркировки ЕН (пдф, 8 на А4)")
    void t5() {
        String apiKey = "1a85cdffb52d67959d040ce9d517ab4c";
        List<String> refs = new ArrayList<>();
        refs.add("9e95acd6-84da-11e8-ad11-005056886752");
        refs.add("ae3fe54b-84da-11e8-ad11-005056886752");

        StringBuilder builder = new StringBuilder();
        builder.append("http://webclient.sb.np.ua/orders/printMarkings/");
        for (String ref : refs) {
            builder.append("orders[]/").append(ref).append("/");
        }

        builder.append("type/pdf/apiKey/").append(apiKey);

        String request = builder.toString();

        System.out.println(request);

    }

    @Test
    void v0() throws IOException {
        System.out.println(new Helper().getApiKeyOfUserByEWNumber("20600000071045", Server.TEST));
    }

}
