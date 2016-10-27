package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.RegisterActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 27/10/16.
 */

public class ProfileInputLayout extends LinearLayout {

    @BindView(R.id.text_title)
    TextView mProfileItemTitleText;

    @BindView(R.id.button_profile_item_dropdown)
    ImageButton mProfileItemDropdownButton;

    @OnClick(R.id.button_profile_item_dropdown)
    void onButtonDropdownPressed() {

    }

    public ProfileInputLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public ProfileInputLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_register, this);
        ButterKnife.bind(this);
        onSetProfileItemTitle(context,attributeSet);
        onShowDropdownButton(context,attributeSet);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    private void onSetProfileItemTitle(Context context,AttributeSet attributeSet){
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.Title, 0, 0);
        if (ta == null) {
            return;
        }
        CharSequence s = ta.getString(R.styleable.Title_itemTitle);
        if (s != null) {
            mProfileItemTitleText.setText(s);
        }
    }

    private void onShowDropdownButton(Context context,AttributeSet attributeSet){
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.SetDropdownText, 0, 0);
        if (ta == null) {
            return;
        }
        boolean s = ta.getBoolean(R.styleable.SetDropdownText_setDropdown,false);
        if (s) {
            mProfileItemDropdownButton.setVisibility(View.VISIBLE);
        }
    }
}
