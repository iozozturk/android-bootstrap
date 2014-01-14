package com.donnfelker.android.bootstrap.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.donnfelker.android.bootstrap.core.Preference;

import java.util.List;

/**
 * Created by ismet on 1/14/14.
 */
public class PreferenceListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Preference> preferenceList;

    PreferenceListAdapter(Activity activity, List<Preference> preferenceList){
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.preferenceList = preferenceList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
