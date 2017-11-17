package Nikolai023.stationList.server.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "dictionary")
public class Dictionary implements Serializable {
    private List<Country> countries = new ArrayList<>();

    public Dictionary() {
    }

    public Dictionary(Dictionary dictionary) {
        ArrayList<Country> clonedCountriesArrayList = new ArrayList<>();
        for (Country country : dictionary.countries) {
            Country clonedCountry = new Country();
            clonedCountry.setName(country.getName());
            ArrayList<City> clonedCities = new ArrayList<>();
            for (City city : country.getCities()) {
                City clonedCity = new City();
                clonedCity.setName(city.getName());

                ArrayList<Station> clonedStations = new ArrayList<>();
                for (Station station : city.getStations()) {
                    Station clonedStation = new Station();
                    clonedStation.setName(station.getName());
                    clonedStation.setAddress(station.getAddress());
                    clonedStation.setPhoneNumber(station.getPhoneNumber());

                    ArrayList<Service> clonedServices = new ArrayList<>(station.getServices());
                    clonedStation.setServices(clonedServices);
                    clonedStations.add(clonedStation);
                }

                clonedCity.setStations(clonedStations);
                clonedCities.add(clonedCity);
            }
            clonedCountry.setCities(clonedCities);
            clonedCountriesArrayList.add(clonedCountry);
        }
        this.countries = clonedCountriesArrayList;
    }

    public List<Country> getCountries() {
        return countries;
    }

    @XmlElementWrapper(name = "countries")
    @XmlElement(name = "country")
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dictionary dictionary = (Dictionary) o;

        return countries != null ? Arrays.deepEquals(countries.toArray(), dictionary.countries.toArray()) : dictionary.countries == null;
    }

    @Override
    public int hashCode() {
        return countries != null ? countries.hashCode() : 0;
    }
}
