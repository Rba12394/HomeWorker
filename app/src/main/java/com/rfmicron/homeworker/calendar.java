package com.rfmicron.homeworker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Blake on 6/8/2015.
 */
public class calendar extends Activity {
    HomeWorker ob;
    Button change_date_but;
    TextView display_txt;
    public static final int Date_dialog_id = 1;
    // date and time
    private int mYear;
    private int mMonth;
    private int mDay;
    TableLayout table_layout;
    EditText firstname_et, lastname_et;
    Button addmem_btn;
    SQLController sqlcon;
    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        change_date_but = (Button) findViewById(R.id.change_button_id);
        display_txt = (TextView) findViewById(R.id.display_id);
        change_date_but = (Button) findViewById(R.id.change_button_id);

        change_date_but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog DPD = new DatePickerDialog(
                        calendar.this, mDateSetListener, mYear, mMonth,
                        mDay);
                DPD.show();
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDisplay();


        Button b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(calendar.this, Setup.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void BuildTable() {

        sqlcon.open();
        Cursor c = sqlcon.readEntry();

        int rows = c.getCount();
        int cols = c.getColumnCount();

        c.moveToFirst();

        // outer for loop
        for (int i = 0; i < rows; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new RelativeLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));

            // inner for loop
            for (int j = 0; j < cols; j++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
               // tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0, 5, 0, 5);

                tv.setText(c.getString(j));

                row.addView(tv);

            }
            c.moveToNext();
            table_layout.addView(row);
        }
        sqlcon.close();
    }

    private class MyAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            table_layout.removeAllViews();

            PD = new ProgressDialog(calendar.this);
            PD.setTitle("Please Wait..");
            PD.setMessage("Loading...");
            PD.setCancelable(false);
            PD.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

            String firstname = firstname_et.getText().toString();
            String lastname = lastname_et.getText().toString();

            // inserting data
            sqlcon.open();
            sqlcon.insertData(firstname, lastname);
            // BuildTable();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            BuildTable();
            PD.dismiss();
        }
    }


    public void setOb( HomeWorker obA){
        this.ob=obA;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void updateDisplay() {
        // TODO Auto-generated method stub
        display_txt.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(mMonth + 1).append("-").append(mDay).append("-")
                .append(mYear));
    }

    public void classBtnClick(View view){
        Intent i = new Intent(calendar.this, ClassList.class);
        startActivity(i);
    }



}



