package com.school.sensorsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.school.sensorsapp.databinding.ActivityRegestrationBinding;

public class RegestrationActivity extends AppCompatActivity {
    private ActivityRegestrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegestrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.regestration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegestrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}