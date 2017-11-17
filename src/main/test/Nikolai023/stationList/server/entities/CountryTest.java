package Nikolai023.stationList.server.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CountryTest {

    @Test
    public void equals_equalCountries() {
        String countryName = "Name";

        Country country1 = new Country();
        country1.setName(countryName);
        country1.getCities().add(new City());

        Country country2 = new Country();
        country2.setName(countryName);
        country2.getCities().add(new City());

        assertEquals(country1, country2);
    }

    @Test
    public void equals_notEqualNames() {
        String country1Name = "Name1";
        Country country1 = new Country();
        country1.setName(country1Name);
        country1.getCities().add(new City());

        String country2Name = "Name2";
        Country country2 = new Country();
        country2.setName(country2Name);
        country2.getCities().add(new City());

        assertNotEquals(country1, country2);
    }

    @Test
    public void equals_notEqualCities() {
        String countryName = "Name";

        Country country1 = new Country();
        country1.setName(countryName);
        City country1City = new City();
        country1City.setName("country1City");
        country1.getCities().add(country1City);

        Country country2 = new Country();
        country2.setName(countryName);
        City country2City = new City();
        country2City.setName("country2City");
        country2.getCities().add(country2City);
        assertNotEquals(country1, country2);
    }

}