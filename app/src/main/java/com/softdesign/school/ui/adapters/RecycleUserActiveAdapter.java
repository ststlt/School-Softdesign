package com.softdesign.school.ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.UserActive;

import java.util.ArrayList;
import java.util.List;

public class RecycleUserActiveAdapter extends BaseAdapter {

    private final Context mContext;
    private List<UserActive> mUsers;
    LayoutInflater mInflater;

    public RecycleUserActiveAdapter(Context context) {
        this.mContext = context;
//    this.mUsers = users;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void resetData(List<UserActive> users) {
        this.mUsers = users;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserActive user = mUsers.get(position);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list_user_active, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.full_name);
        TextView team = (TextView) rowView.findViewById(R.id.user_team);
        name.setText(user.firstName + " " + user.lastName);
//        team.setText(user.team.title);
        return rowView;
    }

}

/*
public class RecycleUserActiveAdapter extends RecyclerView.Adapter<UserActiveViewHolder> {

    ArrayList<UserActive> users;

    public RecycleUserActiveAdapter(ArrayList<UserActive> users) { this.users = users; }

    @Override
    public UserActiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_card, parent, false);
        return new UserActiveViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(UserActiveViewHolder holder, int position) {
        UserActive user = users.get(position);
        holder.fullName.setText(user.getFirstName() + " " + user.getLastName());
//        holder.avatar.setImageDrawable(user.getImage());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}*/
