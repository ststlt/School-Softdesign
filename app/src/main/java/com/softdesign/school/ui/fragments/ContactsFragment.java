package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.RecycleUserAdapter;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> mUsers = new ArrayList<User>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /* Надуваем из xml лэйаута view элемент */
        View convertView = inflater.inflate(R.layout.fragment_contacts, null, false);

        /* Задаем в тулбаре выбранное в меню навигации наименование */
        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));

        ( (MainActivity) getActivity() ).lockAppBar(true);

        generateData();
        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.recycle_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleUserAdapter(mUsers);
        mRecyclerView.setAdapter(mAdapter);

        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab); //инициализируем fab из активити
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams(); // получаем параметры Layout fab приведенные к родителю
        params.setAnchorId(R.id.appbar_layout); //выставляем привязку якоря к appBarLayout
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT; //выставляем anchorGravity
        fab.setLayoutParams(params);
        fab.setImageResource(R.drawable.ic_phone_24dp); // меняем иконку fab
    }

    private void generateData() {
        mUsers.add(new User(getResources().getDrawable(R.drawable.s6578), "Шевцов", "Станислав"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Зеленский", "Илья"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Ильченко", "Александр"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Поляков", "Дмитрий"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Акимов", "Алексей"));
    }
}
