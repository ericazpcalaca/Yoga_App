package com.example.yoga_app;

public class YogaPose {

    private String name;
    private String description;
    private String benefits;
    private String image;

    public YogaPose(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public YogaPose(String name, String description, String benefits, String image) {
        this.name = name;
        this.description = description;
        this.benefits = benefits;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getImage() {
        return image;
    }
}
