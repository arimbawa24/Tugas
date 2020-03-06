package com.example.tugas_1;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



public class PageAdapter extends FragmentPagerAdapter {

    int count;

    public PageAdapter(FragmentManager fm,int count){
        super(fm);
        this.count = count;
    }



    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 1:
                TabChat tabChat = new TabChat();
                return tabChat;
            case 2:
                TabStatus tabStatus = new TabStatus();
                return tabStatus;
            case 3:
                TabPanggilan tabPanggilan = new TabPanggilan();
                return tabPanggilan;
                default:
                    return null;
        }

    }

    public int getCount() {
        return 0;
    }

}
