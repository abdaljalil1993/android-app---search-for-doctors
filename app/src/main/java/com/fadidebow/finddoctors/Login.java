package com.fadidebow.finddoctors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    String url="https://scoutofsyria.000webhostapp.com/login.php";
    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1=findViewById(R.id.name);
        e2=findViewById(R.id.pass);

        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);

        e1.setText(username);
        e2.setText(password);

        if (username == null || password == null) {
            //Prompt for username and password
            Toast.makeText(this, "please login now", Toast.LENGTH_SHORT).show();
        }
    }

    public void createdoctor(View v)
    {
        startActivity(new Intent(Login.this,Createdoctor.class));
    }
    public void createuser(View v)
    {
        startActivity(new Intent(Login.this,Createuser.class));
    }
    public void login(View v)
    {
        final   String uname=e1.getText().toString();
        final    String pass=e2.getText().toString();
        final   global gl= (global) getApplicationContext();



        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject js=new JSONObject(s);
                    Boolean res=js.getBoolean("f");
                    int t=js.getInt("type");

                    int id1=js.getInt("id");
                    if(res==true)
                    {
                        getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                                .edit()
                                .putString(PREF_USERNAME, uname)
                                .putString(PREF_PASSWORD, pass)
                                .commit();
                        gl.setUsername(uname);
                        gl.setId(id1);
                        //  gv=(Globalvar) getApplication();
                        //  gv.setName(name);
                        //  Toast.makeText(Login.this, "Welcome..!", Toast.LENGTH_SHORT).show();
                        if(t==0)
                        {  Toast.makeText(Login.this, "اهلا بالمدير ", Toast.LENGTH_SHORT).show();

                           startActivity(new Intent(Login.this ,Admin.class));

                        }

                       else if(t==1){
                            // Toast.makeText(Login.this, "Welcome..Parents!", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Login.this ,DoctorHome.class));

                        }
                        else{
                            startActivity(new Intent(Login.this ,UserHome.class));

                        }

                    }

                    else{
                        Toast.makeText(Login.this, "اسم المستخدم او كلمة المرور خاطئة ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param=new HashMap<>();
                param.put("username",uname);
                param.put("password",pass);


                return param;
            }
        };
        RequestQueue q= Volley.newRequestQueue(this);
        q.add(request);
    }
    @Override
    public void onBackPressed() {
    //  Login.this.finish();
        Toast.makeText(this, "please press back on screen ", Toast.LENGTH_SHORT).show();
    }
}
