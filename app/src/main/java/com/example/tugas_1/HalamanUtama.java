package com.example.tugas_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import static com.example.tugas_1.CekWifi.CONNECT;
import static com.example.tugas_1.CekWifi.DISCONNECT;



public class HalamanUtama extends AppCompatActivity {

    private NotificationManagerCompat notificationCompat;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_utama);
        viewPager = findViewById(R.id.view);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        notificationCompat = NotificationManagerCompat.from(this);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabCount();

    } @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(check, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(check);
    }

    private BroadcastReceiver check = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra){
                case WifiManager.WIFI_STATE_ENABLED:
                    android.app.Notification notification = new NotificationCompat.Builder(HalamanUtama.this,CONNECT)
                            .setSmallIcon(R.drawable.ic_wifi_black_24dp).setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE).setContentTitle("Koneksi Wifi").setContentText("Wifi Terhubung").build();
                    notificationCompat.notify(1,notification);
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    android.app.Notification notification2 = new NotificationCompat.Builder(HalamanUtama.this,DISCONNECT)
                            .setSmallIcon(R.drawable.ic_wifi_black_24dp).setPriority(NotificationCompat.PRIORITY_LOW).setContentTitle("Koneksi Wifi").setContentText("Wifi Terputus").build();
                    notificationCompat.notify(2,notification2);
                    break;
            }
        }
    };
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            // Toast.makeText(getActivity(),"PORTRAIT",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Potrait", Toast.LENGTH_SHORT).show();
            //add your code what you want to do when screen on PORTRAIT MODE
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            //Toast.makeText(getActivity(),"LANDSCAPE",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Lanscape", Toast.LENGTH_SHORT).show();
            //add your code what you want to do when screen on LANDSCAPE MODE
        }
    }


}
