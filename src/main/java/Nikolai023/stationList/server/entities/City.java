package Nikolai023.stationList.server.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "city")
public class City implements Serializable {
    private String name;
    private List<Station> stations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "stations")
    @XmlElement(name = "station")
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        return stations != null ? Arrays.deepEquals(stations.toArray(), city.stations.toArray()) : city.stations == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (stations != null ? stations.hashCode() : 0);
        return result;
    }
}
