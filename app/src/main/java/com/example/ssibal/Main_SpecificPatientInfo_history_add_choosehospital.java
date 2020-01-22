package com.example.ssibal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Main_SpecificPatientInfo_history_add_choosehospital extends AppCompatActivity {

    EditText edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_specificpatientinfo_history_add_choosehospital);

        edit = (EditText)findViewById(R.id.edit);

        ArrayList<Info_Hospital> hospitallist = new ArrayList<Info_Hospital>();

        String[] A = {"경욱내과의원", "도영재활의학과의원", "준명정신과의원"};
        String[] B = {"010-2222-2222", "010-3333-3333", "010-4444-4444"};
        String[] C = {"서울시 도봉구", "경기도 수원시", "부산시 사하구"};


        for (int i = 0; i < A.length; i++) {
            Info_Hospital thispatient = new Info_Hospital();
            thispatient.sethospitalname(A[i]);
            thispatient.sethospitalphonenumber(B[i]);
            thispatient.sethospitaladdress(C[i]);
            hospitallist.add(thispatient);
        }

        ListView listview;
        Main_SpecificPatientInfo_history_add_choosehospital_adapter adapter;
        adapter = new Main_SpecificPatientInfo_history_add_choosehospital_adapter();
        listview = (ListView)findViewById(R.id.hospitallist);
        listview.setAdapter(adapter);

        for(int i = 0; i < hospitallist.size(); i++) {
            adapter.addItem(hospitallist.get(i).gethospitalname(),  hospitallist.get(i).gethospitalphonenumber(), hospitallist.get(i).gethospitaladdress());
        }
    }
}