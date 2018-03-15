/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.registry;

import api2.service.ModelBuilder;
import api2.service.enums.UserType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Загрузить список реестров
 * Работа с реестрами приема-передачи отправлений
 * Для передачи оформленных отправлений по Реестру, интегрируется функционал формирования и удаления реестров приема-передачи отправлений.
 * <p>
 * При передаче отправлений по Реестру необходимо на каждом отправлении размещать маркировку и распечатать два экземпляра Реестра.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class GetRegistryList extends ModelBuilder {

    private Properties properties;

    public GetRegistryList() throws IOException {
        properties = new Properties();
        InputStream configFile = new FileInputStream("./src/main/resources/properties/keys.properties");
        properties.load(configFile);

        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("ScanSheet")
                .calledMethod("getScanSheetList")
        ;
    }

//----------------------------------------------------------------------------------------------------------------------

    public GetRegistryList user(UserType userType) {
        if (userType == UserType.CORPORATE_LIVE) {
            this.apiKey(properties.getProperty("corporate.live"));
        }
        if (userType == UserType.CORPORATE_TEST) {
            this.apiKey(properties.getProperty("corporate.test"));
        }
        if (userType == UserType.LOYALTY_LIVE) {
            this.apiKey(properties.getProperty("loyalty.live"));
        }
        if (userType == UserType.LOYALTY_TEST) {
            this.apiKey(properties.getProperty("loyalty.test"));
        }
        return this;
    }

}
