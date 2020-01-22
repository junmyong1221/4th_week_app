package com.example.ssibal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Main_SpecificPatientInfo_Interview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_specificpatientinfo_interview);

        //넘어온 이름정보 받아오기
        Intent intent = getIntent();
        final String patientname = intent.getStringExtra("name");
        final String NOKid = intent.getStringExtra("NOKid");
        System.out.println(NOKid + "있나요?");
        TextView nameview = (TextView) findViewById(R.id.setname);
        nameview.setText(patientname+"님 문진을 시작합니다");

        //등록/수정 버튼 클릭 시
        Button saveinterview = (Button) findViewById(R.id.interview);
        saveinterview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();

                EditText patientheight = (EditText)findViewById(R.id.patient_height);
                EditText patientweight = (EditText)findViewById(R.id.patient_weight);



                RadioButton virusByes = (RadioButton)findViewById(R.id.virusByes);
                RadioButton virusBno = (RadioButton)findViewById(R.id.virusBno);
                RadioButton diseasehistory1Ayes = (RadioButton)findViewById(R.id.diseasehistory1Ayes);
                RadioButton diseasehistory1Ano = (RadioButton)findViewById(R.id.diseasehistory1Ano);
                RadioButton diseasehistory2Ayes = (RadioButton)findViewById(R.id.diseasehistory2Ayes);
                RadioButton diseasehistory2Ano = (RadioButton)findViewById(R.id.diseasehistory2Ano);
                RadioButton diseasehistory3Ayes = (RadioButton)findViewById(R.id.diseasehistory3Ayes);
                RadioButton diseasehistory3Ano = (RadioButton)findViewById(R.id.diseasehistory3Ano);
                RadioButton diseasehistory4Ayes = (RadioButton)findViewById(R.id.diseasehistory4Ayes);
                RadioButton diseasehistory4Ano = (RadioButton)findViewById(R.id.diseasehistory4Ano);
                RadioButton diseasehistory5Ayes = (RadioButton)findViewById(R.id.diseasehistory5Ayes);
                RadioButton diseasehistory5Ano = (RadioButton)findViewById(R.id.diseasehistory5Ano);
                RadioButton diseasehistory6Ayes = (RadioButton)findViewById(R.id.diseasehistory6Ayes);
                RadioButton diseasehistory6Ano = (RadioButton)findViewById(R.id.diseasehistory6Ano);
                RadioButton diseasehistory7Ayes = (RadioButton)findViewById(R.id.diseasehistory7Ayes);
                RadioButton diseasehistory7Ano = (RadioButton)findViewById(R.id.diseasehistory7Ano);
                String a = "";
                if(virusByes.isChecked()){
                    a = a + "\nB형간염바이러스 보균자임";
                }
                if(virusBno.isChecked()){
                    a = a + "\nB형간염바이러스 보균자 아님";
                }
                if(diseasehistory1Ayes.isChecked()){
                    a = a + "\n뇌졸중(중풍) 있음";
                }
                if(diseasehistory1Ano.isChecked()){
                    a = a + "\n뇌졸중(중풍) 없음";
                }
                if(diseasehistory2Ayes.isChecked()){
                    a = a + "\n심근경색/협심증 있음";
                }
                if(diseasehistory2Ano.isChecked()){
                    a = a + "\n심근경색/협심증 없음";
                }
                if(diseasehistory3Ayes.isChecked()){
                    a = a + "\n고혈압 있음";
                }
                if(diseasehistory3Ano.isChecked()){
                    a = a + "\n고혈압 없음";
                }
                if(diseasehistory4Ayes.isChecked()){
                    a = a + "\n당뇨병 있음";
                }
                if(diseasehistory4Ano.isChecked()){
                    a = a + "\n당뇨병 없음";
                }
                if(diseasehistory5Ayes.isChecked()){
                    a = a + "\n이상지질혈증 있음";
                }
                if(diseasehistory5Ano.isChecked()){
                    a = a + "\n이상지질혈증 없음";
                }
                if(diseasehistory6Ayes.isChecked()){
                    a = a + "\n폐결핵 있음";
                }
                if(diseasehistory6Ano.isChecked()){
                    a = a + "\n폐결핵 없음";
                }
                if(diseasehistory7Ayes.isChecked()){
                    a = a + "\n기타(암 포함) 있음";
                }
                if(diseasehistory7Ano.isChecked()){
                    a = a + "\n기타(암 포함) 없음";
                }



                RadioButton diseasefamily1yes = (RadioButton)findViewById(R.id.diseasefamily1yes);
                RadioButton diseasefamily1no = (RadioButton)findViewById(R.id.diseasefamily1no);
                RadioButton diseasefamily2yes = (RadioButton)findViewById(R.id.diseasefamily2yes);
                RadioButton diseasefamily2no = (RadioButton)findViewById(R.id.diseasefamily2no);
                RadioButton diseasefamily3yes = (RadioButton)findViewById(R.id.diseasefamily3yes);
                RadioButton diseasefamily3no = (RadioButton)findViewById(R.id.diseasefamily3no);
                RadioButton diseasefamily4yes = (RadioButton)findViewById(R.id.diseasefamily4yes);
                RadioButton diseasefamily4no = (RadioButton)findViewById(R.id.diseasefamily4no);
                RadioButton diseasefamily5yes = (RadioButton)findViewById(R.id.diseasefamily5yes);
                RadioButton diseasefamily5no = (RadioButton)findViewById(R.id.diseasefamily5no);
                String b = "";
                if(diseasefamily1yes.isChecked()){
                    b = b + "\n뇌졸중(중풍) 있음";
                }
                if(diseasefamily1no.isChecked()){
                    b = b + "\n뇌졸중(중풍) 없음";
                }
                if(diseasefamily2yes.isChecked()){
                    b = b + "\n심근경색/협심증 있음";
                }
                if(diseasefamily2no.isChecked()){
                    b = b + "\n심근경색/협심증 없음";
                }
                if(diseasefamily3yes.isChecked()){
                    b = b + "\n고혈압 있음";
                }
                if(diseasefamily3no.isChecked()){
                    b = b + "\n고혈압 없음";
                }
                if(diseasefamily4yes.isChecked()){
                    b = b + "\n당뇨병 있음";
                }
                if(diseasefamily4no.isChecked()){
                    b = b + "\n당뇨병 없음";
                }
                if(diseasefamily5yes.isChecked()){
                    b = b + "\n기타(암 포함) 있음";
                }
                if(diseasefamily5no.isChecked()){
                    b = b + "\n기타(암 포함) 없음";
                }

                String c = "";
                RadioButton smokeyes = (RadioButton)findViewById(R.id.smokeyes);
                RadioButton smokeno = (RadioButton)findViewById(R.id.smokeno);
                RadioButton drinkyes = (RadioButton)findViewById(R.id.drinkyes);
                RadioButton drinkno = (RadioButton)findViewById(R.id.drinkno);

                if(smokeyes.isChecked()){
                    c = c + "\n흡연경험 있음";
                }
                if(smokeno.isChecked()){
                    c = c + "\n흡연경험 없음";
                }
                if(drinkyes.isChecked()){
                    c = c + "\n음주경험 있음";
                }
                if(drinkno.isChecked()){
                    c = c + "\n음주경험 없음";
                }

                //환자이름 : patientname
                //키 : patientheight.getText().toString()
                //몸무게 : patientheight.getText().toString()
                //과거력: a
                //가족력: b
                //사회력: c
                String NOKcomment = "매드캠프 정말 좋습니다";

                requestSignup(NOKid, patientname, patientheight.getText().toString(), patientweight.getText().toString(), a, c, b, NOKcomment);

                setResult(1, intent);
                Toast.makeText(getApplicationContext(), "등록/수정 완료되었습니다", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void requestSignup(String NOKid, String name, String height, String weight, String past, String social, String family, String NOKcomment){
        String url = "http://c7adfae8.ngrok.io/api/reports/upload";


        JSONObject testjson = new JSONObject();
        try{
            testjson.put("NOKid", NOKid);
            testjson.put("name", name);
            testjson.put("height", height);
            testjson.put("weight", weight);
            testjson.put("past", past);
            testjson.put("social", social);
            testjson.put("family", family);
            testjson.put("NOKcomment", NOKcomment);

            String jsonString = testjson.toString();

            final RequestQueue requestQuene = Volley.newRequestQueue(Main_SpecificPatientInfo_Interview.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>(){

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQuene.add(jsonObjectRequest);

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
