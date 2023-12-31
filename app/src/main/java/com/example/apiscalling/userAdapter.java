package com.example.apiscalling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.userHolder> {
    MainActivity mainActivity;
    List<UserModel> userList;
    public userAdapter(MainActivity mainActivity, List<UserModel> userList) {
        this.mainActivity = mainActivity;
        this.userList = userList;
    }

    @NonNull
    @Override
    public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new userHolder(LayoutInflater.from(mainActivity).inflate(R.layout.user_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull userHolder holder, int position) {
        holder.itemText.setText(userList.get(position).getTitle());
        Picasso.get().load(userList.get(position).getThumbnailUrl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class userHolder extends RecyclerView.ViewHolder{
        TextView itemText;
        ImageView imageView;

        public userHolder(@NonNull View itemView) {
            super(itemView);
            itemText=itemView.findViewById(R.id.title_txt);
            imageView=itemView.findViewById(R.id.img);

        }
    }
}
