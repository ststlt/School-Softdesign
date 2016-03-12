package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.school.R;

public class UserActiveViewHolder extends RecyclerView.ViewHolder {

    protected TextView fullName;
    protected TextView teamName;
//    protected ImageView avatar;

    public UserActiveViewHolder(View itemView) {
        super(itemView);

        fullName = (TextView) itemView.findViewById(R.id.full_name);
        teamName = (TextView) itemView.findViewById(R.id.team_name);
//        avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
    }
}
