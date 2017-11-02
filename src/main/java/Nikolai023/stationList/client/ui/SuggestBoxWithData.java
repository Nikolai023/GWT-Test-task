package Nikolai023.stationList.client.ui;

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;

import java.util.ArrayList;
import java.util.List;

public class SuggestBoxWithData extends SuggestBox {
    private MultiWordSuggestOracle oracle;
    private List<String> data;

    public SuggestBoxWithData() {
        super(new MultiWordSuggestOracle());
        this.data = new ArrayList<String>();
        this.oracle = (MultiWordSuggestOracle) this.getSuggestOracle();
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
        this.oracle.addAll(data);
        this.oracle.setDefaultSuggestionsFromText(data);
    }


    public void clearData() {
        data.clear();
        this.oracle.clear();
    }


}
