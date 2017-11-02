package Nikolai023.stationList.client;

import Nikolai023.stationList.client.datatypes.Dictionary;
import Nikolai023.stationList.client.filter.Filter;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StationListServiceAsync {
    void retrieveData(Filter filter, AsyncCallback<Dictionary> async);

}
