package com.rfmicron.homeworker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class Setup extends Activity{
    HomeWorker ob;

    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.setup);

        Button b2 = (Button) findViewById(R.id.buttonReminder);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent i = new Intent(Setup.this, reminderOps.class);
                startActivity(i);
                finish();
            }
        });

        Button b3 = (Button) findViewById(R.id.buttonSemester);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent i = new Intent(Setup.this, SemesterSetup.class);
                startActivity(i);
                finish();
            }
        });

        Button b4 = (Button) findViewById(R.id.backButton);
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent i = new Intent(Setup.this, calendar.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void onResetClick(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        System.out.println("ALL SHAREDPREF SHOULD HAVE BEEN RESET");
    }


}
