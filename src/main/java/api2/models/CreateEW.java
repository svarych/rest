/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models;

import api2.ModelBuilder;

import java.util.LinkedHashMap;

public final class CreateEW extends ModelBuilder {

    public CreateEW() {
        this.apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("InternetDocument")
                .calledMethod("save")
                .properties(modelProperties());
    }

    @SuppressWarnings("SpellCheckingInspection")
    private LinkedHashMap<Object, Object> modelProperties() {
        LinkedHashMap<Object, Object> properties = new LinkedHashMap<>();
        properties.put("PayerType", "Sender");
        properties.put("PaymentMethod", "Cash");
        properties.put("DateTime", getHelper().getCurrentDate());
        properties.put("CargoType", "Cargo");
        properties.put("VolumeGeneral", "0.1");
        properties.put("Weight", "10");
        properties.put("ServiceType", "WarehouseDoors");
        properties.put("SeatsAmount", "1");
        properties.put("Description", "пилосос");
        properties.put("Cost", "450");

        properties.put("Sender", "aa036fd8-75f1-11e7-8ba8-005056881c6b");
        properties.put("CitySender", "db5c88d0-391c-11dd-90d9-001a92567626");
        properties.put("SenderAddress", "182db9cb-f77c-11e7-becf-005056881c6b");
        properties.put("ContactSender", "aa145ec3-75f1-11e7-8ba8-005056881c6b");
        properties.put("SendersPhone", "380666666666");

        properties.put("Recipient", "aa188fd1-75f1-11e7-8ba8-005056881c6b");
        properties.put("CityRecipient", "db5c88de-391c-11dd-90d9-001a92567626");
        properties.put("RecipientAddress", "6a13a7f8-94db-11e5-a023-005056887b8d");
        properties.put("ContactRecipient", "fd46f2d7-00e8-11e8-8b24-005056881c6b");
        properties.put("RecipientsPhone", "380777777777");

        return properties;
    }
}
