package com.antho.newsreader.view.activities.search.datepicker;
/** Date picker fragment**/

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
/** Handles date picker behavior**/
public class DatePickerFragment extends DialogFragment
{
    private static final String ARGUMENT_YEAR = "ARGUMENT_YEAR";
    private static final String ARGUMENT_MONTH = "ARGUMENT_MONTH";
    private static final String ARGUMENT_DAY = "ARGUMENT_DAY";
    private DatePickerDialog.OnDateSetListener listener;
    private int year;
    private int month;
    private int dayOfMonth;
    // Creates new instance of date picker fragment and set date with given parameters
    public static DatePickerFragment newInstance(final int year, final int month, final int dayOfMonth)
    {
        final DatePickerFragment df = new DatePickerFragment();
        final Bundle args = new Bundle();
        args.putInt(ARGUMENT_YEAR, year);
        args.putInt(ARGUMENT_MONTH, month);
        args.putInt(ARGUMENT_DAY, dayOfMonth);
        df.setArguments(args);
        return df;
    }
    //  Create fragment and retrieve existing date data
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        retrieveArguments();
    }
    // Retrieve date data if it exists
    private void retrieveArguments()
    {
        final Bundle args = getArguments();
        if (args != null)
        {
            year = args.getInt(ARGUMENT_YEAR);
            month = args.getInt(ARGUMENT_MONTH);
            dayOfMonth = args.getInt(ARGUMENT_DAY);
        }
    }
    // Create date picker dialog
    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState)
    {
        return new DatePickerDialog(Objects.requireNonNull(getContext()), this.listener, this.year, this.month, this.dayOfMonth);
    }
    // Date listener
    public void setListener(final DatePickerDialog.OnDateSetListener listener)
    {
        this.listener = listener;
    }
}
