/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Перечень ошибок
 * Метод «getMessageCodeText», работает в модели «CommonGeneral», этот метод необходим для скачивания справочника
 * с описанием перечня ошибок.
 * Метод постоянно пополняется новым описанием на трех языках.
 * Для обновления данных, справочник необходимо загружать один раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class ErrorList extends ModelBuilder {

    public ErrorList() {
        this
                .modelName("Common")
                .calledMethod("getMessageCodeText")
        ;
    }
}
