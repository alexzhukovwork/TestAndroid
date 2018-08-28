package com.test.evaluationtestandroidjava.adapters;


/**
 * Created by Алексей on 26.08.2018.
 */

public class PropertySong {

    private String number, name, time;

    public PropertySong(String number, String name, String time) {
        this.number = number;
        this.name = name;
        this.time = time;
    }

    public String getNumber() { return number; }

    public String getName() { return name; }

    public String getTime() { return time; }

}
