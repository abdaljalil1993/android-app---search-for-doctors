package com.fadidebow.finddoctors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Doctordetails extends AppCompatActivity {
    TextView tid,tname,tphone,taddress,tusername,tpassword,ttype;


    String x1,x2,x3,x4,x5,x6,x7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordetails);


        tid=findViewById(R.id.tid);
        tname=findViewById(R.id.tname);
        tphone=findViewById(R.id.tphone);
        taddress=findViewById(R.id.taddress);
        tusername=findViewById(R.id.tusername);
        tpassword=findViewById(R.id.tpassword);


        Intent i=getIntent();

        x1=i.getExtras().getString("id");
        tid.setText("رقم الطبيب   : "+x1.toString());

        x2=i.getExtras().getString("name");
        tname.setText("الاسم الكامل للطبيب : "+x2);

        x3=i.getExtras().getString("phone");
        tphone.setText("رقم الهاتف  : "+x3);

        x4=i.getExtras().getString("address");
        taddress.setText("العنوان  : "+x4);


        x5=i.getExtras().getString("username");
        tusername.setText(" اسم المستخدم  : "+x5);


        x6=i.getExtras().getString("password");
        tpassword.setText("كلمة المرور  : "+x6);
    }


    public void goback(View v)
    {
        startActivity(new Intent(Doctordetails.this,UserHome.class));
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "please press back on screen ", Toast.LENGTH_SHORT).show();
    }
}
