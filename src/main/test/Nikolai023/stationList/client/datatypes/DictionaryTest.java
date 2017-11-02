package Nikolai023.stationList.client.datatypes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DictionaryTest {
    @Test
    public void Dictionary_cloneConstructor() {
        Dictionary dictionary1 = new Dictionary();
        ArrayList<Country> countries = new ArrayList<Country>();
        Country russia = new Country();
        russia.setName("Russia");
        ArrayList<City> russianCities = new ArrayList<City>();
        City novosibirsk = new City();
        novosibirsk.setName("Novosibirsk");
        ArrayList<Station> novosibirskStations = new ArrayList<Station>();
        Station aStation = new Station();
        aStation.setName("a");
        aStation.setAddress("a");
        aStation.setPhoneNumber("a");
        aStation.setServices(Arrays.asList("DELIVERY"));
        novosibirskStations.add(aStation);
        Station bStation = new Station();
        bStation.setName("b");
        bStation.setAddress("b");
        bStation.setPhoneNumber("b");
        bStation.setServices(Arrays.asList("SENDING"));
        novosibirskStations.add(bStation);
        Station cStation = new Station();
        cStation.setName("c");
        cStation.setAddress("c");
        cStation.setPhoneNumber("c");
        cStation.setServices(Arrays.asList("DELIVERY", "SENDING"));
        novosibirskStations.add(cStation);
        novosibirsk.setStations(novosibirskStations);
        russianCities.add(novosibirsk);
        City moscow = new City();
        moscow.setName("Moscow");
        ArrayList<Station> moscowStations = new ArrayList<Station>();
        Station dStation = new Station();
        dStation.setName("d");
        dStation.setAddress("d");
        dStation.setPhoneNumber("d");
        dStation.setServices(Arrays.asList("SENDING"));
        moscowStations.add(dStation);
        Station eStation = new Station();
        eStation.setName("e");
        eStation.setAddress("e");
        eStation.setPhoneNumber("e");
        eStation.setServices(Arrays.asList("SENDING"));
        moscowStations.add(eStation);
        moscow.setStations(moscowStations);
        russianCities.add(moscow);
        russia.setCities(russianCities);
        countries.add(russia);
        Country kazakhstan = new Country();
        kazakhstan.setName("Kazakhstan");
        ArrayList<City> kazakhstanCities = new ArrayList<City>();
        City karaganda = new City();
        karaganda.setName("Karaganda");
        ArrayList<Station> karagandaStations = new ArrayList<Station>();
        Station fStation = new Station();
        fStation.setName("f");
        fStation.setAddress("f");
        fStation.setPhoneNumber("f");
        fStation.setServices(Arrays.asList("DELIVERY"));
        karagandaStations.add(fStation);
        Station gStation = new Station();
        gStation.setName("g");
        gStation.setAddress("g");
        gStation.setPhoneNumber("g");
        gStation.setServices(new ArrayList<String>());
        karagandaStations.add(gStation);
        karaganda.setStations(karagandaStations);
        kazakhstanCities.add(karaganda);
        kazakhstan.setCities(kazakhstanCities);
        countries.add(kazakhstan);
        dictionary1.setCountries(countries);

        Dictionary clonedDictionary = new Dictionary(dictionary1);
        assertEquals(dictionary1, clonedDictionary);
    }

    @Test
    public void equals_equalCountries() {
        Dictionary dictionary1 = new Dictionary();
        dictionary1.setCountries(new ArrayList<Country>());

        Dictionary dictionary2 = new Dictionary();
        dictionary2.setCountries(new ArrayList<Country>());

        assertEquals(dictionary1, dictionary2);
    }

    @Test
    public void equals_notEqualCountries() {
        Dictionary dictionary1 = new Dictionary();
        Country dictionary1Country = new Country();
        dictionary1Country.setName("dictionary1Country");
        dictionary1.getCountries().add(dictionary1Country);

        Dictionary dictionary2 = new Dictionary();
        Country dictionary2Country = new Country();
        dictionary2Country.setName("dictionary2Country");
        dictionary1.getCountries().add(dictionary2Country);

        assertNotEquals(dictionary1, dictionary2);
    }
}