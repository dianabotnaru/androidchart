package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextWatcher;
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

    @OnClick(R.id.layout_input)
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
        inflater.inflate(R.layout.layout_register_item, this);
        ButterKnife.bind(this);

        onSetProfileItemTitle(context,attributeSet);
        onShowDropdownButton(context,attributeSet);
        onSetProfileInputFocusable(context,attributeSet);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    private void onSetProfileItemTitle(Context context,AttributeSet attributeSet){
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.Label, 0, 0);
        if (ta == null) {
            return;
        }
        CharSequence s = ta.getString(R.styleable.Label_title);
        if (s != null) {
            mProfileItemTitleText.setText(s);
            onSetInputType(s.toString());
        }
    }

    private void onSetProfileInputFocusable(Context context,AttributeSet attributeSet){
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.EditText, 0, 0);
        if (ta == null) {
            return;
        }
        boolean focus = ta.getBoolean(R.styleable.EditText_focus,true);
        mProfileItemInputEditText.setFocusable(focus);
    }


    private void onSetInputType(String itemTitle){
        if (itemTitle.equals(getResources().getString(R.string.register_profile_email))){
            mProfileItemInputEditText.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
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
            mProfileItemInputEditText.setFocusable(false);
        }
    }

    public void addTextChangedListener(TextWatcher mTextWatcher) {
        mProfileItemInputEditText.addTextChangedListener(mTextWatcher);
    }

    public void setInputText(String inputtext){
        mProfileItemInputEditText.setText(inputtext);
    }

    public String getInputText(){
        return mProfileItemInputEditText.getText().toString();
    }
}
