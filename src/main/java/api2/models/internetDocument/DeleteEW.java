/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.internetDocument;

import api2.service.Model;
import api2.service.ModelBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DeleteEW extends ModelBuilder {

    private ArrayList refList;

    private List<String> LIST = new ArrayList<>();

    public DeleteEW() throws IOException {
        refList = getRefList();
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("InternetDocument")
                .calledMethod("delete")
                .addProperty("DocumentRefs", LIST);
    }

//======================================================================================================================

    public DeleteEW deleteEW(String... refs) {
        LIST.addAll(Arrays.asList(refs));
        return this;
    }

    public DeleteEW deleteEW(List<String> list) {
        LIST.addAll(list);
        return this;
    }

    public DeleteEW deleteAllToday() {
        for (Object ref : refList) {
            String s = ref.toString().replace("\"", "").trim();
            LIST.add(s);
        }
        return this;
    }

    private ArrayList getRefList() throws IOException {
        Model model = new GetListEW().build().run();
        return refList = new ArrayList<>(model.getResponse().findValues("Ref"));
    }
}
