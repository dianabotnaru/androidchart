package com.alcoholcountermeasuresystems.android.elan.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment2;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseDialogFragment;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 04/11/16.
 */

public class DateTimePickerFragment extends BaseDialogFragment {

    public static final String TAG = DateTimePickerFragment.class.getCanonicalName();

    public interface DateTimePickerListener {
        void onSelectDateTime(DateTime date);
    }

    private  int mYear,mMonth,mDay,mHour,mMinute;

    @BindView(R.id.date_picker)
    DatePicker mDatePicker;

    @BindView(R.id.time_picker)
    TimePicker mTimeicker;

    public static DateTimePickerFragment newInstance() {
        return new DateTimePickerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_date_time_picker_dialog);
        mUnbinder = ButterKnife.bind(this, dialog.getWindow().getDecorView());
        initTimePicker();
        initDatePicker();
        return dialog;
    }

    private void initTimePicker(){
        mTimeicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                setDateAndTime();
            }
        });
    }

    private void initDatePicker(){
        Calendar today = Calendar.getInstance();
        mDatePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener(){
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        setDateAndTime();
                    }});
    }

    private void setDateAndTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mYear);
        calendar.set(Calendar.MONTH, mMonth);
        calendar.set(Calendar.DAY_OF_MONTH, mDay);
        calendar.set(Calendar.HOUR_OF_DAY,mHour);
        calendar.set(Calendar.MINUTE,mMinute);
        DateTime dateTime = new DateTime(calendar);
        try{
            ((DateTimePickerListener) getActivity()).onSelectDateTime(dateTime);
        }catch (ClassCastException cce){
            throw new ClassCastException("DateTimePickerListener getTargetFragment is not set");
        }
    }

}


