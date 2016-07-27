package com.rfmicron.homeworker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClassList extends Activity{
    HomeWorker ob;
    final int classListSize = 6;
    TextView class1, class2, class3, class4, class5, class6;
   String[] classList = new String[6];
    int classCount;


    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.classlist);

        class1 = (TextView) findViewById(R.id.Class);
        class2 = (TextView) findViewById(R.id.Class2);
        class3 = (TextView) findViewById(R.id.Class3);
        class4 = (TextView) findViewById(R.id.Class4);
        class5 = (TextView) findViewById(R.id.Class5);
        class6 = (TextView) findViewById(R.id.Class6);


        loadSavedPreferences();


        Button b3 = (Button) findViewById(R.id.doneButton);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent i = new Intent(ClassList.this, calendar.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void onBackButtonClick(View view){
        Intent i = new Intent(ClassList.this, SemesterSetup.class);
        startActivity(i);
        finish();
    }

    public void onAddClassClick(View view){
        System.out.println("classCount before add:" + classCount);
        Intent i = new Intent(ClassList.this, newClass.class);
        startActivity(i);
        System.out.println("classCount after add:" + classCount);
        finish();
    }

    private void loadSavedPreferences(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String className = sharedPreferences.getString("className", "");
        classCount = sharedPreferences.getInt("classCount", 0);
        String tempName;
        for(int i = 0; i <= classCount; i++){
            if(i == 1){
                tempName = sharedPreferences.getString("class1", "");
                if(tempName.equals("")){
                    class1.setText(className);
                    savePreferences("class1", className);
                }
                else{
                    class1.setText(" " + tempName);
                }
            }

            if(i == 2){
                tempName = sharedPreferences.getString("class2", "");
                if(tempName.equals("")){
                    class2.setText(" "+ className);
                    savePreferences("class2", className);
                }
                else{
                    class2.setText(" " + tempName);
                }
            }
            if(i == 3){
                tempName = sharedPreferences.getString("class3", "");
                if(tempName.equals("")){
                    class3.setText(" "+ className);
                    savePreferences("class3", className);
                }
                else{
                    class3.setText(" " + tempName);
                }
            }

            if(i == 4){
                tempName = sharedPreferences.getString("class4", "");
                if(tempName.equals("")){
                    class4.setText(" "+ className);
                    savePreferences("class4", className);
                }
                else{
                    class4.setText(" " + tempName);
                }
            }
            if(i == 5){
                tempName = sharedPreferences.getString("class5", "");
                if(tempName.equals("")){
                    class5.setText(" "+ className);
                    savePreferences("class5", className);
                }
                else{
                    class5.setText(" " + tempName);
                }
            }
            if(i == 6){
                tempName = sharedPreferences.getString("class6", "");
                if(tempName.equals("")){
                    class6.setText(" "+ className);
                    savePreferences("class6", className);
                }
                else{
                    class6.setText(" " + tempName);
                }
            }
        }
    }

    private void savePreferences(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    private void savePreferences(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}