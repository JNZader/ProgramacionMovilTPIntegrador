package com.example.tpi.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpi.model.Post;
import com.example.tpi.providers.PostProvider;

import java.util.List;

public class PostViewModel {
    private MutableLiveData<String> postSucces;
    private PostProvider postProvider;
    LiveData<List<Post>> posts;


    public PostViewModel(){
        postSucces = new MutableLiveData<>();
        postProvider = new PostProvider();
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    public MutableLiveData<String> getPostSucces() {
        return postSucces;
    }

    public void publicar(Post post){

    }
}
