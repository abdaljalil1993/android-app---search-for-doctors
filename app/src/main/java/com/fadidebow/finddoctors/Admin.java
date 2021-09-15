package com.fadidebow.finddoctors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

public void manageuser(View v)
{
    startActivity(new Intent(Admin.this,ManageUser.class));
}
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "please press back on screen ", Toast.LENGTH_SHORT).show();
    }
    public void managedoctor(View v)
    {
        startActivity(new Intent(Admin.this,ManageDoctor.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.admin,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int x=item.getItemId();
        if(x==R.id.logout)
        {
            startActivity(new Intent(Admin.this,Login.class));
        }



        return super.onOptionsItemSelected(item);
    }
}
