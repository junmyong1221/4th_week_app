package com.example.ssibal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //JSON파일 생성
    JSONObject obj = new JSONObject();
    JSONArray jArray = new JSONArray();


    String guardianId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_patientinfo);

        Button addpatient = (Button)findViewById(R.id.addpatient);

        Intent intent = getIntent();
        guardianId = intent.getStringExtra("GuardianId");

        addpatient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(MainActivity.this, Main_PatientInfo_AddPatient.class);
                registerIntent.putExtra("GuardianId", guardianId);
                startActivityForResult(registerIntent, 1);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 1){
            if(resultCode == 1){
                ArrayList<Info_Patient> patientlist = new ArrayList<Info_Patient>();
                try {
                    //JSONObject에 방금 입력한 값 추가
                    JSONObject sObject = new JSONObject();
                    sObject.put("patientface", intent.getStringExtra("patient_face"));
                    sObject.put("patientname", intent.getStringExtra("patient_name"));
                    sObject.put("patientrelationship", intent.getStringExtra("patient_relationship"));
                    sObject.put("patientphonenumber", intent.getStringExtra("patient_phonenumber"));
                    sObject.put("patientage", intent.getStringExtra("patient_age"));
                    sObject.put("patientgender", intent.getStringExtra("patient_gender"));
                    jArray.put(sObject);
                    obj.put("item", jArray);
                    System.out.println(obj);


                    //JSON 파싱 과정
                    JSONArray patientarray = obj.getJSONArray("item");
                    for (int i = 0; i < patientarray.length(); i++) {
                        JSONObject patientobject = patientarray.getJSONObject(i);
                    Info_Patient thispatient = new Info_Patient();
                    thispatient.setfacetostring(patientobject.getString("patientface"));
                    thispatient.setname(patientobject.getString("patientname"));
                    thispatient.setrelationship(patientobject.getString("patientrelationship"));
                    thispatient.setphonenumber(patientobject.getString("patientphonenumber"));
                    thispatient.setage(patientobject.getString("patientage"));
                    thispatient.setgender(patientobject.getString("patientgender"));
                    patientlist.add(thispatient);
                }


            }catch(JSONException e){
                e.printStackTrace();
                }

                ListView listview;
                Main_PatientInfo_adapter adapter;
                adapter = new Main_PatientInfo_adapter();
                listview = (ListView)findViewById(R.id.patientlist);
                listview.setAdapter(adapter);

                for(int i = 0; i < patientlist.size(); i++) {
                    adapter.addItem(getBitmapFromString(patientlist.get(i).getfacetostring()),  patientlist.get(i).getrelationship(), patientlist.get(i).getphonenumber(), patientlist.get(i).getname(), patientlist.get(i).getage(), patientlist.get(i).getgender(), guardianId);
                }
            }
        }
    }

    private Bitmap getBitmapFromString(String stringPicture){
        byte[] decodedString = Base64.decode(stringPicture, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
