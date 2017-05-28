package com.bignerdranch.android.criminalintent;

import static com.bignerdranch.android.criminalintent.DatePickerFragment.EXTRA_DATE;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Mateusz on 2017-05-28.
 */

public class TimePickerFragment extends DialogFragment {

    private static final String ARG_TIME = "crime_time";
    public static final String EXTRA_TIME = "extra_time";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date time = (Date) getArguments().getSerializable(ARG_TIME);
        final TimePicker timePicker = initTimePicker(time);
        return new AlertDialog.Builder(getActivity())
                .setView(timePicker)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = timePicker.getCurrentHour();
                        int minutes = timePicker.getCurrentMinute();
                        Date date = new GregorianCalendar(0,0,0,hour,minutes).getTime();
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                .create();
    }

    private TimePicker initTimePicker(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        TimePicker timePicker = (TimePicker) LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minutes);
        return timePicker;
    }

    public static TimePickerFragment newInstance(Date time) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, time);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void sendResult(int resultCode, Date time) {
        if(getTargetFragment() == null) return;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, time);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
