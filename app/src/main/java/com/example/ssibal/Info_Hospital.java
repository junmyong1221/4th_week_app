package com.example.ssibal;

import android.graphics.Bitmap;

public class Info_Hospital {

    private String hospitalname;
    private String hospitalphonenumber;
    private String hospitaladdress;


    public String gethospitalname() {
        return hospitalname;
    }
    public String gethospitalphonenumber(){
        return hospitalphonenumber;
    }
    public String gethospitaladdress(){
        return hospitaladdress;
    }

    public void sethospitalname(String hospitalname){
        this.hospitalname = hospitalname;
    }
    public void sethospitalphonenumber(String hospitalphonenumber){
        this.hospitalphonenumber = hospitalphonenumber;
    }
    public void sethospitaladdress(String hospitaladdress){
        this.hospitaladdress = hospitaladdress;
    }
}
