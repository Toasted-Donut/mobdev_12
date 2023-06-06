package com.example.mobdev12;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface PlaceholderAPI {
    @GET("/posts/{id}")
    Call<PlaceholderPost> getPostWithID(@Path("id") int id);

    @GET("/posts")
    Call<List<PlaceholderPost>> getAllPosts();

    @POST("/posts")
    Call<PlaceholderPost> postData(@Body PlaceholderPost data);
}
