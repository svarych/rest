/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.internetDocument;

import api2.ModelBuilder;

public final class CreateEWToAddress extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public CreateEWToAddress() {
        CreateEW createEW = new CreateEW();
        apiKey(createEW.getApiKey());
        modelName(createEW.getModelName());
        calledMethod(createEW.getCalledMethod());

        addProperty("NewAddress", "1");

        addProperty("PayerType", "Sender");
        addProperty("PaymentMethod", "Cash");
        addProperty("DateTime", getHelper().getToday());
        addProperty("CargoType", "Cargo");
        addProperty("VolumeGeneral", "0.1");
        addProperty("Weight", "10");
        addProperty("ServiceType", "WarehouseDoors");
        addProperty("SeatsAmount", "1");
        addProperty("Description", "пилососНаАдресу");
        addProperty("Cost", "450");

        addProperty("Sender", "aa036fd8-75f1-11e7-8ba8-005056881c6b");
        addProperty("CitySender", "db5c88d0-391c-11dd-90d9-001a92567626");
        addProperty("SenderAddress", "182db9cb-f77c-11e7-becf-005056881c6b");
        addProperty("ContactSender", "aa145ec3-75f1-11e7-8ba8-005056881c6b");
        addProperty("SendersPhone", "380666666666");

        addProperty("RecipientCityName", "київ");
        addProperty("RecipientArea", "");
        addProperty("RecipientAreaRegions", "");
        addProperty("RecipientAddressName", "Столичне шосе");
        addProperty("RecipientHouse", "20");
        addProperty("RecipientFlat", "37");
        addProperty("RecipientName", "Тест Тест Тест");
        addProperty("RecipientType", "PrivatePerson");
        addProperty("RecipientsPhone", "380999999999");
    }
}
