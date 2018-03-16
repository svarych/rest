/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.redirecting;

import api2.service.ModelBuilder;

import java.io.IOException;

/**
 * Проверка возможности создания заявки на переадресацию отправления
 * Метод «checkPossibilityForRedirecting», работает в модели «AdditionalServiceGeneral», этот метод позволяет проверить
 * возможность создания заявки на переадресацию отправления. Метод доступен только для клиентов отправителей.
 * В случае успеха возвращаются необходимые параметры для создания заявки.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class CheckPossibilityForRedirecting extends ModelBuilder {

    public CheckPossibilityForRedirecting() throws IOException {
        this
                .apiKey("8f7e235f0afe315e2e81e10ac9d5c914")
//                .apiKey(new Helper().getApiKey("50cdd@hell.yeah", "1234", Server.LIVE))
                .modelName("AdditionalServiceGeneral")
                .calledMethod("checkPossibilityForRedirecting")
                .addProperty("Number", "20450066644175")
        ;
    }

    public CheckPossibilityForRedirecting docNumber(String number) {
        this
                .replaceProperty("Number", number);
        return this;
    }
}
