package com.donnfelker.android.bootstrap.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.User;
import com.squareup.picasso.Picasso;

import java.util.Map;

import butterknife.InjectView;

import static com.donnfelker.android.bootstrap.core.Constants.Extra.USER;

public class UserActivity extends BootstrapActivity {

    @InjectView(R.id.iv_avatar) protected ImageView avatar;
    @InjectView(R.id.tv_name) protected TextView name;
    @InjectView(R.id.user_name_value) protected TextView user_name;
    @InjectView(R.id.user_school_value) protected TextView user_school;
    @InjectView(R.id.user_email_value) protected TextView user_email;
    @InjectView(R.id.user_c_value) protected TextView user_c_value;
    @InjectView(R.id.user_cplusplus_value) protected TextView user_cplusplus_value;
    @InjectView(R.id.user_java_value) protected TextView user_java_value;
    @InjectView(R.id.user_experience_value) protected TextView user_experience_value;
    @InjectView(R.id.user_schoolgrade_value) protected TextView user_schoolgrade_value;

    private User user;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_view);

        if (getIntent() != null && getIntent().getExtras() != null) {
            user = (User) getIntent().getExtras().getSerializable(USER);
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.with(this).load(user.getAvatarUrl())
                .placeholder(R.drawable.gravatar_icon)
                .into(avatar);

        Map technical = user.getTechnical();

        user_name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        user_email.setText(user.getEmail());
        user_school.setText((CharSequence) technical.get("schoolName"));
        user_schoolgrade_value.setText((CharSequence) technical.get("schoolGrade"));
        user_c_value.setText((CharSequence) technical.get("c"));
        user_cplusplus_value.setText((CharSequence) technical.get("cpp"));
        user_java_value.setText((CharSequence) technical.get("java"));
        user_experience_value.setText((CharSequence) technical.get("xp"));

    }


}
