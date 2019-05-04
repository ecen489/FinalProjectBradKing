package com.google.firebase.codelab.mlkit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
    {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
            {
                Calendar C = Calendar.getInstance();
                int year = C.get(Calendar.YEAR);
                int month = C.get(Calendar.MONTH);
                int day = C.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(), year, month,day);
            }
    }
