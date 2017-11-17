package Nikolai023.stationList.client;

import Nikolai023.stationList.client.filter.Filter;
import Nikolai023.stationList.shared.DictionaryData;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface StationListServiceAsync {
    void retrieveData(Filter filter, AsyncCallback<DictionaryData> async);
}
