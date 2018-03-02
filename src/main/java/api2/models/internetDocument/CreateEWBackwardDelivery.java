/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.internetDocument;

import api2.service.ModelBuilder;

import java.util.LinkedHashMap;

public final class CreateEWBackwardDelivery extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public CreateEWBackwardDelivery() {
        CreateEW createEW = new CreateEW();
        apiKey(createEW.getApiKey());
        modelName(createEW.getModelName());
        calledMethod(createEW.getCalledMethod());
        propertiesMap(createEW.getMethodProperties());

        addProperty("BackwardDeliveryData", backwardDeliveryData());
    }

    private LinkedHashMap<String, Object> backwardDeliveryData() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("PayerType", "Sender");
        map.put("CargoType", "Documents");
        map.put("Services", servicesData());
        return map;
    }

    private LinkedHashMap<String, Object> servicesData() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("Attorney", true);
        map.put("WaybillNewPostWithStamp", true);
        map.put("UserActions", "UserCallSender");
        return map;
    }
}
