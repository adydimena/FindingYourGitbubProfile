package com.example.ady.findingyourgitbubprofile;

import com.example.ady.findingyourgitbubprofile.model.github.GithubProfile;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ady on 11/18/2017.
 */


public class RetrofitHelper {

    public static final String BASE_URL = "https://api.github.com/";

    //    build the retrofit object to be used
    public static Retrofit create() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

    //call the interface to get the response
    public static Call<GithubProfile> getMyProfile(String user){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getProfile(user);
    }


    //    create an interface for http verbs
    interface RetrofitService {

        @GET("users/{user}")
        Call<GithubProfile> getProfile(@Path("user") String user);
    }


}
