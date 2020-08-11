package com.mycorp.javachallengefour.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.luqman.dev.helloworld.R;
import com.mycorp.javachallengefour.models.UsersModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    private List<UsersModel> users;
    Context context;

    public UserAdapter(Context applicationContext, List<UsersModel> users) {
        this.context = applicationContext;
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewHolder holder, int position) {


        holder.name.setText(users.get(position).getFirstName()+ " "+users.get(position).getLastName());
        Log.d("name", users.get(position).getFirstName());
        holder.email.setText(users.get(position).getEmail());
        Glide.with(context).load(users.get(position).getAvatar()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        }else {
            return 0;
        }
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView name, email;
        ImageView image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.users_names);
            email = itemView.findViewById(R.id.user_email);
            image = itemView.findViewById(R.id.images_profiles);
        }
    }
}