package com.rfmicron.homeworker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Blake on 7/1/2015.
 */
public class newClass2 extends FragmentActivity {
    String[] classes = {};
    int cnt;

    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.newclass2);
        loadSavedPreferences();

        Button b2 = (Button) findViewById(R.id.nc2backButton);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent i = new Intent(newClass2.this, newClass.class);
                startActivity(i);
                finish();
            }
        });
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            //save the time for scheduling purposes
        }
    }

    public void handleNextButton(View view){
        Intent i = new Intent(newClass2.this, ClassList.class);
        startActivity(i);
        cnt++;
        savePreferences("classCount", cnt);
        System.out.println("CNT = " + cnt);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private void loadSavedPreferences(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String className = sharedPreferences.getString("className", "");
        TextView tv7 = (TextView) findViewById(R.id.editTextClassName2);
        int count = sharedPreferences.getInt("classCount", 0);
        cnt = count;
        System.out.println("CLASS NAME: " + className);
        tv7.setText(className);
        String schedDays = sharedPreferences.getString("schedDays", "");
        String schedMode = sharedPreferences.getString("schedMode", "");
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.clear();
        System.out.println("SCHED DAYS: " + schedDays);

        if(schedMode.equals("w")){
            if(schedDays.contains("M")){
                list.add("Monday class");
            }
            if(schedDays.contains("T")){
                list.add("Tuesday class");
            }
            if(schedDays.contains("W")){
                list.add("Wednesday class");
            }
            if(schedDays.contains("Tr")){
                list.add("Thursday class");
            }
            if(schedDays.contains("Fr")){
                list.add("Friday class");
            }
        }

        else{
            if(schedDays.contains("A")){
                list.add("A day class");
            }
            if(schedDays.contains("B")){
                list.add("B day class");
            }
            if(schedDays.contains("C")){
                list.add("C day class");
            }
            if(schedDays.contains("D")){
                list.add("D day class");
            }
            if(schedDays.contains("E")){
                list.add("E day class");
            }
            if(schedDays.contains("F")){
                list.add("F day class");
            }
            if(schedDays.contains("G")){
                list.add("G day class");
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

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
        editor.putInt(key,value);
        editor.commit();
    }

}
