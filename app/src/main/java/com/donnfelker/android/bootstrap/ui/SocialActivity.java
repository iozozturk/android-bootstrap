package com.donnfelker.android.bootstrap.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.Constants;
import com.donnfelker.android.bootstrap.core.Preference;

import java.util.ArrayList;
import java.util.List;

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
        preferenceList.add(new Preference("Java",3, 5));
        preferenceList.add(new Preference("C", 3, 5));
        preferenceList.add(new Preference("C++", 3, 5));
        preferenceList.add(new Preference("Experience", 3, 5));
        preferenceList.add(new Preference("School", 3, 5));

        ListView customListView = (ListView) findViewById(R.id.social_list_view);
        PreferenceListAdapter preferenceListAdapter = new PreferenceListAdapter(this, preferenceList);
        customListView.setAdapter(preferenceListAdapter);

        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), preferenceList.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
        Log.d(Constants.IMeetapp.Log, "Created Social Activity..");

    }
}
