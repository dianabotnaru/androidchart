package com.alcoholcountermeasuresystems.android.elan.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;
import com.alcoholcountermeasuresystems.android.elan.fragments.AddDrinkFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.DateTimePickerFragment;

import java.util.Date;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 31/10/16.
 */

public class AddDrinkActivity extends BaseActivity implements DateTimePickerFragment.DateTimePickerListener{

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindString(R.string.bac_disclaimer_title)
    String mTitleString;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);
        ButterKnife.bind(this);
        initViews();
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitleText.setText(mTitleString);
    }

    private void initViews(){
        AddDrinkFragment fragment = new AddDrinkFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_add_drink_content, fragment)
                .commit();
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_drink, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            return true;
        }
        else if (id == R.id.action_dedisclaimer_adddrink) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectDateTime(Date date) {
        AddDrinkFragment addDrinkFragment = (AddDrinkFragment)
                getSupportFragmentManager().findFragmentById(R.id.layout_add_drink_content);
        if (addDrinkFragment != null) {
            addDrinkFragment.setDateTimeTextview(date);
        }
    }
}
