package com.example.tugas_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;



public class halamanLogin extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);
//        Toolbar toolbar = findViewById(R.id.toolbar);
////        setActionBar(toolbar);
//
//        TableLayout tabLayout = findViewById(R.id.tabLayout);
////        tabLayout.addView();
        viewPager = (ViewPager) findViewById(R.id.view);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        List<String> isTab = new ArrayList<>();
        isTab.add("Pesan");
        isTab.add("Status");
        isTab.add("Panggilan");

        for (int i = 0;i < isTab.size(); i++){
            tabLayout.addTab(tabLayout.newTab().setText(isTab.get(i)));
        }

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), isTab);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
     }
}
