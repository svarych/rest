/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

/**
 * Справочник географических областей Украины
 * Метод «getAreas», работает в модели «Address», этот метод необходим для скачивания справочника географических областей Украины, компании «Новая Почта».
 * Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class GetGeographicalRegions extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public GetGeographicalRegions() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Address")
                .calledMethod("getAreas")
        ;
    }
}
