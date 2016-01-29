package com.softdesign.school.ui.activities;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.softdesign.school.R;
import com.softdesign.school.utils.Lg;

/* Имплементация слушателя события Click по элементу View */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Объявление view-элемента для дальнейшего обращения к нему
     */
    private CheckBox mCheckBox;
    private EditText mEditText;
    private EditText mEditText2;

    private Button mBtnBlue;
    private Button mBtnGreen;
    private Button mBtnRed;

    private Toolbar mToolbar;

    /* Статическая переменная, в которой хранится состояние скрытого/видимого второго input (см. outState) */
    private static final String VISIBLE_KEY = "visible";


    /* Bundle - это переменная окружения, хранящая ключ-занчение (для сохранения состояния view-элементов активити при повороте экрана и других действий)
    Только для хранения маленьких значений!
    Если есть идентификатор, то при повороте экрана значения сохранятся за счет 2-х внутренних методов (см. ниже onSaveInstanceState). */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Задаем свой заголовок в ActionBar |
        ToolBar - это тот же ActionBar, но представленный в виде view-элемента в xml
        (см. res/values/styles.xml - необходимо наследоваться от Theme.AppCompat.Light.NoActionBar (текущая тема Theme.AppCompat.Light.DarkActionBar)) */
        setTitle("Lesson 2");

        Lg.e(this.getLocalClassName(), "on create"); /* Сообщение в логгер о создании главного активити */

        /* Создание экземпляра объекта Checkbox для дальнейшей работы с ним | Приведение типов обязательно | Поиск по идентификатору в классе R */
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        /* Вешаем обработчик клика на созданный экземпляр чекбокса предварительно имплементировав листнер View.OnClickListener и создав метод onClick */
        mCheckBox.setOnClickListener(this);

        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);

        mBtnBlue = (Button) findViewById(R.id.btn_blue);
        mBtnBlue.setOnClickListener(this);

        mBtnGreen = (Button) findViewById(R.id.btn_green);
        mBtnGreen.setOnClickListener(this);

        mBtnRed = (Button) findViewById(R.id.btn_red);
        mBtnRed.setOnClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setupToolbar();
    }


    /* Инициализация тулбара после танцев со стилями */
    private void setupToolbar () {
        setSupportActionBar(mToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        /* Если экшнбар поддерживается, не нулл */
        if (actionBar != null) {
            /* Чтобы появился сэндвич в тулбаре */
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    /* Метод для оперирования с items в опциях меню тулбара */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Menu click", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /* Alt+Ins -> Implements Methods -> onClick(View v) */
    @Override
    public void onClick(View v) {
        /* Получаем id кликнутого элемента активити */
        int id = v.getId();
        switch (id) {
            /* Обработка клика по CheckBox */
            case R.id.checkBox:
                /* this - это текущее активити в качестве контекста для Toast */
                Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
                /* По клику на чекбокс скрываем или показываем второй input */
                if (mCheckBox.isChecked()) {
                    mEditText2.setVisibility(View.INVISIBLE);
                } else {
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
            /* Обработка клика синей кнопки */
            case R.id.btn_blue:
//                mBtnBlue.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbar_blue));
                mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbar_blue));
                break;
            case R.id.btn_green:
//                mBtnGreen.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbar_green));
                mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbar_green));
                break;
            case R.id.btn_red:
//                mBtnRed.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbar_red));
                mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbar_red));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getLocalClassName(), "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getLocalClassName(), "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getLocalClassName(), "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getLocalClassName(), "on stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getLocalClassName(), "on restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getLocalClassName(), "on destroy");
    }

    /* За счет этого метода происходит сохранение значений в Bundle для view-элементов, не имеющих идентификатор */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getLocalClassName(), "on Save Instance State");
        /* Сохранение в Bundle текущего состояния */
        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
    }

    /* За счет этого метода происходит восстановление значений из Bundle для view-элементов, не имеющих идентификатор */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        /* Получение из Bundle бывшего состояния */
        int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        /* Задаем второму input полученное состояние из Bundle */
        mEditText2.setVisibility(visibleState);
    }
}
