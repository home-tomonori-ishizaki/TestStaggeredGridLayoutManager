package com.example.teststaggeeredgridlayout;

public class MyModel {
    public static final int LARGE = 1;
    public static final int MEDIUM = 2;
    public static final int SMALL = 3;

    public String title;
    public int type;
    public MyModel(String title, int type) {
        this.title = title;
        this.type = type;
    }
}
