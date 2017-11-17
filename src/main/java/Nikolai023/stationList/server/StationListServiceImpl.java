package Nikolai023.stationList.server;

import Nikolai023.stationList.client.StationListService;
import Nikolai023.stationList.server.entities.*;
import Nikolai023.stationList.client.filter.Filter;
import Nikolai023.stationList.server.parser.XMLParser;
import Nikolai023.stationList.shared.DictionaryData;
import Nikolai023.stationList.shared.SharedService;
import Nikolai023.stationList.shared.SharedStation;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StationListServiceImpl extends RemoteServiceServlet implements StationListService {
    private static Dictionary dictionary;

    public DictionaryData retrieveData(Filter filter) {
        try {
            if (dictionary == null) {
                dictionary = new XMLParser().parseXML(new FileInputStream(getServletContext().getRealPath("/WEB-INF/StationsDictionary.xml")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DictionaryData dictionaryData = new DictionaryData();
        List<String> serviceList = new ArrayList<>();
        List<String> countryList = new ArrayList<>();
        List<String> cityList = new ArrayList<>();
        List<SharedStation> stationList = new ArrayList<>();

        Dictionary tempDictionary = new Dictionary(dictionary);
        String requiredCountryName = filter.getCountryName();
        String requiredCityName = filter.getCityName();
        String requiredServiceType = filter.getServiceName();

        if (!requiredServiceType.equals("")) {
            Iterator<Country> countryIterator = tempDictionary.getCountries().iterator();
            while (countryIterator.hasNext()) {
                Country currentCountry = countryIterator.next();
                boolean isValidCountry = isValidCountry(currentCountry, requiredCountryName, requiredCityName, requiredServiceType);
                if (!isValidCountry) {
                    countryIterator.remove();
                }
            }
        }

        for (Country country : tempDictionary.getCountries()) {
            countryList.add(country.getName());
            for (City city : country.getCities()) {
                cityList.add(city.getName());
                for (Station station : city.getStations()) {
                    SharedStation sharedStation = new SharedStation();
                    sharedStation.setName(station.getName());
                    sharedStation.setAddress(station.getAddress());
                    sharedStation.setPhoneNumber(station.getPhoneNumber());
                    ArrayList<SharedService> stations = new ArrayList<>();
                    for (Service service : station.getServices()) {
                        SharedService sharedService = new SharedService();
                        sharedService.setName(service.getName());
                        stations.add(sharedService);
                    }
                    sharedStation.setServices(stations);
                    stationList.add(sharedStation);
                    for (Service service : station.getServices()) {
                        String serviceName = service.getName();
                        if (!serviceList.contains(serviceName)) {
                            serviceList.add(serviceName);
                        }
                    }
                }
            }
        }

        dictionaryData.setCountryList(countryList);
        dictionaryData.setCityList(cityList);
        dictionaryData.setStationList(stationList);
        dictionaryData.setServiceList(serviceList);
        return dictionaryData;
    }

    private boolean isValidStation(Station station, String requiredServiceType) {
        for (Service service : station.getServices()) {
            if (service.getName().equals(requiredServiceType)) {
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