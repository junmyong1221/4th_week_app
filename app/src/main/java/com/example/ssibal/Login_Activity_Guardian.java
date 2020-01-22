package com.example.ssibal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ssibal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Activity_Guardian extends AppCompatActivity {

    //권한 설정
    String[] permission_list = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_guardian);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        checkPermission();

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        Button loginButton = (Button)findViewById(R.id.loginButton);
        Button guardianregisterButton = (Button)findViewById(R.id.guardianregister);


        guardianregisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(Login_Activity_Guardian.this, Login_Activity_GuardianRegister.class);
                Login_Activity_Guardian.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(true){

                    String ID = idText.getText().toString();
                    String PW = passwordText.getText().toString();
                    requestSignup(ID, PW);
//                    Intent registerIntent = new Intent(Login_Activity_Guardian.this, MainActivity.class);
//                    Login_Activity_Guardian.this.startActivity(registerIntent);
                }
            }
        });
    }

    public void requestSignup(final String ID, String PW){
        String url = "http://c7adfae8.ngrok.io/api/NOKs/login";

        JSONObject testjson = new JSONObject();
        try{
            testjson.put("id", ID);
            testjson.put("password", PW);
            String jsonString = testjson.toString();

            final RequestQueue requestQuene = Volley.newRequestQueue(Login_Activity_Guardian.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>(){

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

//                        //key값에 따라 value값을 쪼개 받아옵니다.
//                        String resultId = jsonObject.getString("approve_id");
//                        String resultPassword = jsonObject.getString("approve_pw");
                        String success = jsonObject.getString("success");

                        if(success.equals("true")){
                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent registerIntent = new Intent(Login_Activity_Guardian.this, MainActivity.class);
                            registerIntent.putExtra("GuardianId", ID);
                            Login_Activity_Guardian.this.startActivity(registerIntent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "로그인 정보가 일치하지 않습니다", Toast.LENGTH_LONG).show();
                        }

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


    public void checkPermission() {
        //현재 안드로이드 버전이 6.0 미만이면 메소드 종료
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        for (String permission : permission_list) {
            //권한 허용 여부 확인
            int chk = checkCallingOrSelfPermission(permission);

            if (chk == PackageManager.PERMISSION_DENIED) {
                //권한 허용 여부를 확인하는 창을 띄운다
                requestPermissions(permission_list, 0);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            for(int i=0;i<grantResults.length;i++){
                //허용되었다면
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                }
                else{
                    Toast.makeText(getApplicationContext(), "권한 설정이 필요합니다", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
}
