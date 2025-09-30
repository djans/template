package com.cogitosum.cmod.modules;

import java.util.ArrayList;
import java.util.List;

public class Module1 {
    String name;
    ArrayList<String> cities;
    public Module1() {
    }
    public Module1(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities() {
        cities = new ArrayList<>();
        cities.add("Bruxelles");
        cities.add("Montréal");
        cities.add("Québec");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
