package com.example.yoga_app;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class YogaCategories {

    private int idCategory;
    private String nameCategory;
    private String descriptionCategory;
    private ArrayList<Integer> posesID;
    private String levelCategory;

    public YogaCategories(int idCategory, String nameCategory, String descriptionCategory, ArrayList<Integer> posesID, String levelCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.descriptionCategory = descriptionCategory;
        this.posesID = posesID;
        this.levelCategory = levelCategory;
    }

    public YogaCategories(int idCategory, String nameCategory, String descriptionCategory, ArrayList<Integer> posesID) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.descriptionCategory = descriptionCategory;
        this.posesID = posesID;
    }

    public String getLevelCategory() {
        return levelCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public String getDescriptionCategory() {
        return descriptionCategory;
    }

    public ArrayList<Integer> getPosesID() {
        return posesID;
    }
}
