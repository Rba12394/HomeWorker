package com.rfmicron.homeworker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;
import android.widget.DatePicker;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rfmicron.homeworkerstruct.Semester;

import java.util.Calendar;
import java.util.Date;

public class SemesterSetup extends Activity {
    HomeWorker ob;
    Button change_date_but, change_date_but2;
    RadioButton radioButton1, radioButton2;
    TextView display_txt, display_txt2, editText3;
    public static final int Date_dialog_id = 1;
    private int mYear, mYear2;
    private int mMonth, mMonth2;
    private int mDay, mDay2;
    private SharedPreferences mPrefs;
    private RadioGroup radioGroup;
    Semester s1;
    Date d1, d2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semester_setup);

        change_date_but = (Button) findViewById(R.id.change_button_id);
        change_date_but2 = (Button) findViewById(R.id.change_button2_id);
        display_txt = (TextView) findViewById(R.id.display_id);
        display_txt2 = (TextView) findViewById(R.id.display2_id);
        final Calendar c = Calendar.getInstance();
        final Calendar c2 = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mYear2 = c2.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mMonth2 = c2.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mDay2 = c2.get(Calendar.DAY_OF_MONTH);
        editText3 = (TextView) findViewById(R.id.editText3);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        s1 = new Semester();

        loadSavedPreferences();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        Button b2 = (Button) findViewById(R.id.backButton);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent i = new Intent(SemesterSetup.this, Setup.class);
                startActivity(i);
                finish();
            }
        });

        /*/ time picker
        JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date()); // will only show the current time
        /*/

        d1 = new Date(mYear, mMonth, mDay);
        d2 = new Date(mYear2, mMonth2, mDay2);
        s1.setStartDate(d1);
        s1.setEndDate(d2);
    }

    public void onRadioButtonClicked(View view) {
        //
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked){
                    System.out.println("week mode");
                    s1.setScheduleMode("w");
                    s1.setBlockdays(false);
                    s1.setBlockNum(0);
                    savePreferences("schedMode", "w");
                }
                    break;
            case R.id.radioButton2:
                if (checked){
                    System.out.println("block mode");
                    s1.setScheduleMode("b");
                    int numba = Integer.parseInt(editText3.getText().toString());
                    s1.setBlockdays(false);
                    s1.setBlockNum(numba);
                    savePreferences("numOf", numba);
                    savePreferences("schedMode", "b");
                }
                    break;
        }
    }

    public void onChangeDateClick(View view) {
        DatePickerDialog DPD = new DatePickerDialog(SemesterSetup.this, mDateSetListener, mYear, mMonth, mDay);
        DPD.show();
        String startDate = mMonth + "-" + mDay + "-" + mYear;
        savePreferences("startDate", startDate);
    }


    //todo: change format of date string
    public void onChangeDate2Click(View view){
        DatePickerDialog DPD2 = new DatePickerDialog(SemesterSetup.this, mDateSetListener2, mYear2, mMonth2, mDay2);
        DPD2.show();
        String endDate = mMonth2 + "-" + mDay2 + "-" + mYear2;
        savePreferences("endDate", endDate);

    }


    private void loadSavedPreferences(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String startDate = sharedPreferences.getString("startDate", "");
        String endDate = sharedPreferences.getString("endDate", "");
        //todo: figure out where this is being saved as a string
        String numOf = sharedPreferences.getString("numOf", "");
        System.out.println("NUM OF"+ String.valueOf(numOf +2));
        String schedMode = sharedPreferences.getString("schedMode", "w");
        if(schedMode.equals("w")){
            radioButton1.setChecked(true);
        }
        else{
            radioButton2.setChecked(true);
        }

        editText3.setText(numOf);
        display_txt.setText(startDate);
        display_txt2.setText(endDate);
    }

    private void savePreferences(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    private void savePreferences(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //todo: figure out how to store Semester/class/Assignment object and access them later

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear2 = year;
            mMonth2 = monthOfYear;
            mDay2 = dayOfMonth;
            updateDisplay2();
        }
    };


    private void updateDisplay() {
        // TODO Auto-generated method stub
        display_txt.setText(new StringBuilder()
                .append(mMonth + 1).append("-").append(mDay).append("-")
                .append(mYear));
    }

    private void updateDisplay2() {
        // TODO Auto-generated method stub
        display_txt2.setText(new StringBuilder()
                .append(mMonth2 + 1).append("-").append(mDay2).append("-")
                .append(mYear2));
    }

    public void handleNextButton(View view) {
        savePreferences("numOf", editText3.getText().toString());
        Intent i = new Intent(SemesterSetup.this, ClassList.class);
        startActivity(i);
        System.out.println(s1);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("INSIDE: onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("INSIDE: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("INSIDE: onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("INSIDE: onPause");
        SharedPreferences settings = getSharedPreferences("mPrefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("startDate", display_txt.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("INSIDE: onResume");

    }


}