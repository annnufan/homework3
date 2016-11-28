package ru.ifmo.droid2016.imageapp.model;

/**
 * Created by Anna Kopeliovich on 29.11.2016.
 */

public class Image {
    public boolean download;
    public String fileName;

    public Image(boolean f, String name) {
        download = f;
        fileName = name;
    }
}
