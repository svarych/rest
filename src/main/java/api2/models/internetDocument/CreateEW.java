/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.internetDocument;

import api2.service.ModelBuilder;

public final class CreateEW extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public CreateEW() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Recipient")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", getHelper().getToday())
                .addProperty("CargoType", "Parcel")
                .addProperty("VolumeGeneral", "0.01")
                .addProperty("Weight", "10")
                .addProperty("ServiceType", "WarehouseDoors")
                .addProperty("SeatsAmount", "1")
                .addProperty("Description", "Бджоли")
                .addProperty("Cost", "124")

                .addProperty("Sender", "aa036fd8-75f1-11e7-8ba8-005056881c6b")
                .addProperty("CitySender", "db5c88d0-391c-11dd-90d9-001a92567626")
                .addProperty("SenderAddress", "182db9cb-f77c-11e7-becf-005056881c6b")
                .addProperty("ContactSender", "aa145ec3-75f1-11e7-8ba8-005056881c6b")
                .addProperty("SendersPhone", "380666666666")

                .addProperty("Recipient", "aa188fd1-75f1-11e7-8ba8-005056881c6b")
                .addProperty("CityRecipient", "db5c88de-391c-11dd-90d9-001a92567626")
                .addProperty("RecipientAddress", "6a13a7f8-94db-11e5-a023-005056887b8d")
                .addProperty("ContactRecipient", "fd46f2d7-00e8-11e8-8b24-005056881c6b")
                .addProperty("RecipientsPhone", "380777777777");
    }

//    ------------------------------------------------------------------------------------------------------------------

    public CreateEW sender(String senderRef) {
        this.replaceProperty("Sender", senderRef);
        return this;
    }

    public CreateEW citySender(String cityRef) {
        this.replaceProperty("CitySender", cityRef);
        return this;
    }

    public CreateEW addressSender(String senderAddressRef) {
        this.replaceProperty("SenderAddress", senderAddressRef);
        return this;
    }

    public CreateEW contactSender(String contactSenderRef) {
        this.replaceProperty("ContactSender", contactSenderRef);
        return this;
    }

    public CreateEW phoneSender(String phoneSender) {
        this.replaceProperty("SendersPhone", phoneSender);
        return this;
    }

//    ------------------------------------------------------------------------------------------------------------------

    public CreateEW recipient(String recipientRef) {
        this.replaceProperty("Recipient", recipientRef);
        return this;
    }

    public CreateEW cityRecipient(String cityRecipientRef) {
        this.replaceProperty("CityRecipient", cityRecipientRef);
        return this;
    }

    public CreateEW addressRecipient(String addressRecipientRef) {
        this.replaceProperty("RecipientAddress", addressRecipientRef);
        return this;
    }

    public CreateEW contactRecipient(String contactRecipientRef) {
        this.replaceProperty("ContactRecipient", contactRecipientRef);
        return this;
    }

    public CreateEW phoneRecipient(String phoneRecipient) {
        this.replaceProperty("RecipientsPhone", phoneRecipient);
        return this;
    }
}
