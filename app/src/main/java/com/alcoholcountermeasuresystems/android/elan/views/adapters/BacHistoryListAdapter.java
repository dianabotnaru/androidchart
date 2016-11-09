package com.alcoholcountermeasuresystems.android.elan.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.models.BAC;
import com.alcoholcountermeasuresystems.android.elan.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 06/11/16.
 */

public class BacHistoryListAdapter extends BaseAdapter {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<BAC> mBacs = new ArrayList<>();

    public BacHistoryListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mBacs.size();
    }

    @Override
    public BAC getItem(int position) {
        return mBacs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DeviceHolder holder;
        if (convertView == null) {
            int layoutId = R.layout.item_history_listview;
            convertView = mLayoutInflater.inflate(layoutId, parent, false);
            holder = new DeviceHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DeviceHolder) convertView.getTag();
        }

        BAC bac = getItem(position);
        holder.mBacData.setText(String.valueOf(bac.getVolumeConsumption())+"ml @ "+String.valueOf(bac.getPercentageConsumption())+"%");
        holder.mDate.setText(DateUtils.getTimeStringFromTimeStamp(bac.getTimeStamp()));
        return convertView;
    }

    public void setItems(List<BAC> bacs) {
        mBacs.clear();
        for (BAC bac : bacs) {
            mBacs.add(bac);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        mBacs.clear();
        notifyDataSetChanged();
    }

    static class DeviceHolder {
        @BindView(R.id.text_bac_data)
        TextView mBacData;

        @BindView(R.id.text_date)
        TextView mDate;

        DeviceHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
