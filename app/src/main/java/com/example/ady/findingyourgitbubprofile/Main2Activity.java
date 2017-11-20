package com.example.ady.findingyourgitbubprofile;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ady.findingyourgitbubprofile.model.github.GithubProfile;
import com.example.ady.findingyourgitbubprofile.model.github.Item;
import com.example.ady.findingyourgitbubprofile.model.github.MyResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;







public class Main2Activity extends AppCompatActivity {
    OkHttpClient client;
    Request request;
    String Response;
    private String repoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("magic");



        RetrofitHelper.getMyProfile(username)
                .enqueue(new retrofit2.Callback<GithubProfile>() {
                    @Override
                    public void onResponse(Call<GithubProfile> call, Response<GithubProfile> response) {
                        repoUrl = response.body().getReposUrl();
                        client = new OkHttpClient();
                        request = new Request.Builder()
                                .url(repoUrl)
                                .build();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Response = client
                                            .newCall(request)
                                            .execute()
                                            .body()
                                            .string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        List<Item> list = new ArrayList<>();
                                        JSONArray arr = null;
                                        try {
                                            arr = new JSONArray(Response);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        JSONObject jObj = null;
                                        for (int i = 0; i < arr.length(); i++) {
                                            try {

                                                jObj = arr.getJSONObject(i);


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                String repo = jObj.getString("name");
                                                String fullname = jObj.getString("full_name");
                                                String id = jObj.getString("id");
                                                list.add(new Item("Repository: " + repo
                                                        ,"Full Name: " + fullname
                                                        ,"ID:" + id));

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }



                                        RecyclerView recyclerView = findViewById(R.id.recycleMainLayout);
                                        RecyclerView.LayoutManager layoutManager =
                                                new LinearLayoutManager(Main2Activity.this);
                                        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                                        Recycleadapter recycleadapter = new Recycleadapter(list);
                                        recyclerView.setLayoutManager(layoutManager);
                                        recyclerView.setItemAnimator(itemAnimator);
                                        recyclerView.setAdapter(recycleadapter);


                                    }
                                });
                            }
                        }).start();



                    }

                    @Override
                    public void onFailure(Call<GithubProfile> call, Throwable t) {

                    }
                });















    }
}
