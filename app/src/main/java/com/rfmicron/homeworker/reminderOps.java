package com.rfmicron.homeworker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class reminderOps extends Activity {

    HomeWorker ob;
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.reminderops);

        Button b5 = (Button) findViewById(R.id.backButton);
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent i = new Intent(reminderOps.this, Setup.class);
                startActivity(i);
                finish();
            }
        });

    }



    public void setOb( HomeWorker obA){
        this.ob=obA;
    }
}


