package Nikolai023.stationList.client;

import Nikolai023.stationList.client.datatypes.Dictionary;
import Nikolai023.stationList.client.filter.Filter;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("StationListService")
public interface StationListService extends RemoteService {
    Dictionary retrieveData(Filter filter);
}
