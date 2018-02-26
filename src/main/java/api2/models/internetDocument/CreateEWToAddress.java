/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.internetDocument;

import api2.ModelBuilder;

public final class CreateEWToAddress extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public CreateEWToAddress() {
        CreateEW createEW = new CreateEW();
        this.apiKey(createEW.getApiKey());
        this.modelName(createEW.getModelName());
        this.calledMethod(createEW.getCalledMethod());

        this.addProperty("NewAddress", "1");

        this.addProperty("PayerType", "Sender");
        this.addProperty("PaymentMethod", "Cash");
        this.addProperty("DateTime", getHelper().getToday());
        this.addProperty("CargoType", "Cargo");
        this.addProperty("VolumeGeneral", "0.1");
        this.addProperty("Weight", "10");
        this.addProperty("ServiceType", "WarehouseDoors");
        this.addProperty("SeatsAmount", "1");
        this.addProperty("Description", "пилососНаАдресу");
        this.addProperty("Cost", "450");

        this.addProperty("Sender", "aa036fd8-75f1-11e7-8ba8-005056881c6b");
        this.addProperty("CitySender", "db5c88d0-391c-11dd-90d9-001a92567626");
        this.addProperty("SenderAddress", "182db9cb-f77c-11e7-becf-005056881c6b");
        this.addProperty("ContactSender", "aa145ec3-75f1-11e7-8ba8-005056881c6b");
        this.addProperty("SendersPhone", "380666666666");

        this.addProperty("RecipientCityName", "київ");
        this.addProperty("RecipientArea", "");
        this.addProperty("RecipientAreaRegions", "");
        this.addProperty("RecipientAddressName", "Столичне шосе");
        this.addProperty("RecipientHouse", "20");
        this.addProperty("RecipientFlat", "37");
        this.addProperty("RecipientName", "Тест Тест Тест");
        this.addProperty("RecipientType", "PrivatePerson");
        this.addProperty("RecipientsPhone", "380999999999");
    }
}
