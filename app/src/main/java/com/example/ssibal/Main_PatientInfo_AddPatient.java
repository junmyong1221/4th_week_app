package com.example.ssibal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Main_PatientInfo_AddPatient extends AppCompatActivity {

    ImageView faceimageView;
    Button faceaddbutton;
    Bitmap faceimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_patientinfo_addpatient);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        radioGroup.check(R.id.checkmale);

        Intent intent = getIntent();
        final String guardianId = intent.getStringExtra("GuardianId");




        //이미지 추가 버튼 클릭하면 이미지 보여지는 코드
        faceimageView = (ImageView)findViewById(R.id.patient_face);
        faceaddbutton = (Button)findViewById(R.id.patient_face_button);
        faceaddbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 100);
            }
        });


        //추가버튼 누르면 전체 데이터가 intent로 listview될 수 있게 넘어가는 코드
        Button savedata = (Button) findViewById(R.id.btn_add);
        savedata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                EditText patientname = (EditText)findViewById(R.id.patient_name);
                EditText patientrelationship = (EditText)findViewById(R.id.patient_relationship);
                EditText patientphonenumber = (EditText)findViewById(R.id.patient_phonenumber);
                EditText patientage = (EditText)findViewById(R.id.patient_age);

                String gender;
                RadioButton maleoption = (RadioButton)findViewById(R.id.checkmale);
                RadioButton femaleoption = (RadioButton)findViewById(R.id.checkfemale);
                if(maleoption.isChecked()){
                    gender = "남성";
                }
                else{
                    gender = "여성";
                }



                String name = patientname.getText().toString();
                String image = getStringFromBitmap(faceimg);
                String NOKid = guardianId;
                String relationship = patientrelationship.getText().toString();
                String phonenum = patientphonenumber.getText().toString();
                String age = patientage.getText().toString();
                String sex = gender;
                Boolean onQueue = true;
                Boolean onTreat = false;
                requestSignup(name, image, NOKid, relationship, phonenum, age, sex, onQueue, onTreat);





                intent.putExtra("patient_name", patientname.getText().toString());
                intent.putExtra("patient_relationship", patientrelationship.getText().toString());
                intent.putExtra("patient_phonenumber", patientphonenumber.getText().toString());
                intent.putExtra("patient_age", patientage.getText().toString());
                intent.putExtra("patient_gender", gender);

//                Bitmap resized = Bitmap.createScaledBitmap(faceimg, 400, 400, true);
                intent.putExtra("patient_face", getStringFromBitmap(faceimg));
                setResult(1, intent);
                finish();
            }
        });
    }

    //이미지 선택되면 표시되는 코드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                try {
                    //선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    faceimg = BitmapFactory.decodeStream(in);
                    in.close();
                    //이미지 표시
                    faceimageView.setImageBitmap(faceimg);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

    //이미지를 JSON 형태로 바꿀 수 있도록 변환
    private String getStringFromBitmap(Bitmap bitmapPicture){
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    public void requestSignup(String name, String image, String NOKid, String relationship, String phonenum, String age, String sex, boolean onQueue, boolean onTreat){
        String url = "http://c7adfae8.ngrok.io/api/NOKs/addpatient";

        JSONObject testjson = new JSONObject();
        try{
            testjson.put("name", name);
            testjson.put("image", image);
            testjson.put("NOKid", NOKid);
            testjson.put("relationship", relationship);
            testjson.put("phonenum", phonenum);
            testjson.put("age", age);
            testjson.put("sex", sex);
            testjson.put("onQueue", onQueue);
            testjson.put("onTreat", onTreat);

            String jsonString = testjson.toString();

            final RequestQueue requestQuene = Volley.newRequestQueue(Main_PatientInfo_AddPatient.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>(){

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        Toast.makeText(getApplicationContext(), "환자 추가 성공", Toast.LENGTH_SHORT).show();
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
