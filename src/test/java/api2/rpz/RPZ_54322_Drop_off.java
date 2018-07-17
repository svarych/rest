package api2.rpz;

import api2.service.Helper;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RPZ_54322_Drop_off {

    private Model model;

    /**
     * RPZ-54322 - Новий формат відділення Drop Off - без грошових операцій
     */

    @Test
    @DisplayName("1.1 - Drop-off is Warehouse-sender: should be rejected")
    void senderIsDropOff() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("CargoType", "Parcel")
                .addProperty("VolumeGeneral", "0.1")
                .addProperty("Weight", "10")
                .addProperty("SeatsAmount", "1")
                .addProperty("ServiceType", "WarehouseWarehouse")
                .addProperty("Description", "Ковбаса")
                .addProperty("Cost", "300")

                .addProperty("CitySender", "8d5a980d-391c-11dd-90d9-001a92567626") // Київ
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "511fcf97-e1c2-11e3-8c4a-0050568002cf") //Відділення №42 (Drop-off)
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380444071624")

                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "92ca63f5-4175-11e8-ad11-005056886752")
                .addProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")
                .addProperty("RecipientsPhone", "380631813780")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;

        String error = new Helper().clear(model.getResponse().get("errors").toString());
        assertEquals("It is forbidden to send an order from the warehouse of Drop-Off!", error);
    }

    @Test
    @DisplayName("1.2 - Drop-off is Warehouse-recipient AND backward delivery option present: should be rejected")
    void backwardDeliveryPresent() throws IOException {

        // Backward delivery services
        Map<String, Object> services = new LinkedHashMap<>();
        services.put("Attorney", true);
        services.put("WaybillNewPostWithStamp", true);
        services.put("UserActions", "UserCallSender");

        // Backward delivery data
        Map<String, Object> d = new LinkedHashMap<>();
        d.put("PayerType", "Sender");
        d.put("CargoType", "Documents");
        d.put("Services", services);

        // Data object (list is needed by server data parser)
        List<Map<String, Object>> data = new ArrayList<>();
        data.add(d);

        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("CargoType", "Parcel")
                .addProperty("VolumeGeneral", "0.1")
                .addProperty("Weight", "10")
                .addProperty("SeatsAmount", "1")
                .addProperty("ServiceType", "WarehouseWarehouse")
                .addProperty("Description", "Ковбаса")
                .addProperty("Cost", "300")

                .addProperty("CitySender", "db5c88f5-391c-11dd-90d9-001a92567626")
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "1ec09d2e-e1c2-11e3-8c4a-0050568002cf")
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380444071624")

                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "511fcf97-e1c2-11e3-8c4a-0050568002cf") // В.№42 (Drop-off)
                .addProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")
                .addProperty("RecipientsPhone", "380631813780")

                .addProperty("BackwardDeliveryData", data)

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;

        String error = new Helper().clear(model.getResponse().get("errors").toString());
        assertEquals("It is forbidden to send an order to the warehouse of Drop-Off!", error);
    }

    @Test
    @DisplayName("1.3 - Should pass if CargoType is Parcel or Documents - (Parcel)")
    void cargoTypeParcel() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("CargoType", "Parcel")
                .addProperty("VolumeGeneral", "0.1")
                .addProperty("Weight", "10")
                .addProperty("SeatsAmount", "1")
                .addProperty("ServiceType", "WarehouseWarehouse")
                .addProperty("Description", "Ковбаса")
                .addProperty("Cost", "300")

                .addProperty("CitySender", "db5c88f5-391c-11dd-90d9-001a92567626")
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "1ec09d2e-e1c2-11e3-8c4a-0050568002cf")
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380444071624")

                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "511fcf97-e1c2-11e3-8c4a-0050568002cf") // В.№42 (Drop-off)
                .addProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")
                .addProperty("RecipientsPhone", "380631813780")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;

        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Should pass if CargoType is Parcel or Documents - (Documents)")
    void cargoTypeDocuments() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("CargoType", "Documents")
                .addProperty("Weight", "0.1")
                .addProperty("SeatsAmount", "1")
                .addProperty("ServiceType", "WarehouseWarehouse")
                .addProperty("Description", "Накладна")
                .addProperty("Cost", "100")

                .addProperty("CitySender", "db5c88f5-391c-11dd-90d9-001a92567626")
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "1ec09d2e-e1c2-11e3-8c4a-0050568002cf")
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380444071624")

                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "511fcf97-e1c2-11e3-8c4a-0050568002cf") // В.№42 (Drop-off)
                .addProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")
                .addProperty("RecipientsPhone", "380631813780")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;

        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Should be rejected if CargoType is not Parcel or Documents - (Cargo)")
    void cargoType() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("CargoType", "Cargo")
                .addProperty("VolumeGeneral", "1.0")
                .addProperty("Weight", "30")
                .addProperty("SeatsAmount", "1")
                .addProperty("ServiceType", "WarehouseWarehouse")
                .addProperty("Description", "Сир")
                .addProperty("Cost", "900")

                .addProperty("CitySender", "db5c88f5-391c-11dd-90d9-001a92567626")
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "1ec09d2e-e1c2-11e3-8c4a-0050568002cf")
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380444071624")

                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "511fcf97-e1c2-11e3-8c4a-0050568002cf") // В.№42 (Drop-off)
                .addProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")
                .addProperty("RecipientsPhone", "380631813780")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;

        String error = new Helper().clear(model.getResponse().get("errors").toString());
        assertEquals("It is allowed to send order to warehouse of Drop-Off only with CargoType \\Documents\\ or \\Parcel\\", error);
    }

    @ParameterizedTest
    @ValueSource(ints = {15000, 15001, 14999})
    @DisplayName("Drop-off is Warehouse-recipient AND cost >= 15000: should be rejected")
    void costEqualOrMoreThan15000(int cost) throws IOException {

        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("CargoType", "Documents")
                .addProperty("Weight", "0.1")
                .addProperty("SeatsAmount", "1")
                .addProperty("ServiceType", "WarehouseWarehouse")
                .addProperty("Description", "Накладна")
                .addProperty("Cost", cost)

                .addProperty("CitySender", "db5c88f5-391c-11dd-90d9-001a92567626")
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "1ec09d2e-e1c2-11e3-8c4a-0050568002cf")
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380444071624")

                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "511fcf97-e1c2-11e3-8c4a-0050568002cf") // В.№42 (Drop-off)
                .addProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")
                .addProperty("RecipientsPhone", "380631813780")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;

        if (cost < 15000) {
            assertTrue(model.getResponse().get("success").asBoolean());
        } else {
            String error = new Helper().clear(model.getResponse().get("errors").toString());
            assertEquals("It is allowed to send order to warehouse of Drop-Off only with Cost < 15000", error);
        }
    }

    @Test
    @DisplayName("Drop-off is Warehouse-recipient AND payer is Recipient AND paymentForm = Cash: should be rejected")
    void payerIsRecipientCash() throws IOException {

        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Recipient")
                .addProperty("PaymentMethod", "Cash")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("CargoType", "Documents")
                .addProperty("Weight", "0.1")
                .addProperty("SeatsAmount", "1")
                .addProperty("ServiceType", "WarehouseWarehouse")
                .addProperty("Description", "Накладна")
                .addProperty("Cost", 100)

                .addProperty("CitySender", "db5c88f5-391c-11dd-90d9-001a92567626")
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "1ec09d2e-e1c2-11e3-8c4a-0050568002cf")
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380444071624")

                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "511fcf97-e1c2-11e3-8c4a-0050568002cf") // В.№42 (Drop-off)
                .addProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")
                .addProperty("RecipientsPhone", "380631813780")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;

        String error = new Helper().clear(model.getResponse().get("errors").toString());
        assertEquals("It is forbidden to send Cash to the warehouse of Drop-Off with PayerType Recipient", error);
    }

    @ParameterizedTest
    @ValueSource(strings = {"IsTakeAttorney", "AfterpaymentOnGoodsCost", "ForwardingCount", "MarketPlaceSecurePayment", "DeliveryByHand", "FillingWarranty"})
    @DisplayName("Drop-off is Warehouse-recipient AND cost >= 15000: should be rejected")
    void presentOneOf(String additional) throws IOException {

        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("CargoType", "Documents")
                .addProperty("Weight", "0.1")
                .addProperty("SeatsAmount", "1")
                .addProperty("ServiceType", "WarehouseWarehouse")
                .addProperty("Description", "Накладна")
                .addProperty("Cost", 110)

                .addProperty("CitySender", "db5c88f5-391c-11dd-90d9-001a92567626")
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "1ec09d2e-e1c2-11e3-8c4a-0050568002cf")
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380444071624")

                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "511fcf97-e1c2-11e3-8c4a-0050568002cf") // В.№42 (Drop-off)
                .addProperty("ContactRecipient", "0276c314-9c58-11e7-af9a-005056886752")
                .addProperty("RecipientsPhone", "380631813780")

                .addProperty(additional, 1)

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;

        Assertions.assertEquals("[\"It is forbidden to send order to the warehouse of Drop-Off with this additional service\"]", model.getResponse().get("errors").toString());
    }

}
