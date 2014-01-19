package com.donnfelker.android.bootstrap.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.Constants;
import com.donnfelker.android.bootstrap.core.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ismet on 1/18/14.
 */
public class MatchedUsersActivity extends BootstrapActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Constants.IMeetapp.Log, "Creating Matched Users Activity..");
        final List<User> users;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matched_users);

        setTitle(R.string.matched_users_title);

        if (getIntent() != null && getIntent().getExtras() != null) {
            users = (ArrayList<User>) getIntent().getExtras().getSerializable(Constants.IMeetapp.USERS);
        } else {
            users = new ArrayList<User>();
        }

        ListView customListView = (ListView) findViewById(R.id.matched_users_list_view);
        MatchedUsersListAdapter matchedUsersListAdapter = new MatchedUsersListAdapter(this, users);
        customListView.setAdapter(matchedUsersListAdapter);

        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), users.get(position).getFirstName(), Toast.LENGTH_LONG).show();
            }
        });
        Log.d(Constants.IMeetapp.Log, "Created Social Activity..");

    }
}
