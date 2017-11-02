package Nikolai023.stationList.server.parser;

import Nikolai023.stationList.client.datatypes.City;
import Nikolai023.stationList.client.datatypes.Country;
import Nikolai023.stationList.client.datatypes.Dictionary;
import Nikolai023.stationList.client.datatypes.Station;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class XMLParser {
    public Dictionary parseXML(InputStream inputStream) {
        Dictionary dictionary = new Dictionary();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element dictionaryNode = (Element) document.getFirstChild();
            if (dictionaryNode != null) {
                dictionary = parseDictionary(dictionaryNode);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    private Dictionary parseDictionary(Element dictionaryNode) {
        Dictionary dictionary = new Dictionary();
        ArrayList<Country> countries = new ArrayList<Country>();
        NodeList countryNodes = dictionaryNode.getElementsByTagName("country");
        if (countryNodes != null) {
            for (int i = 0; i < countryNodes.getLength(); i++) {
                Element countryNode = (Element) countryNodes.item(i);
                countries.add(parseCountry(countryNode));
            }
        }
        dictionary.setCountries(countries);
        return dictionary;
    }

    private Country parseCountry(Element countryNode) {
        Country country = new Country();
        ArrayList<City> cities = new ArrayList<City>();
        NodeList childNodes = countryNode.getChildNodes();
        if (null != childNodes) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node currentNode = childNodes.item(i);
                if ("name".equals(currentNode.getNodeName())) {
                    String name = currentNode.getTextContent();
                    country.setName(name);
                }
                if ("cities".equals(currentNode.getNodeName())) {
                    NodeList cityNodes = ((Element) currentNode).getElementsByTagName("city");
                    if (cityNodes != null) {
                        for (int j = 0; j < cityNodes.getLength(); j++) {
                            Element cityNode = (Element) cityNodes.item(j);
                            cities.add(parseCity(cityNode));
                        }
                    }
                }
            }
        }
        country.setCities(cities);
        return country;
    }

    private City parseCity(Element cityNode) {
        City city = new City();
        ArrayList<Station> stations = new ArrayList<Station>();
        NodeList childNodes = cityNode.getChildNodes();
        if (null != childNodes) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node currentNode = childNodes.item(i);
                if ("name".equals(currentNode.getNodeName())) {
                    String name = currentNode.getTextContent();
                    city.setName(currentNode.getTextContent());
                }
                if ("stations".equals(currentNode.getNodeName())) {
                    NodeList stationNodes = ((Element) currentNode).getElementsByTagName("station");
                    if (stationNodes != null) {
                        for (int j = 0; j < stationNodes.getLength(); j++) {
                            Element stationNode = (Element) stationNodes.item(j);
                            stations.add(parseStation(stationNode));
                        }
                    }
                }
            }
        }
        city.setStations(stations);
        return city;
    }

    private Station parseStation(Element stationNode) {
        Station station = new Station();
        ArrayList<String> services = new ArrayList<String>();
        NodeList childNodes = stationNode.getChildNodes();
        if (null != childNodes) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node currentNode = childNodes.item(i);
                if ("name".equals(currentNode.getNodeName())) {
                    String name = currentNode.getTextContent();
                    station.setName(currentNode.getTextContent());
                }
                if ("address".equals(currentNode.getNodeName())) {
                    String address = currentNode.getTextContent();
                    station.setAddress(currentNode.getTextContent());
                }
                if ("phoneNumber".equals(currentNode.getNodeName())) {
                    String phoneNumber = currentNode.getTextContent();
                    station.setPhoneNumber(currentNode.getTextContent());
                }
                if ("services".equals(currentNode.getNodeName())) {
                    NodeList serviceNodes = ((Element) currentNode).getElementsByTagName("service");
                    if (serviceNodes != null) {
                        for (int j = 0; j < serviceNodes.getLength(); j++) {
                            Element serviceNode = (Element) serviceNodes.item(j);
                            services.add(parseService(serviceNode));
                        }
                    }
                }
            }
        }
        station.setServices(services);
        return station;
    }

    private String parseService(Element serviceNode) {
        return serviceNode.getTextContent();
    }
}
