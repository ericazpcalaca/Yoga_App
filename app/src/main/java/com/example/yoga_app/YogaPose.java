package com.example.yoga_app;

public class YogaPose {
    private int poseId;
    private String name;
    private String description;
    private String benefits;
    private String image;
    private String difficulty;

    public YogaPose(int poseId, String name, String description, String benefits, String image, String difficulty) {
        this.poseId = poseId;
        this.name = name;
        this.description = description;
        this.benefits = benefits;
        this.image = image;
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    //
//    protected YogaPose(Parcel in) {
//        name = in.readString();
//        description = in.readString();
//        benefits = in.readString();
//        image = in.readString();
//    }

//    public static final Creator<YogaPose> CREATOR = new Creator<YogaPose>() {
//        @Override
//        public YogaPose createFromParcel(Parcel in) {
//            return new YogaPose(in);
//        }
//
//        @Override
//        public YogaPose[] newArray(int size) {
//            return new YogaPose[size];
//        }
//    };

    public int getPoseId() {
        return poseId;
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

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(description);
//        dest.writeString(benefits);
//        dest.writeString(image);
//    }


    @Override
    public String toString() {
        return "YogaPose{" +
                "poseId=" + poseId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", benefits='" + benefits + '\'' +
                ", image='" + image + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}

