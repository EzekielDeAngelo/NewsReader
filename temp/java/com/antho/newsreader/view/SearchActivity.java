package com.antho.newsreader.view;
/****/
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import com.antho.newsreader.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/****/
public class SearchActivity extends AppCompatActivity
{
    private android.app.DatePickerDialog.OnDateSetListener mBeginDateSetListener;
    Calendar calendarBeginDate;
    String beginDate;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /** Dialog creation to set begin date*/

        View.OnClickListener listenerBeginDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        android.app.DatePickerDialog dialog = new android.app.DatePickerDialog(
        SearchArticlesActivity.this,
        mBeginDateSetListener,
        year, month, day);
        dialog.show();

        }
        };


        mBeginDateSetListener = new android.app.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendarBeginDate.set(year,month,dayOfMonth);

                beginDate = null;

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if (calendarBeginDate != null){
                    beginDate = sdf.format(calendarBeginDate.getTime());
                }

    //            tv_beginDateTextView.setText(beginDate);

            }
        };

    }
    //

    //
    /*public void onCheckboxClicked(View view)
    {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_arts:
                if (checked){}
                //
            else{}
                //
                break;
            case R.id.checkbox_business:
                if (checked){}
                //
            else{}
                //
                break;

        }
    }*/
}
