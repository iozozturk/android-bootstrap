package com.donnfelker.android.bootstrap.ui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.Constants;
import com.donnfelker.android.bootstrap.core.Preference;
import com.donnfelker.android.bootstrap.core.User;

import java.util.List;

/**
 * Created by ismet on 1/14/14.
 */
public class MatchedUsersListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<User> userList;

    MatchedUsersListAdapter(Activity activity, List<User> userList) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userList = userList;
        Log.d(Constants.IMeetapp.Log, "User List Adapter constructed...");
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(Constants.IMeetapp.Log, "Building userList view..");
        final View vi;
        if (convertView == null)
            vi = inflater.inflate(R.layout.matched_user_list_item, null); // create layout from
        else
            vi = convertView;
        TextView textLabel = null; // preference name
        if (vi != null) {
            textLabel = (TextView) vi.findViewById(R.id.matched_user_name);
        }
        User user = userList.get(position);

        if (textLabel != null) {
            textLabel.setText(String.format("%1$s %2$s", user.getFirstName(), user.getLastName()));
        }

        String isViewNull = vi != null ? "view is not null" : "view is null";
        Log.d(Constants.IMeetapp.Log, "Returning Preference view for " + user.getFirstName() + " and " + isViewNull);
        return vi;
    }
}
