package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.alcoholcountermeasuresystems.android.elan.R;

import butterknife.ButterKnife;

/**
 * Created by jordi on 31/10/16.
 */

public class DatePickUpLayout extends RelativeLayout {

    public DatePickUpLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public DatePickUpLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_date_pickup, this);
        ButterKnife.bind(this);
    }

}
