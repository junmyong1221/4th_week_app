package com.example.ssibal;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Info_Patient {
    private Bitmap iconDrawable;
    private String facetostring;
    private String name;
    private String relationship;
    private String phonenumber;
    private String age;
    private String gender;
    private String NOKid;


    public Bitmap getIcon(){
        return iconDrawable;
    }
    public String getfacetostring() {
        return facetostring;
    }
    public String getname(){
        return name;
    }
    public String getrelationship(){
        return relationship;
    }
    public String getphonenumber(){
        return phonenumber;
    }
    public String getage(){
        return age;
    }
    public String getgender(){
        return gender;
    }
    public String getNOKid(){
        return NOKid;
    }


    public void setIcon(Bitmap icon){
        this.iconDrawable = icon;
    };
    public void setfacetostring(String facetostring){
        this.facetostring = facetostring;
    }
    public void setname(String name){
        this.name = name;
    }
    public void setrelationship(String relationship){
        this.relationship = relationship;
    }
    public void setphonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }
    public void setage(String age){
        this.age = age;
    }
    public void setgender(String gender){
        this.gender = gender;
    }
    public void setNOKid(String NOKid){
        this.NOKid = NOKid;
    }
}
