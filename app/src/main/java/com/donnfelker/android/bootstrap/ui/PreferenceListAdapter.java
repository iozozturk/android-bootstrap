package com.donnfelker.android.bootstrap.ui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.Constants;
import com.donnfelker.android.bootstrap.core.Preference;
import com.donnfelker.android.bootstrap.util.Strings;

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
        Log.d(Constants.IMeetapp.Log,"Preference List Adapter constructed...");
    }

    @Override
    public int getCount() {
        return preferenceList.size();
    }

    @Override
    public Object getItem(int position) {
        return preferenceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(Constants.IMeetapp.Log,"Building Preference view..");
        View vi = convertView;
          if (convertView == null)
              vi = inflater.inflate(R.layout.social_list_item, null); // create layout from

        TextView textView = null; // preference name
        if (vi != null) {
            textView = (TextView) vi.findViewById(R.id.social_item_title);
        }

        Preference preference = preferenceList.get(position);

        if (textView != null) {
            textView.setText(preference.getName());
        }
        String isViewNull = vi != null ? "view is not null": "view is null";
        Log.d(Constants.IMeetapp.Log,"Returning Preference view for " + preference.getName() + " and " + isViewNull);
        return vi;
    }
}
