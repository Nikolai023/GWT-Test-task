package Nikolai023.stationList.server.parser;

import Nikolai023.stationList.server.entities.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;


public class XMLParserTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void parseXML() {
        File file;
        try {
            temporaryFolder.create();
            file = temporaryFolder.newFile();
            String testData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<dictionary>\n" +
                    "    <countries>\n" +
                    "        <country>\n" +
                    "            <name>Russia</name>\n" +
                    "            <cities>\n" +
                    "                <city>\n" +
                    "                    <name>Novosibirsk</name>\n" +
                    "                    <stations>\n" +
                    "                        <station>\n" +
                    "                            <name>a</name>\n" +
                    "                            <address>a</address>\n" +
                    "                            <phoneNumber>a</phoneNumber>\n" +
                    "                            <services>\n" +
                    "                                <service>DELIVERY</service>\n" +
                    "                            </services>\n" +
                    "                        </station>\n" +
                    "                        <station>\n" +
                    "                            <name>b</name>\n" +
                    "                            <address>b</address>\n" +
                    "                            <phoneNumber>b</phoneNumber>\n" +
                    "                            <services>\n" +
                    "                                <service>SENDING</service>\n" +
                    "                            </services>\n" +
                    "                        </station>\n" +
                    "                        <station>\n" +
                    "                            <name>c</name>\n" +
                    "                            <address>c</address>\n" +
                    "                            <phoneNumber>c</phoneNumber>\n" +
                    "                            <services>\n" +
                    "                                <service>DELIVERY</service>\n" +
                    "                                <service>SENDING</service>\n" +
                    "                            </services>\n" +
                    "                        </station>\n" +
                    "                    </stations>\n" +
                    "                </city>\n" +
                    "                <city>\n" +
                    "                    <name>Moscow</name>\n" +
                    "                    <stations>\n" +
                    "                        <station>\n" +
                    "                            <name>d</name>\n" +
                    "                            <address>d</address>\n" +
                    "                            <phoneNumber>d</phoneNumber>\n" +
                    "                            <services>\n" +
                    "                                <service>SENDING</service>\n" +
                    "                            </services>\n" +
                    "                        </station>\n" +
                    "                        <station>\n" +
                    "                            <name>e</name>\n" +
                    "                            <address>e</address>\n" +
                    "                            <phoneNumber>e</phoneNumber>\n" +
                    "                            <services>\n" +
                    "                                <service>SENDING</service>\n" +
                    "                            </services>\n" +
                    "                        </station>\n" +
                    "                    </stations>\n" +
                    "                </city>\n" +
                    "            </cities>\n" +
                    "        </country>\n" +
                    "        <country>\n" +
                    "            <name>Kazakhstan</name>\n" +
                    "            <cities>\n" +
                    "                <city>\n" +
                    "                    <name>Karaganda</name>\n" +
                    "                    <stations>\n" +
                    "                        <station>\n" +
                    "                            <name>f</name>\n" +
                    "                            <address>f</address>\n" +
                    "                            <phoneNumber>f</phoneNumber>\n" +
                    "                            <services>\n" +
                    "                                <service>DELIVERY</service>\n" +
                    "                            </services>\n" +
                    "                        </station>\n" +
                    "                        <station>\n" +
                    "                            <name>g</name>\n" +
                    "                            <address>g</address>\n" +
                    "                            <phoneNumber>g</phoneNumber>\n" +
                    "                            <services>\n" +
                    "                            </services>\n" +
                    "                        </station>\n" +
                    "                    </stations>\n" +
                    "                </city>\n" +
                    "            </cities>\n" +
                    "        </country>\n" +
                    "    </countries>\n" +
                    "</dictionary>";
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(testData.getBytes());
            }

            Dictionary expectedDictionary = new Dictionary();
            ArrayList<Country> countries = new ArrayList<>();
            Country russia = new Country();
            russia.setName("Russia");
            ArrayList<City> russianCities = new ArrayList<>();
            City novosibirsk = new City();
            novosibirsk.setName("Novosibirsk");
            ArrayList<Station> novosibirskStations = new ArrayList<>();
            Station aStation = new Station();
            aStation.setName("a");
            aStation.setAddress("a");
            aStation.setPhoneNumber("a");
            Service aService = new Service();
            aService.setName("DELIVERY");
            aStation.setServices(Collections.singletonList(aService));
            novosibirskStations.add(aStation);
            Station bStation = new Station();
            bStation.setName("b");
            bStation.setAddress("b");
            bStation.setPhoneNumber("b");
            Service bService = new Service();
            bService.setName("SENDING");
            bStation.setServices(Collections.singletonList(bService));
            novosibirskStations.add(bStation);
            Station cStation = new Station();
            cStation.setName("c");
            cStation.setAddress("c");
            cStation.setPhoneNumber("c");
            Service cService1 = new Service();
            cService1.setName("DELIVERY");
            Service cService2 = new Service();
            cService2.setName("SENDING");
            cStation.setServices(Arrays.asList(cService1, cService2));
            novosibirskStations.add(cStation);
            novosibirsk.setStations(novosibirskStations);
            russianCities.add(novosibirsk);
            City moscow = new City();
            moscow.setName("Moscow");
            ArrayList<Station> moscowStations = new ArrayList<>();
            Station dStation = new Station();
            dStation.setName("d");
            dStation.setAddress("d");
            dStation.setPhoneNumber("d");
            Service dService = new Service();
            dService.setName("SENDING");
            dStation.setServices(Collections.singletonList(dService));
            moscowStations.add(dStation);
            Station eStation = new Station();
            eStation.setName("e");
            eStation.setAddress("e");
            eStation.setPhoneNumber("e");
            Service eService = new Service();
            eService.setName("SENDING");
            eStation.setServices(Collections.singletonList(eService));
            moscowStations.add(eStation);
            moscow.setStations(moscowStations);
            russianCities.add(moscow);
            russia.setCities(russianCities);
            countries.add(russia);
            Country kazakhstan = new Country();
            kazakhstan.setName("Kazakhstan");
            ArrayList<City> kazakhstanCities = new ArrayList<>();
            City karaganda = new City();
            karaganda.setName("Karaganda");
            ArrayList<Station> karagandaStations = new ArrayList<>();
            Station fStation = new Station();
            fStation.setName("f");
            fStation.setAddress("f");
            fStation.setPhoneNumber("f");
            Service fService = new Service();
            fService.setName("DELIVERY");
            fStation.setServices(Collections.singletonList(fService));
            karagandaStations.add(fStation);
            Station gStation = new Station();
            gStation.setName("g");
            gStation.setAddress("g");
            gStation.setPhoneNumber("g");
            gStation.setServices(new ArrayList<>());
            karagandaStations.add(gStation);
            karaganda.setStations(karagandaStations);
            kazakhstanCities.add(karaganda);
            kazakhstan.setCities(kazakhstanCities);
            countries.add(kazakhstan);
            expectedDictionary.setCountries(countries);
            XMLParser parser = new XMLParser();
            Dictionary dictionary = parser.parseXML(new ByteArrayInputStream(testData.getBytes()));
            assertEquals(expectedDictionary, dictionary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}