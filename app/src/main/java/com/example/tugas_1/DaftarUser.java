package com.example.tugas_1;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DaftarUser extends AppCompatActivity {

    ListView ListUser;
    DatabaseReference reference;
    List<User>userList;
    public void onCreate (Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_user);
        ListUser = findViewById(R.id.ListViewUser);
        reference = FirebaseDatabase.getInstance().getReference("users");
        userList = new ArrayList<>();

        ListUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                User user = userList.get(position);

                ShowUpdate(user.getEmail(),user.getUserId());


                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userList.clear();
                for (DataSnapshot userShanpshot: dataSnapshot.getChildren()){
                    User user = userShanpshot.getValue(User.class);
                    userList.add(user);
                }
                UserList adapter = new UserList(DaftarUser.this, userList);
                ListUser.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void ShowUpdate(final String userId, final String email){

        AlertDialog.Builder update = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View updateView = inflater.inflate(R.layout.update,null);

        update.setView(updateView);

        final  EditText emailBaru= updateView.findViewById(R.id.edit_nama);
        final Button Update = updateView.findViewById(R.id.btn_update);
        final Button Delete = updateView.findViewById(R.id.btn_update);

        update.setTitle("Update Email  "+email);

        final AlertDialog alertDialog= update.create();
        alertDialog.show();


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = emailBaru.getText().toString().trim();
                if (TextUtils.isEmpty(newEmail)){
                    emailBaru.setError("masih kosong");
                    return;
                }
                updatemail(userId,newEmail);
                alertDialog.dismiss();
            }

        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(userId);
            }
        });
    }
    private void updatemail(String userId, String email) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);

        User user = new User(userId,email);

        databaseReference.setValue(user);

        Toast.makeText(this, "Update Berhasil",Toast.LENGTH_LONG).show();
    }

    private void delete (String userId){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);

        databaseReference.removeValue();

    }
}
