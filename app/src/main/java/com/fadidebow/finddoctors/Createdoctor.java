package com.fadidebow.finddoctors;

import android.content.Intent;
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

public class Createdoctor extends AppCompatActivity {
    EditText name,phone,address,uname,pass;
    String url="https://scoutofsyria.000webhostapp.com/signup1.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createdoctor);

        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        uname=findViewById(R.id.username);
        pass=findViewById(R.id.password);
    }

    public void signup(View v)
    {
        final   String namee= name.getText().toString();
        final String phonee= phone.getText().toString();
        final String addresss= address.getText().toString();
        final  String username=uname.getText().toString();
        final  String password=pass.getText().toString();


        if(namee.isEmpty() || phonee.isEmpty() || addresss.isEmpty() ||username.isEmpty()||password.isEmpty())
        {
            Toast.makeText(this, "all fileds are required..!!!", Toast.LENGTH_SHORT).show();
        }
        else{
            StringRequest tt=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONObject js=new JSONObject(s);
                        Boolean xr=js.getBoolean("ss");
                        if(xr==true)
                        {
                            Toast.makeText(Createdoctor.this, "create acount successfully", Toast.LENGTH_SHORT).show();
                            name.setText("");
                            phone.setText("");
                            address.setText("");
                            uname.setText("");
                            pass.setText("");

                        }

                        else{
                            Toast.makeText(Createdoctor.this, "create acount failed", Toast.LENGTH_SHORT).show();

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

                //ارسال القيم للويب سيرفس من اجل استخدامها في قاعدة البيانات
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> param=new HashMap<>();
                    param.put("name",namee);
                    param.put("phone",phonee);
                    param.put("address",addresss);
                    param.put("uname",username);
                    param.put("password",password);



                    return param;
                }

                //map data
            };
            RequestQueue r= Volley.newRequestQueue(this);
            r.add(tt);
        }
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "please press back on screen ", Toast.LENGTH_SHORT).show();
    }
    public void backtologin(View v)
    {
        startActivity(new Intent(Createdoctor.this,Login.class));
    }

}
