package Nikolai023.stationList.client.datatypes;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Country implements IsSerializable {
    private String name;
    private List<City> cities = new ArrayList<City>();

    public String getName() {
        return name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        return cities != null ? Arrays.deepEquals(cities.toArray(), country.cities.toArray()) : country.cities == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (cities != null ? cities.hashCode() : 0);
        return result;
    }
}
