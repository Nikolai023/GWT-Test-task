package Nikolai023.stationList.server.entities;


import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StationTest {
    @Test
    public void equals_equalStations() {
        String stationName = "name";
        String stationAddress = "address";
        String stationPhoneNumber = "phoneNumber";

        Station station1 = new Station();
        station1.setName(stationName);
        station1.setAddress(stationAddress);
        station1.setPhoneNumber(stationPhoneNumber);

        Station station2 = new Station();
        station2.setName(stationName);
        station2.setAddress(stationAddress);
        station2.setPhoneNumber(stationPhoneNumber);

        assertEquals(station1, station2);
    }

    @Test
    public void equals_notEqualFields() {
        String station1Name = "station1Name";
        String station1Address = "station1Address";
        String station1PhoneNumber = "station1PhoneNumber";

        Station station1 = new Station();
        station1.setName(station1Name);
        station1.setAddress(station1Address);
        station1.setPhoneNumber(station1PhoneNumber);

        String station2Name = "station2Name";
        String station2Address = "station2Address";
        String station2PhoneNumber = "station2PhoneNumber";

        Station station2 = new Station();
        station1.setName(station2Name);
        station1.setAddress(station2Address);
        station1.setPhoneNumber(station2PhoneNumber);

        assertNotEquals(station1, station2);
    }

    @Test
    public void equals_notEqualStations() {
        String stationName = "name";
        String stationAddress = "address";
        String stationPhoneNumber = "phoneNumber";

        Station station1 = new Station();
        station1.setName(stationName);
        station1.setAddress(stationAddress);
        station1.setPhoneNumber(stationPhoneNumber);
        Service service1 = new Service();
        service1.setName("station1Service");
        station1.setServices(Collections.singletonList(service1));

        Station station2 = new Station();
        station2.setName(stationName);
        station2.setAddress(stationAddress);
        station2.setPhoneNumber(stationPhoneNumber);
        Service service2 = new Service();
        service2.setName("station2Service");
        station2.setServices(Collections.singletonList(service2));

        assertNotEquals(station1, station2);
    }
}