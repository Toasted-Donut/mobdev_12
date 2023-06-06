package com.example.mobdev12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mobdev12.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        final PlaceholderPost[] post_ = new PlaceholderPost[1];

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitFactory.getInstance().getAPI().getPostWithID(7).enqueue(new Callback<PlaceholderPost>() {
                    @Override
                    public void onResponse(Call<PlaceholderPost> call, Response<PlaceholderPost> response) {
                        PlaceholderPost post = response.body();
                        post_[0] = post;
                        binding.txt.setText("");
                        binding.txt.append(post.getId()+" ");
                        binding.txt.append(post.getUserId()+" ");
                        binding.txt.append(post.getTitle()+"\n");
                        binding.txt.append(post.getBody()+"");
                    }

                    @Override
                    public void onFailure(Call<PlaceholderPost> call, Throwable t) {
                        binding.txt.setText("smth went wrong getting request");
                    }
                });
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitFactory.getInstance().getAPI().getAllPosts().enqueue(new Callback<List<PlaceholderPost>>() {
                    @Override
                    public void onResponse(Call<List<PlaceholderPost>> call, Response<List<PlaceholderPost>> response) {
                        List<PlaceholderPost> postList = response.body();
                        binding.txt.setText("responses:\n");
                        if(postList!=null){
                            for(PlaceholderPost post : postList){
                                binding.txt.append("ID: "+post.getId()+" Title: "+post.getTitle()+"\n");
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<PlaceholderPost>> call, Throwable t) {
                        binding.txt.setText("smth went wrong getting request");
                    }
                });
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitFactory.getInstance().getAPI().postData(post_[0]).enqueue(new Callback<PlaceholderPost>() {
                    @Override
                    public void onResponse(Call<PlaceholderPost> call, Response<PlaceholderPost> response) {
                        PlaceholderPost post = response.body();
                        binding.txt.setText(String.format("post: %s\n", post.getTitle()));
                        binding.txt.append(String.format("success: %s", response.isSuccessful()));

                    }

                    @Override
                    public void onFailure(Call<PlaceholderPost> call, Throwable t) {
                        binding.txt.setText("nothing to post");
                    }
                });
            }
        });
        setContentView(binding.getRoot());
    }
}