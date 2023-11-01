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
        Bundle bundle = new Bundle();
        bundle.putString("userID", userId);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment(), bundle);
        Log.d("Home", "Home Activity");
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.home:
                    Log.d("home", "1");
                    replaceFragment(new HomeFragment(),bundle);
                    break;
                case R.id.profile:
                    Log.d("profile", "1");
                    replaceFragment(new ProfileFragment(),bundle);
                    break;
                case R.id.setting:
                    Log.d("setting", "1");
                    replaceFragment(new SettingFragment(),bundle);
                    break;
            }
            return true;
        });
    }


    private void replaceFragment(Fragment fragment, Bundle bundle)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}