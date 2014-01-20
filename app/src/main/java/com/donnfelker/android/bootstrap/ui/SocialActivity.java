package com.donnfelker.android.bootstrap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.donnfelker.android.bootstrap.BootstrapServiceProvider;
import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.authenticator.ApiKeyProvider;
import com.donnfelker.android.bootstrap.core.Constants;
import com.donnfelker.android.bootstrap.core.MatchingUtils;
import com.donnfelker.android.bootstrap.core.Preference;
import com.donnfelker.android.bootstrap.core.User;
import com.donnfelker.android.bootstrap.core.UserAgentProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ismet on 1/14/14.
 */
public class SocialActivity extends BootstrapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Constants.IMeetapp.Log, "Creating Social Activity..");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social);

        setTitle(R.string.social);

        final List<Preference> preferenceList = new ArrayList<Preference>();
        preferenceList.add(new Preference(Constants.IMeetapp.JAVA, 0, 5));
        preferenceList.add(new Preference(Constants.IMeetapp.C, 0, 5));
        preferenceList.add(new Preference(Constants.IMeetapp.CPLUSPLUS, 0, 5));
        preferenceList.add(new Preference(Constants.IMeetapp.EXPERIENCE, 0, 5));
        preferenceList.add(new Preference(Constants.IMeetapp.SCHOOL_GRADE, 0, 5));

        ListView customListView = (ListView) findViewById(R.id.social_list_view);
        Button goButton = (Button) findViewById(R.id.buttonGo);

        PreferenceListAdapter preferenceListAdapter = new PreferenceListAdapter(this, preferenceList);
        customListView.setAdapter(preferenceListAdapter);


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Integer> values = getValues();
                HashMap<Character, List<User>> matchedUsers = new HashMap<Character, List<User>>();

                try {
                    matchedUsers = MatchingUtils.matchUsers(values);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(v.getContext(), MatchedUsersActivity.class).putExtra(Constants.IMeetapp.USERS, matchedUsers));
            }

            public Map<String, Integer> getValues() {
                Map<String, Integer> values = new HashMap<String, Integer>();
                for (int i = 0; i < 5; ++i) {
                    SeekBar seekbar = (SeekBar) findViewById(R.id.SEEKBAR + i);
                    int progress = seekbar.getProgress();
                    if (seekbar.isEnabled())
                        values.put(preferenceList.get(i).getName(), progress);
                }
                return values;
            }
        });
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), preferenceList.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
        Log.d(Constants.IMeetapp.Log, "Created Social Activity..");

    }
}
