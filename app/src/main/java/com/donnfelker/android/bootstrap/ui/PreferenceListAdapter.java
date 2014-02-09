package com.donnfelker.android.bootstrap.ui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.Constants;
import com.donnfelker.android.bootstrap.core.Preference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ismet on 1/14/14.
 */
public class PreferenceListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Preference> preferenceList;
    Map<String, Integer> seekbarValues ;
    Map<String, Boolean> toggleValues;

    PreferenceListAdapter(Activity activity, List<Preference> preferenceList) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        seekbarValues = new HashMap<String, Integer>();
        toggleValues = new HashMap<String, Boolean>();
        for(Preference preference : preferenceList){
            seekbarValues.put(preference.getName(),0);
            toggleValues.put(preference.getName(),true);
        }
        Log.d(Constants.IMeetapp.Log, "!!!ADAPTER CONSTRUCTED!!!");
        this.preferenceList = preferenceList;
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

    public Map<String, Integer> getSeekbarVal() {
        return seekbarValues;
    }

    public Map<String, Boolean> getToggleVal() {
        return toggleValues;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View vi;
        Log.d(Constants.IMeetapp.Log,"SEEK MAP VALUES: " + seekbarValues.toString());
        Log.d(Constants.IMeetapp.Log,"TOGGLE MAP VALUES: " + toggleValues.toString());
        if (convertView == null) {
            vi = inflater.inflate(R.layout.social_list_item, null); // create layout from
        } else {
            vi = convertView;
        }
        TextView textLabel; // preference name
        final SeekBar prefcontrol;
        ToggleButton prefereceToggle;
        textLabel = (TextView) vi.findViewById(R.id.social_item_title);
        prefcontrol = (SeekBar) vi.findViewById(R.id.social_item_seekbar);
        prefereceToggle = (ToggleButton) vi.findViewById(R.id.social_item_toggle);
        Preference preference = preferenceList.get(position);
        textLabel.setText(preference.getName());

        prefereceToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    prefcontrol.setEnabled(false);
                    toggleValues.put(preferenceList.get(position).getName(), false);
                } else {
                    prefcontrol.setEnabled(true);
                    toggleValues.put(preferenceList.get(position).getName(), true);
                }
            }
        });

        prefereceToggle.setChecked(toggleValues.get(preferenceList.get(position).getName()));
        prefcontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;
            TextView textValue_ = (TextView) vi.findViewById(R.id.social_item_value);

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                this.progress = progress;
                seekbarValues.put(preferenceList.get(position).getName(), progress);
                if (textValue_ != null) {
                    textValue_.setText("" + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        prefcontrol.setProgress(seekbarValues.get(preferenceList.get(position).getName()));
        return vi;
    }
}
