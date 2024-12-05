package com.example.tpi.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tpi.R;
import com.example.tpi.adapters.ImageAdapter;
import com.example.tpi.databinding.ActivityPostBinding;
import com.example.tpi.viewModel.PostViewModel;

public class PostActivity extends AppCompatActivity {
    private int MAX_IMAGES;
    private int REQUEST_IMAGE;
    private ActivityPostBinding binding;
    private PostViewModel viewModel;
    private ImageAdapter adapter;
    private String categoria;
    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setupRecyclerView(){}
    public void setupViewModels(){}
    public void setupCategorySpinner(){}
    public void setupGalleryLauncher(){}
    public void publicarPost(){}
    public void updateRecyclerViewVisibility(){}
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}