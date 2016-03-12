package com.softdesign.school.ui.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.softdesign.school.data.storage.models.TeamActive;

import java.util.List;

public class SpinnerTeamActiveAdapter extends BaseAdapter implements SpinnerAdapter {

    private Activity mActivity;
    private List<TeamActive> mTeamList;

    public SpinnerTeamActiveAdapter(Activity activity, List<TeamActive> team) {
        this.mActivity = activity;
        this.mTeamList = team;
    }

    @Override
    public int getCount() {
        return mTeamList.size();
    }

    @Override
    public Object getItem(int position) {return mTeamList.get(position);}

            @Override
    public long getItemId(int position) {return position;}

    /**
     * Берёт текущий элемент массива комманд, надувает стандартную вьюху для элемента списка,
     * вставляет в её текстовое поле название команды
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeamActive team = (TeamActive) mTeamList.get(position);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        convertView = inflater.inflate(android.R.layout.simple_spinner_item, null);
        TextView teamName = (TextView) convertView.findViewById(android.R.id.text1);
        teamName.setText(team.getTeamName());
        return convertView;
    }
}
