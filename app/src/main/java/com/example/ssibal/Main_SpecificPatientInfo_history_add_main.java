package com.example.ssibal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main_SpecificPatientInfo_history_add_main extends AppCompatActivity {


    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    String date = "";
    String NOKid = "alalal" +
            "";
    String hospitalname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_specificpatientinfo_history_add_main);

        Button choosehospital = (Button) findViewById(R.id.selecthospital);
        choosehospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent interviewIntent = new Intent(Main_SpecificPatientInfo_history_add_main.this, Main_SpecificPatientInfo_history_add_choosehospital.class);
                startActivityForResult(interviewIntent, 3);
            }
        });
        TextView hospitalnameview = (TextView) findViewById(R.id.hospitalchosen);



        if(getIntent().hasExtra("hospitalname"))
        {
            System.out.println("weare ij12n");
            Intent intent1 = getIntent();
            hospitalname = intent1.getStringExtra("hospitalname");
            hospitalnameview.setText(hospitalname);
        }

        if(getIntent().hasExtra("NOKid"))
        {
            System.out.println("weare ijn");
            Intent intent2 = getIntent();
            NOKid = intent2.getStringExtra("NOKid");
        }




        this.InitializeView();
        this.InitializeListener();


        //추가버튼 누르면 데이터 서버에 저장되고 history 볼 수 있는 곳으로 가는 것
        Button savedata = (Button) findViewById(R.id.btn_add);
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(hospitalname);
                System.out.println(NOKid);
                Intent intent = new Intent(Main_SpecificPatientInfo_history_add_main.this, Main_SpecificPatientInfo_history.class);
                EditText sayingdoctor = (EditText) findViewById(R.id.sayingdoctor);
                //병원명 : intent.getStringExtra("hospitalname")
                //날짜 : date
                //전달내용 : sayingdoctor.getText().toString()




                String NOKcomment = sayingdoctor.getText().toString();

//                requestSignup(NOKid, NOKcomment);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(getApplicationContext(), "진료 추가 완료되었습니다", Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 1);
            }
        });


    }

    public void InitializeView() {
        textView_Date = (TextView) findViewById(R.id.hospitaldate);
    }

    public void InitializeListener() {
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                textView_Date.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                date = year+"년 "+ monthOfYear+"월 " + dayOfMonth + "일";
            }
        };
    }

    public void OnClickHandler(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2020, 0, 1);
        dialog.show();
    }

//    public void requestSignup(String NOKid, String NOKcomment){
//        String url = "http://e14ef50a.ngrok.io/api/reports/reserve/" + NOKid;
//
//        JSONObject testjson = new JSONObject();
//        try{
//            testjson.put("NOKcomment", NOKcomment);
//
//            String jsonString = testjson.toString();
//
//            final RequestQueue requestQuene = Volley.newRequestQueue(Main_SpecificPatientInfo_history_add_main.this);
//            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, testjson, new Response.Listener<JSONObject>(){
//
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
//                        //받은 json형식의 응답을 받아
//                        JSONObject jsonObject = new JSONObject(response.toString());
//                        finish();
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                }
//            });
//            requestQuene.add(jsonObjectRequest);
//
//        }catch(JSONException e){
//            e.printStackTrace();
//        }
//    }
}
