package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.tokuverseproject.R;
import com.example.tokuverseproject.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String userId = getIntent().getStringExtra("userID");
        Log.d("userID", userId);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.home:
                    Log.d("home", "1");
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    Log.d("profile", "1");
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.setting:
                    Log.d("setting", "1");
                    replaceFragment(new SettingFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}