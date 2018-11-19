package com.antho.newsreader.view.activities.search;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Objects;

public class DatePickerFragment extends DialogFragment {

    private static final String ARGUMENT_YEAR = "ARGUMENT_YEAR";
    private static final String ARGUMENT_MONTH = "ARGUMENT_MONTH";
    private static final String ARGUMENT_DAY = "ARGUMENT_DAY";
    private DatePickerDialog.OnDateSetListener listener;

    private int year;
    private int month;
    private int dayOfMonth;

    public static DatePickerFragment newInstance(final int year, final int month, final int dayOfMonth) {
        final DatePickerFragment df = new DatePickerFragment();
        final Bundle args = new Bundle();
        args.putInt(ARGUMENT_YEAR, year);
        args.putInt(ARGUMENT_MONTH, month);
        args.putInt(ARGUMENT_DAY, dayOfMonth);
        df.setArguments(args);
        return df;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrieveArguments();
    }

    private void retrieveArguments() {
        final Bundle args = getArguments();
        if (args != null) {
            year = args.getInt(ARGUMENT_YEAR);
            month = args.getInt(ARGUMENT_MONTH);
            dayOfMonth = args.getInt(ARGUMENT_DAY);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        return new DatePickerDialog(Objects.requireNonNull(getContext()), this.listener, this.year, this.month, this.dayOfMonth);
    }

    public void setListener(final DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }
}
