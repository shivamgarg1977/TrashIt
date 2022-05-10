package com.shivamgarg.trashit.helperClasses.userAdapter;

import java.util.ArrayList;
import java.util.List;
public class ProgressDataClass {
    private String date;
    private String weight;

    public ProgressDataClass(String date, String weight) {
        this.date=date;
        this.weight=weight;
    }


    public String getDate() {
        return date;
    }

    public String getWeight() {
        return weight;
    }

}


