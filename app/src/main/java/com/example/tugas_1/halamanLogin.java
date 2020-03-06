package com.example.tugas_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toolbar;
//import android.support.design.wiget.TabLayout;

public class halamanLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setActionBar(toolbar);

        TableLayout tabLayout = findViewById(R.id.tabLayout);
//        tabLayout.addView();
    }
}
