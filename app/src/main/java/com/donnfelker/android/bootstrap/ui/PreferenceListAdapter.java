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

import java.util.List;

/**
 * Created by ismet on 1/14/14.
 */
public class PreferenceListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Preference> preferenceList;

    PreferenceListAdapter(Activity activity, List<Preference> preferenceList) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vi;
        if (convertView == null)
            vi = inflater.inflate(R.layout.social_list_item, null); // create layout from
        else
            vi = convertView;
        TextView textLabel = null; // preference name
        final SeekBar prefcontrol ;
        ToggleButton prefereceToggle = null;
        if (vi != null) {
            textLabel = (TextView) vi.findViewById(R.id.social_item_title);
            prefcontrol = (SeekBar) vi.findViewById(R.id.social_item_seekbar);
            prefereceToggle = (ToggleButton) vi.findViewById(R.id.social_item_toggle);
        }else{
            prefcontrol = null;
        }
        Preference preference = preferenceList.get(position);

        if (textLabel != null) {
            textLabel.setText(preference.getName());
        }
        if (prefcontrol != null) {
            prefereceToggle.setId(R.id.TOGGLE +10 +  position);
            prefereceToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked){
                        prefcontrol.setEnabled(false);
                    }else{
                        prefcontrol.setEnabled(true);
                    }
                }
            });
            prefcontrol.setProgress(preference.getVal());
            prefcontrol.setId(R.id.SEEKBAR + position);
            prefcontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progress;
                TextView textValue_ = (TextView) vi.findViewById(R.id.social_item_value);

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    this.progress = progress;
                    if (textValue_ != null) {
                        textValue_.setText("" + progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                   // if (vi.getContext() != null)
                      //  Toast.makeText(vi.getContext(), "Value changed to" + progress, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return vi;
    }
}
