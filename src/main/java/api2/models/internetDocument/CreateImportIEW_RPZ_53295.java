package api2.models.internetDocument;

import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateImportIEW_RPZ_53295 extends ModelBuilder {

    enum Option {
        save, update
    }

    private Map<String, Object> goods = new LinkedHashMap<>();

    public void addGoods(String K, Object V) {
        goods.put(K, V);
    }

    private Map<String, Object> optionsSeat = new LinkedHashMap<>();

    private Map<String, Object> items = new LinkedHashMap<>();

//    public CreateImportIEW_RPZ_53295(Option option) {
//        if (option == Option.save) {
//            System.out.println("It is not, you need");
//        }
//        if (option == Option.update) {
//            System.out.println("Trying to update");
//
//        }
//    }

    public CreateImportIEW_RPZ_53295() {

        addGoods("goodsDescription", "Shirt");
        addGoods("goodsMeasure", "шт.");
        addGoods("goodsQuantity", "2");
        addGoods("goodsCostPerUnit", "13.99");
        addGoods("goodsTotalCost", "27.98");
        addGoods("goodsMaterial", "Cotton");
        addGoods("goodsOrigin", "CN");

        items.put("volumetricVolume", "0.0004");
        items.put("volumetricWidth", "5.00");
        items.put("volumetricLength", "5.00");
        items.put("volumetricHeight", "2.00");
        items.put("weight", "0.0860");

        optionsSeat.put("item", items);

        this
                .apiKey("a99f4b6f4152636c73f98d90e0f82f5f")
                .modelName("InternetDocument")
                .calledMethod("save")

//                .addProperty("Latin", "1")
//                .addProperty("NewAddress", "1")
//
//                .addProperty("PayerType", "Sender")
//                .addProperty("PaymentMethod", "Cash")
//
//                .addProperty("CargoType", "Parcel")
//                .addProperty("VolumeGeneral", "0.0004")
//                .addProperty("VolumeWeight", "0.0125")
//                .addProperty("Weight", "0.0860")
//
//                .addProperty("ServiceType", "WarehouseDoors")
//                .addProperty("SeatsAmount", "1")
//                .addProperty("Description", "Міжнародний вантаж")
//                .addProperty("Cost", "52.35691")
//
//                .addProperty("CitySender", "8d5a980d-391c-11dd-90d9-001a92567626")
//                .addProperty("Sender", "6b4e9074-290d-11e4-acce-0050568002cf")
//                .addProperty("SenderAddress", "1ec09d88-e1c2-11e3-8c4a-0050568002cf")
//                .addProperty("ContactSender", "c08aeacd-bc5a-11e6-af9a-005056886752")
//                .addProperty("SendersPhone", "+380991234567")
//                .addProperty("AreaIndex", "01001")
//
//                .addProperty("RecipientCityName", "Kiev")
//                .addProperty("RecipientAddressName", "prospekt Sobornosti, 6")
//                .addProperty("RecipientName", "Riabtsev AAAAAAAAA")
//                .addProperty("RecipientType", "PrivatePerson")
//                .addProperty("AccompanyingDocuments", "Shpilevay Alla")
//                .addProperty("RecipientsPhone", "380674002274")
//
//                .addProperty("CountrySender", "China")
//                .addProperty("InternationalDeliveryType", "Import")
//
//                .addProperty("InternationalCitySender", "BlablaCity")
//
//                .addProperty("InternationalSender", "SF-Express")
//                .addProperty("InternationalSenderAddress", "Sandju")
//                .addProperty("InternationalContactSender", "Hui Pen")
//                .addProperty("InternationalSendersPhone", "+380800500609")
//                .addProperty("InternationalWaybill", "460652161291")
//                .addProperty("InternationalWaybillPrice", "0")
//                .addProperty("InfoRegClientBarcodes", "460652161291")
//                .addProperty("PackingNumber", "460652161291")


                // TODO: ADDITIONAL FIELDS for create EW
//                .addProperty("AddressDefinition", "www") // It is not web address or method not works
//                .addProperty("InternationalZipCode", "101-11001")
//                .addProperty("Incoterms", "DAP")
//                .addProperty("Currency", "USD")

                .addProperty("Goods", goods)

//                .addProperty("OriginalDimensionalWeight", "0.022")
//                .addProperty("OriginalActualWeight", "0.123")
//                .addProperty("InternationalWarehouse", "Säge Weber, AT10002, DE, 3500, AT, Krems, Körnermarkt")
//                .addProperty("OriginalRecipientAddress", "01001, Kyiv, prospekt Sobornosti, 6")
//                .addProperty("InternationalServiceType", "Стандарт")
//                .addProperty("InternationalReason", "Інше")
//                .addProperty("InternationalReasonAttachments", "Повернення вантажу")
//
//                .addProperty("OptionsSeat", optionsSeat)
        ;


    }

    @Test
    void importTest() throws IOException {
        new CreateImportIEW_RPZ_53295().build().printPrettyRequest()
//                .run(Server.TEST).printPrettyResponse()
        ;
    }

    //    @Test
    void getKey() throws IOException {
        new ModelBuilder()
                .modelName("CorporateUserGeneral").calledMethod("getCorporateByLogin")
                .addProperty("Login", "NPIOS")
                .addProperty("Password", "Hahl0Doo")
                .build()
                .printPrettyRequest()
                .run(Server.TEST)
                .printPrettyResponse();
    }

    //    @Test
    void getAnyKey() throws IOException {
        new ModelBuilder()
                .modelName("LoyaltyUser").calledMethod("getLoyaltyUserByLogin")
                .addProperty("Login", "NPIOS")
                .addProperty("Password", "Hahl0Doo")
                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }
}
