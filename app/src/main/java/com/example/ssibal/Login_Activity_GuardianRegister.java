package com.example.ssibal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Retrofit;


public class Login_Activity_GuardianRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_guardianregister);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText phonenumber = (EditText) findViewById(R.id.phonenumber);
        Button registerbutton = (Button) findViewById(R.id.registerButton);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = idText.getText().toString();
                String PW = passwordText.getText().toString();
                String PHONENUMBER = phonenumber.getText().toString();
                requestSignup(ID, PW, PHONENUMBER);
            }
        });
    }

    public void requestSignup(String ID, String PW, String PHONENUMBER){
        String url = "http://c7adfae8.ngrok.io/api/NOKs/register";

        JSONObject testjson = new JSONObject();
        try{
            testjson.put("id", ID);
            testjson.put("password", PW);
            testjson.put("phonenum", PHONENUMBER);
            testjson.put("patientid", "");
            String jsonString = testjson.toString();


            final RequestQueue requestQuene = Volley.newRequestQueue(Login_Activity_GuardianRegister.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>(){

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        Toast.makeText(getApplicationContext(), "계정 추가 성공", Toast.LENGTH_SHORT).show();
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