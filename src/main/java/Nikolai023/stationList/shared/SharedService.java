package Nikolai023.stationList.shared;

import java.io.Serializable;

public class SharedService implements Serializable{
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
