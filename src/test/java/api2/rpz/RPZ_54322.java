package api2.rpz;

import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

class RPZ_54322 {

    private Model model;

    /**
     * RPZ-54322 - Новий формат відділення Drop Off - без грошових операцій
     */

    @Test
    @DisplayName("Drop-off is Warehouse-sender: should be rejected")
    void senderIsDropOff() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("")
                .calledMethod("")

                .addProperty("", "")
                .addProperty("", "")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }

    @Test
    @DisplayName("Drop-off is Warehouse-recipient AND backward delivery option present: should be rejected")
    void backwardDeliveryPresent() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("")
                .calledMethod("")

                .addProperty("", "")
                .addProperty("", "")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pallet", "TiresWheels"})
    @DisplayName("Drop-off is Warehouse-recipient AND cargoType != 'Documents' || 'Parcel': should be rejected")
    void cargoTypeNotDocumentsOrParcel() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("")
                .calledMethod("")

                .addProperty("", "")
                .addProperty("", "")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"15000", "15001", "14999"})
    @DisplayName("Drop-off is Warehouse-recipient AND cost >= 15000: should be rejected")
    void costEqualOrMoreThan15000() throws IOException {

        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("")
                .calledMethod("")

                .addProperty("", "")
                .addProperty("", "")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }

    @Test
    @DisplayName("Drop-off is Warehouse-recipient AND payer is Recipient AND paymentForm = Cash: should be rejected")
    void payerIsRecipientCash() throws IOException {

        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("")
                .calledMethod("")

                .addProperty("", "")
                .addProperty("", "")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"IsTakeAttorney", "AfterpaymentOnGoodsCost", "ForwardingCount", "MarketPlaceSecurePayment", "DeliveryByHand", "FillingWarranty"})
    @DisplayName("Drop-off is Warehouse-recipient AND cost >= 15000: should be rejected")
    void presentOneOf() throws IOException {

        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("")
                .calledMethod("")

                .addProperty("", "")
                .addProperty("", "")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }

}
