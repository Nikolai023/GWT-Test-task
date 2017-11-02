package Nikolai023.stationList.server;

import Nikolai023.stationList.client.StationListService;
import Nikolai023.stationList.client.datatypes.City;
import Nikolai023.stationList.client.datatypes.Country;
import Nikolai023.stationList.client.datatypes.Dictionary;
import Nikolai023.stationList.client.datatypes.Station;
import Nikolai023.stationList.client.filter.Filter;
import Nikolai023.stationList.server.parser.XMLParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


public class StationListServiceImpl extends RemoteServiceServlet implements StationListService {
    private static Dictionary dictionary;

    public Dictionary retrieveData(Filter filter) {
        try {
            if (dictionary == null) {
                dictionary = new XMLParser().parseXML(new FileInputStream(getServletContext().getRealPath("/WEB-INF/StationsDictionary.xml")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dictionary dataDictionary = new Dictionary(dictionary);

        String requiredCountryName = filter.getCountryName();
        String requiredCityName = filter.getCityName();
        String requiredServiceType = filter.getServiceName();

        if (!requiredServiceType.equals("")) {
            Iterator<Country> countryIterator = dataDictionary.getCountries().iterator();
            while (countryIterator.hasNext()) {
                Country currentCountry = countryIterator.next();
                boolean isValidCountry = isValidCountry(currentCountry, requiredCountryName, requiredCityName, requiredServiceType);
                if (!isValidCountry) {
                    countryIterator.remove();
                }
            }
        }
        return dataDictionary;
    }

    private boolean isValidStation(Station station, String requiredServiceType) {
        for (String s : station.getServices()) {
            if (s.equals(requiredServiceType)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidCity(City city, String requiredCityName, String requiredServiceType) {
        if (!requiredCityName.equals("") && !city.getName().equals(requiredCityName)) {
            return false;
        }

        boolean isValidCity = false;
        Iterator<Station> stationIterator = city.getStations().iterator();
        while (stationIterator.hasNext()) {
            Station currentStation = stationIterator.next();
            boolean isValidStation = isValidStation(currentStation, requiredServiceType);
            if (isValidStation) {
                isValidCity = true;
            } else {
                stationIterator.remove();
            }
        }
        return isValidCity;
    }

    private boolean isValidCountry(Country country, String requiredCountryName, String requiredCityName, String requiredServiceType) {
        if (!"".equals(requiredCountryName) && !requiredCountryName.equals(country.getName())) {
            return false;
        }

        boolean isValidCountry = false;
        Iterator<City> cityIterator = country.getCities().iterator();
        while (cityIterator.hasNext()) {
            City currentCity = cityIterator.next();
            boolean isValidCity = isValidCity(currentCity, requiredCityName, requiredServiceType);
            if (isValidCity) {
                isValidCountry = true;
            } else {
                cityIterator.remove();
            }
        }
        return isValidCountry;
    }
}