package com.example.tugas_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Status extends Fragment {

    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.blood_shot,R.drawable.janin,R.drawable.gundala,R.drawable.wulan};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.fragment_status,container,false);
        s1 = getResources().getStringArray(R.array.nama_film);
        s2 = getResources().getStringArray(R.array.Deskripsi);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        RecycleAdapter recyclerviewAdapter = new RecycleAdapter(getContext(),s1,s2,images);
        recyclerView.setAdapter(recyclerviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
