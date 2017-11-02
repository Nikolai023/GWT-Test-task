package Nikolai023.stationList.client.filter;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FilterTest {
    @Test
    public void setFilter_emptyServiceName() {
        String serviceName = "";
        String countryName = "countryName";
        String cityName = "countryName";

        Filter filter = new Filter();
        filter.setFilter(serviceName, countryName, cityName);

        assertEquals(filter.getServiceName(),"");
        assertEquals(filter.getCountryName(),"");
        assertEquals(filter.getCityName(),"");
    }

    @Test
    public void setFilter_emptyCountryName() {
        String serviceName = "serviceName";
        String countryName = "";
        String cityName = "countryName";

        Filter filter = new Filter();
        filter.setFilter(serviceName, countryName, cityName);

        assertEquals(filter.getServiceName(),"serviceName");
        assertEquals(filter.getCountryName(),"");
        assertEquals(filter.getCityName(),"");
    }

    @Test
    public void setFilter_notEmptyFields() {
        String serviceName = "serviceName";
        String countryName = "countryName";
        String cityName = "cityName";

        Filter filter = new Filter();
        filter.setFilter(serviceName, countryName, cityName);

        assertEquals(filter.getServiceName(),"serviceName");
        assertEquals(filter.getCountryName(),"countryName");
        assertEquals(filter.getCityName(),"cityName");
    }


}