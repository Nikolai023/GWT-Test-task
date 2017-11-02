package Nikolai023.stationList.client.filter;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Filter implements IsSerializable {
    private String serviceName = "";
    private String countryName = "";
    private String cityName = "";

    public String getCountryName() {
        return countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setFilter(String serviceName, String countryName, String cityName) {
        this.serviceName = serviceName;

        if (this.serviceName.equals("")) {
            this.countryName = "";
        } else {
            this.countryName = countryName;
        }

        if (this.countryName.equals("")) {
            this.cityName = "";
        } else {
            this.cityName = cityName;
        }
    }
}
