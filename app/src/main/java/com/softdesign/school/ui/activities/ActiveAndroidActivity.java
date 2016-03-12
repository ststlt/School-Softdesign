package com.softdesign.school.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.TeamActive;
import com.softdesign.school.data.storage.models.UserActive;
import com.softdesign.school.ui.adapters.RecycleUserActiveAdapter;
import com.softdesign.school.ui.adapters.SpinnerTeamActiveAdapter;
import com.softdesign.school.ui.adapters.UserActiveViewHolder;
import com.softdesign.school.utils.UserAsyncLoader;

import java.util.ArrayList;
import java.util.List;

public class ActiveAndroidActivity extends AppCompatActivity implements View.OnClickListener, android.app.LoaderManager.LoaderCallbacks<ArrayList<UserActive>> {

    private Activity mActivity = this;
    private Spinner mSpinner;

    // Определение сущностей диалогового окна
    View dialogView, dialogViewTeam;
    EditText mFirstName, mLastName, mTeamName;
    RadioGroup mRadioGroup;
    /*List<UserActive> mUserActive;
    RecycleUserActiveAdapter userActiveAdapter;
    ListView listView;*/
    private ArrayList<UserActive> mUsers = new ArrayList<UserActive>();
    private RecyclerView.Adapter<UserActiveViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView mReciclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_android);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**Инициализация Loader для данных пользователя*/
        getLoaderManager().initLoader(0, null, this);

        // Инициализация элементов AlertDialog для доступа к ним
        LayoutInflater inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_add_user, null, false);
        mFirstName = (EditText) dialogView.findViewById(R.id.first_name);
        mLastName = (EditText) dialogView.findViewById(R.id.last_name);
//        mRadioGroup = (RadioGroup) dialogView.findViewById(R.id.radio_group);

        mSpinner = (Spinner) dialogView.findViewById(R.id.dialog_user_team);

        dialogViewTeam = inflater.inflate(R.layout.dialog_add_team, null, false);
        mTeamName = (EditText) dialogViewTeam.findViewById(R.id.dialog_team_name);

        /*listView = (ListView) findViewById(R.id.user_list);

        loadData();

        userActiveAdapter = new RecycleUserActiveAdapter(this);
//        userActiveAdapter.setData(mUserActive);
        listView.setAdapter(userActiveAdapter);*/
        /**Наполнение RecyclerView карточками с данными пользователей*/
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        mUsers.addAll(UserActive.getAllUsers());
        mAdapter = new RecycleUserActiveAdapter(mUsers, this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        View mBtnAddUser = (View) findViewById(R.id.button_add_user);
        mBtnAddUser.setOnClickListener(this);

        View mBtnAddTeam = (View) findViewById(R.id.button_add_team);
        mBtnAddTeam.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fab.setImageResource(R.drawable.ic_add_24dp1);
    }

    private void saveData() {

        /**Берём выбранный элемент в спиннере*/
        View selectView = mSpinner.getSelectedView();
        /**В нём TextView с названием команды*/
        TextView selectTeam = (TextView) selectView.findViewById(android.R.id.text1);
        /**Из него извлекаем строку с названием команды*/
        String name = selectTeam.getText().toString();
        /**Кидаем запрос в БД о команде с таким названием*/
        /**Как Кощей Бессмертный блин*/
        TeamActive team = TeamActive.getTeam(name);

//        UserActive userActive = new UserActive(mFirstName.getText().toString(), mLastName.getText().toString(), getTeam(mRadioGroup));
        UserActive userActive = new UserActive(mFirstName.getText().toString(), mLastName.getText().toString(), name);
        userActive.save();
        mFirstName.getText();
    }

    private void saveDataTeam() {
        TeamActive teamActive = new TeamActive(mTeamName.getText().toString());
        teamActive.save();
    }

    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ActiveAndroidActivity.this); // Создание экземпляра класса к контексте данного активити

        int vId = v.getId();
        switch (vId) {
            case R.id.button_add_user:

                /**Заполняем спиннер с названиями команд*/
                List<TeamActive> team = TeamActive.getAllTeams();
                SpinnerTeamActiveAdapter teamAdapter = new SpinnerTeamActiveAdapter(mActivity, team);
                mSpinner.setAdapter(teamAdapter);

                builder.setTitle("Создать пользователя")
                        .setCancelable(false) // Запрет закрятия диалогового окна по клавише "Назад"
                        .setPositiveButton("Готово",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {

                                        saveData();

                                        dialog.cancel();

                                        reloadUserData();
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setView(dialogView);
//                .setView(R.layout.dialog_add_user);

                break;

            case R.id.button_add_team:

//                AlertDialog.Builder builder = new AlertDialog.Builder(ActiveAndroidActivity.this); // Создание экземпляра класса к контексте данного активити
                builder.setTitle("Создать команду")
                        .setCancelable(false) // Запрет закрятия диалогового окна по клавише "Назад"
                        .setPositiveButton("Готово",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {

                                        saveDataTeam();

                                        dialog.cancel();

                                        //reloadUserData();
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setView(this.dialogViewTeam);
//                .setView(R.layout.dialog_add_user);

/*                AlertDialog alert = builder.create();
                alert.show();*/

                break;

        }

        AlertDialog alert = builder.create();
        alert.show();


    }

    /*@Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ActiveAndroidActivity.this); // Создание экземпляра класса к контексте данного активити
        builder.setTitle("Создать пользователя")
                .setCancelable(false) // Запрет закрятия диалогового окна по клавише "Назад"
                .setPositiveButton("Готово",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                saveData();

                                dialog.cancel();

                                //reloadUserData();
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setView(dialogView);
//                .setView(R.layout.dialog_add_user);

        AlertDialog alert = builder.create();
        alert.show();

    }*/


    /*private String getTeam(RadioGroup radioGroup) {
        String team;
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_blue:
                team = getResources().getString(R.string.blue_team);
                break;
            case R.id.radio_red:
                team = getResources().getString(R.string.red_team);
                break;
            case R.id.radio_green:
                team = getResources().getString(R.string.green_team);
                break;
            default:
                team = "Команда не задана";
                break;
        }
        return team;
    }*/

    /**
     * Метод, вызывающий обновление данных о пользователях
     */
    private void reloadUserData() {
        getLoaderManager().getLoader(0).forceLoad();
    }

    /**
     * В качестве Loader будем использовать кастомный Loader, который берёт из БД свежие данные о пользователях
     */
    @Override
    public Loader<ArrayList<UserActive>> onCreateLoader(int id, Bundle args) {return new UserAsyncLoader(this);}

    /**
     * Когда свежие данные о юзерах из БД пришли, мы пихаем их в наш массив с данными для RecyclerView
     * и вызываем его обновление
     */
    @Override
    public void onLoadFinished(Loader<ArrayList<UserActive>> loader, ArrayList<UserActive> data) {
        mUsers.clear();
        mUsers.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<UserActive>> loader) {

    }


}
