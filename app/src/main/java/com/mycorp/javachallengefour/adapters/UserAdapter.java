package com.mycorp.javachallengefour.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luqman.dev.helloworld.R;
import com.mycorp.javachallengefour.models.UsersModel;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> implements Filterable {

    private List<UsersModel> users;
    Context context;
    private List<UsersModel> filteredUser;

    public UserAdapter(Context applicationContext, List<UsersModel> users) {
        this.context = applicationContext;

        if (this.users == null) {
            this.users = users;
            this.filteredUser = users;
            notifyItemChanged(0, filteredUser);
        }
    }


    @NonNull
    @Override
    public UserAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user, parent, false);

        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewHolder holder, int position) {

        holder.name.setText(filteredUser.get(position).getFirstName() + " " + filteredUser.get(position).getLastName());
        holder.email.setText(filteredUser.get(position).getEmail());
        Glide.with(context).load(filteredUser.get(position).getAvatar()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return filteredUser.size();
        }else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.d("data", String.valueOf(charSequence));
                String string = charSequence.toString();
                if (string.isEmpty()) {
                    filteredUser = users;
                }else {
                    List<UsersModel> filteredList = new ArrayList<>();
                    for (UsersModel user : users) {
                        if (user.getFirstName().toLowerCase().contains(string.toLowerCase())) {
                            Log.d("user", user.getFirstName());
                            filteredList.add(user);
                        }
                    }
                    filteredUser = filteredList;

                    Log.d("ec", String.valueOf(filteredUser));
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUser;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                Log.d("publishResults: ", String.valueOf(filterResults.values));
                filteredUser = (ArrayList<UsersModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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