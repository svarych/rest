package api2.models.internetDocument;

import api2.ModelBuilder;

public final class GetListEW extends ModelBuilder {

    public GetListEW() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("InternetDocument")
                .calledMethod("getDocumentList")
                .addProperty("GetFullList", "1");
    }

    public GetListEW getPage(int page) {
        this.replaceProperty("GetFullList", "0");
        this.addProperty("Page", String.valueOf(page));
        return this;
    }

    public GetListEW getTodayList() {
        this.addProperty("DateTime", getHelper().getToday());
        return this;
    }

    public GetListEW getListForDate(String date) {
        this.addProperty("DateTime", date);
        return this;
    }

    public GetListEW getListForPeriod(String from, String to) {
        this.dateFrom(from).dateTo(to);
        return this;
    }

    public GetListEW getFullList() {
        this.dateFrom("01.01.1991").dateTo(getHelper().getToday());
        return this;
    }

    public GetListEW dateFrom(String date) {
        this.addProperty("DateTimeFrom", date);
        return this;
    }

    public GetListEW dateTo(String date) {
        this.addProperty("DateTimeTo", date);
        return this;
    }
}
