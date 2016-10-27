package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 27/10/16.
 */

public class ProfileInputLayout extends LinearLayout {


    public interface OnDropDownButtonPressedListener {
        void onDropDownButtonClicked();
    }

    public void setDropDownButtonPressedListener(OnDropDownButtonPressedListener eventListener) {
        mListener = eventListener;
    }

    @BindView(R.id.text_title)
    TextView mProfileItemTitleText;

    @BindView(R.id.edittext_input)
    EditText mProfileItemInputEditText;

    @BindView(R.id.button_profile_item_dropdown)
    ImageButton mProfileItemDropdownButton;

    @BindView(R.id.layout_input)
    RelativeLayout mInputLayout;

    @OnClick(R.id.button_profile_item_dropdown)
    void onButtonDropDownPressed() {
        if (mListener!=null){
            mListener.onDropDownButtonClicked();
        }
    }

    OnDropDownButtonPressedListener mListener;

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
//        mInputLayout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                if (mListener!=null){
//                    mListener.onDropDownButtonClicked();
//                }
//            }
//        });
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
            mProfileItemInputEditText.setEnabled(false);
        }
    }

    public void setInputText(String inputtext){
        mProfileItemInputEditText.setText(inputtext);
    }

    public String getInputText(){
        return mProfileItemInputEditText.getText().toString();
    }
}
