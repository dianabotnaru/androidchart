package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 27/10/16.
 */

public class ProfileInputLayout extends LinearLayout {

    @BindView(R.id.text_title)
    TextView mProfileItemTitleText;

    public ProfileInputLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public ProfileInputLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_register, this);
        ButterKnife.bind(this);

        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.Title, 0, 0);
        if (ta == null) {
            return;
        }
        CharSequence s = ta.getString(R.styleable.Title_itemTitle);
        if (s != null) {
            mProfileItemTitleText.setText(s);
        }
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }
}
