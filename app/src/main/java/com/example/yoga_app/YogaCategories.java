package com.example.yoga_app;

public class YogaCategories {

    private int idCategory;
    private String nameCategory;
    private String descriptionCategory;
    private int[] posesID;

    public YogaCategories(int idCategory, String nameCategory, String descriptionCategory, int[] posesID) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.descriptionCategory = descriptionCategory;
        this.posesID = posesID;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescriptionCategory() {
        return descriptionCategory;
    }

    public void setDescriptionCategory(String descriptionCategory) {
        this.descriptionCategory = descriptionCategory;
    }

    public int[] getPosesID() {
        return posesID;
    }

    public void setPosesID(int[] posesID) {
        this.posesID = posesID;
    }
}
