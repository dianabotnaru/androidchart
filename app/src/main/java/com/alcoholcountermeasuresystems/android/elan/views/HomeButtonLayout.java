package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 31/10/16.
 */

public class HomeButtonLayout extends RelativeLayout {

    @BindView(R.id.text_title)
    TextView mButtonTitleText;

    @BindView(R.id.image_icon)
    ImageView mIconImage;

    @BindDrawable(R.drawable.shape_button_start_inactive)
    Drawable mInActiveBackground;

    @BindDrawable(R.drawable.shape_button_start_active)
    Drawable mActiveBackground;

    public HomeButtonLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public HomeButtonLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_home_button, this);
        ButterKnife.bind(this);
        setTitleText(context,attributeSet);
        setIconImage(context,attributeSet);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    private void setTitleText(Context context,AttributeSet attributeSet){
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.Label, 0, 0);
        if (ta == null) {
            return;
        }
        CharSequence s = ta.getString(R.styleable.Label_title);
        if (s != null) {
            mButtonTitleText.setText(s);
        }
    }

    private void setIconImage(Context context,AttributeSet attributeSet) {
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.Homebutton, 0, 0);
        if (ta == null) {
            return;
        }
        Drawable drawable = ta.getDrawable(R.styleable.Homebutton_buttonicon);
        if(drawable != null){
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mIconImage.setBackgroundDrawable(drawable);
            } else {
                mIconImage.setBackground(drawable);
            }
        }
    }

    public void setEnabled(boolean enable){
        if (enable){
            setLayoutBackground(mActiveBackground);
            mButtonTitleText.setAlpha(1);
            setIconImageViewAlpha(1);
        }
        else{
            setLayoutBackground(mInActiveBackground);
            mButtonTitleText.setAlpha(0.5f);
            setIconImageViewAlpha(0.5f);
        }
    }

    public void setLayoutBackground (Drawable drawable){
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
    }

    private void setIconImageViewAlpha(float alphaValue){
        AlphaAnimation alpha = new AlphaAnimation(alphaValue, alphaValue);
        alpha.setDuration(0);
        alpha.setFillAfter(true);
        mIconImage.startAnimation(alpha);
    }
}
