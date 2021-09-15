package com.fadidebow.finddoctors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView i1=findViewById(R.id.imageView);
        // i1.setBackgroundResource(R.drawable.ic);
        Thread t=new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    sleep(3000);
                    startActivity(new Intent(MainActivity.this,Login.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
