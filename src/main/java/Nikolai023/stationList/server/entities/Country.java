package Nikolai023.stationList.server.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "country")
public class Country implements Serializable {
    private String name;
    private List<City> cities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<City> getCities() {
        return cities;
    }

    @XmlElement(name =  "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "cities")
    @XmlElement(name = "city")
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
