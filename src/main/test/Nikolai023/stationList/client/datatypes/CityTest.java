package Nikolai023.stationList.client.datatypes;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CityTest {
    @Test
    public void equals_equalCities() {
        String cityName = "Name";

        City city1 = new City();
        city1.setName(cityName);
        city1.getStations().add(new Station());

        City city2 = new City();
        city2.setName(cityName);
        city2.getStations().add(new Station());
        assertEquals(city1, city2);
    }


    @Test
    public void equals_notEqualNames() {
        String city1Name = "Name1";
        City city1 = new City();
        city1.setName(city1Name);
        city1.getStations().add(new Station());

        String city2Name = "Name2";
        City city2 = new City();
        city2.setName(city2Name);
        city2.getStations().add(new Station());
        assertNotEquals(city1, city2);
    }

    @Test
    public void equals_notEqualStations() {
        String cityName = "Name";
        City city1 = new City();
        city1.setName(cityName);
        Station city1Station = new Station();
        city1Station.setName("city1station");
        city1.getStations().add(city1Station);

        City city2 = new City();
        city2.setName(cityName);
        Station city2Station = new Station();
        city2Station.setName("city2station");
        city2.getStations().add(city2Station);
        assertNotEquals(city1, city2);
    }
}