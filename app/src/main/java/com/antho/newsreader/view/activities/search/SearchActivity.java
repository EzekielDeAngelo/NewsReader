package com.antho.newsreader.view.activities.search;
/** Search activity **/
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

/** Handle search system **/
public class SearchActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener
{
    private android.app.DatePickerDialog.OnDateSetListener mBeginDateSetListener;
    // TODO: create DatePicker for search purposes
    // Set up toolbar for navigation through activities

    @BindView(R.id.fromDate)
    TextView fromDate;
    @BindView(R.id.toDate)
    TextView toDate;
    @BindView(R.id.searchButton)
    Button searchButton;
    @BindView(R.id.artsCheckBox)
    CheckBox artsCheckBox;
    @BindView(R.id.businessCheckBox)
    CheckBox businessCheckBox;
    @BindView(R.id.entrepreneursCheckBox)
    CheckBox entrepreneursCheckBox;
    @BindView(R.id.politicsCheckBox)
    CheckBox politicsCheckBox;
    @BindView(R.id.sportsCheckBox)
    CheckBox sportsCheckBox;
    @BindView(R.id.travelCheckBox)
    CheckBox travelCheckBox;
    @BindView(R.id.notificationSearchTerm)
    EditText searchTermText;
    @BindView(R.id.sortSwitch)
    Switch sortSwitch;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    ArrayList<CheckBox> searchSubjectCheckBoxes;

    private boolean isFromDateSelected;
    private String categoriesText;
    private String beginDate;
    private String endDate;

    @Override
    protected int layoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create a new array list to store all the check boxes in the Search activity and add all the checkboxes to facilitate easy checking of state
        searchSubjectCheckBoxes = new ArrayList<>();
        searchSubjectCheckBoxes.add(artsCheckBox);
        searchSubjectCheckBoxes.add(businessCheckBox);
        searchSubjectCheckBoxes.add(entrepreneursCheckBox);
        searchSubjectCheckBoxes.add(politicsCheckBox);
        searchSubjectCheckBoxes.add(sportsCheckBox);
        searchSubjectCheckBoxes.add(travelCheckBox);

        fromDate.setOnClickListener(v -> {
            isFromDateSelected = true;
            final Calendar c = Calendar.getInstance();
            DatePickerFragment datePicker = DatePickerFragment.newInstance(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            datePicker.setListener(this);
            datePicker.show(getSupportFragmentManager(), null);

        });

        toDate.setOnClickListener(v -> {
            isFromDateSelected = false;
            final Calendar c = Calendar.getInstance();
            DatePickerFragment datePicker = DatePickerFragment.newInstance(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            datePicker.setListener(this);
            datePicker.show(getSupportFragmentManager(), null);
        });

        searchButton.setOnClickListener(v -> {
            if (goToSearch()) {
                StringBuilder sb = new StringBuilder();
                sb.append("news_desk:(");
                for (CheckBox checkBox : searchSubjectCheckBoxes) {
                    if (checkBox.isChecked()) {
                        sb.append("\"").append(checkBox.getText().toString()).append("\"");
                    }
                }
                sb.append(")");
                categoriesText = sb.toString();

                showSearchResults(
                        searchTermText.getText().toString(),
                        categoriesText,
                        beginDate,
                        endDate
                );


            } else {
                showToast(getString(R.string.search_msg));
            }

        });

        Calendar c = Calendar.getInstance();
        beginDate = String.format(Locale.getDefault(), "%d%02d%02d", c.get(Calendar.YEAR) - 1, c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));
        endDate = String.format(Locale.getDefault(), "%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));
        showToast("b: " + beginDate + " e: " + endDate);
    }

    private boolean goToSearch() {
        String searchTerm = searchTermText.getText().toString();
        for (CheckBox checkBox : searchSubjectCheckBoxes) {
            if (checkBox.isChecked() && !searchTerm.isEmpty()) {
                return true;
            }
        }

        return false;
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (isFromDateSelected) {
            fromDate.setText(String.format(Locale.getDefault(), "%d/%d/%d", dayOfMonth, month + 1, year));
            beginDate = String.format(Locale.getDefault(), "%d%02d%02d", year, month + 1, dayOfMonth);
        } else {
            toDate.setText(String.format(Locale.US, "%d/%d/%d", dayOfMonth, month + 1, year));
            endDate = String.format(Locale.getDefault(), "%d%02d%02d", year, month + 1, dayOfMonth);
        }
    }

    private void showSearchResults(String query, String categories, String beginDate, String endDate) {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        bundle.putString("categories", categories);
        bundle.putString("begin", beginDate);
        bundle.putString("end", endDate);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    // OnClick method to start search results activity if user has typed a search query
    /*public void searchArticles(View view)
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
                    categories += "BusinessFragment";
                break;
        }
    }*/
}
