package com.elliott.mikeevent;

/**
 * Created by Elliott on 2016-09-18.
 */
public class FoodInfo {

    String name;
    String number;

    public FoodInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return name;
    }
}
