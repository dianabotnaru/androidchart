package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment2;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseInjectableActivity;
import com.alcoholcountermeasuresystems.android.elan.fragments.AddDrinkFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.DateTimePickerFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.WarningDialogFragment;
import com.alcoholcountermeasuresystems.android.elan.managers.RealmStore;
import com.alcoholcountermeasuresystems.android.elan.models.BAC;

import org.joda.time.DateTime;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alcoholcountermeasuresystems.android.elan.data.enums.BundleKey.KeyBac;
import static com.alcoholcountermeasuresystems.android.elan.data.enums.BundleKey.KeyIsComeHistory;

/**
 * Created by jordi on 31/10/16.
 */

public class AddDrinkActivity extends BaseInjectableActivity implements DateTimePickerFragment.DateTimePickerListener,AddDrinkFragment.AddDrinkFragmentListener,WarningDialogFragment.WarningDialogListener{

    @Inject
    RealmStore mRealmStore;

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindString(R.string.bac_disclaimer_title)
    String mTitleString;

    @BindString(R.string.add_drink_delete_entry_dialog_title)
    String mDeleteDialogTitleString;

    @BindString(R.string.add_drink_delete_entry_dialog_description)
    String mDeleteDialogDescriptionString;

    private boolean isComeFromHistory = false;

    private WarningDialogFragment addDrinkConfirmDialog;
    private WarningDialogFragment DeleteConfirmConfirmDialog;

    public BAC mBac;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);
        ButterKnife.bind(this);
        isComeFromHistory = getIntent().getBooleanExtra(KeyIsComeHistory.toString(),false);
        mBac = getIntent().getParcelableExtra(KeyBac.toString());
        initViews();
    }

    @Override
    protected void injectComponents() {
        MainApplication.getAppComponent().inject(this);
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitleText.setText(mTitleString);
    }

    private void initViews(){
        AddDrinkFragment fragment = AddDrinkFragment.newInstance(mBac,isComeFromHistory);
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
    public void onSelectDateTime(DateTime dateTime) {
        AddDrinkFragment addDrinkFragment = (AddDrinkFragment)
                getSupportFragmentManager().findFragmentById(R.id.layout_add_drink_content);
        if (addDrinkFragment != null) {
            addDrinkFragment.setDateTimeTextview(dateTime);
        }
    }

    @Override
    public void onAddDrink(BAC bac){
        mRealmStore.addBac(bac);
        addDrinkConfirmDialog = WarningDialogFragment.newInstance("Added Drink Successfully","New Drink Added",true);
        addDrinkConfirmDialog.show(getSupportFragmentManager(), WarningDialogFragment.TAG);
    }

    @Override
    public void onDeleteEntry(){
        DeleteConfirmConfirmDialog = WarningDialogFragment.newInstance(mDeleteDialogTitleString,mDeleteDialogDescriptionString,false);
        DeleteConfirmConfirmDialog.show(getSupportFragmentManager(), WarningDialogFragment.TAG);
    }


    @Override
    public void onDialogOkButtonClicked(WarningDialogFragment dialogFragment2) {
        if (dialogFragment2 == DeleteConfirmConfirmDialog){
            deleteEntry();
        }
        dialogFragment2.dismiss();
    }

    @Override
    public void onBackPressed()
    {
        if (isComeFromHistory){
            startActivity(new Intent(AddDrinkActivity.this, HistoryActivity.class));
        }else {
            startActivity(new Intent(AddDrinkActivity.this, BacEstimationActivity.class));
        }
        finish();
    }

    private void deleteEntry(){
        // todo delete entry from realm
    }
}
