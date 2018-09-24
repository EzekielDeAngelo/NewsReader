package com.antho.newsreader.view.activities.search;
/** Search activity **/
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.antho.newsreader.R;

import java.util.Calendar;
/** Handle search system **/
public class SearchActivity extends AppCompatActivity
{
    private android.app.DatePickerDialog.OnDateSetListener mBeginDateSetListener;
    // TODO: create DatePicker for search purposes
    private Calendar calendarBeginDate;
    private String beginDate;
    // Set up toolbar for navigation through activities
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    // OnClick method to start search results activity if user has typed a search query
    public void searchArticles(View view)
    {
        final EditText searchQuery = (EditText) findViewById(R.id.search_query);
        String query = searchQuery.getText().toString();
        Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
        startActivity(intent);
    }
    // Update the list of checkbox clicked by user
    public void onCheckboxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        String categories="";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_arts:
                if (checked)
                    categories += "Arts";
                break;
            case R.id.checkbox_business:
                if (checked)
                    categories += "Business";
                break;
        }
    }
}
