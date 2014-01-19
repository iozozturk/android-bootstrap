package com.donnfelker.android.bootstrap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.Constants;
import com.donnfelker.android.bootstrap.core.MatchingUtils;
import com.donnfelker.android.bootstrap.core.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ismet on 1/18/14.
 */
public class MatchedUsersActivity extends BootstrapActivity  {


    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Constants.IMeetapp.Log, "Creating Matched Users Activity..");
        final HashMap<Character, ArrayList<User>> matchedUsers;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matched_users);

        setTitle(R.string.matched_users_title);

        if (getIntent() != null && getIntent().getExtras() != null) {
            matchedUsers = (HashMap<Character, ArrayList<User>>) getIntent().getExtras().getSerializable(Constants.IMeetapp.USERS);
        } else {
            matchedUsers = new HashMap<Character, ArrayList<User>>();
        }

        final ArrayList<User> euclideanUsers = matchedUsers.get(MatchingUtils.EUCLIDEAN);
        final ArrayList<User> manhattanUsers = matchedUsers.get(MatchingUtils.MANHATTAN);
        final ArrayList<User> mahalanobisUsers = matchedUsers.get(MatchingUtils.MAHALANOBIS);


        ListView euclideanListView = (ListView) findViewById(R.id.matched_users_euclidean_list_view);
        ListView manhattanListView = (ListView) findViewById(R.id.matched_users_manhattan_list_view);
        ListView mahalanobisListView = (ListView) findViewById(R.id.matched_users_mahalanobis_list_view);
        MatchedUsersListAdapter euclideanMatchedUsersListAdapter = new MatchedUsersListAdapter(this, euclideanUsers);
        MatchedUsersListAdapter manhattanMatchedUsersListAdapter = new MatchedUsersListAdapter(this, manhattanUsers);
        MatchedUsersListAdapter mahalanobisMatchedUsersListAdapter = new MatchedUsersListAdapter(this, mahalanobisUsers);
        euclideanListView.setAdapter(euclideanMatchedUsersListAdapter);
        manhattanListView.setAdapter(manhattanMatchedUsersListAdapter);
        mahalanobisListView.setAdapter(mahalanobisMatchedUsersListAdapter);

        euclideanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(view.getContext(), UserActivity.class).putExtra(Constants.Extra.USER, euclideanUsers.get(position)));
            }
        });
        manhattanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(view.getContext(), UserActivity.class).putExtra(Constants.Extra.USER, manhattanUsers.get(position)));
            }
        });
        mahalanobisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(view.getContext(), UserActivity.class).putExtra(Constants.Extra.USER, mahalanobisUsers.get(position)));
            }
        });
        Log.d(Constants.IMeetapp.Log, "Created Social Activity..");

    }
}
