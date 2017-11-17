package Nikolai023.stationList.client;

import Nikolai023.stationList.client.filter.Filter;
import Nikolai023.stationList.shared.DictionaryData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("StationListService")
public interface StationListService extends RemoteService {
    DictionaryData retrieveData(Filter filter);
}
