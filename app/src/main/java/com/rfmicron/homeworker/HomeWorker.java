package com.rfmicron.homeworker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class HomeWorker extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar obB=new calendar();
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // here i call new screen;
                Intent i = new Intent(HomeWorker.this, calendar.class);
                startActivity(i);
                finish();
            }
        });
    }
}
