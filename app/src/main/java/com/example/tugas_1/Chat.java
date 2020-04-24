package com.example.tugas_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import static android.content.Context.MODE_PRIVATE;

public class Chat extends Fragment {

    private Button logout;
    private  Button lihat;
    private  Button galeri;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntancesState){
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        logout = view.findViewById(R.id.btn_logout);
        lihat = view.findViewById(R.id.btn_lihat);
        galeri= view.findViewById(R.id.btn_picture);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferen = getActivity().getSharedPreferences("masuk", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferen.edit();
                editor.putString("ingat","false");
                editor.apply();
                getActivity().finish();
            }
        });
        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),DaftarUser.class);
                startActivity(intent);

            }
        });

        galeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Camera.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
