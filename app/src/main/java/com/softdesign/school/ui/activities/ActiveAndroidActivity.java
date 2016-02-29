package com.softdesign.school.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.activeandroid.query.Select;
import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.UserActive;
import com.softdesign.school.ui.adapters.RecycleUserActiveAdapter;

import java.util.List;

public class ActiveAndroidActivity extends AppCompatActivity implements View.OnClickListener {

    // Определение сущностей диалогового окна
    View dialogView;
    EditText mFirstName, mLastName;
    RadioGroup mRadioGroup;
    List<UserActive> mUserActive;
    RecycleUserActiveAdapter userActiveAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_android);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Инициализация элементов AlertDialog для доступа к ним
        LayoutInflater inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_add_user, null, false);
        mFirstName = (EditText) dialogView.findViewById(R.id.first_name);
        mLastName = (EditText) dialogView.findViewById(R.id.last_name);
        mRadioGroup = (RadioGroup) dialogView.findViewById(R.id.radio_group);

        /*listView = (ListView) findViewById(R.id.user_list);

        loadData();

        userActiveAdapter = new RecycleUserActiveAdapter(this);
//        userActiveAdapter.setData(mUserActive);
        listView.setAdapter(userActiveAdapter);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fab.setImageResource(R.drawable.ic_add_24dp1);
    }

    private void saveData() {
        UserActive userActive = new UserActive(mFirstName.getText().toString(), mLastName.getText().toString(), getTeam(mRadioGroup));
        userActive.save();
        mFirstName.getText();
    }

    @Override
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

    }


    private String getTeam(RadioGroup radioGroup) {
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
    }

    public void loadData() {
        mUserActive = getDataListUsers();
    }

    public List<UserActive> getDataListUsers() {
        return new Select()
                .from(UserActive.class)
                .execute();
    }

}
