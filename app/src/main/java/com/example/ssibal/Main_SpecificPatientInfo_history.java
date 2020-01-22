package com.example.ssibal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_SpecificPatientInfo_history extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_specificpatientinfo_history);

        Intent intent = getIntent();
        final String NOKid = intent.getStringExtra("NOKid");

        Button adding = (Button)findViewById(R.id.adding);

        adding.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent interviewIntent = new Intent(Main_SpecificPatientInfo_history.this, Main_SpecificPatientInfo_history_add_main.class);
                interviewIntent.putExtra("NOKid", NOKid);
                startActivityForResult(interviewIntent, 1);
            }
        });
        //서버로부터 리스트 받아옵시다


    }
}
