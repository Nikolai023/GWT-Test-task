package Nikolai023.stationList.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SharedStation implements Serializable{
    private String name;
    private String address;
    private String phoneNumber;
    private List<SharedService> services = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<SharedService> getServices() {
        return services;
    }

    public void setServices(List<SharedService> services) {
        this.services = services;
    }
}
