package com.rfmicron.homeworker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rfmicron.homeworkerstruct.Course;

/**
 * Created by Blake on 7/1/2015.
 */
public class newClass extends Activity {
    HomeWorker ob;
    TextView editClassName;
    Course c1;
    int numba;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.newclass);

        editClassName = (TextView) findViewById(R.id.editTextClassName);
        loadSavedPreferences();

    }

    //if schedMode = "w"
    //todo: figure out how to make other box visible vs this one
    public void onCheckboxClicked() {
        String days = "";

        if (((CheckBox)findViewById(R.id.checkBox3)).isChecked()){
            days += "M";
        }
        if (((CheckBox)findViewById(R.id.checkBox4)).isChecked()) {
            days += "T";
        }
        if(((CheckBox)findViewById(R.id.checkBox5)).isChecked()){
            days+= "W";
        }
        if(((CheckBox)findViewById(R.id.checkBox6)).isChecked()){
            days+= "Tr";
        }
        if(((CheckBox)findViewById(R.id.checkBox7)).isChecked()){
            days+= "Fr";
        }
              //  c1.setMeetingDays(days);
        savePreferences("schedDays", days);
        System.out.println("DAYS: " + days);
    }

    public void onBackButtonClick(View view){
        Intent i = new Intent(newClass.this, ClassList.class);
        startActivity(i);
        finish();
    }

    public void onCheckboxClicked2() {
        String days = "";

        if (((CheckBox)findViewById(R.id.checkBox8)).isChecked()){
            days += "A";
        }
        if (((CheckBox)findViewById(R.id.checkBox9)).isChecked()) {
            days += "B";
        }
        if(((CheckBox)findViewById(R.id.checkBox10)).isChecked()){
            days+= "C";
        }
        if(((CheckBox)findViewById(R.id.checkBox11)).isChecked()){
            days+= "D";
        }
        if(((CheckBox)findViewById(R.id.checkBox12)).isChecked()){
            days+= "E";
        }
        if(((CheckBox)findViewById(R.id.checkBox13)).isChecked()) {
            days += "F";
        }
        if(((CheckBox)findViewById(R.id.checkBox14)).isChecked()) {
            days += "G";
        }

        // c1.setMeetingDays(days);
        savePreferences("schedDays", days);
        System.out.println("DAYS: " + days);
    }

    public void handleNextButton(View view){
        Intent i = new Intent(newClass.this, newClass2.class);
        String name = editClassName.getText().toString();
        savePreferences("className", name);
        System.out.println("THIS IS SAVED NAME: " + name);
        startActivity(i);
        finish();
    }

    private void loadSavedPreferences(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String schedDays = sharedPreferences.getString("schedDays", "");
        String numOf = sharedPreferences.getString("numOf", "");
        String schedMode = sharedPreferences.getString("schedMode", "w");
        String className = sharedPreferences.getString("className", "");
        LinearLayout L1 = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout L2 = (LinearLayout) findViewById(R.id.linearLayout2);
        editClassName.setText(className);


        if(schedMode.equals("b")){
            int num = Integer.parseInt(numOf);
            formatList(L2 ,num);
            L1.setVisibility(View.INVISIBLE);
            L2.setVisibility(View.VISIBLE);

        }
        if(schedMode.equals("w")){
            L1.setVisibility(View.VISIBLE);
            L2.setVisibility(View.INVISIBLE);
        }
    }

    private void formatList(LinearLayout layout, int num){
        for(int i = 0; i < num; i++){
            View v = layout.getChildAt(i);
          //  System.out.println("CHILd @ " + i +":  "+ v);
            v.setVisibility(View.VISIBLE);
        }
    }


    private void savePreferences(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("INSIDE: onPause");
        SharedPreferences settings = getSharedPreferences("mPrefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        //todo:select which one is called below
        //onCheckboxClicked();
        onCheckboxClicked2();
        editor.commit();
    }

}
