package com.example.tugas_1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class UserList extends ArrayAdapter<User> {

private Activity contex;
private List<User>userList;

    public UserList(@NonNull Activity context, List<User> userList) {
        super(context, R.layout.user_list, userList);
        this.contex = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = contex.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.user_list,null,true);

        TextView emailUser = listViewItem.findViewById(R.id.email_user);

        User user = userList.get(position);

        emailUser.setText(user.getEmail());

        return listViewItem;
    }
}
