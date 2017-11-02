package Nikolai023.stationList.client;

import Nikolai023.stationList.client.datatypes.City;
import Nikolai023.stationList.client.datatypes.Country;
import Nikolai023.stationList.client.datatypes.Dictionary;
import Nikolai023.stationList.client.datatypes.Station;
import Nikolai023.stationList.client.filter.Filter;
import Nikolai023.stationList.client.ui.SuggestBoxWithData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class StationList implements EntryPoint {
    private StationListServiceAsync stationListServiceAsync = GWT.create(StationListService.class);

    private final FlexTable suggestBoxesTable = new FlexTable();
    private final SuggestBoxWithData serviceBox = new SuggestBoxWithData();
    private final SuggestBoxWithData countryBox = new SuggestBoxWithData();
    private final SuggestBoxWithData cityBox = new SuggestBoxWithData();
    private final FlexTable stationsTable = new FlexTable();
    private final RootPanel mainPanel = RootPanel.get("mainPanel");

    private final Filter filter = new Filter();
    private final AsyncCallback<Dictionary> callback = new AsyncCallback<Dictionary>() {
        public void onFailure(Throwable caught) {
            caught.printStackTrace();
        }

        public void onSuccess(Dictionary result) {
            clearBoxesAndTable();
            initTableHeader();
            refreshBoxesAndTable(result);
            setUpBoxesAvailability();
        }
    };
    private final KeyUpHandler keyUpHandler = new KeyUpHandler() {
        public void onKeyUp(KeyUpEvent event) {
            setUpFilter();
            stationListServiceAsync.retrieveData(filter, callback);
        }
    };
    private final SelectionHandler<SuggestOracle.Suggestion> selectionHandler = new SelectionHandler<SuggestOracle.Suggestion>() {
        public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {
            setUpFilter();
            stationListServiceAsync.retrieveData(filter, callback);
        }
    };

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        initSuggestBox(serviceBox);
        initSuggestBox(countryBox);
        initSuggestBox(cityBox);

        stationsTable.addStyleName("stationsTable");
        suggestBoxesTable.setText(0, 0, "Введите название услуги:");
        suggestBoxesTable.setText(0, 1, "Введите страну:");
        suggestBoxesTable.setText(0, 2, "Введите город:");
        suggestBoxesTable.setWidget(1, 0, serviceBox);
        suggestBoxesTable.setWidget(1, 1, countryBox);
        suggestBoxesTable.setWidget(1, 2, cityBox);

        mainPanel.add(suggestBoxesTable);
        mainPanel.add(stationsTable);

        stationListServiceAsync.retrieveData(filter, callback);
    }

    private void refreshBoxesAndTable(Dictionary result) {
        ArrayList<String> countrySuggestions = new ArrayList<String>();
        ArrayList<String> citySuggestions = new ArrayList<String>();
        ArrayList<String> serviceSuggestions = new ArrayList<String>();
        for (Country country : result.getCountries()) {
            countrySuggestions.add(country.getName());
            for (City city : country.getCities()) {
                citySuggestions.add(city.getName());
                for (Station station : city.getStations()) {
                    StringBuilder tableServices = new StringBuilder();
                    for (String service : station.getServices()) {
                        if (!serviceSuggestions.contains(service)) {
                            serviceSuggestions.add(service);
                        }
                        tableServices.append(service).append(", ");
                    }
                    putStationOnTable(station.getName(), station.getAddress(), station.getPhoneNumber(),
                            tableServices.toString().substring(0, tableServices.length() - 2));
                }
            }
        }
        serviceBox.setData(serviceSuggestions);
        countryBox.setData(countrySuggestions);
        cityBox.setData(citySuggestions);
    }

    private void setUpBoxesAvailability() {
        boolean countryBoxIsAvailable = !filter.getServiceName().equals("");
        boolean cityBoxIsAvailable = countryBoxIsAvailable && !filter.getCountryName().equals("");
        boolean stationTableIsVisible = cityBoxIsAvailable && !filter.getCityName().equals("");

        if (countryBoxIsAvailable) {
            countryBox.setEnabled(true);
        } else {
            countryBox.setEnabled(false);
            countryBox.setValue("");
        }

        if (cityBoxIsAvailable) {
            cityBox.setEnabled(true);
        } else {
            cityBox.setEnabled(false);
            cityBox.setValue("");
        }

        stationsTable.setVisible(stationTableIsVisible);
    }

    private void clearBoxesAndTable() {
        countryBox.clearData();
        cityBox.clearData();
        serviceBox.clearData();
        stationsTable.removeAllRows();
    }

    private void initSuggestBox(final SuggestBox suggestBox) {
        suggestBox.getValueBox().addFocusHandler(new FocusHandler() {
            public void onFocus(FocusEvent event) {
                suggestBox.showSuggestionList();
            }
        });
        suggestBox.setLimit(5);
        suggestBox.addSelectionHandler(selectionHandler);
        suggestBox.addKeyUpHandler(keyUpHandler);
    }

    private void setUpFilter() {
        String serviceName = "";
        String countryName = "";
        String cityName = "";

        String serviceBoxValue = serviceBox.getValue();
        if (serviceBox.getData().contains(serviceBoxValue)) {
            serviceName = serviceBoxValue;
            String countryBoxValue = countryBox.getValue();
            if (countryBox.getData().contains(countryBoxValue)) {
                countryName = countryBoxValue;
                String cityBoxValue = cityBox.getValue();
                if (cityBox.getData().contains(cityBoxValue)) {
                    cityName = cityBoxValue;
                }
            }
        }

        filter.setFilter(serviceName, countryName, cityName);
    }

    private void initTableHeader() {
        stationsTable.setText(0, 0, "Название станции");
        stationsTable.setText(0, 1, "Адрес");
        stationsTable.setText(0, 2, "Телефон");
        stationsTable.setText(0, 3, "Услуги");
        stationsTable.getRowFormatter().addStyleName(0, "stationsTableHeader");
    }

    private void putStationOnTable(String stationName, String address, String phoneNumber, String services) {
        int lastRow = stationsTable.getRowCount();
        stationsTable.setText(lastRow, 0, stationName);
        stationsTable.setText(lastRow, 1, address);
        stationsTable.setText(lastRow, 2, phoneNumber);
        stationsTable.setText(lastRow, 3, services);
    }
}
