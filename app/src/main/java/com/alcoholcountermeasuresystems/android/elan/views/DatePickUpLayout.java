package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.utils.DateUtils;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 31/10/16.
 */

public class DatePickUpLayout extends RelativeLayout {

    public interface DatePickUpListener {
        void onForwardDate();
        void onBackDate();
    }

    @BindView(R.id.text_date)
    TextView mDateText;

    @OnClick(R.id.layout_forward)
    void onForwardButtonPressed() {
        if (datePickUplistener != null)
            datePickUplistener.onForwardDate();
    }

    @OnClick(R.id.layout_back)
    void onBackButtonPressed() {
        if (datePickUplistener != null)
            datePickUplistener.onBackDate();
    }
    private DatePickUpListener datePickUplistener;

    public DatePickUpLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
        this.datePickUplistener = null;
    }

    public DatePickUpLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_date_pickup, this);
        ButterKnife.bind(this);
    }

    public void setDatePickUpListener(DatePickUpListener listener) {
        this.datePickUplistener = listener;
    }

    public void setDateText(DateTime date){
        mDateText.setText(DateUtils.getDateStringFromDateTime(date));
    }
}
