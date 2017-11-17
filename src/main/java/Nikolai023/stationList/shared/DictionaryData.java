package Nikolai023.stationList.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DictionaryData implements Serializable {
    private List<String> serviceList = new ArrayList<>();
    private List<String> countryList = new ArrayList<>();
    private List<String> cityList = new ArrayList<>();
    private List<SharedStation> stationList = new ArrayList<>();

    public List<SharedStation> getStationList() {
        return stationList;
    }

    public void setStationList(List<SharedStation> stationList) {
        this.stationList = stationList;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    public List<String> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }
}
