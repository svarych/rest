/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Виды упаковки
 * Метод «getPackList», работает в модели «Common», этот метод необходим для скачивания видов упаковки груза
 * на Украинском или русском языках.
 * Для обновления данных, справочник необходимо загружать один раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class PackageTypes extends ModelBuilder {

    public PackageTypes() {
        this
                .modelName("Common")
                .calledMethod("getPackList")

                .addProperty("Length", "")
                .addProperty("Width", "")
                .addProperty("Height", "")
                .addProperty("TypeOfPacking", "")
        ;
    }

//  --------------------------------------------------------------------------------------------------------------------

    public PackageTypes length(int length) {
        this.replaceProperty("Length", length + ".0");
        return this;
    }

    public PackageTypes width(int width) {
        this.replaceProperty("Width", width + ".0");
        return this;
    }

    public PackageTypes height(int height) {
        this.replaceProperty("Height", height + ".0");
        return this;
    }

    public PackageTypes typeOfPacking(String typeOfPacking) {
        this.replaceProperty("TypeOfPacking", typeOfPacking);
        return this;
    }

}
