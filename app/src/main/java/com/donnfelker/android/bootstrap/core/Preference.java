package com.donnfelker.android.bootstrap.core;

import java.io.Serializable;

/**
 * Created by ismet on 1/14/14.
 */
public class Preference implements Serializable {
    private static final long serialVersionUID = 1L;
    public Preference(String name, int val, int maxVal) {
        super();
        this.name = name;
        this.val = val;
        this.maxVal = maxVal;
    }

    private String  name;
    private int val;
    private int maxVal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }
}
