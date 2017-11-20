package com.example.ady.findingyourgitbubprofile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ady.findingyourgitbubprofile.model.github.GithubProfile;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView name ;
    private ImageView image ;
    private TextView followers ;
    private TextView following;
    private TextView company ;
    private EditText username ;
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void getProfile(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
         name = findViewById(R.id.name);
         image = findViewById(R.id.image);
         followers = findViewById(R.id.followers);
         following = findViewById(R.id.following);
         company = findViewById(R.id.company);
         username = findViewById(R.id.etusername);
        userName = username.getText().toString();
        RetrofitHelper.getMyProfile(username.getText().toString())
                .enqueue(new retrofit2.Callback<GithubProfile>() {
                    @Override
                    public void onResponse(Call<GithubProfile> call, Response<GithubProfile> response) {
                        name.setText(response.body().getName());
                        followers.setText("" + response.body().getFollowers());
                        following.setText("" + response.body().getFollowing());
                        company.setText(response.body().getCompany());

                        String urlforimage = response.body().getAvatarUrl();
                        Glide.with(MainActivity.this).load(urlforimage).into(image);
                    }

                    @Override
                    public void onFailure(Call<GithubProfile> call, Throwable t) {

                    }
                });
    }

    public void goToacitivitytwo(View view) {
        if (userName != "") {
            Intent intent = new Intent(this, Main2Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("magic", userName);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}