package com.softdesign.school.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.adapters.RecycleUserAdapter;

import java.util.ArrayList;

public class RecyclerviewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> mUsers = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        generateData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter = new RecycleUserAdapter(mUsers);
        mRecyclerView.setAdapter(mAdapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void generateData() {
        mUsers.add(new User(getResources().getDrawable(R.drawable.s6578), "Шевцов", "Станислав"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Зеленский", "Илья"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Ильченко", "Александр"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Поляков", "Дмитрий"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Акимов", "Алексей"));
    }

}
