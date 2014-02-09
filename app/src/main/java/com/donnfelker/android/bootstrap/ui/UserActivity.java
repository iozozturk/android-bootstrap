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
    @InjectView(R.id.user_matlab_value) protected TextView user_matlab_value;
    @InjectView(R.id.user_cuda_value) protected TextView user_cuda_value;
    @InjectView(R.id.user_html_value) protected TextView user_html_value;
    @InjectView(R.id.user_javascript_value) protected TextView user_javascript_value;
    @InjectView(R.id.user_xml_value) protected TextView user_xml_value;
    @InjectView(R.id.user_php_value) protected TextView user_php_value;
    @InjectView(R.id.user_python_value) protected TextView user_python_value;
    @InjectView(R.id.user_css_value) protected TextView user_css_value;
    @InjectView(R.id.user_english_value) protected TextView user_english_value;
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

        Map<String,Integer> technical = user.getTechnical();

        user_name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        user_email.setText(user.getEmail());
        user_school.setText( user.getSchoolName());
        user_schoolgrade_value.setText( technical.get("schoolGrade").toString());
        user_c_value.setText( technical.get("c").toString());
        user_cplusplus_value.setText( technical.get("cpp").toString());
        user_java_value.setText( technical.get("java").toString());
        user_experience_value.setText( technical.get("xp").toString());
        user_matlab_value.setText( technical.get("matlab").toString());
        user_cuda_value.setText( technical.get("cuda").toString());
        user_html_value.setText( technical.get("html").toString());
        user_javascript_value.setText( technical.get("js").toString());
        user_xml_value.setText( technical.get("xml").toString());
        user_php_value.setText( technical.get("php").toString());
        user_python_value.setText( technical.get("python").toString());
        user_css_value.setText( technical.get("css").toString());
        user_english_value.setText( technical.get("english").toString());
    }


}
