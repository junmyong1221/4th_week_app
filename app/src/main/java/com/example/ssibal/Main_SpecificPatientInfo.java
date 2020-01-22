package com.example.ssibal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//상세정보 눌렀을때 나오는 화면
public class Main_SpecificPatientInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_specificpatientinfo);

        ImageView faceview = (ImageView) findViewById(R.id.patient_face);
        TextView nameview = (TextView) findViewById(R.id.patient_name);
        TextView relationshipview = (TextView) findViewById(R.id.patient_relationship);
        TextView phonenumberview = (TextView) findViewById(R.id.patient_phonenumber);
        TextView ageview = (TextView) findViewById(R.id.patient_age);
        TextView genderview = (TextView) findViewById(R.id.patient_gender);

        Intent intent = getIntent();
        final String Name = intent.getStringExtra("name");
        final String NOKid = intent.getStringExtra("NOKid");
        faceview.setImageBitmap((Bitmap)intent.getParcelableExtra("face"));
        nameview.setText(Name);
        relationshipview.setText(intent.getStringExtra("relationship"));
        phonenumberview.setText(intent.getStringExtra("phonenumber"));
        ageview.setText(intent.getStringExtra("age"));
        genderview.setText(intent.getStringExtra("gender"));


        Button interview = (Button)findViewById(R.id.interview);

        interview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent interviewIntent = new Intent(Main_SpecificPatientInfo.this, Main_SpecificPatientInfo_Interview.class);
                interviewIntent.putExtra("name", Name);
                interviewIntent.putExtra("NOKid", NOKid);
                startActivityForResult(interviewIntent, 1);
            }
        });

        Button history = (Button)findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent historyIntent = new Intent(Main_SpecificPatientInfo.this, Main_SpecificPatientInfo_history.class);
                historyIntent.putExtra("name", Name);
                historyIntent.putExtra("NOKid", NOKid);
                startActivityForResult(historyIntent, 1);
            }
        });

    }

}
